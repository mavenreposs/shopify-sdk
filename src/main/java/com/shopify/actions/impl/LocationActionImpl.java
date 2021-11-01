package com.shopify.actions.impl;

import com.shopify.ShopifyEndpoint;
import com.shopify.ShopifySdk;
import com.shopify.actions.LocationAction;
import com.shopify.actions.impl.CustomerActionImpl;
import com.shopify.model.roots.ShopifyLocationsRoot;
import com.shopify.model.structs.ShopifyLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ClassName LocationActionImpl
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 11:00
 */
public class LocationActionImpl implements LocationAction {

    private final ShopifySdk shopifySdk;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerActionImpl.class);

    public LocationActionImpl(ShopifySdk shopifySdk) {
        this.shopifySdk = shopifySdk;
    }

    public List<ShopifyLocation> getLocations() {
        final String locationsEndpoint = new StringBuilder().append(ShopifyEndpoint.LOCATIONS).append(ShopifyEndpoint.JSON).toString();
        final Response response = shopifySdk.getShopifyWebTarget().get(shopifySdk.getWebTarget().path(locationsEndpoint));
        final ShopifyLocationsRoot shopifyLocationRootResponse = response.readEntity(ShopifyLocationsRoot.class);
        return shopifyLocationRootResponse.getLocations();
    }

}
