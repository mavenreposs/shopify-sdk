package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.ShopifyProduct;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyProductRoot {

	private ShopifyProduct product;

	public ShopifyProduct getProduct() {
		return product;
	}

	public void setProduct(ShopifyProduct product) {
		this.product = product;
	}

}
