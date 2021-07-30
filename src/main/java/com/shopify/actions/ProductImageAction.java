package com.shopify.actions;

import com.shopify.model.request.ShopifyProductRequest;
import com.shopify.model.structs.Image;
import com.shopify.model.structs.ShopifyProduct;

public interface ProductImageAction {

    Image createProductImage(final String productId, final Image image);

    Image createProductImage(final String productId, final String imageSource);

    Image createProductImage(final String productId, final String imageSource, final int position);

    boolean deleteProductImage(final String productId, final String imageId);

    ShopifyProduct updateProductImages(final ShopifyProductRequest shopifyProductRequest,
                                       final ShopifyProduct shopifyProduct);

}
