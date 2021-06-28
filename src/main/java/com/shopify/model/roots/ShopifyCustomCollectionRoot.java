package com.shopify.model.roots;

import com.shopify.model.ShopifyCustomCollection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShopifyCustomCollectionRoot {

	@XmlElement(name = "custom_collection")
	private ShopifyCustomCollection customCollection;

	public ShopifyCustomCollection getCustomCollection() {
		return customCollection;
	}

	public void setCustomCollection(ShopifyCustomCollection customCollection) {
		this.customCollection = customCollection;
	}
}
