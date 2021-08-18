package com.shopify.actions;

import com.shopify.model.request.ShopifyVariantMetafieldCreationRequest;
import com.shopify.model.request.ShopifyVariantUpdateRequest;
import com.shopify.model.structs.Metafield;
import com.shopify.model.structs.ShopifyVariant;

import java.util.List;

public interface ProductVariantAction {

    ShopifyVariant getVariant(final String variantId);

    ShopifyVariant updateVariant(final ShopifyVariant shopifyVariant);

    ShopifyVariant updateVariant(final ShopifyVariantUpdateRequest shopifyVariantUpdateRequest);

    Metafield createVariantMetafield(
            final ShopifyVariantMetafieldCreationRequest shopifyVariantMetafieldCreationRequest);

    List<Metafield> getVariantMetafields(final String variantId);

}
