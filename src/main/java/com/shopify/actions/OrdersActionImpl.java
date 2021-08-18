package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.ShopifyPage;
import com.shopify.model.request.*;
import com.shopify.model.roots.*;
import com.shopify.model.structs.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ClassName OrdersActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/28 10:45
 */
public class OrdersActionImpl implements OrdersAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersActionImpl.class);

    public OrdersActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders() {
        return getOrders(ShopifySdk.DEFAULT_REQUEST_LIMIT);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint()
                .queryParam(ShopifyEndpoint.STATUS_QUERY_PARAMETER, ShopifyEndpoint.ANY_STATUSES)
                .queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize));
        return getOrders(response);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate) {
        return getOrders(mininumCreationDate, ShopifySdk.DEFAULT_REQUEST_LIMIT);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint()
                .queryParam(ShopifyEndpoint.STATUS_QUERY_PARAMETER, ShopifyEndpoint.ANY_STATUSES)
                .queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize)
                .queryParam(ShopifyEndpoint.CREATED_AT_MIN_QUERY_PARAMETER, mininumCreationDate.toString()));
        return getOrders(response);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate) {
        return getOrders(mininumCreationDate, maximumCreationDate, ShopifySdk.DEFAULT_REQUEST_LIMIT);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getUpdatedOrdersCreatedBefore(final DateTime minimumUpdatedAtDate, final DateTime maximumUpdatedAtDate, final DateTime maximumCreatedAtDate, final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint()
                .queryParam(ShopifyEndpoint.STATUS_QUERY_PARAMETER, ShopifyEndpoint.ANY_STATUSES)
                .queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize)
                .queryParam(ShopifyEndpoint.UPDATED_AT_MIN_QUERY_PARAMETER, minimumUpdatedAtDate.toString())
                .queryParam(ShopifyEndpoint.UPDATED_AT_MAX_QUERY_PARAMETER, maximumUpdatedAtDate.toString())
                .queryParam(ShopifyEndpoint.CREATED_AT_MAX_QUERY_PARAMETER, maximumCreatedAtDate.toString()));
        return getOrders(response);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate, final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint()
                .queryParam(ShopifyEndpoint.STATUS_QUERY_PARAMETER, ShopifyEndpoint.ANY_STATUSES)
                .queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize)
                .queryParam(ShopifyEndpoint.CREATED_AT_MIN_QUERY_PARAMETER, mininumCreationDate.toString())
                .queryParam(ShopifyEndpoint.CREATED_AT_MAX_QUERY_PARAMETER, maximumCreationDate.toString()));
        return getOrders(response);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate, final String appId) {
        return getOrders(mininumCreationDate, maximumCreationDate, appId, ShopifySdk.DEFAULT_REQUEST_LIMIT);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate, final String appId, final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint()
                .queryParam(ShopifyEndpoint.STATUS_QUERY_PARAMETER, ShopifyEndpoint.ANY_STATUSES)
                .queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize)
                .queryParam(ShopifyEndpoint.CREATED_AT_MIN_QUERY_PARAMETER, mininumCreationDate.toString())
                .queryParam(ShopifyEndpoint.CREATED_AT_MAX_QUERY_PARAMETER, maximumCreationDate.toString())
                .queryParam(ShopifyEndpoint.ATTRIBUTION_APP_ID_QUERY_PARAMETER, appId));
        return getOrders(response);
    }

    @Override
    public ShopifyPage<ShopifyOrder> getOrders(final String pageInfo, final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint()
                .queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize)
                .queryParam(ShopifyEndpoint.PAGE_INFO_QUERY_PARAMETER, pageInfo));
        return getOrders(response);
    }

    @Override
    public ShopifyOrder createOrder(final ShopifyOrderCreationRequest shopifyOrderCreationRequest) {
        final ShopifyOrderRoot shopifyOrderRoot = new ShopifyOrderRoot();
        final ShopifyOrder shopifyOrder = shopifyOrderCreationRequest.getRequest();
        shopifyOrderRoot.setOrder(shopifyOrder);
        final Response response = shopifySdk.getShopifyWebTarget().post(buildOrdersEndpoint(), shopifyOrderRoot);
        final ShopifyOrderRoot shopifyOrderRootResponse = response.readEntity(ShopifyOrderRoot.class);
        return shopifyOrderRootResponse.getOrder();
    }

    @Override
    public ShopifyOrder updateOrderShippingAddress(final ShopifyOrderShippingAddressUpdateRequest shopifyOrderUpdateRequest) {
        final ShopifyOrderUpdateRoot shopifyOrderRoot = new ShopifyOrderUpdateRoot();
        shopifyOrderRoot.setOrder(shopifyOrderUpdateRequest);
        final Response response = shopifySdk.getShopifyWebTarget().put(buildOrdersEndpoint().path(shopifyOrderUpdateRequest.getId()), shopifyOrderRoot);
        final ShopifyOrderRoot shopifyOrderRootResponse = response.readEntity(ShopifyOrderRoot.class);
        return shopifyOrderRootResponse.getOrder();
    }

    @Override
    public ShopifyOrder closeOrder(final String orderId) {
        final Response response = shopifySdk.getShopifyWebTarget().post(buildOrdersEndpoint().path(orderId).path(ShopifyEndpoint.CLOSE), new ShopifyOrder());
        final ShopifyOrderRoot shopifyOrderRootResponse = response.readEntity(ShopifyOrderRoot.class);
        return shopifyOrderRootResponse.getOrder();
    }

    @Override
    public ShopifyOrder cancelOrder(final String orderId, final String reason) {
        final ShopifyCancelOrderRequest shopifyCancelOrderRequest = new ShopifyCancelOrderRequest();
        shopifyCancelOrderRequest.setReason(reason);
        final Response response = shopifySdk.getShopifyWebTarget().post(buildOrdersEndpoint().path(orderId).path(ShopifyEndpoint.CANCEL), shopifyCancelOrderRequest);
        final ShopifyOrderRoot shopifyOrderRootResponse = response.readEntity(ShopifyOrderRoot.class);
        return shopifyOrderRootResponse.getOrder();
    }

    public ShopifyFulfillment createFulfillment(
            final ShopifyFulfillmentCreationRequest shopifyFulfillmentCreationRequest) {
        final ShopifyFulfillmentRoot shopifyFulfillmentRoot = new ShopifyFulfillmentRoot();
        final ShopifyFulfillment shopifyFulfillment = shopifyFulfillmentCreationRequest.getRequest();

        shopifyFulfillmentRoot.setFulfillment(shopifyFulfillment);
        final Response response = shopifySdk.getShopifyWebTarget().post(buildOrdersEndpoint().path(shopifyFulfillment.getOrderId()).path(ShopifyEndpoint.FULFILLMENTS),
                shopifyFulfillmentRoot);
        final ShopifyFulfillmentRoot shopifyFulfillmentRootResponse = response.readEntity(ShopifyFulfillmentRoot.class);
        return shopifyFulfillmentRootResponse.getFulfillment();
    }

    public ShopifyFulfillment updateFulfillment(final ShopifyFulfillmentUpdateRequest shopifyFulfillmentUpdateRequest) {
        final ShopifyFulfillmentRoot shopifyFulfillmentRoot = new ShopifyFulfillmentRoot();
        final ShopifyFulfillment shopifyFulfillment = shopifyFulfillmentUpdateRequest.getRequest();
        shopifyFulfillmentRoot.setFulfillment(shopifyFulfillment);
        final Response response = shopifySdk.getShopifyWebTarget().put(buildOrdersEndpoint().path(shopifyFulfillment.getOrderId()).path(ShopifyEndpoint.FULFILLMENTS)
                .path(shopifyFulfillment.getId()), shopifyFulfillmentRoot);
        final ShopifyFulfillmentRoot shopifyFulfillmentRootResponse = response.readEntity(ShopifyFulfillmentRoot.class);
        return shopifyFulfillmentRootResponse.getFulfillment();
    }

    public ShopifyFulfillment cancelFulfillment(final String orderId, final String fulfillmentId) {
        final WebTarget buildOrdersEndpoint = buildOrdersEndpoint();
        final Response response = shopifySdk.getShopifyWebTarget().post(
                buildOrdersEndpoint.path(orderId).path(ShopifyEndpoint.FULFILLMENTS).path(fulfillmentId).path(ShopifyEndpoint.CANCEL),
                new ShopifyFulfillment());
        final ShopifyFulfillmentRoot shopifyFulfillmentRootResponse = response.readEntity(ShopifyFulfillmentRoot.class);
        return shopifyFulfillmentRootResponse.getFulfillment();
    }

    public ShopifyOrder getOrder(final String orderId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint().path(orderId));
        final ShopifyOrderRoot shopifyOrderRootResponse = response.readEntity(ShopifyOrderRoot.class);
        return shopifyOrderRootResponse.getOrder();
    }

    public List<ShopifyTransaction> getOrderTransactions(final String orderId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint().path(orderId).path(ShopifyEndpoint.TRANSACTIONS));

        final ShopifyTransactionsRoot shopifyTransactionsRootResponse = response
                .readEntity(ShopifyTransactionsRoot.class);
        return shopifyTransactionsRootResponse.getTransactions();
    }

    public List<ShopifyOrderRisk> getOrderRisks(final String orderId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint().path(orderId).path(ShopifyEndpoint.RISKS));
        final ShopifyOrderRisksRoot shopifyOrderRisksRootResponse = response.readEntity(ShopifyOrderRisksRoot.class);
        return shopifyOrderRisksRootResponse.getRisks();
    }

    public List<Metafield> getOrderMetafields(final String orderId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(buildOrdersEndpoint().path(orderId).path(ShopifyEndpoint.METAFIELDS));
        final MetafieldsRoot metafieldsRootResponse = response.readEntity(MetafieldsRoot.class);
        return metafieldsRootResponse.getMetafields();
    }

    public ShopifyRefund refund(final ShopifyRefundCreationRequest shopifyRefundCreationRequest) {
        final ShopifyRefund calculatedShopifyRefund = calculateRefund(shopifyRefundCreationRequest);
        calculatedShopifyRefund.getTransactions().forEach(transaction -> transaction.setKind(ShopifyEndpoint.REFUND_KIND));

        final WebTarget path = buildOrdersEndpoint().path(shopifyRefundCreationRequest.getRequest().getOrderId())
                .path(ShopifyEndpoint.REFUNDS);
        final ShopifyRefundRoot shopifyRefundRoot = new ShopifyRefundRoot();
        shopifyRefundRoot.setRefund(calculatedShopifyRefund);
        final Response response = shopifySdk.getShopifyWebTarget().post(path, shopifyRefundRoot);
        final ShopifyRefundRoot shopifyRefundRootResponse = response.readEntity(ShopifyRefundRoot.class);
        return shopifyRefundRootResponse.getRefund();
    }

    private ShopifyRefund calculateRefund(final ShopifyRefundCreationRequest shopifyRefundCreationRequest) {
        final ShopifyRefundRoot shopifyRefundRoot = new ShopifyRefundRoot();

        shopifyRefundRoot.setRefund(shopifyRefundCreationRequest.getRequest());

        final WebTarget path = buildOrdersEndpoint().path(shopifyRefundCreationRequest.getRequest().getOrderId())
                .path(ShopifyEndpoint.REFUNDS).path(ShopifyEndpoint.CALCULATE);
        final Response response = shopifySdk.getShopifyWebTarget().post(path, shopifyRefundRoot);
        final ShopifyRefundRoot shopifyRefundRootResponse = response.readEntity(ShopifyRefundRoot.class);
        return shopifyRefundRootResponse.getRefund();
    }

    public int getOrderCount() {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.ORDERS).path(ShopifyEndpoint.COUNT));
        final Count count = response.readEntity(Count.class);
        return count.getCount();
    }

    private WebTarget buildOrdersEndpoint() {
        return shopifySdk.getWebTarget().path(ShopifyEndpoint.ORDERS);
    }

    private ShopifyPage<ShopifyOrder> getOrders(final Response response) {
        final ShopifyOrdersRoot shopifyOrderRootResponse = response.readEntity(ShopifyOrdersRoot.class);
        return shopifySdk.mapPagedResponse(shopifyOrderRootResponse.getOrders(), response);
    }

}
