package com.shopify;

import com.shopify.model.ShopifyPage;
import com.shopify.model.structs.ShopifyDeprecatedApiCall;
import com.shopify.model.structs.ShopifyProduct;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class ShopifyEndpointTest {

    private static final String SHOP_SUBDOMAIN = System.getenv("SHOP_SUBDOMAIN");
    private static final String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");

    private ShopifySdk shopifySdk;

    @Before
    public void setUp() {
        shopifySdk = ShopifySdk.newBuilder()
                .withSubdomain(SHOP_SUBDOMAIN)
                .withAccessToken(ACCESS_TOKEN)
                .withMaximumRequestRetryTimeout(5, TimeUnit.SECONDS)
                .withMaximumRequestRetryRandomDelay(5, TimeUnit.SECONDS)
                .withApiVersion("2021-04")
                .build();
    }

    @Test
    public void testShopifySdk() {
        System.out.println(shopifySdk);
    }

    @Test
    public void getShop() {
        System.out.println(shopifySdk.getShop());
    }

    @Test
    public void getProducts() {
        final ShopifyPage<ShopifyProduct> shopifyProducts = shopifySdk.getProducts(null, 1);
        System.out.println(shopifyProducts);
    }

    @Test
    public void getProductCount() {
        final int count = shopifySdk.getProductCount();
        System.out.println(count);
    }

    @Test
    public void getOrderCount() {
        final int count = shopifySdk.getOrderCount();
        System.out.println(count);
    }

    @Test
    public void getDeprecatedApiCalls() {
        final List<ShopifyDeprecatedApiCall> shopifyDeprecatedApiCalls = shopifySdk.getDeprecatedApiCalls();
        System.out.println(shopifyDeprecatedApiCalls);
    }

    @Test
    public void enableLogging() {
        shopifySdk.getWebTarget().register(new LoggingFeature(java.util.logging.Logger.getLogger(ShopifySdk.class.getName()), Level.OFF, LoggingFeature.Verbosity.PAYLOAD_TEXT, 819200000));
        System.out.println(shopifySdk.getShop());
    }

}
