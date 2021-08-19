package com.shopify.actions;

import com.shopify.model.structs.ShopifyLocation;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ClassName LocationAction
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 11:00
 */
public interface LocationAction {

    List<ShopifyLocation> getLocations();

}
