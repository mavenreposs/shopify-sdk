package com.shopify.model.roots;

import com.shopify.model.structs.ShopifyProduct;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShopifyProductsRoot {

	private List<ShopifyProduct> products = new LinkedList<ShopifyProduct>();

	public List<ShopifyProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ShopifyProduct> products) {
		this.products = products;
	}
}
