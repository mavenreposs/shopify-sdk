package com.shopify.actions.impl;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.actions.GiftCardAction;
import com.shopify.actions.impl.CustomerActionImpl;
import com.shopify.model.request.ShopifyGiftCardCreationRequest;
import com.shopify.model.roots.ShopifyGiftCardRoot;
import com.shopify.model.structs.ShopifyGiftCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * ClassName GiftCardActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 11:10
 */
public class GiftCardActionImpl implements GiftCardAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerActionImpl.class);

    public GiftCardActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    public ShopifyGiftCard createGiftCard(final ShopifyGiftCardCreationRequest shopifyGiftCardCreationRequest) {
        final ShopifyGiftCardRoot shopifyGiftCardRoot = new ShopifyGiftCardRoot();
        final ShopifyGiftCard shopifyGiftCard = shopifyGiftCardCreationRequest.getRequest();
        shopifyGiftCardRoot.setGiftCard(shopifyGiftCard);
        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.GIFT_CARDS), shopifyGiftCardRoot);
        final ShopifyGiftCardRoot shopifyOrderRootResponse = response.readEntity(ShopifyGiftCardRoot.class);
        return shopifyOrderRootResponse.getGiftCard();
    }

}
