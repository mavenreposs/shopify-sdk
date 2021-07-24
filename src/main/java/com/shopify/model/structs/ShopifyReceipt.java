package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyReceipt
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/24 18:27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyReceipt {
    private boolean testcase;
    private String authorization;
    @XmlElement(name = "paid_amount")
    private String paid_amount;

    public boolean isTestcase() {
        return testcase;
    }

    public void setTestcase(boolean testcase) {
        this.testcase = testcase;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    @Override
    public String toString() {
        return "ShopifyReceipt{" +
                "testcase=" + testcase +
                ", authorization='" + authorization + '\'' +
                ", paid_amount='" + paid_amount + '\'' +
                '}';
    }
}
