package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyPriceSet
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 10:48
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyPriceSet {
    @XmlElement(name = "presentment_money")
    private ShopifyMoney presentmentMoney;
    @XmlElement(name = "shop_money")
    private ShopifyMoney shopMoney;

    public ShopifyMoney getPresentmentMoney() {
        return presentmentMoney;
    }

    public void setPresentmentMoney(ShopifyMoney presentmentMoney) {
        this.presentmentMoney = presentmentMoney;
    }

    public ShopifyMoney getShopMoney() {
        return shopMoney;
    }

    public void setShopMoney(ShopifyMoney shopMoney) {
        this.shopMoney = shopMoney;
    }

    @Override
    public String toString() {
        return "ShopifyPriceSet{" +
                "presentmentMoney=" + presentmentMoney +
                ", shopMoney=" + shopMoney +
                '}';
    }
}
