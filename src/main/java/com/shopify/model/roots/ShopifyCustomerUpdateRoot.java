package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.request.ShopifyCustomerUpdateRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyCustomerUpdateRoot {

	private ShopifyCustomerUpdateRequest customer;

	public ShopifyCustomerUpdateRequest getCustomer() {
		return customer;
	}

	public void setCustomer(final ShopifyCustomerUpdateRequest customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ShopifyCustomerUpdateRoot{" +
				"customer=" + customer +
				'}';
	}
}
