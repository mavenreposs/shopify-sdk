package com.shopify.actions;

import com.shopify.model.ShopifyPage;
import com.shopify.model.request.ShopifyCustomerUpdateRequest;
import com.shopify.model.request.ShopifyGetCustomersRequest;
import com.shopify.model.structs.ShopifyCustomer;

/**
 * Created with IntelliJ IDEA.
 * ClassName CustomerAction
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/28 13:58
 */
public interface CustomerAction {

    ShopifyCustomer updateCustomer(final ShopifyCustomerUpdateRequest shopifyCustomerUpdateRequest);

    ShopifyCustomer getCustomer(final String customerId);

    ShopifyPage<ShopifyCustomer> getCustomers(final ShopifyGetCustomersRequest shopifyGetCustomersRequest);

    ShopifyPage<ShopifyCustomer> searchCustomers(final String query);

}
