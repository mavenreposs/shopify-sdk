package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyCustomer;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyCustomerRoot {

	private ShopifyCustomer customer;

	public ShopifyCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(final ShopifyCustomer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ShopifyCustomerRoot{" +
				"customer=" + customer +
				'}';
	}
}
