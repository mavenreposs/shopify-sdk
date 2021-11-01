package com.shopify.actions.impl;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.actions.ProductImageAction;
import com.shopify.model.request.ShopifyProductRequest;
import com.shopify.model.roots.ShopifyImageRoot;
import com.shopify.model.roots.ShopifyProductRoot;
import com.shopify.model.structs.Image;
import com.shopify.model.structs.ShopifyProduct;

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
    public Image createProductImage(final String productId, final Image imageRequest) {
        final ShopifyImageRoot shopifyImageRootRequest = new ShopifyImageRoot();
        shopifyImageRootRequest.setImage(imageRequest);
        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId)
                        .path(ShopifyEndpoint.IMAGES),
                shopifyImageRootRequest);
        final ShopifyImageRoot shopifyImageRootResponse = response.readEntity(ShopifyImageRoot.class);
        final Image createdImage = shopifyImageRootResponse.getImage();
        return createdImage;
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
    public Image createVariantImage(ShopifyImageRoot shopifyImageRootRequest) {
        Image image = shopifyImageRootRequest.getImage();
        Response response = shopifySdk.getShopifyWebTarget().post(
                shopifySdk.getWebTarget()
                        .path(ShopifyEndpoint.PRODUCTS).path(image.getProductId())
                        .path(ShopifyEndpoint.IMAGES), shopifyImageRootRequest);
        final ShopifyImageRoot shopifyImageRootResponse = response.readEntity(ShopifyImageRoot.class);
        return shopifyImageRootResponse.getImage();
    }

    @Override
    public Image updateProductImage(final String productId, final Image imageRequest) {
        final ShopifyImageRoot shopifyImageRootRequest = new ShopifyImageRoot();
        imageRequest.setSource(null);
        imageRequest.setName(null);
        imageRequest.setAlt(null);
        imageRequest.setCreatedAt(null);
        imageRequest.setUpdatedAt(null);
        imageRequest.setHeight(null);
        imageRequest.setWidth(null);
        shopifyImageRootRequest.setImage(imageRequest);
        final Response response = shopifySdk.getShopifyWebTarget().put(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId)
                        .path(ShopifyEndpoint.IMAGES).path(imageRequest.getId()),
                shopifyImageRootRequest);
        final ShopifyImageRoot shopifyImageRootResponse = response.readEntity(ShopifyImageRoot.class);
        return shopifyImageRootResponse.getImage();
    }

    @Override
    public boolean deleteProductImage(final String productId, final String imageId) {
        final Response response = shopifySdk.getShopifyWebTarget().delete(shopifySdk.getWebTarget()
                .path(ShopifyEndpoint.PRODUCTS).path(productId)
                .path(ShopifyEndpoint.IMAGES).path(imageId));
        return Response.Status.OK.getStatusCode() == response.getStatus();
    }

    public ShopifyProduct updateProductImages(final ShopifyProductRequest shopifyProductRequest,
                                              final ShopifyProduct shopifyProduct) {
        setVariantImageIds(shopifyProductRequest, shopifyProduct);
        final ShopifyProductRoot shopifyProductRootRequest = new ShopifyProductRoot();
        shopifyProductRootRequest.setProduct(shopifyProduct);
        final Response response = shopifySdk.getShopifyWebTarget().put(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(shopifyProduct.getId()),
                shopifyProductRootRequest);
        final ShopifyProductRoot shopifyProductRootResponse = response.readEntity(ShopifyProductRoot.class);
        return shopifyProductRootResponse.getProduct();
    }

    private void setVariantImageIds(final ShopifyProductRequest shopifyProductRequest,
                                    final ShopifyProduct shopifyProduct) {
        shopifyProduct.getVariants().stream().forEach(variant -> {
            final int variantPosition = variant.getPosition();
            if (shopifyProductRequest.hasVariantImagePosition(variantPosition)) {
                final int imagePosition = shopifyProductRequest.getVariantImagePosition(variantPosition);
                shopifyProduct.getImages().stream().filter(image -> image.getPosition() == imagePosition).findFirst()
                        .ifPresent(variantImage -> variant.setImageId(variantImage.getId()));
            }
        });
    }

}
