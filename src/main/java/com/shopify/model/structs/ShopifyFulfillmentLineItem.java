package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ClassName ShopifyFulfillmentLineItem
 * Create by zhengdong.wang@bloomchic.tech
 * Date 2021/8/19 09:56
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyFulfillmentLineItem {

    private long id;
    @XmlElement(name = "variant_id")
    private String variantId;
    private String title;
    private long quantity;
    private String price;
    private long grams;
    private String sku;
    @XmlElement(name = "variant_title")
    private String variantTitle;
    private String vendor;
    @XmlElement(name = "fulfillment_service")
    private String fulfillmentService;
    @XmlElement(name = "product_id")
    private long productId;
    @XmlElement(name = "requires_shipping")
    private boolean requiresShipping;
    private boolean taxable;
    @XmlElement(name = "gift_card")
    private boolean giftCard;
    private String name;
    @XmlElement(name = "variant_inventory_management")
    private String variantInventoryManagement;
    private List<ShopifyProperty> properties;
    @XmlElement(name = "product_exists")
    private boolean productExists;
    @XmlElement(name = "fulfillable_quantity")
    private int fulfillableQuantity;
    @XmlElement(name = "total_discount")
    private String totalDiscount;
    @XmlElement(name = "fulfillment_status")
    private String fulfillmentStatus;
    @XmlElement(name = "tax_lines")
    private List<ShopifyTaxLine> taxLines;
    private List<Object> duties;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getGrams() {
        return grams;
    }

    public void setGrams(long grams) {
        this.grams = grams;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getVariantTitle() {
        return variantTitle;
    }

    public void setVariantTitle(String variantTitle) {
        this.variantTitle = variantTitle;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getFulfillmentService() {
        return fulfillmentService;
    }

    public void setFulfillmentService(String fulfillmentService) {
        this.fulfillmentService = fulfillmentService;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean isRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public boolean isGiftCard() {
        return giftCard;
    }

    public void setGiftCard(boolean giftCard) {
        this.giftCard = giftCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariantInventoryManagement() {
        return variantInventoryManagement;
    }

    public void setVariantInventoryManagement(String variantInventoryManagement) {
        this.variantInventoryManagement = variantInventoryManagement;
    }

    public List<ShopifyProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ShopifyProperty> properties) {
        this.properties = properties;
    }

    public boolean isProductExists() {
        return productExists;
    }

    public void setProductExists(boolean productExists) {
        this.productExists = productExists;
    }

    public int getFulfillableQuantity() {
        return fulfillableQuantity;
    }

    public void setFulfillableQuantity(int fulfillableQuantity) {
        this.fulfillableQuantity = fulfillableQuantity;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getFulfillmentStatus() {
        return fulfillmentStatus;
    }

    public void setFulfillmentStatus(String fulfillmentStatus) {
        this.fulfillmentStatus = fulfillmentStatus;
    }

    public List<ShopifyTaxLine> getTaxLines() {
        return taxLines;
    }

    public void setTaxLines(List<ShopifyTaxLine> taxLines) {
        this.taxLines = taxLines;
    }

    public List<Object> getDuties() {
        return duties;
    }

    public void setDuties(List<Object> duties) {
        this.duties = duties;
    }

    @Override
    public String toString() {
        return "ShopifyFulfillmentLineItem{" +
                "id=" + id +
                ", variantId='" + variantId + '\'' +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", price='" + price + '\'' +
                ", grams=" + grams +
                ", sku='" + sku + '\'' +
                ", variantTitle='" + variantTitle + '\'' +
                ", vendor='" + vendor + '\'' +
                ", fulfillmentService='" + fulfillmentService + '\'' +
                ", productId=" + productId +
                ", requiresShipping=" + requiresShipping +
                ", taxable=" + taxable +
                ", giftCard=" + giftCard +
                ", name='" + name + '\'' +
                ", variantInventoryManagement='" + variantInventoryManagement + '\'' +
                ", properties=" + properties +
                ", productExists=" + productExists +
                ", fulfillableQuantity=" + fulfillableQuantity +
                ", totalDiscount='" + totalDiscount + '\'' +
                ", fulfillmentStatus='" + fulfillmentStatus + '\'' +
                ", taxLines=" + taxLines +
                ", duties=" + duties +
                '}';
    }
}
