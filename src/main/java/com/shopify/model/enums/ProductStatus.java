package com.shopify.model.enums;

public enum ProductStatus {

	/**
	 * The product is ready to sell and is available to customers on the online store, sales channels, and apps.
	 * By default, existing products are set to active.
	 */
	ACTIVE("active"),

	/**
	 * The product is no longer being sold and isn't available to customers on sales channels and apps.
	 */
	ARCHIVED("archived"),

	/**
	 * The product isn't ready to sell and is unavailable to customers on sales channels and apps.
	 * By default, duplicated and unarchived products are set to draft.
	 */
	DRAFT("draft");

	private final String value;

	private ProductStatus(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}
