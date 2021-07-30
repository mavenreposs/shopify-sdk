package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyVariant;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyVariantRoot {

	private ShopifyVariant variant;

	public ShopifyVariant getVariant() {
		return variant;
	}

	public void setVariant(ShopifyVariant variant) {
		this.variant = variant;
	}

	@Override
	public String toString() {
		return "ShopifyVariantRoot{" +
				"variant=" + variant +
				'}';
	}
}
