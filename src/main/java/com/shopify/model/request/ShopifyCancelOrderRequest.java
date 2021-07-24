package com.shopify.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyCancelOrderRequest {

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
