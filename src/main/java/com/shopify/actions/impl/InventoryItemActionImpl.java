package com.shopify.actions.impl;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.actions.InventoryItemAction;
import com.shopify.model.roots.ShopifyInventoryItemRoot;
import com.shopify.model.structs.ShopifyInventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * ClassName InventoryItemActionImpl
 *
 * @author Zhengdong Wang
 * @version jdk11
 */
public class InventoryItemActionImpl implements InventoryItemAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductActionImpl.class);

    public InventoryItemActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }


    @Override
    public ShopifyInventoryItem getInventoryItem(String inventoryItemId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(
                        shopifySdk.getWebTarget()
                                .path(ShopifyEndpoint.INVENTORY_ITEMS)
                                .path(inventoryItemId)
                );
        final ShopifyInventoryItemRoot shopifyInventoryItemRootResponse = response.readEntity(ShopifyInventoryItemRoot.class);
        return shopifyInventoryItemRootResponse.getInventoryItem();
    }

    @Override
    public ShopifyInventoryItem updateInventoryItem(ShopifyInventoryItem shopifyInventoryItem) {
        final ShopifyInventoryItemRoot shopifyInventoryItemRootRequest = new ShopifyInventoryItemRoot();
        shopifyInventoryItem.setCreatedAt(null);
        shopifyInventoryItem.setUpdatedAt(null);
        shopifyInventoryItemRootRequest.setInventoryItem(shopifyInventoryItem);
        final Response response = shopifySdk.getShopifyWebTarget()
                .put(
                        shopifySdk.getWebTarget()
                                .path(ShopifyEndpoint.INVENTORY_ITEMS).path(shopifyInventoryItem.getId()),
                        shopifyInventoryItemRootRequest
                );
        final ShopifyInventoryItemRoot shopifyInventoryItemRootResponse = response.readEntity(ShopifyInventoryItemRoot.class);
        final ShopifyInventoryItem updatedShopifyInventoryItem = shopifyInventoryItemRootResponse.getInventoryItem();
        return updatedShopifyInventoryItem;
    }

    @Override
    public ShopifyInventoryItem updateInventoryItem(String inventoryItemId, boolean requiresShipping) {
        final ShopifyInventoryItemRoot shopifyInventoryItemRootRequest = new ShopifyInventoryItemRoot();
        final ShopifyInventoryItem shopifyInventoryItem = new ShopifyInventoryItem();
        shopifyInventoryItem.setId(inventoryItemId);
        shopifyInventoryItem.setRequiresShipping(requiresShipping);
        shopifyInventoryItemRootRequest.setInventoryItem(shopifyInventoryItem);
        final Response response = shopifySdk.getShopifyWebTarget()
                .put(
                        shopifySdk.getWebTarget()
                                .path(ShopifyEndpoint.INVENTORY_ITEMS).path(shopifyInventoryItem.getId()),
                        shopifyInventoryItemRootRequest
                );
        final ShopifyInventoryItemRoot shopifyInventoryItemRootResponse = response.readEntity(ShopifyInventoryItemRoot.class);
        final ShopifyInventoryItem updatedShopifyInventoryItem = shopifyInventoryItemRootResponse.getInventoryItem();
        return updatedShopifyInventoryItem;
    }

}
