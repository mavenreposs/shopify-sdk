package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyDiscountApplication
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/7/24 18:18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyDiscountApplication {

    private String code;
    private String title;
    private String description;
    @XmlElement(name = "target_type")
    private String targetType;
    private String type;
    private String value;
    @XmlElement(name = "value_type")
    private String valueType;
    @XmlElement(name = "allocation_method")
    private String allocationMethod;
    @XmlElement(name = "target_selection")
    private String targetSelection;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getAllocationMethod() {
        return allocationMethod;
    }

    public void setAllocationMethod(String allocationMethod) {
        this.allocationMethod = allocationMethod;
    }

    public String getTargetSelection() {
        return targetSelection;
    }

    public void setTargetSelection(String targetSelection) {
        this.targetSelection = targetSelection;
    }

    @Override
    public String toString() {
        return "ShopifyDiscountApplication{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", targetType='" + targetType + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", valueType='" + valueType + '\'' +
                ", allocationMethod='" + allocationMethod + '\'' +
                ", targetSelection='" + targetSelection + '\'' +
                '}';
    }
}
