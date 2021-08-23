package com.shopify.model.enums;

/**
 * Filter orders by their fulfillment status.
 */
public enum OrderFulfillmentStatus {

    /**
     * Show orders that have been shipped. Returns orders with fulfillment_status of fulfilled.
     */
    SHIPPED("shipped"),

    /**
     * Show partially shipped orders.
     */
    PARTIAL("partial"),

    /**
     * Show orders that have not yet been shipped. Returns orders with fulfillment_status of null.
     */
    UNSHIPPED("unshipped"),

    /**
     * Show orders of any fulfillment status.
     * default: any
     */
    ANY("any"),

    /**
     * Returns orders with fulfillment_status of null or partial.
     */
    UNFULFILLED("unfulfilled"),
    ;

    private final String value;

    static final String NO_MATCHING_ENUMS_ERROR_MESSAGE = "No matching enum found for %s";

    OrderFulfillmentStatus(final String value) {
        this.value = value;
    }

    public static OrderFulfillmentStatus toEnum(String value) {
        if (SHIPPED.toString().equals(value)) {
            return OrderFulfillmentStatus.SHIPPED;
        } else if (PARTIAL.toString().equals(value)) {
            return OrderFulfillmentStatus.PARTIAL;
        } else if (UNSHIPPED.toString().equals(value)) {
            return OrderFulfillmentStatus.UNSHIPPED;
        } else if (ANY.toString().equals(value)) {
            return OrderFulfillmentStatus.ANY;
        } else if (UNFULFILLED.toString().equals(value)) {
            return OrderFulfillmentStatus.UNFULFILLED;
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
    public boolean matching(OrderFulfillmentStatus enumObject) {
        return this.equals(enumObject);
    }

}
