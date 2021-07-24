package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyClientDetail
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/24 17:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ShopifyClientDetail {
    @XmlElement(name = "accept_language")
    private String acceptLanguage;
    @XmlElement(name = "browser_height")
    private String browserHeight;
    @XmlElement(name = "browser_ip")
    private String browserIp;
    @XmlElement(name = "browser_width")
    private String browserWidth;
    @XmlElement(name = "session_hash")
    private String sessionHash;
    @XmlElement(name = "user_agent")
    private String userAgent;

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public String getBrowserHeight() {
        return browserHeight;
    }

    public void setBrowserHeight(String browserHeight) {
        this.browserHeight = browserHeight;
    }

    public String getBrowserIp() {
        return browserIp;
    }

    public void setBrowserIp(String browserIp) {
        this.browserIp = browserIp;
    }

    public String getBrowserWidth() {
        return browserWidth;
    }

    public void setBrowserWidth(String browserWidth) {
        this.browserWidth = browserWidth;
    }

    public String getSessionHash() {
        return sessionHash;
    }

    public void setSessionHash(String sessionHash) {
        this.sessionHash = sessionHash;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "ShopifyClientDetail{" +
                "acceptLanguage='" + acceptLanguage + '\'' +
                ", browserHeight='" + browserHeight + '\'' +
                ", browserIp='" + browserIp + '\'' +
                ", browserWidth='" + browserWidth + '\'' +
                ", sessionHash='" + sessionHash + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
