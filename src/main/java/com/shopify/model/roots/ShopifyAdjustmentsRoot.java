package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.ShopifyAdjustment;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyAdjustmentsRoot {

	private List<ShopifyAdjustment> adjustments = new LinkedList<>();

	public List<ShopifyAdjustment> getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(final List<ShopifyAdjustment> adjustments) {
		this.adjustments = adjustments;
	}
}
