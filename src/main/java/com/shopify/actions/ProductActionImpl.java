package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.request.ShopifyProductCreationRequest;
import com.shopify.model.request.ShopifyProductUpdateRequest;
import com.shopify.model.roots.ShopifyProductRoot;
import com.shopify.model.structs.ShopifyProduct;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * ClassName ProductActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/27 18:30
 */
public class ProductActionImpl implements ProductAction {

    private final ShopifySdk shopifySdk;

    public ProductActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public ShopifyProduct createProduct(final ShopifyProductCreationRequest shopifyProductCreationRequest) {
        final ShopifyProductRoot shopifyProductRootRequest = new ShopifyProductRoot();
        final ShopifyProduct shopifyProduct = shopifyProductCreationRequest.getRequest();
        shopifyProductRootRequest.setProduct(shopifyProduct);
        final Response response = shopifySdk.getShopifyWebTarget()
                .post(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS), shopifyProductRootRequest);
        final ShopifyProductRoot shopifyProductRootResponse = response.readEntity(ShopifyProductRoot.class);
        final ShopifyProduct createdShopifyProduct = shopifyProductRootResponse.getProduct();
        return shopifySdk.updateProductImages(shopifyProductCreationRequest, createdShopifyProduct);
    }

    @Override
    public ShopifyProduct updateProduct(final ShopifyProductUpdateRequest shopifyProductUpdateRequest) {
        final ShopifyProductRoot shopifyProductRootRequest = new ShopifyProductRoot();
        final ShopifyProduct shopifyProduct = shopifyProductUpdateRequest.getRequest();
        shopifyProductRootRequest.setProduct(shopifyProduct);
        final Response response = shopifySdk.getShopifyWebTarget()
                .put(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(shopifyProduct.getId()),
                shopifyProductRootRequest);
        final ShopifyProductRoot shopifyProductRootResponse = response.readEntity(ShopifyProductRoot.class);
        final ShopifyProduct updatedShopifyProduct = shopifyProductRootResponse.getProduct();
        return shopifySdk.updateProductImages(shopifyProductUpdateRequest, updatedShopifyProduct);
    }

    @Override
    public boolean deleteProduct(final String productId) {
        final Response response = shopifySdk.getShopifyWebTarget()
                .delete(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId));
        return Response.Status.OK.getStatusCode() == response.getStatus();
    }

}
