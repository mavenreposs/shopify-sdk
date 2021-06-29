package com.shopify.model.roots;

import com.shopify.model.structs.Shop;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShopifyShopRoot {

	private Shop shop;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
