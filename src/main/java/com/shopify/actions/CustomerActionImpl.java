package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.ShopifyPage;
import com.shopify.model.request.ShopifyCustomerUpdateRequest;
import com.shopify.model.request.ShopifyGetCustomersRequest;
import com.shopify.model.roots.ShopifyCustomerRoot;
import com.shopify.model.roots.ShopifyCustomerUpdateRoot;
import com.shopify.model.roots.ShopifyCustomersRoot;
import com.shopify.model.structs.ShopifyCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * ClassName CustomerActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/28 13:58
 */
public class CustomerActionImpl implements CustomerAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductActionImpl.class);

    public CustomerActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    public ShopifyCustomer updateCustomer(final ShopifyCustomerUpdateRequest shopifyCustomerUpdateRequest) {
        final ShopifyCustomerUpdateRoot shopifyCustomerUpdateRequestRoot = new ShopifyCustomerUpdateRoot();
        shopifyCustomerUpdateRequestRoot.setCustomer(shopifyCustomerUpdateRequest);
        final Response response = shopifySdk.getShopifyWebTarget().put(shopifySdk.getWebTarget().path(ShopifyEndpoint.CUSTOMERS).path(shopifyCustomerUpdateRequest.getId()),
                shopifyCustomerUpdateRequestRoot);
        final ShopifyCustomerRoot shopifyCustomerRootResponse = response.readEntity(ShopifyCustomerRoot.class);
        return shopifyCustomerRootResponse.getCustomer();
    }

    public ShopifyCustomer getCustomer(final String customerId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.CUSTOMERS).path(customerId));
        final ShopifyCustomerRoot shopifyCustomerRootResponse = response.readEntity(ShopifyCustomerRoot.class);
        return shopifyCustomerRootResponse.getCustomer();
    }

    public ShopifyPage<ShopifyCustomer> getCustomers(final ShopifyGetCustomersRequest shopifyGetCustomersRequest) {
        WebTarget target = shopifySdk.getWebTarget().path(ShopifyEndpoint.CUSTOMERS);
        if (shopifyGetCustomersRequest.getPageInfo() != null) {
            target = target.queryParam(ShopifyEndpoint.PAGE_INFO_QUERY_PARAMETER, shopifyGetCustomersRequest.getPageInfo());
        }
        if (shopifyGetCustomersRequest.getLimit() != 0) {
            target = target.queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, shopifyGetCustomersRequest.getLimit());
        } else {
            target = target.queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, ShopifySdk.DEFAULT_REQUEST_LIMIT);
        }
        if (shopifyGetCustomersRequest.getIds() != null) {
            target = target.queryParam(ShopifyEndpoint.IDS_QUERY_PARAMETER, String.join(",", shopifyGetCustomersRequest.getIds()));
        }
        if (shopifyGetCustomersRequest.getSinceId() != null) {
            target = target.queryParam(ShopifyEndpoint.SINCE_ID_QUERY_PARAMETER, shopifyGetCustomersRequest.getSinceId());
        }
        if (shopifyGetCustomersRequest.getCreatedAtMin() != null) {
            target = target.queryParam(ShopifyEndpoint.CREATED_AT_MIN_QUERY_PARAMETER, shopifyGetCustomersRequest.getCreatedAtMin());
        }
        if (shopifyGetCustomersRequest.getCreatedAtMax() != null) {
            target = target.queryParam(ShopifyEndpoint.CREATED_AT_MAX_QUERY_PARAMETER, shopifyGetCustomersRequest.getCreatedAtMax());
        }
        final Response response = shopifySdk.getShopifyWebTarget().get(target);
        return getCustomers(response);
    }

    public ShopifyPage<ShopifyCustomer> searchCustomers(final String query) {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.CUSTOMERS).path(ShopifyEndpoint.SEARCH)
                .queryParam(ShopifyEndpoint.QUERY_QUERY_PARAMETER, query).queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, ShopifySdk.DEFAULT_REQUEST_LIMIT));
        return getCustomers(response);
    }

    private ShopifyPage<ShopifyCustomer> getCustomers(final Response response) {
        final ShopifyCustomersRoot shopifyCustomersRootResponse = response.readEntity(ShopifyCustomersRoot.class);
        return shopifySdk.mapPagedResponse(shopifyCustomersRootResponse.getCustomers(), response);
    }

}
