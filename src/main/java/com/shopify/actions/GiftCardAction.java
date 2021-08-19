package com.shopify.actions;

import com.shopify.model.request.ShopifyGiftCardCreationRequest;
import com.shopify.model.structs.ShopifyGiftCard;

/**
 * Created with IntelliJ IDEA.
 * ClassName GiftCardAction
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 11:10
 */
public interface GiftCardAction {

    ShopifyGiftCard createGiftCard(final ShopifyGiftCardCreationRequest shopifyGiftCardCreationRequest);

}
