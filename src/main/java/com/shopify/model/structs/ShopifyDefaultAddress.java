package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyDefaultAddress
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/24 18:14
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyDefaultAddress extends ShopifyAddress {
    private String id;
    @XmlElement(name = "customer_id")
    private String customerId;
    @XmlElement(name = "default")
    private String default_;
    @XmlElement(name = "country_name")
    private String countryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDefault_() {
        return default_;
    }

    public void setDefault_(String default_) {
        this.default_ = default_;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "ShopifyDefaultAddress{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", default_='" + default_ + '\'' +
                ", countryName='" + countryName + '\'' +
                "} " + super.toString();
    }
}
