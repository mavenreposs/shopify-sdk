package com.shopify.actions;

import com.shopify.model.ShopifyPage;
import com.shopify.model.request.*;
import com.shopify.model.structs.*;
import org.joda.time.DateTime;

import java.util.List;

public interface OrdersAction {

    ShopifyPage<ShopifyOrder> getOrders();

    ShopifyPage<ShopifyOrder> getOrders(final int pageSize);

    ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate);

    ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final int pageSize);

    ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate);

    ShopifyPage<ShopifyOrder> getUpdatedOrdersCreatedBefore(final DateTime minimumUpdatedAtDate,
                                                            final DateTime maximumUpdatedAtDate, final DateTime maximumCreatedAtDate, final int pageSize);

    ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate,
                                        final int pageSize);

    ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate,
                                        final String appId);

    ShopifyPage<ShopifyOrder> getOrders(final DateTime mininumCreationDate, final DateTime maximumCreationDate,
                                        final String appId, final int pageSize);

    ShopifyPage<ShopifyOrder> getOrders(final String pageInfo, final int pageSize);

    ShopifyOrder createOrder(final ShopifyOrderCreationRequest shopifyOrderCreationRequest);

    ShopifyOrder updateOrderShippingAddress(
            final ShopifyOrderShippingAddressUpdateRequest shopifyOrderUpdateRequest);

    ShopifyOrder closeOrder(final String orderId);

    ShopifyOrder cancelOrder(final String orderId, final String reason);

    ShopifyFulfillment createFulfillment(
            final ShopifyFulfillmentCreationRequest shopifyFulfillmentCreationRequest);

    ShopifyFulfillment updateFulfillment(final ShopifyFulfillmentUpdateRequest shopifyFulfillmentUpdateRequest);

    ShopifyFulfillment cancelFulfillment(final String orderId, final String fulfillmentId);

    ShopifyOrder getOrder(final String orderId);

    List<ShopifyTransaction> getOrderTransactions(final String orderId);

    List<ShopifyOrderRisk> getOrderRisks(final String orderId);

    List<Metafield> getOrderMetafields(final String orderId);

    ShopifyRefund refund(final ShopifyRefundCreationRequest shopifyRefundCreationRequest);

    ShopifyRefund calculateRefund(final ShopifyRefundCreationRequest shopifyRefundCreationRequest);

    int getOrderCount();

}
