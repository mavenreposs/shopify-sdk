package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.ShopifyTransaction;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyTransactionsRoot {

	private List<ShopifyTransaction> transactions = new LinkedList<>();

	public List<ShopifyTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(final List<ShopifyTransaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "ShopifyTransactionsRoot{" +
				"transactions=" + transactions +
				'}';
	}
}
