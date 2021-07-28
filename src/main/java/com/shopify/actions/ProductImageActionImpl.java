package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.roots.ShopifyImageRoot;
import com.shopify.model.structs.Image;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * ClassName ProductImageActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/28 09:39
 */
public class ProductImageActionImpl implements ProductImageAction {

    private final ShopifySdk shopifySdk;

    public ProductImageActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public Image createProductImage(final String productId, final String imageSource) {
        final ShopifyImageRoot shopifyImageRootRequest = new ShopifyImageRoot();
        final Image imageRequest = new Image();
        imageRequest.setSource(imageSource);
        shopifyImageRootRequest.setImage(imageRequest);
        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId)
                        .path(ShopifyEndpoint.IMAGES),
                shopifyImageRootRequest);
        final ShopifyImageRoot shopifyImageRootResponse = response.readEntity(ShopifyImageRoot.class);
        final Image createdImage = shopifyImageRootResponse.getImage();
        return createdImage;
    }

    @Override
    public Image createProductImage(final String productId, final String imageSource, final int position) {
        final ShopifyImageRoot shopifyImageRootRequest = new ShopifyImageRoot();
        final Image imageRequest = new Image();
        imageRequest.setSource(imageSource);
        imageRequest.setPosition(position);
        shopifyImageRootRequest.setImage(imageRequest);
        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId)
                        .path(ShopifyEndpoint.IMAGES),
                shopifyImageRootRequest);
        final ShopifyImageRoot shopifyImageRootResponse = response.readEntity(ShopifyImageRoot.class);
        final Image createdImage = shopifyImageRootResponse.getImage();
        return createdImage;
    }

    @Override
    public boolean deleteProductImage(final String productId, final String imageId) {
        final Response response = shopifySdk.getShopifyWebTarget().delete(shopifySdk.getWebTarget()
                .path(ShopifyEndpoint.PRODUCTS).path(productId)
                .path(ShopifyEndpoint.IMAGES).path(imageId));
        return Response.Status.OK.getStatusCode() == response.getStatus();
    }

}
