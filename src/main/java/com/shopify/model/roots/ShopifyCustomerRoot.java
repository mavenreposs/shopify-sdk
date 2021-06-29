package com.shopify.model.roots;

import com.shopify.model.structs.ShopifyCustomer;

public class ShopifyCustomerRoot {

	private ShopifyCustomer customer;

	public ShopifyCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(final ShopifyCustomer customer) {
		this.customer = customer;
	}

}
