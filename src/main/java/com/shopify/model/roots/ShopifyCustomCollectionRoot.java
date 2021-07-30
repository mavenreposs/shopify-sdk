package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyCustomCollection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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

	@Override
	public String toString() {
		return "ShopifyCustomCollectionRoot{" +
				"customCollection=" + customCollection +
				'}';
	}
}
