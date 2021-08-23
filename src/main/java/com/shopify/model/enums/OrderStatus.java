package com.shopify.model.enums;

/**
 * Created with IntelliJ IDEA.
 * ClassName OrderStatus
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/18 10:17
 */
public enum OrderStatus {
    /**
     * Show only open orders.
     * default: open
     */
    OPEN("open"),

    /**
     * Show only closed orders.
     */
    CLOSED("closed"),

    /**
     * Show only canceled orders.
     */
    CANCELLED("cancelled"),

    /**
     * Show orders of any status, including archived orders.
     */
    ANY("any"),
    ;

    private final String value;

    static final String NO_MATCHING_ENUMS_ERROR_MESSAGE = "No matching enum found for %s";

    OrderStatus(final String value) {
        this.value = value;
    }

    public static OrderStatus toEnum(String value) {
        if (OPEN.toString().equals(value)) {
            return OrderStatus.OPEN;
        } else if (CLOSED.toString().equals(value)) {
            return OrderStatus.CLOSED;
        } else if (CANCELLED.toString().equals(value)) {
            return OrderStatus.CANCELLED;
        } else if (ANY.toString().equals(value)) {
            return OrderStatus.ANY;
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
    public boolean matching(OrderStatus enumObject) {
        return this.equals(enumObject);
    }

}
