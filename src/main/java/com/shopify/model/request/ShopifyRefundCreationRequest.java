package com.shopify.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.ShopifyRefund;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyRefundCreationRequest {

	private ShopifyRefund request;

	public ShopifyRefund getRequest() {
		return request;
	}

	public void setRequest(final ShopifyRefund request) {
		this.request = request;
	}

}
