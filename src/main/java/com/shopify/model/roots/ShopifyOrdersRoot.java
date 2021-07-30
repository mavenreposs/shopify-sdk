package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyOrder;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyOrdersRoot {

	private List<ShopifyOrder> orders = new LinkedList<>();

	public List<ShopifyOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ShopifyOrder> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "ShopifyOrdersRoot{" +
				"orders=" + orders +
				'}';
	}
}
