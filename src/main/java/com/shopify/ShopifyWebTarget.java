package com.shopify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.github.rholder.retry.*;
import com.shopify.exceptions.ShopifyClientException;
import com.shopify.exceptions.ShopifyErrorResponseException;
import com.shopify.mappers.ShopifySdkObjectMapper;
import com.shopify.model.roots.ShopifyAccessTokenRoot;
import com.shopify.model.roots.ShopifyShopRoot;
import com.shopify.model.structs.Shop;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ShopifyWebTarget {

    private static final String HTTPS = "https://";
    private static final String API_TARGET = ".myshopify.com/admin";

    static final String ACCESS_TOKEN_HEADER = "X-Shopify-Access-Token";
    static final String DEPRECATED_REASON_HEADER = "X-Shopify-API-Deprecated-Reason";

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String AUTHORIZATION_CODE = "code";

    private static final String EMPTY_STRING = "";

    static final String API_VERSION_PREFIX = "api";

    private String shopSubdomain;
    private String apiUrl;
    private String apiVersion;
    private String clientId;
    private String clientSecret;
    private String authorizationToken;
    private String accessToken;

    private WebTarget webTarget;

    private static final Client CLIENT = buildClient();

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopifySdk.class);

    private static final String SHOP_RETRIEVED_MESSAGE = "Starting to make calls for Shopify store with ID of {} and name of {}";
    private static final String COULD_NOT_BE_SAVED_SHOPIFY_ERROR_MESSAGE = "could not successfully be saved";
    private static final String RETRY_FAILED_MESSAGE = "Request retry has failed.";
    private static final String DEPRECATED_SHOPIFY_CALL_ERROR_MESSAGE = "Shopify call is deprecated. Please take note of the X-Shopify-API-Deprecated-Reason and correct the call.\nRequest Location of {}\nResponse Status Code of {}\nResponse Headers of:\n{}";
    static final String GENERAL_ACCESS_TOKEN_EXCEPTION_MESSAGE = "There was a problem generating access token using shop subdomain of %s and authorization code of %s.";


    private WebTarget getWebTarget() {
        if (this.webTarget == null) {

            if (StringUtils.isNotBlank(this.shopSubdomain)) {
                this.webTarget = CLIENT.target(
                        new StringBuilder().append(HTTPS).append(this.shopSubdomain).append(API_TARGET).toString());

            } else {
                this.webTarget = CLIENT.target(this.apiUrl);
            }
            if (StringUtils.isNotBlank(this.apiVersion)) {
                this.webTarget = this.webTarget.path(API_VERSION_PREFIX).path(this.apiVersion);
            }
            if (this.accessToken == null) {
                this.accessToken = generateToken();
            }

            final Shop shop = this.getShop().getShop();
            LOGGER.info(SHOP_RETRIEVED_MESSAGE, shop.getId(), shop.getName());
        }
        return webTarget;
    }

    private String generateToken() {
        try {

            final Entity<String> entity = Entity.entity(EMPTY_STRING, MediaType.APPLICATION_JSON);
            final Response response = this.getUnversionedWebTarget().path(ShopifyEndpoint.OAUTH).path(ShopifyEndpoint.ACCESS_TOKEN)
                    .queryParam(CLIENT_ID, this.clientId).queryParam(CLIENT_SECRET, this.clientSecret)
                    .queryParam(AUTHORIZATION_CODE, this.authorizationToken).request(MediaType.APPLICATION_JSON)
                    .post(entity);

            final int status = response.getStatus();

            if (Response.Status.OK.getStatusCode() == status) {
                final ShopifyAccessTokenRoot shopifyResponse = response.readEntity(ShopifyAccessTokenRoot.class);
                return shopifyResponse.getAccessToken();
            }
            throw new ShopifyErrorResponseException(response);
        } catch (final Exception e) {
            throw new ShopifyClientException(
                    String.format(GENERAL_ACCESS_TOKEN_EXCEPTION_MESSAGE, shopSubdomain, this.authorizationToken), e);
        }
    }

    private WebTarget getUnversionedWebTarget() {
        if (StringUtils.isNotBlank(this.shopSubdomain)) {
            return CLIENT
                    .target(new StringBuilder().append(HTTPS).append(this.shopSubdomain).append(API_TARGET).toString());
        }
        return CLIENT.target(this.apiUrl);
    }

    private Response get(final WebTarget webTarget) {
        final Callable<Response> responseCallable = () -> webTarget.request(MediaType.APPLICATION_JSON)
                .header(ACCESS_TOKEN_HEADER, accessToken).get();
        final Response response = invokeResponseCallable(responseCallable);
        return handleResponse(response, Response.Status.OK);
    }

    private Response handleResponse(final Response response, final Response.Status... expectedStatus) {

        if ((response.getHeaders() != null) && response.getHeaders().containsKey(DEPRECATED_REASON_HEADER)) {
            LOGGER.error(DEPRECATED_SHOPIFY_CALL_ERROR_MESSAGE, response.getLocation(), response.getStatus(),
                    response.getStringHeaders());
        }

        final List<Integer> expectedStatusCodes = getExpectedStatusCodes(expectedStatus);
        if (expectedStatusCodes.contains(response.getStatus())) {
            return response;
        }

        throw new ShopifyErrorResponseException(response);
    }

    private List<Integer> getExpectedStatusCodes(final Response.Status... expectedStatus) {
        return Arrays.asList(expectedStatus).stream().map(Response.Status::getStatusCode).collect(Collectors.toList());
    }

    private Response invokeResponseCallable(final Callable<Response> responseCallable) {
        final Retryer<Response> retryer = new ShopifyResponseRetyer().buildResponseRetyer();
        try {
            return retryer.call(responseCallable);
        } catch (ExecutionException | RetryException e) {
            throw new ShopifyClientException(RETRY_FAILED_MESSAGE, e);
        }
    }


    private static Client buildClient() {
        final ObjectMapper mapper = ShopifySdkObjectMapper.buildMapper();
        final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);

        return ClientBuilder.newClient().register(JacksonFeature.class).register(provider);
    }

    public ShopifyShopRoot getShop() {
        final Response response = get(getWebTarget().path(ShopifyEndpoint.SHOP));
        return response.readEntity(ShopifyShopRoot.class);
    }

}
