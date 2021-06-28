package com.shopify.model;

import com.shopify.model.request.ShopifyOrderShippingAddressUpdateRequest;

public class ShopifyOrderUpdateRoot {

	private ShopifyOrderShippingAddressUpdateRequest order;

	public ShopifyOrderShippingAddressUpdateRequest getOrder() {
		return order;
	}

	public void setOrder(final ShopifyOrderShippingAddressUpdateRequest order) {
		this.order = order;
	}

}
