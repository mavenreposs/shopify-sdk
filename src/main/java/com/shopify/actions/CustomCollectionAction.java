package com.shopify.actions;

import com.shopify.model.ShopifyPage;
import com.shopify.model.request.ShopifyCustomCollectionCreationRequest;
import com.shopify.model.structs.ShopifyCustomCollection;

import java.util.List;

public interface CustomCollectionAction {

    ShopifyPage<ShopifyCustomCollection> getCustomCollections(final int pageSize);

    ShopifyPage<ShopifyCustomCollection> getCustomCollections(final String pageInfo, final int pageSize);

    List<ShopifyCustomCollection> getCustomCollections();

    ShopifyCustomCollection createCustomCollection(
            final ShopifyCustomCollectionCreationRequest shopifyCustomCollectionCreationRequest);

}
