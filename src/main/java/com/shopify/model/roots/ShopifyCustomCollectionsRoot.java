package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyCustomCollection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyCustomCollectionsRoot {

	@XmlElement(name = "custom_collections")
	private List<ShopifyCustomCollection> customCollections = new LinkedList<ShopifyCustomCollection>();

	public List<ShopifyCustomCollection> getCustomCollections() {
		return customCollections;
	}

	public void setCustomCollections(List<ShopifyCustomCollection> customCollections) {
		this.customCollections = customCollections;
	}

	@Override
	public String toString() {
		return "ShopifyCustomCollectionsRoot{" +
				"customCollections=" + customCollections +
				'}';
	}
}
