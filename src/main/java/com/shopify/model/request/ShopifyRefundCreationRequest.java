package com.shopify.model.request;

import com.shopify.model.structs.ShopifyRefund;

public class ShopifyRefundCreationRequest {

	private ShopifyRefund request;

	public ShopifyRefund getRequest() {
		return request;
	}

	public void setRequest(final ShopifyRefund request) {
		this.request = request;
	}

}
