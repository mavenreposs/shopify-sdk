package com.shopify.model.roots;

import com.shopify.model.structs.ShopifyVariant;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShopifyVariantRoot {

	private ShopifyVariant variant;

	public ShopifyVariant getVariant() {
		return variant;
	}

	public void setVariant(ShopifyVariant variant) {
		this.variant = variant;
	}
}
