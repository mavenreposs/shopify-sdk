package com.shopify.model.roots;

import com.shopify.model.structs.ShopifyOrder;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShopifyOrdersRoot {

	private List<ShopifyOrder> orders = new LinkedList<>();

	public List<ShopifyOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ShopifyOrder> orders) {
		this.orders = orders;
	}

}
