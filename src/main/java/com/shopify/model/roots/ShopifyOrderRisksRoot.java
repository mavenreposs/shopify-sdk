package com.shopify.model.roots;

import com.shopify.model.structs.ShopifyOrderRisk;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShopifyOrderRisksRoot {

	private List<ShopifyOrderRisk> risks = new LinkedList<>();

	public List<ShopifyOrderRisk> getRisks() {
		return risks;
	}

	public void setRisks(List<ShopifyOrderRisk> risks) {
		this.risks = risks;
	}

}
