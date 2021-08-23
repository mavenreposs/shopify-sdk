package com.shopify.model.enums;

public enum FulfillmentService {

	MANUAL("manual");

	private final String value;

	static final String NO_MATCHING_ENUMS_ERROR_MESSAGE = "No matching enum found for %s";

	private FulfillmentService(final String value) {
		this.value = value;
	}

	public static FulfillmentService toEnum(final String value) {
		if (MANUAL.toString().equals(value)) {
			return FulfillmentService.MANUAL;
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
	public boolean matching(FulfillmentService enumObject) {
		return this.equals(enumObject);
	}

}
