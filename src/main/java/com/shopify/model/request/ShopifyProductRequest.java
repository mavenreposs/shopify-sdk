package com.shopify.model.request;

import com.shopify.model.structs.ShopifyProduct;

public interface ShopifyProductRequest {

	public ShopifyProduct getRequest();

	public int getVariantImagePosition(final int variantPosition);

	public boolean hasVariantImagePosition(final int variantPosition);

	public boolean hasChanged();

}
