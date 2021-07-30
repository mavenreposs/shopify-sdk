package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyCustomer;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyCustomersRoot {
    public List<ShopifyCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<ShopifyCustomer> customers) {
        this.customers = customers;
    }

    private List<ShopifyCustomer> customers = new LinkedList<>();

    @Override
    public String toString() {
        return "ShopifyCustomersRoot{" +
                "customers=" + customers +
                '}';
    }
}
