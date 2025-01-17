package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyInventoryLevel {

	@XmlElement(name = "inventory_item_id")
	private String inventoryItemId;

	@XmlElement(name = "location_id")
	private String locationId;

	private long available;

	@XmlElement(name = "updated_at")
	private String updatedAt;

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(final String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(final String locationId) {
		this.locationId = locationId;
	}

	public long getAvailable() {
		return available;
	}

	public void setAvailable(final long available) {
		this.available = available;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "ShopifyInventoryLevel{" +
				"inventoryItemId='" + inventoryItemId + '\'' +
				", locationId='" + locationId + '\'' +
				", available=" + available +
				", updatedAt='" + updatedAt + '\'' +
				'}';
	}
}
