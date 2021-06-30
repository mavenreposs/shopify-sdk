package com.shopify;

import com.github.rholder.retry.*;
import com.shopify.exceptions.ShopifyClientException;
import com.shopify.exceptions.ShopifyErrorResponseException;
import com.shopify.mappers.ResponseEntityToStringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final Steps steps;

    private String accessToken;

    static final String ACCESS_TOKEN_HEADER = "X-Shopify-Access-Token";
    static final String DEPRECATED_REASON_HEADER = "X-Shopify-API-Deprecated-Reason";
    static final String RETRY_AFTER_HEADER = "Retry-After";

    private static final String COULD_NOT_BE_SAVED_SHOPIFY_ERROR_MESSAGE = "could not successfully be saved";
    private static final String RETRY_FAILED_MESSAGE = "Request retry has failed.";
    private static final String DEPRECATED_SHOPIFY_CALL_ERROR_MESSAGE = "Shopify call is deprecated. Please take note of the X-Shopify-API-Deprecated-Reason and correct the call.\nRequest Location of {}\nResponse Status Code of {}\nResponse Headers of:\n{}";

    private static final int TOO_MANY_REQUESTS_STATUS_CODE = 429;
    private static final int UNPROCESSABLE_ENTITY_STATUS_CODE = 422;
    private static final int LOCKED_STATUS_CODE = 423;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopifyWebTarget.class);

    public ShopifyWebTarget(final Steps steps) {
        this.steps = steps;
        this.accessToken = steps.getAccessToken();
    }

    public Steps getSteps() {
        return steps;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Response get(final WebTarget webTarget) {
        final Callable<Response> responseCallable = () -> webTarget.request(MediaType.APPLICATION_JSON)
                .header(ACCESS_TOKEN_HEADER, accessToken).get();
        final Response response = invokeResponseCallable(responseCallable);
        return handleResponse(response, Response.Status.OK);
    }

    public Response delete(final WebTarget webTarget) {
        final Callable<Response> responseCallable = () -> webTarget.request(MediaType.APPLICATION_JSON)
                .header(ACCESS_TOKEN_HEADER, accessToken).delete();
        final Response response = invokeResponseCallable(responseCallable);
        return handleResponse(response, Response.Status.OK);
    }

    public  <T> Response post(final WebTarget webTarget, final T object) {
        final Callable<Response> responseCallable = () -> {
            final Entity<T> entity = Entity.entity(object, MediaType.APPLICATION_JSON);
            return webTarget.request(MediaType.APPLICATION_JSON)
                    .header(ACCESS_TOKEN_HEADER, accessToken).post(entity);
        };
        final Response response = invokeResponseCallable(responseCallable);
        return handleResponse(response, Response.Status.CREATED, Response.Status.OK);
    }

    public  <T> Response put(final WebTarget webTarget, final T object) {
        final Callable<Response> responseCallable = () -> {
            final Entity<T> entity = Entity.entity(object, MediaType.APPLICATION_JSON);
            return webTarget.request(MediaType.APPLICATION_JSON)
                    .header(ACCESS_TOKEN_HEADER, accessToken).put(entity);
        };
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

    private Response invokeResponseCallable(final Callable<Response> responseCallable) {
        final Retryer<Response> retryer = buildResponseRetyer();
        try {
            return retryer.call(responseCallable);
        } catch (ExecutionException | RetryException e) {
            throw new ShopifyClientException(RETRY_FAILED_MESSAGE, e);
        }
    }

    private List<Integer> getExpectedStatusCodes(final Response.Status... expectedStatus) {
        return Arrays.asList(expectedStatus).stream().map(Response.Status::getStatusCode).collect(Collectors.toList());
    }

    public Retryer<Response> buildResponseRetyer() {
        return RetryerBuilder.<Response>newBuilder().retryIfResult(ShopifyWebTarget::shouldRetryResponse).retryIfException()
                .withWaitStrategy(WaitStrategies.randomWait(steps.getMinimumRequestRetryRandomDelayMilliseconds(),
                        TimeUnit.MILLISECONDS, steps.getMaximumRequestRetryRandomDelayMilliseconds(), TimeUnit.MILLISECONDS))
                .withStopStrategy(
                        StopStrategies.stopAfterDelay(steps.getMaximumRequestRetryTimeoutMilliseconds(), TimeUnit.MILLISECONDS))
                .withRetryListener(new ShopifySdkRetryListener()).build();
    }

    public static boolean shouldRetryResponse(final Response response) {
        return isServerError(response) || hasExceededRateLimit(response) || hasNotBeenSaved(response);
    }

    public static boolean hasExceededRateLimit(final Response response) {
        return TOO_MANY_REQUESTS_STATUS_CODE == response.getStatus()
                && response.getHeaders().containsKey(RETRY_AFTER_HEADER);
    }

    public static boolean isServerError(final Response response) {
        return (Response.Status.Family.SERVER_ERROR == Response.Status.Family.familyOf(response.getStatus()))
                || (LOCKED_STATUS_CODE == response.getStatus());
    }

    public static boolean hasNotBeenSaved(final Response response) {
        if ((UNPROCESSABLE_ENTITY_STATUS_CODE == response.getStatus()) && response.hasEntity()) {
            final String shopifyErrorResponse = ResponseEntityToStringMapper.map(response);
            LOGGER.debug(shopifyErrorResponse);
            return shopifyErrorResponse.contains(COULD_NOT_BE_SAVED_SHOPIFY_ERROR_MESSAGE);
        }
        return false;
    }

}
