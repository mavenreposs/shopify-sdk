package com.shopify.actions;

import com.shopify.model.structs.ShopifyInventoryItem;

public interface InventoryItemAction {

    ShopifyInventoryItem getInventoryItem(final String inventoryItemId);

    ShopifyInventoryItem updateInventoryItem(final ShopifyInventoryItem shopifyInventoryItem);

    ShopifyInventoryItem updateInventoryItem(final String inventoryItemId, boolean requiresShipping);


}
