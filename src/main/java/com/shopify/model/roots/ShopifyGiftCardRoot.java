package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyGiftCard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyGiftCardRoot {
	@XmlElement(name = "gift_card")
	private ShopifyGiftCard giftCard;

	public ShopifyGiftCard getGiftCard() {
		return giftCard;
	}

	public void setGiftCard(final ShopifyGiftCard giftCard) {
		this.giftCard = giftCard;
	}

	@Override
	public String toString() {
		return "ShopifyGiftCardRoot{" +
				"giftCard=" + giftCard +
				'}';
	}
}
