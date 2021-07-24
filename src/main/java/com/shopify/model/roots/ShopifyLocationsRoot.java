package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.ShopifyLocation;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyLocationsRoot {

	private List<ShopifyLocation> locations = new LinkedList<>();

	public List<ShopifyLocation> getLocations() {
		return locations;
	}

	public void setLocations(final List<ShopifyLocation> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "ShopifyLocationsRoot{" +
				"locations=" + locations +
				'}';
	}
}
