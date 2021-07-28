package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.request.ShopifyRecurringApplicationChargeCreationRequest;
import com.shopify.model.roots.ShopifyRecurringApplicationChargeRoot;
import com.shopify.model.structs.ShopifyRecurringApplicationCharge;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * ClassName RecurringApplicationChargeActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/28 14:17
 */
public class RecurringApplicationChargeActionImpl implements RecurringApplicationChargeAction {

    private final ShopifySdk shopifySdk;

    public RecurringApplicationChargeActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public ShopifyRecurringApplicationCharge createRecurringApplicationCharge(
            final ShopifyRecurringApplicationChargeCreationRequest shopifyRecurringApplicationChargeCreationRequest) {
        final ShopifyRecurringApplicationChargeRoot shopifyRecurringApplicationChargeRootRequest = new ShopifyRecurringApplicationChargeRoot();
        final ShopifyRecurringApplicationCharge shopifyRecurringApplicationChargeRequest = shopifyRecurringApplicationChargeCreationRequest
                .getRequest();
        shopifyRecurringApplicationChargeRootRequest
                .setRecurringApplicationCharge(shopifyRecurringApplicationChargeRequest);

        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.RECURRING_APPLICATION_CHARGES),
                shopifyRecurringApplicationChargeRootRequest);
        final ShopifyRecurringApplicationChargeRoot shopifyRecurringApplicationChargeRootResponse = response
                .readEntity(ShopifyRecurringApplicationChargeRoot.class);
        return shopifyRecurringApplicationChargeRootResponse.getRecurringApplicationCharge();
    }

    @Override
    public ShopifyRecurringApplicationCharge getRecurringApplicationCharge(final String chargeId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.RECURRING_APPLICATION_CHARGES).path(chargeId));
        final ShopifyRecurringApplicationChargeRoot shopifyRecurringApplicationChargeRootResponse = response
                .readEntity(ShopifyRecurringApplicationChargeRoot.class);
        return shopifyRecurringApplicationChargeRootResponse.getRecurringApplicationCharge();
    }

    @Override
    public ShopifyRecurringApplicationCharge activateRecurringApplicationCharge(final String chargeId) {
        final ShopifyRecurringApplicationCharge shopifyRecurringApplicationChargeRequest = getRecurringApplicationCharge(
                chargeId);
        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.RECURRING_APPLICATION_CHARGES).path(chargeId).path(ShopifyEndpoint.ACTIVATE),
                shopifyRecurringApplicationChargeRequest);
        final ShopifyRecurringApplicationChargeRoot shopifyRecurringApplicationChargeRootResponse = response
                .readEntity(ShopifyRecurringApplicationChargeRoot.class);
        return shopifyRecurringApplicationChargeRootResponse.getRecurringApplicationCharge();
    }

}
