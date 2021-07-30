package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.Metafield;

import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class MetafieldRoot {

	private Metafield metafield;

	public Metafield getMetafield() {
		return metafield;
	}

	public void setMetafield(Metafield metafield) {
		this.metafield = metafield;
	}

	@Override
	public String toString() {
		return "MetafieldRoot{" +
				"metafield=" + metafield +
				'}';
	}
}
