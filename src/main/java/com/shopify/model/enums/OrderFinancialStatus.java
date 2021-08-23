package com.shopify.model.enums;

/**
 * Created with IntelliJ IDEA.
 * ClassName OrderFinancialStatus
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/18 10:21
 *
 * <p>
 *     Filter orders by their financial status.
 * </p>
 */
public enum OrderFinancialStatus {
    /**
     * Show only authorized orders
     */
    AUTHORIZED("authorized"),

    /**
     * Show only pending orders
     */
    PENDING("pending"),

    /**
     * Show only paid orders
     */
    PAID("paid"),

    /**
     * Show only partially paid orders
     */
    PARTIALLY_PAID("partially_paid"),

    /**
     * Show only refunded orders
     */
    REFUNDED("refunded"),

    /**
     * Show only voided orders
     */
    VOIDED("voided"),

    /**
     * Show only partially refunded orders
     */
    PARTIALLY_REFUNDED("partially_refunded"),

    /**
     * Show orders of any financial status.
     */
    ANY("any"),

    /**
     * Show authorized and partially paid orders.
     */
    UNPAID("unpaid"),
    ;

    private final String value;

    static final String NO_MATCHING_ENUMS_ERROR_MESSAGE = "No matching enum found for %s";

    OrderFinancialStatus(final String value) {
        this.value = value;
    }

    public static OrderFinancialStatus toEnum(String value) {
        if (AUTHORIZED.toString().equals(value)) {
            return OrderFinancialStatus.AUTHORIZED;
        } else if (PENDING.toString().equals(value)) {
            return OrderFinancialStatus.PENDING;
        } else if (PAID.toString().equals(value)) {
            return OrderFinancialStatus.PAID;
        } else if (PARTIALLY_PAID.toString().equals(value)) {
            return OrderFinancialStatus.PARTIALLY_PAID;
        } else if (REFUNDED.toString().equals(value)) {
            return OrderFinancialStatus.REFUNDED;
        } else if (VOIDED.toString().equals(value)) {
            return OrderFinancialStatus.VOIDED;
        } else if (PARTIALLY_REFUNDED.toString().equals(value)) {
            return OrderFinancialStatus.PARTIALLY_REFUNDED;
        } else if (ANY.toString().equals(value)) {
            return OrderFinancialStatus.ANY;
        } else if (UNPAID.toString().equals(value)) {
            return OrderFinancialStatus.UNPAID;
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
    public boolean matching(OrderFinancialStatus enumObject) {
        return this.equals(enumObject);
    }

}
