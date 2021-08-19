package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyPresentmentPrice
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 10:53
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyPresentmentPrice {
    private ShopifyMoney price;
    @XmlElement(name = "compare_at_price")
    private BigDecimal compareAtPrice;

    public ShopifyMoney getPrice() {
        return price;
    }

    public void setPrice(ShopifyMoney price) {
        this.price = price;
    }

    public BigDecimal getCompareAtPrice() {
        return compareAtPrice;
    }

    public void setCompareAtPrice(BigDecimal compareAtPrice) {
        this.compareAtPrice = compareAtPrice;
    }

    @Override
    public String toString() {
        return "ShopifyPresentmentPrice{" +
                "price=" + price +
                ", compareAtPrice=" + compareAtPrice +
                '}';
    }
}
