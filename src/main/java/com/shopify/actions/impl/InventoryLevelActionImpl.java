package com.shopify.actions.impl;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.actions.InventoryLevelAction;
import com.shopify.actions.impl.CustomerActionImpl;
import com.shopify.model.roots.ShopifyInventoryLevelRoot;
import com.shopify.model.structs.ShopifyInventoryLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * ClassName InventoryLevelActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 11:06
 */
public class InventoryLevelActionImpl implements InventoryLevelAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerActionImpl.class);

    public InventoryLevelActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    public ShopifyInventoryLevel updateInventoryLevel(final String inventoryItemId, final String locationId,
                                                      final long quantity) {
        final ShopifyInventoryLevel shopifyInventoryLevel = new ShopifyInventoryLevel();
        shopifyInventoryLevel.setAvailable(quantity);
        shopifyInventoryLevel.setLocationId(locationId);
        shopifyInventoryLevel.setInventoryItemId(inventoryItemId);
        final Response response = shopifySdk.getShopifyWebTarget().post(
                shopifySdk.getWebTarget()
                        .path(ShopifyEndpoint.INVENTORY_LEVELS)
                        .path(ShopifyEndpoint.SET), shopifyInventoryLevel);
        final ShopifyInventoryLevelRoot shopifyInventoryLevelRootResponse = response
                .readEntity(ShopifyInventoryLevelRoot.class);
        return shopifyInventoryLevelRootResponse.getInventoryLevel();
    }

}
