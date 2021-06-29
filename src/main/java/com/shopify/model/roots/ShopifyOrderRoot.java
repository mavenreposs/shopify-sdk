package com.shopify.model.roots;

import com.shopify.model.structs.ShopifyOrder;

public class ShopifyOrderRoot {

	private ShopifyOrder order;

	public ShopifyOrder getOrder() {
		return order;
	}

	public void setOrder(ShopifyOrder order) {
		this.order = order;
	}

}
