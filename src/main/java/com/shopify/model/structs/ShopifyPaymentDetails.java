package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyPaymentDetails
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/24 18:23
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyPaymentDetails {
    @XmlElement(name = "credit_card_bin")
    private String creditCardBin;
    @XmlElement(name = "avs_result_code")
    private String avsResultCode;
    @XmlElement(name = "cvv_result_code")
    private String cvvResultCode;
    @XmlElement(name = "credit_card_number")
    private String creditCardNumber;
    @XmlElement(name = "credit_card_company")
    private String creditCardCompany;
    @XmlElement(name = "credit_card_name")
    private String creditCardName;
    @XmlElement(name = "credit_card_wallet")
    private String creditCardWallet;
    @XmlElement(name = "credit_card_expiration_month")
    private String creditCardExpirationMonth;
    @XmlElement(name = "credit_card_expiration_year")
    private String creditCardExpirationYear;

    public String getCreditCardBin() {
        return creditCardBin;
    }

    public void setCreditCardBin(String creditCardBin) {
        this.creditCardBin = creditCardBin;
    }

    public String getAvsResultCode() {
        return avsResultCode;
    }

    public void setAvsResultCode(String avsResultCode) {
        this.avsResultCode = avsResultCode;
    }

    public String getCvvResultCode() {
        return cvvResultCode;
    }

    public void setCvvResultCode(String cvvResultCode) {
        this.cvvResultCode = cvvResultCode;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardCompany() {
        return creditCardCompany;
    }

    public void setCreditCardCompany(String creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardWallet() {
        return creditCardWallet;
    }

    public void setCreditCardWallet(String creditCardWallet) {
        this.creditCardWallet = creditCardWallet;
    }

    public String getCreditCardExpirationMonth() {
        return creditCardExpirationMonth;
    }

    public void setCreditCardExpirationMonth(String creditCardExpirationMonth) {
        this.creditCardExpirationMonth = creditCardExpirationMonth;
    }

    public String getCreditCardExpirationYear() {
        return creditCardExpirationYear;
    }

    public void setCreditCardExpirationYear(String creditCardExpirationYear) {
        this.creditCardExpirationYear = creditCardExpirationYear;
    }

    @Override
    public String toString() {
        return "ShopifyPaymentDetails{" +
                "creditCardBin='" + creditCardBin + '\'' +
                ", avsResultCode='" + avsResultCode + '\'' +
                ", cvvResultCode='" + cvvResultCode + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardCompany='" + creditCardCompany + '\'' +
                ", creditCardName='" + creditCardName + '\'' +
                ", creditCardWallet='" + creditCardWallet + '\'' +
                ", creditCardExpirationMonth='" + creditCardExpirationMonth + '\'' +
                ", creditCardExpirationYear='" + creditCardExpirationYear + '\'' +
                '}';
    }
}
