package com.shopify;

import com.shopify.actions.ProductAction;
import com.shopify.actions.ProductImageAction;
import com.shopify.actions.ProductVariantAction;
import com.shopify.actions.ShopAction;

public interface ShopifySdkAction extends ShopAction,
        ProductImageAction, ProductVariantAction, ProductAction {
}
