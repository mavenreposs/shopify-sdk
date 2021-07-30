package com.shopify.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
