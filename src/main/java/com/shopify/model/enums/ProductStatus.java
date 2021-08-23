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

	static final String NO_MATCHING_ENUMS_ERROR_MESSAGE = "No matching enum found for %s";

	private ProductStatus(final String value) {
		this.value = value;
	}

	public static ProductStatus toEnum(String value) {
		if (ACTIVE.toString().equals(value)) {
			return ProductStatus.ACTIVE;
		} else if (ARCHIVED.toString().equals(value)) {
			return ProductStatus.ARCHIVED;
		} else if (DRAFT.toString().equals(value)) {
			return ProductStatus.DRAFT;
		}
		throw new IllegalArgumentException(String.format(NO_MATCHING_ENUMS_ERROR_MESSAGE, value));
	}

	@Override
	public String toString() {
		return value;
	}

	/**
	 * Enum Object matching
	 * @param enumObject Enum Object
	 * @return boolean
	 */
	public boolean matching(ProductStatus enumObject) {
		return this.equals(enumObject);
	}

}
