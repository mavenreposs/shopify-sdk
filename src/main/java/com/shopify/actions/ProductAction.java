package com.shopify.actions;

import com.shopify.model.ShopifyPage;
import com.shopify.model.ShopifyProducts;
import com.shopify.model.request.ShopifyProductCreationRequest;
import com.shopify.model.request.ShopifyProductUpdateRequest;
import com.shopify.model.structs.ShopifyProduct;

/**
 * Created with IntelliJ IDEA.
 * ClassName ProductAction
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/27 17:33
 */
public interface ProductAction {

    ShopifyProduct getProduct(final String productId);

    ShopifyPage<ShopifyProduct> getProducts(final int pageSize);

    ShopifyPage<ShopifyProduct> getProducts(final String pageInfo, final int pageSize);

    ShopifyProducts getProducts();

    int getProductCount();

    ShopifyProduct createProduct(final ShopifyProductCreationRequest shopifyProductCreationRequest);

    ShopifyProduct updateProduct(final ShopifyProductUpdateRequest shopifyProductUpdateRequest);

    boolean deleteProduct(final String productId);


}
