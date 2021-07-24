package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyAccessTokenRoot {

	private String accessToken;

	@XmlAttribute(name = "access_token")
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "ShopifyAccessTokenRoot{" +
				"accessToken='" + accessToken + '\'' +
				'}';
	}
}
