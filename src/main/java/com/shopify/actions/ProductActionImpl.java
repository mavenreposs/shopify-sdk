package com.shopify.actions;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.model.ShopifyPage;
import com.shopify.model.ShopifyProducts;
import com.shopify.model.request.ShopifyProductCreationRequest;
import com.shopify.model.request.ShopifyProductMetafieldCreationRequest;
import com.shopify.model.request.ShopifyProductUpdateRequest;
import com.shopify.model.roots.MetafieldRoot;
import com.shopify.model.roots.MetafieldsRoot;
import com.shopify.model.roots.ShopifyProductRoot;
import com.shopify.model.roots.ShopifyProductsRoot;
import com.shopify.model.structs.Count;
import com.shopify.model.structs.Metafield;
import com.shopify.model.structs.ShopifyProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ClassName ProductActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/27 18:30
 */
public class ProductActionImpl implements ProductAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductActionImpl.class);

    public ProductActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    @Override
    public ShopifyProduct getProduct(final String productId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId));
        final ShopifyProductRoot shopifyProductRootResponse = response.readEntity(ShopifyProductRoot.class);
        return shopifyProductRootResponse.getProduct();
    }

    @Override
    public ShopifyPage<ShopifyProduct> getProducts(final int pageSize) {
        return this.getProducts(null, pageSize);
    }

    @Override
    public ShopifyPage<ShopifyProduct> getProducts(final String pageInfo, final int pageSize) {
        final WebTarget url = shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).queryParam(ShopifyEndpoint.LIMIT_QUERY_PARAMETER, pageSize)
                .queryParam(ShopifyEndpoint.PAGE_INFO_QUERY_PARAMETER, pageInfo);
        final Response response = shopifySdk.getShopifyWebTarget().get(url);
        final ShopifyProductsRoot shopifyProductsRoot = response.readEntity(ShopifyProductsRoot.class);
        return shopifySdk.mapPagedResponse(shopifyProductsRoot.getProducts(), response);
    }

    @Override
    public ShopifyProducts getProducts() {
        final List<ShopifyProduct> shopifyProducts = new LinkedList<>();

        ShopifyPage<ShopifyProduct> shopifyProductsPage = getProducts(ShopifySdk.DEFAULT_REQUEST_LIMIT);
        LOGGER.info("Retrieved {} products from first page", shopifyProductsPage.size());
        shopifyProducts.addAll(shopifyProductsPage);
        while (shopifyProductsPage.getNextPageInfo() != null) {
            shopifyProductsPage = getProducts(shopifyProductsPage.getNextPageInfo(), ShopifySdk.DEFAULT_REQUEST_LIMIT);
            LOGGER.info("Retrieved {} products from page {}", shopifyProductsPage.size(),
                    shopifyProductsPage.getNextPageInfo());
            shopifyProducts.addAll(shopifyProductsPage);
        }
        return new ShopifyProducts(shopifyProducts);
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

    @Override
    public int getProductCount() {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(ShopifyEndpoint.COUNT));
        final Count count = response.readEntity(Count.class);
        return count.getCount();
    }

    public Metafield createProductMetafield(
            final ShopifyProductMetafieldCreationRequest shopifyProductMetafieldCreationRequest) {
        final MetafieldRoot metafieldRoot = new MetafieldRoot();
        metafieldRoot.setMetafield(shopifyProductMetafieldCreationRequest.getRequest());
        final Response response = shopifySdk.getShopifyWebTarget().post(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS)
                .path(shopifyProductMetafieldCreationRequest.getProductId()).path(ShopifyEndpoint.METAFIELDS), metafieldRoot);
        final MetafieldRoot metafieldRootResponse = response.readEntity(MetafieldRoot.class);
        return metafieldRootResponse.getMetafield();
    }

    public List<Metafield> getProductMetafields(final String productId) {
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(ShopifyEndpoint.PRODUCTS).path(productId).path(ShopifyEndpoint.METAFIELDS));
        final MetafieldsRoot metafieldsRootResponse = response.readEntity(MetafieldsRoot.class);
        return metafieldsRootResponse.getMetafields();
    }

}
