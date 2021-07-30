package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyOrderRisk;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyOrderRisksRoot {

	private List<ShopifyOrderRisk> risks = new LinkedList<>();

	public List<ShopifyOrderRisk> getRisks() {
		return risks;
	}

	public void setRisks(List<ShopifyOrderRisk> risks) {
		this.risks = risks;
	}

	@Override
	public String toString() {
		return "ShopifyOrderRisksRoot{" +
				"risks=" + risks +
				'}';
	}
}
