package com.shopify.model.enums;

public enum WeightUnit {

	G("g"),
	KG("kg"),
	OZ("oz"),
	LB("lb");

	private final String value;

	static final String NO_MATCHING_ENUMS_ERROR_MESSAGE = "No matching enum found for %s";

	private WeightUnit(final String value) {
		this.value = value;
	}

	public static WeightUnit toEnum(String value) {
		if (G.toString().equals(value)) {
			return WeightUnit.G;
		} else if (KG.toString().equals(value)) {
			return WeightUnit.KG;
		} else if (OZ.toString().equals(value)) {
			return WeightUnit.OZ;
		} else if (LB.toString().equals(value)) {
			return WeightUnit.LB;
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
	public boolean matching(WeightUnit enumObject) {
		return this.equals(enumObject);
	}

}
