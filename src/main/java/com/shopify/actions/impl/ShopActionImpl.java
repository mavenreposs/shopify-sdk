package com.shopify.actions.impl;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.actions.ShopAction;
import com.shopify.model.roots.ShopifyShopRoot;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopAction1
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/27 18:04
 */
public class ShopActionImpl implements ShopAction {

    private final ShopifySdk shopifySdk;

    public ShopActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public ShopifyShopRoot getShop() {
        final Response response = shopifySdk.getShopifyWebTarget()
                .get(shopifySdk.getWebTarget().path(ShopifyEndpoint.SHOP));
        return response.readEntity(ShopifyShopRoot.class);
    }

}
