package com.shopify;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.shopify.actions.*;
import com.shopify.actions.impl.*;
import com.shopify.model.roots.*;
import com.shopify.model.structs.*;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.shopify.exceptions.ShopifyClientException;
import com.shopify.exceptions.ShopifyErrorResponseException;
import com.shopify.mappers.ShopifySdkObjectMapper;
import com.shopify.model.request.ShopifyCustomCollectionCreationRequest;
import com.shopify.model.request.ShopifyCustomerUpdateRequest;
import com.shopify.model.request.ShopifyFulfillmentCreationRequest;
import com.shopify.model.request.ShopifyFulfillmentUpdateRequest;
import com.shopify.model.request.ShopifyGetCustomersRequest;
import com.shopify.model.request.ShopifyGiftCardCreationRequest;
import com.shopify.model.request.ShopifyOrderCreationRequest;
import com.shopify.model.request.ShopifyOrderShippingAddressUpdateRequest;
import com.shopify.model.ShopifyPage;
import com.shopify.model.request.ShopifyProductCreationRequest;
import com.shopify.model.request.ShopifyProductMetafieldCreationRequest;
import com.shopify.model.request.ShopifyProductRequest;
import com.shopify.model.request.ShopifyProductUpdateRequest;
import com.shopify.model.ShopifyProducts;
import com.shopify.model.request.ShopifyRecurringApplicationChargeCreationRequest;
import com.shopify.model.request.ShopifyRefundCreationRequest;
import com.shopify.model.request.ShopifyVariantMetafieldCreationRequest;
import com.shopify.model.request.ShopifyVariantUpdateRequest;

public class ShopifySdk implements ShopifySdkAction {

	static final String API_VERSION_PREFIX = "api";
	private static final long TWO_HUNDRED_MILLISECONDS = 200L;
	private static final String EQUALS = "=";
	private static final String AMPERSAND = "&";
	private static final String REL_PREVIOUS_HEADER_KEY = "previous";
	private static final String REL_NEXT_HEADER_KEY = "next";
	private static final String EMPTY_STRING = "";
	private static final String MINIMUM_REQUEST_RETRY_DELAY_CANNOT_BE_LARGER_THAN_MAXIMUM_REQUEST_RETRY_DELAY_MESSAGE = "Maximum request retry delay must be larger than minimum request retry delay.";
	private static final String INVALID_MINIMUM_REQUEST_RETRY_DELAY_MESSAGE = "Minimum request retry delay cannot be set lower than 200 milliseconds.";

	public static final Logger LOGGER = LoggerFactory.getLogger(ShopifySdk.class);

	private static final String HTTPS = "https://";
	private static final String API_TARGET = ".myshopify.com/admin";
	static final String ACCESS_TOKEN_HEADER = "X-Shopify-Access-Token";
	static final String DEPRECATED_REASON_HEADER = "X-Shopify-API-Deprecated-Reason";
	static final String RETRY_AFTER_HEADER = "Retry-After";

	private static final String CLIENT_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String AUTHORIZATION_CODE = "code";

	public static final int DEFAULT_REQUEST_LIMIT = 50;

	private static final String SHOP_RETRIEVED_MESSAGE = "Starting to make calls for Shopify store with ID of {} and name of {}";
	private static final String SHOP_EXCEPTION_MESSAGE = "Shopify call is a error of {}";
	private static final String COULD_NOT_BE_SAVED_SHOPIFY_ERROR_MESSAGE = "could not successfully be saved";
	private static final String RETRY_FAILED_MESSAGE = "Request retry has failed.";
	private static final String DEPRECATED_SHOPIFY_CALL_ERROR_MESSAGE = "Shopify call is deprecated. Please take note of the X-Shopify-API-Deprecated-Reason and correct the call.\nRequest Location of {}\nResponse Status Code of {}\nResponse Headers of:\n{}";
	static final String GENERAL_ACCESS_TOKEN_EXCEPTION_MESSAGE = "There was a problem generating access token using shop subdomain of %s and authorization code of %s.";

	static final Long DEFAULT_MAXIMUM_REQUEST_RETRY_TIMEOUT_IN_MILLISECONDS = 180000L;
	static final Long DEFAULT_MAXIMUM_REQUEST_RETRY_RANDOM_DELAY_IN_MILLISECONDS = 5000L;
	static final Long DEFAULT_MINIMUM_REQUEST_RETRY_RANDOM_DELAY_IN_MILLISECONDS = 1000L;
	static final long DEFAULT_READ_TIMEOUT_IN_MILLISECONDS = 15000L;
	static final long DEFAULT_CONNECTION_TIMEOUT_IN_MILLISECONDS = 60000L;

	private String shopSubdomain;
	private String apiUrl;
	private String apiVersion;
	private String clientId;
	private String clientSecret;
	private String authorizationToken;

	private WebTarget webTarget;
	private String accessToken;

	private long minimumRequestRetryRandomDelayMilliseconds;
	private long maximumRequestRetryRandomDelayMilliseconds;
	private long maximumRequestRetryTimeoutMilliseconds;

	private static final Client CLIENT = buildClient();

	private ShopifyWebTarget shopifyWebTarget;

	private final ShopAction shopAction = new ShopActionImpl(this);
	private final OrdersAction ordersAction = new OrdersActionImpl(this);
	private final ProductAction productAction = new ProductActionImpl(this);
	private final ProductVariantAction productVariantAction = new ProductVariantActionImpl(this);
	private final ProductImageAction productImageAction = new ProductImageActionImpl(this);
	private final CustomCollectionAction customCollectionAction = new CustomCollectionActionImpl(this);
	private final CustomerAction customerAction = new CustomerActionImpl(this);
	private final RecurringApplicationChargeAction recurringApplicationChargeAction = new RecurringApplicationChargeActionImpl(this);
	private final LocationAction locationAction = new LocationActionImpl(this);
	private final InventoryLevelAction inventoryLevelAction = new InventoryLevelActionImpl(this);
	private final InventoryItemAction inventoryItemAction = new InventoryItemActionImpl(this);
	private final GiftCardAction giftCardAction = new GiftCardActionImpl(this);

	public static ShopifySdkBuilder.SubdomainStep newBuilder() {
		return ShopifySdkBuilder.newBuilder();
	}

	protected ShopifySdk(final ShopifySdkBuilder.Steps steps) {
		if (steps != null) {
			this.shopSubdomain = steps.getSubdomain();
			this.accessToken = steps.getAccessToken();
			this.clientId = steps.getClientId();
			this.clientSecret = steps.getClientSecret();
			this.authorizationToken = steps.getAuthorizationToken();
			this.apiUrl = steps.getApiUrl();
			this.apiVersion = steps.getApiVersion();
			this.minimumRequestRetryRandomDelayMilliseconds = steps.getMinimumRequestRetryRandomDelayMilliseconds();
			this.maximumRequestRetryRandomDelayMilliseconds = steps.getMaximumRequestRetryRandomDelayMilliseconds();
			this.maximumRequestRetryTimeoutMilliseconds = steps.getMaximumRequestRetryTimeoutMilliseconds();

			CLIENT.property(ClientProperties.CONNECT_TIMEOUT, Math.toIntExact(steps.getConnectionTimeoutMilliseconds()));
			CLIENT.property(ClientProperties.READ_TIMEOUT, Math.toIntExact(steps.getReadTimeoutMilliseconds()));
			validateConstructionOfShopifySdk();

			shopifyWebTarget = new ShopifyWebTarget(steps);
		}

	}

	private void validateConstructionOfShopifySdk() {
		if (this.minimumRequestRetryRandomDelayMilliseconds < TWO_HUNDRED_MILLISECONDS) {
			throw new IllegalArgumentException(INVALID_MINIMUM_REQUEST_RETRY_DELAY_MESSAGE);
		}
		if (minimumRequestRetryRandomDelayMilliseconds > maximumRequestRetryRandomDelayMilliseconds) {
			throw new IllegalArgumentException(
					MINIMUM_REQUEST_RETRY_DELAY_CANNOT_BE_LARGER_THAN_MAXIMUM_REQUEST_RETRY_DELAY_MESSAGE);
		}
	}

	public boolean revokeOAuthToken() {
		try {
			final Response response = shopifyWebTarget.delete(getUnversionedWebTarget().path(ShopifyEndpoint.OAUTH).path(ShopifyEndpoint.REVOKE));
			return Status.OK.getStatusCode() == response.getStatus();
		} catch (final ShopifyErrorResponseException e) {
			return false;
		}
	}

	public ShopifyShopRoot getShop() {
		return shopAction.getShop();
	}

	public ShopifyProduct getProduct(final String productId) {
		return productAction.getProduct(productId);
	}

	public ShopifyPage<ShopifyProduct> getProducts(final int pageSize) {
		return productAction.getProducts(pageSize);
	}

	public ShopifyPage<ShopifyProduct> getProducts(final String pageInfo, final int pageSize) {
		return productAction.getProducts(pageInfo, pageSize);
	}

	public ShopifyProducts getProducts() {
		return productAction.getProducts();
	}

	public int getProductCount() {
		return productAction.getProductCount();
	}

	public ShopifyProduct createProduct(final ShopifyProductCreationRequest shopifyProductCreationRequest) {
		return productAction.createProduct(shopifyProductCreationRequest);
	}

	public ShopifyProduct updateProduct(final ShopifyProductUpdateRequest shopifyProductUpdateRequest) {
		return productAction.updateProduct(shopifyProductUpdateRequest);
	}

	public ShopifyProduct updateProduct(final ShopifyProduct shopifyProduct) {
		return productAction.updateProduct(shopifyProduct);
	}

	public boolean deleteProduct(final String productId) {
		return productAction.deleteProduct(productId);
	}

	public Image createProductImage(final String productId, final Image imageRequest) {
		return productImageAction.createProductImage(productId, imageRequest);
	}

	public Image createProductImage(final String productId, final String imageSource) {
		return productImageAction.createProductImage(productId, imageSource);
	}

	public Image createProductImage(final String productId, final String imageSource, final int position) {
		return productImageAction.createProductImage(productId, imageSource, position);
	}

	public Image createVariantImage(ShopifyImageRoot shopifyImageRootRequest) {
		return productImageAction.createVariantImage(shopifyImageRootRequest);
	}

	public boolean deleteProductImage(final String productId, final String imageId) {
		return productImageAction.deleteProductImage(productId, imageId);
	}

	public ShopifyVariant getVariant(final String variantId) {
		return productVariantAction.getVariant(variantId);
	}

	public ShopifyVariant updateVariant(final ShopifyVariant shopifyVariant) {
		return productVariantAction.updateVariant(shopifyVariant);
	}

	public ShopifyVariant updateVariant(final ShopifyVariantUpdateRequest shopifyVariantUpdateRequest) {
		return productVariantAction.updateVariant(shopifyVariantUpdateRequest);
	}

	public ShopifyOrder getOrder(final String orderId) {
		return ordersAction.getOrder(orderId);
	}

	public List<ShopifyTransaction> getOrderTransactions(final String orderId) {
		return ordersAction.getOrderTransactions(orderId);
	}

	public ShopifyPage<ShopifyOrder> getOrders() {
		return ordersAction.getOrders();
	}

	public ShopifyPage<ShopifyOrder> getOrders(final int pageSize) {
		return ordersAction.getOrders(pageSize);
	}

	public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate) {
		return ordersAction.getOrders(mininumCreationDate);
	}

	public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final int pageSize) {
		return ordersAction.getOrders(mininumCreationDate, pageSize);
	}

	public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate) {
		return ordersAction.getOrders(mininumCreationDate, maximumCreationDate);
	}

	public ShopifyPage<ShopifyOrder> getUpdatedOrdersCreatedBefore(final DateTime minimumUpdatedAtDate,
																   final DateTime maximumUpdatedAtDate, final DateTime maximumCreatedAtDate, final int pageSize) {
		return ordersAction.getUpdatedOrdersCreatedBefore(minimumUpdatedAtDate, maximumUpdatedAtDate, maximumCreatedAtDate, pageSize);
	}

	public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate,
											   final int pageSize) {
		return ordersAction.getOrders(mininumCreationDate, maximumCreationDate, pageSize);
	}

	public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate,
											   final String appId) {
		return ordersAction.getOrders(mininumCreationDate, maximumCreationDate, appId);
	}

	public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate,
											   final String appId, final int pageSize) {
		return ordersAction.getOrders(mininumCreationDate, maximumCreationDate, appId, pageSize);
	}

	public ShopifyPage<ShopifyOrder> getOrders(final String pageInfo, final int pageSize) {
		return ordersAction.getOrders(pageInfo, pageSize);
	}

	public ShopifyFulfillment createFulfillment(
			final ShopifyFulfillmentCreationRequest shopifyFulfillmentCreationRequest) {
		return ordersAction.createFulfillment(shopifyFulfillmentCreationRequest);
	}

	public ShopifyFulfillment updateFulfillment(final ShopifyFulfillmentUpdateRequest shopifyFulfillmentUpdateRequest) {
		return ordersAction.updateFulfillment(shopifyFulfillmentUpdateRequest);
	}

	public ShopifyOrder createOrder(final ShopifyOrderCreationRequest shopifyOrderCreationRequest) {
		return ordersAction.createOrder(shopifyOrderCreationRequest);
	}

	public ShopifyOrder updateOrderShippingAddress(
			final ShopifyOrderShippingAddressUpdateRequest shopifyOrderUpdateRequest) {
		return ordersAction.updateOrderShippingAddress(shopifyOrderUpdateRequest);
	}

	public ShopifyFulfillment cancelFulfillment(final String orderId, final String fulfillmentId) {
		return ordersAction.cancelFulfillment(orderId, fulfillmentId);
	}

	public ShopifyOrder closeOrder(final String orderId) {
		return ordersAction.closeOrder(orderId);
	}

	public ShopifyOrder cancelOrder(final String orderId, final String reason) {
		return ordersAction.cancelOrder(orderId, reason);
	}

	public List<ShopifyOrderRisk> getOrderRisks(final String orderId) {
		return ordersAction.getOrderRisks(orderId);
	}

	public List<Metafield> getOrderMetafields(final String orderId) {
		return ordersAction.getOrderMetafields(orderId);
	}

	public ShopifyRefund refund(final ShopifyRefundCreationRequest shopifyRefundCreationRequest) {
		return ordersAction.refund(shopifyRefundCreationRequest);
	}

	public int getOrderCount() {
		return ordersAction.getOrderCount();
	}

	public ShopifyPage<ShopifyCustomCollection> getCustomCollections(final int pageSize) {
		return customCollectionAction.getCustomCollections(pageSize);
	}

	public ShopifyPage<ShopifyCustomCollection> getCustomCollections(final String pageInfo, final int pageSize) {
		return customCollectionAction.getCustomCollections(pageInfo, pageSize);
	}

	public List<ShopifyCustomCollection> getCustomCollections() {
		return customCollectionAction.getCustomCollections();
	}

	public ShopifyCustomCollection createCustomCollection(
			final ShopifyCustomCollectionCreationRequest shopifyCustomCollectionCreationRequest) {
		return customCollectionAction.createCustomCollection(shopifyCustomCollectionCreationRequest);
	}

	public ShopifyCustomer updateCustomer(final ShopifyCustomerUpdateRequest shopifyCustomerUpdateRequest) {
		return customerAction.updateCustomer(shopifyCustomerUpdateRequest);
	}

	public ShopifyCustomer getCustomer(final String customerId) {
		return customerAction.getCustomer(customerId);
	}

	public ShopifyPage<ShopifyCustomer> getCustomers(final ShopifyGetCustomersRequest shopifyGetCustomersRequest) {
		return customerAction.getCustomers(shopifyGetCustomersRequest);
	}

	public ShopifyPage<ShopifyCustomer> searchCustomers(final String query) {
		return customerAction.searchCustomers(query);
	}

	public Metafield createVariantMetafield(
			final ShopifyVariantMetafieldCreationRequest shopifyVariantMetafieldCreationRequest) {
		return productVariantAction.createVariantMetafield(shopifyVariantMetafieldCreationRequest);
	}

	public List<Metafield> getVariantMetafields(final String variantId) {
		return productVariantAction.getVariantMetafields(variantId);
	}

	public Metafield createProductMetafield(
			final ShopifyProductMetafieldCreationRequest shopifyProductMetafieldCreationRequest) {
		return productAction.createProductMetafield(shopifyProductMetafieldCreationRequest);
	}

	public List<Metafield> getProductMetafields(final String productId) {
		return productAction.getProductMetafields(productId);
	}

	public Image updateProductImage(final String productId, final Image image) {
		return productImageAction.updateProductImage(productId, image);
	}

	public ShopifyProduct updateProductImages(final ShopifyProductRequest shopifyProductRequest,
											  final ShopifyProduct shopifyProduct) {
		return productImageAction.updateProductImages(shopifyProductRequest, shopifyProduct);
	}

	public ShopifyRecurringApplicationCharge createRecurringApplicationCharge(
			final ShopifyRecurringApplicationChargeCreationRequest shopifyRecurringApplicationChargeCreationRequest) {
		return recurringApplicationChargeAction.createRecurringApplicationCharge(shopifyRecurringApplicationChargeCreationRequest);
	}

	public ShopifyRecurringApplicationCharge getRecurringApplicationCharge(final String chargeId) {
		return recurringApplicationChargeAction.getRecurringApplicationCharge(chargeId);
	}

	public ShopifyRecurringApplicationCharge activateRecurringApplicationCharge(final String chargeId) {
		return recurringApplicationChargeAction.activateRecurringApplicationCharge(chargeId);
	}

	public List<ShopifyLocation> getLocations() {
		return locationAction.getLocations();
	}

	public ShopifyInventoryLevel updateInventoryLevel(final String inventoryItemId, final String locationId,
			final long quantity) {
		return inventoryLevelAction.updateInventoryLevel(inventoryItemId, locationId, quantity);
	}

	public ShopifyInventoryItem getInventoryItem(final String inventoryItemId) {
		return inventoryItemAction.getInventoryItem(inventoryItemId);
	}

	public ShopifyInventoryItem updateInventoryItem(final ShopifyInventoryItem shopifyInventoryItem) {
		return inventoryItemAction.updateInventoryItem(shopifyInventoryItem);
	}

	public ShopifyInventoryItem updateInventoryItem(final String inventoryItemId, boolean requiresShipping) {
		return inventoryItemAction.updateInventoryItem(inventoryItemId, requiresShipping);
	}

	public ShopifyGiftCard createGiftCard(final ShopifyGiftCardCreationRequest shopifyGiftCardCreationRequest) {
		return giftCardAction.createGiftCard(shopifyGiftCardCreationRequest);
	}

	public List<ShopifyDeprecatedApiCall> getDeprecatedApiCalls() {
		final Response response = shopifyWebTarget.get(getWebTarget().path(ShopifyEndpoint.DEPRECATED_API_CALLS));
		final ShopifyDeprecatedApiCallsRoot shopifyDeprecatedApiCallsRoot = response.readEntity(ShopifyDeprecatedApiCallsRoot.class);
		return shopifyDeprecatedApiCallsRoot.getDeprecatedApiCalls();
	}

	public String getAccessToken() {
		return accessToken;
	}

	private String generateToken() {
		try {

			final Entity<String> entity = Entity.entity(EMPTY_STRING, MediaType.APPLICATION_JSON);
			final Response response = this.getUnversionedWebTarget().path(ShopifyEndpoint.OAUTH).path(ShopifyEndpoint.ACCESS_TOKEN)
					.queryParam(CLIENT_ID, this.clientId).queryParam(CLIENT_SECRET, this.clientSecret)
					.queryParam(AUTHORIZATION_CODE, this.authorizationToken).request(MediaType.APPLICATION_JSON)
					.post(entity);

			final int status = response.getStatus();

			if (Status.OK.getStatusCode() == status) {
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

	public WebTarget getWebTarget() {
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

			try {
				final Shop shop = this.getShop().getShop();
				LOGGER.info(SHOP_RETRIEVED_MESSAGE, shop.getId(), shop.getName());
			} catch (Exception exception) {
				LOGGER.info(SHOP_EXCEPTION_MESSAGE, exception.getMessage());
			}
		}
		return webTarget;
	}

	private static Client buildClient() {
		final ObjectMapper mapper = ShopifySdkObjectMapper.buildMapper();
		final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		provider.setMapper(mapper);

		return ClientBuilder.newClient().register(JacksonFeature.class).register(provider);
	}

	public  <T> ShopifyPage<T> mapPagedResponse(final List<T> items, final Response response) {

		final ShopifyPage<T> shopifyPage = new ShopifyPage<>();
		shopifyPage.addAll(items);

		final Set<Link> links = response.getLinks();
		final String nextLink = links.stream().filter(link -> link.getRel().equals(REL_NEXT_HEADER_KEY))
				.map(link -> getQueryParam(link.getUri(), ShopifyEndpoint.PAGE_INFO_QUERY_PARAMETER)).findFirst().orElse(null);
		final String previousLink = links.stream().filter(link -> link.getRel().equals(REL_PREVIOUS_HEADER_KEY))
				.map(link -> getQueryParam(link.getUri(), ShopifyEndpoint.PAGE_INFO_QUERY_PARAMETER)).findFirst().orElse(null);
		shopifyPage.setNextPageInfo(nextLink);
		shopifyPage.setPreviousPageInfo(previousLink);

		return shopifyPage;
	}

	private String getQueryParam(final URI uri, final String key) {
		final String[] params = uri.getQuery().split(AMPERSAND);
		for (final String param : params) {
			final String name = param.split(EQUALS)[0];
			final String value = param.split(EQUALS)[1];
			if (name.equals(key)) {
				return value;
			}
		}
		return null;
	}

	public ShopifyWebTarget getShopifyWebTarget() {
		return shopifyWebTarget;
	}

	@Override
	public String toString() {
		return "ShopifySdk{" +
				"shopSubdomain='" + shopSubdomain + '\'' +
				", apiUrl='" + apiUrl + '\'' +
				", apiVersion='" + apiVersion + '\'' +
				", clientId='" + clientId + '\'' +
				", clientSecret='" + clientSecret + '\'' +
				", authorizationToken='" + authorizationToken + '\'' +
				", webTarget=" + webTarget +
				", accessToken='" + accessToken + '\'' +
				", minimumRequestRetryRandomDelayMilliseconds=" + minimumRequestRetryRandomDelayMilliseconds +
				", maximumRequestRetryRandomDelayMilliseconds=" + maximumRequestRetryRandomDelayMilliseconds +
				", maximumRequestRetryTimeoutMilliseconds=" + maximumRequestRetryTimeoutMilliseconds +
				'}';
	}

}
