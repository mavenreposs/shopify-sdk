package com.shopify;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * ClassName ProxyTest
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/27 17:40
 */
public class ProxyTest {

    private static final String SHOP_SUBDOMAIN = System.getenv("SHOP_SUBDOMAIN");
    private static final String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");

    @Before
    public void setup() {
        //将JDK动态代理生成的class文件保存到本地
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    @Test
    public void testProxy() {

        ShopifySdkAction shopifySdk = ShopifySdk.newBuilder()
                .withSubdomain(SHOP_SUBDOMAIN)
                .withAccessToken(ACCESS_TOKEN)
                .withMaximumRequestRetryTimeout(5, TimeUnit.SECONDS)
                .withMaximumRequestRetryRandomDelay(5, TimeUnit.SECONDS)
                .withApiVersion("2021-04")
                .build();

        System.out.println(Arrays.toString(shopifySdk.getClass().getInterfaces()));

        InvocationHandler handler = new ProxyHandler(shopifySdk);

        ShopifySdkAction proxy = (ShopifySdkAction) Proxy.newProxyInstance(
                shopifySdk.getClass().getClassLoader(),
                shopifySdk.getClass().getInterfaces(),
                handler);

        System.out.println(proxy.getShop());

    }

}
