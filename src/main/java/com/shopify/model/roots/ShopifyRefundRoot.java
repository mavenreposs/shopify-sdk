package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyRefund;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyRefundRoot {

	private ShopifyRefund refund;

	public ShopifyRefund getRefund() {
		return refund;
	}

	public void setRefund(final ShopifyRefund refund) {
		this.refund = refund;
	}

	@Override
	public String toString() {
		return "ShopifyRefundRoot{" +
				"refund=" + refund +
				'}';
	}
}
