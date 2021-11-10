package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * ClassName ShopifyInventoryItem
 *
 * @author Zhengdong Wang
 * @version jdk11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyInventoryItem {

    private String id;

    private String sku;

    private String cost;

    private Boolean tracked;
    @XmlElement(name = "created_at")
    private String createdAt;
    @XmlElement(name = "updated_at")
    private String updatedAt;
    @XmlElement(name = "requires_shipping")
    private Boolean requiresShipping;
    @XmlElement(name = "country_code_of_origin")
    private String countryCodeOfOrigin;

    private List<ShopifyCountryHarmonizedSystemCode> countryHarmonizedSystemCodes;
    @XmlElement(name = "province_code_of_origin")
    private String provinceCodeOfOrigin;
    @XmlElement(name = "harmonized_system_code")
    private String harmonizedSystemCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Boolean getTracked() {
        return tracked;
    }

    public void setTracked(Boolean tracked) {
        this.tracked = tracked;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(Boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public String getCountryCodeOfOrigin() {
        return countryCodeOfOrigin;
    }

    public void setCountryCodeOfOrigin(String countryCodeOfOrigin) {
        this.countryCodeOfOrigin = countryCodeOfOrigin;
    }

    public List<ShopifyCountryHarmonizedSystemCode> getCountryHarmonizedSystemCodes() {
        return countryHarmonizedSystemCodes;
    }

    public void setCountryHarmonizedSystemCodes(List<ShopifyCountryHarmonizedSystemCode> countryHarmonizedSystemCodes) {
        this.countryHarmonizedSystemCodes = countryHarmonizedSystemCodes;
    }

    public String getProvinceCodeOfOrigin() {
        return provinceCodeOfOrigin;
    }

    public void setProvinceCodeOfOrigin(String provinceCodeOfOrigin) {
        this.provinceCodeOfOrigin = provinceCodeOfOrigin;
    }

    public String getHarmonizedSystemCode() {
        return harmonizedSystemCode;
    }

    public void setHarmonizedSystemCode(String harmonizedSystemCode) {
        this.harmonizedSystemCode = harmonizedSystemCode;
    }

    @Override
    public String toString() {
        return "ShopifyInventoryItem{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", cost='" + cost + '\'' +
                ", tracked=" + tracked +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", requiresShipping=" + requiresShipping +
                ", countryCodeOfOrigin='" + countryCodeOfOrigin + '\'' +
                ", countryHarmonizedSystemCodes=" + countryHarmonizedSystemCodes +
                ", provinceCodeOfOrigin='" + provinceCodeOfOrigin + '\'' +
                ", harmonizedSystemCode='" + harmonizedSystemCode + '\'' +
                '}';
    }
}
