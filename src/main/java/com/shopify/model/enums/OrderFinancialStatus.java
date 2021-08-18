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

    OrderFinancialStatus(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
