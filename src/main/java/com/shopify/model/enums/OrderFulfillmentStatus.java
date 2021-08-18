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

    OrderFulfillmentStatus(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
