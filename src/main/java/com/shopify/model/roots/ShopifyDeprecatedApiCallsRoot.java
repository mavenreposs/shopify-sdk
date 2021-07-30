package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyDeprecatedApiCall;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyDeprecatedApiCallsRoot {

    @XmlElement(name = "data_updated_at")
    private String dataUpdatedAt;
    @XmlElement(name = "deprecated_api_calls")
    private List<ShopifyDeprecatedApiCall> deprecatedApiCalls;

    public String getDataUpdatedAt() {
        return dataUpdatedAt;
    }

    public void setDataUpdatedAt(String dataUpdatedAt) {
        this.dataUpdatedAt = dataUpdatedAt;
    }

    public List<ShopifyDeprecatedApiCall> getDeprecatedApiCalls() {
        return deprecatedApiCalls;
    }

    public void setDeprecatedApiCalls(List<ShopifyDeprecatedApiCall> deprecatedApiCalls) {
        this.deprecatedApiCalls = deprecatedApiCalls;
    }

    @Override
    public String toString() {
        return "ShopifyDeprecatedApiCallsRoot{" +
                "dataUpdatedAt='" + dataUpdatedAt + '\'' +
                ", deprecatedApiCalls=" + deprecatedApiCalls +
                '}';
    }
}
