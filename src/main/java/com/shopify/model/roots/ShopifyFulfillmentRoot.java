package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.ShopifyFulfillment;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyFulfillmentRoot {

	private ShopifyFulfillment fulfillment;

	public ShopifyFulfillment getFulfillment() {
		return fulfillment;
	}

	public void setFulfillment(ShopifyFulfillment fulfillment) {
		this.fulfillment = fulfillment;
	}

	@Override
	public String toString() {
		return "ShopifyFulfillmentRoot{" +
				"fulfillment=" + fulfillment +
				'}';
	}
}
