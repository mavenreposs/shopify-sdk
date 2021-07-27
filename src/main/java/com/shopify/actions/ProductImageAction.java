package com.shopify.actions;

import com.shopify.model.structs.Image;

public interface ProductImageAction {

    Image createProductImage(final String productId, final String imageSource);

    boolean deleteProductImage(final String productId, final String imageId);

}
