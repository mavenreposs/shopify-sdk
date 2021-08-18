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

    OrderStatus(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
