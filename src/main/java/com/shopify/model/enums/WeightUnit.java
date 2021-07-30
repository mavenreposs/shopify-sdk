package com.shopify.model.enums;

public enum WeightUnit {

	G("g"),
	KG("kg"),
	OZ("oz"),
	LB("lb");

	private final String value;

	private WeightUnit(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}
