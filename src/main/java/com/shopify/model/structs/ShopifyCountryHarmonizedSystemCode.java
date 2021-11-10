package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ClassName ShopifyCountryHarmonizedSystemCode
 *
 * @author Zhengdong Wang
 * @version jdk11
 * @CreateDate 2021/11/10 09:51
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyCountryHarmonizedSystemCode {

    @XmlElement(name = "country_code")
    private String countryCode;

    @XmlElement(name = "harmonized_system_code")
    private String harmonizedSystemCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getHarmonizedSystemCode() {
        return harmonizedSystemCode;
    }

    public void setHarmonizedSystemCode(String harmonizedSystemCode) {
        this.harmonizedSystemCode = harmonizedSystemCode;
    }

    @Override
    public String toString() {
        return "ShopifyCountryHarmonizedSystemCode{" +
                "countryCode='" + countryCode + '\'' +
                ", harmonizedSystemCode='" + harmonizedSystemCode + '\'' +
                '}';
    }
}
