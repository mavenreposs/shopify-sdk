package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.request.ImageAltTextCreationRequest;
import com.shopify.model.request.ShopifyVariantUpdateRequest;
import com.shopify.model.roots.ShopifyImageRoot;
import com.shopify.model.roots.ShopifyVariantRoot;
import com.shopify.model.structs.Image;
import com.shopify.model.structs.Metafield;
import com.shopify.model.structs.ShopifyVariant;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ClassName ProductVariantActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/28 10:37
 */
public class ProductVariantActionImpl implements ProductVariantAction {

    private final ShopifySdk shopifySdk;

    public ProductVariantActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public ShopifyVariant getVariant(final String variantId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.VARIANTS).path(variantId));
        final ShopifyVariantRoot shopifyVariantRootResponse = response.readEntity(ShopifyVariantRoot.class);
        return shopifyVariantRootResponse.getVariant();
    }

    @Override
    public ShopifyVariant updateVariant(final ShopifyVariantUpdateRequest shopifyVariantUpdateRequest) {
        final ShopifyVariant shopifyVariant = shopifyVariantUpdateRequest.getRequest();
        final String shopifyVariantId = shopifyVariant.getId();
        if (StringUtils.isNotBlank(shopifyVariantUpdateRequest.getImageSource())) {
            final ShopifyImageRoot shopifyImageRootRequest = new ShopifyImageRoot();
            final Image imageRequest = new Image();
            imageRequest.setSource(shopifyVariantUpdateRequest.getImageSource());
            final List<Metafield> metafields = ImageAltTextCreationRequest.newBuilder()
                    .withImageAltText(shopifyVariant.getTitle()).build();
            imageRequest.setMetafields(metafields);
            imageRequest.setVariantIds(Arrays.asList(shopifyVariantId));
            shopifyImageRootRequest.setImage(imageRequest);
            final String productId = shopifyVariant.getProductId();
            final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId).path(ShopifyEndpoint.IMAGES),
                    shopifyImageRootRequest);
            final ShopifyImageRoot shopifyImageRootResponse = response.readEntity(ShopifyImageRoot.class);
            final Image createdImage = shopifyImageRootResponse.getImage();
            shopifyVariant.setImageId(createdImage.getId());
        }

        final ShopifyVariantRoot shopifyVariantRootRequest = new ShopifyVariantRoot();
        shopifyVariantRootRequest.setVariant(shopifyVariant);
        final Response response = shopifySdk.getShopifyWebTarget().put(shopifySdk.getWebTarget().path(ShopifyEndpoint.VARIANTS).path(shopifyVariantId), shopifyVariantRootRequest);
        final ShopifyVariantRoot shopifyVariantRootResponse = response.readEntity(ShopifyVariantRoot.class);
        return shopifyVariantRootResponse.getVariant();
    }

}
