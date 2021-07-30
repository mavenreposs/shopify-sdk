package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyProduct;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyProductsRoot {

	private List<ShopifyProduct> products = new LinkedList<ShopifyProduct>();

	public List<ShopifyProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ShopifyProduct> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ShopifyProductsRoot{" +
				"products=" + products +
				'}';
	}
}
