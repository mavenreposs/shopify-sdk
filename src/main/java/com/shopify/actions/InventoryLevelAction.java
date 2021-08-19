package com.shopify.actions;

import com.shopify.model.structs.ShopifyInventoryLevel;

/**
 * Created with IntelliJ IDEA.
 * ClassName InventoryLevelAction
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 11:06
 */
public interface InventoryLevelAction {

    ShopifyInventoryLevel updateInventoryLevel(final String inventoryItemId, final String locationId,
                                               final long quantity);

}
