package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyInventoryLevel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyInventoryLevelRoot {

	@XmlElement(name = "inventory_level")
	private ShopifyInventoryLevel inventoryLevel;

	public ShopifyInventoryLevel getInventoryLevel() {
		return inventoryLevel;
	}

	public void setInventoryLevel(final ShopifyInventoryLevel inventoryLevel) {
		this.inventoryLevel = inventoryLevel;
	}

	@Override
	public String toString() {
		return "ShopifyInventoryLevelRoot{" +
				"inventoryLevel=" + inventoryLevel +
				'}';
	}
}
