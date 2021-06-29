package com.shopify.model.structs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Count {

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
