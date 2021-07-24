package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.ShopifyOrder;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyOrderRoot {

	private ShopifyOrder order;

	public ShopifyOrder getOrder() {
		return order;
	}

	public void setOrder(ShopifyOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "ShopifyOrderRoot{" +
				"order=" + order +
				'}';
	}
}
