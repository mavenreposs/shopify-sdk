package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.ShopifyPage;
import com.shopify.model.request.ShopifyCustomCollectionCreationRequest;
import com.shopify.model.roots.ShopifyCustomCollectionRoot;
import com.shopify.model.roots.ShopifyCustomCollectionsRoot;
import com.shopify.model.structs.ShopifyCustomCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ClassName CustomCollectionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/28 13:51
 */
public class CustomCollectionActionImpl implements CustomCollectionAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductActionImpl.class);

    public CustomCollectionActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public ShopifyPage<ShopifyCustomCollection> getCustomCollections(final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(
                shopifySdk.getWebTarget().path(ShopifyEndpoint.CUSTOM_COLLECTIONS).queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize));
        final ShopifyCustomCollectionsRoot shopifyCustomCollectionsRoot = response
                .readEntity(ShopifyCustomCollectionsRoot.class);
        return shopifySdk.mapPagedResponse(shopifyCustomCollectionsRoot.getCustomCollections(), response);
    }

    @Override
    public ShopifyPage<ShopifyCustomCollection> getCustomCollections(final String pageInfo, final int pageSize) {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.CUSTOM_COLLECTIONS)
                .queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize).queryParam(ShopifyEndpoint.PAGE_INFO_QUERY_PARAMETER, pageInfo));
        final ShopifyCustomCollectionsRoot shopifyCustomCollectionsRoot = response
                .readEntity(ShopifyCustomCollectionsRoot.class);
        return shopifySdk.mapPagedResponse(shopifyCustomCollectionsRoot.getCustomCollections(), response);
    }

    @Override
    public List<ShopifyCustomCollection> getCustomCollections() {
        final List<ShopifyCustomCollection> shopifyCustomCollections = new LinkedList<>();

        ShopifyPage<ShopifyCustomCollection> customCollectionsPage = getCustomCollections(ShopifySdk.DEFAULT_REQUEST_LIMIT);
        LOGGER.info("Retrieved {} custom collections from first page", customCollectionsPage.size());
        shopifyCustomCollections.addAll(customCollectionsPage);

        while (customCollectionsPage.getNextPageInfo() != null) {
            customCollectionsPage = getCustomCollections(customCollectionsPage.getNextPageInfo(),
                    ShopifySdk.DEFAULT_REQUEST_LIMIT);
            LOGGER.info("Retrieved {} custom collections from page info {}", customCollectionsPage.size(),
                    customCollectionsPage.getNextPageInfo());
            shopifyCustomCollections.addAll(customCollectionsPage);
        }

        return shopifyCustomCollections;
    }

    @Override
    public ShopifyCustomCollection createCustomCollection(
            final ShopifyCustomCollectionCreationRequest shopifyCustomCollectionCreationRequest) {
        final ShopifyCustomCollectionRoot shopifyCustomCollectionRootRequest = new ShopifyCustomCollectionRoot();
        final ShopifyCustomCollection shopifyCustomCollection = shopifyCustomCollectionCreationRequest.getRequest();
        shopifyCustomCollectionRootRequest.setCustomCollection(shopifyCustomCollection);
        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.CUSTOM_COLLECTIONS), shopifyCustomCollectionRootRequest);
        final ShopifyCustomCollectionRoot shopifyCustomCollectionRootResponse = response
                .readEntity(ShopifyCustomCollectionRoot.class);
        return shopifyCustomCollectionRootResponse.getCustomCollection();
    }


}
