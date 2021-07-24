package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopify.model.structs.Metafield;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class MetafieldsRoot {

	private List<Metafield> metafields = new LinkedList<>();

	public List<Metafield> getMetafields() {
		return metafields;
	}

	public void setMetafields(List<Metafield> metafields) {
		this.metafields = metafields;
	}

	@Override
	public String toString() {
		return "MetafieldsRoot{" +
				"metafields=" + metafields +
				'}';
	}
}
