package com.shopify.actions;

import com.shopify.model.request.ShopifyVariantUpdateRequest;
import com.shopify.model.structs.ShopifyVariant;

public interface ProductVariantAction {

    ShopifyVariant getVariant(final String variantId);

    ShopifyVariant updateVariant(final ShopifyVariantUpdateRequest shopifyVariantUpdateRequest);


}
