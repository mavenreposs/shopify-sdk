package com.shopify.model.structs;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.adapters.EscapedStringAdapter;
import com.shopify.model.adapters.InventoryPolicyAdapter;
import com.shopify.model.enums.InventoryPolicy;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyVariant {

	private String id;
	@XmlElement(name = "product_id")
	private String productId;
	@XmlJavaTypeAdapter(EscapedStringAdapter.class)
	private String title;
	private BigDecimal price;
	@XmlElement(name = "compare_at_price")
	private BigDecimal compareAtPrice;
	private String sku;
	private String barcode;
	private Integer position;
	private Long grams;
	@XmlTransient
	private Long inventoryQuantity;
	@XmlElement(name = "image_id")
	private String imageId;
	@XmlJavaTypeAdapter(InventoryPolicyAdapter.class)
	@XmlElement(name = "inventory_policy")
	private InventoryPolicy inventoryPolicy;
	@XmlElement(name = "inventory_management")
	private String inventoryManagement;
	@XmlJavaTypeAdapter(EscapedStringAdapter.class)
	private String option1;
	@XmlJavaTypeAdapter(EscapedStringAdapter.class)
	private String option2;
	@XmlJavaTypeAdapter(EscapedStringAdapter.class)
	private String option3;
	@XmlElement(name = "fulfillment_service")
	private String fulfillmentService;
	@XmlElement(name = "requires_shipping")
	private Boolean requiresShipping;
	private Boolean taxable;

	@XmlElement(name = "inventory_item_id")
	private String inventoryItemId;

	@XmlTransient
	private Long available;

	private Integer weight;
	@XmlElement(name = "weight_unit")
	private String weightUnit;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCompareAtPrice() {
		return compareAtPrice;
	}

	public void setCompareAtPrice(final BigDecimal compareAtPrice) {
		this.compareAtPrice = compareAtPrice;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(final String sku) {
		this.sku = sku;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(final String barcode) {
		this.barcode = barcode;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(final Integer position) {
		this.position = position;
	}

	public Long getGrams() {
		return grams;
	}

	public void setGrams(final Long grams) {
		this.grams = grams;
	}

	public Long getInventoryQuantity() {
		return inventoryQuantity;
	}

	@XmlElement(name = "inventory_quantity")
	public void setInventoryQuantity(final Long inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(final String imageId) {
		this.imageId = imageId;
	}

	public InventoryPolicy getInventoryPolicy() {
		return inventoryPolicy;
	}

	public void setInventoryPolicy(final InventoryPolicy inventoryPolicy) {
		this.inventoryPolicy = inventoryPolicy;
	}

	public String getInventoryManagement() {
		return inventoryManagement;
	}

	public void setInventoryManagement(final String inventoryManagement) {
		this.inventoryManagement = inventoryManagement;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(final String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(final String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(final String option3) {
		this.option3 = option3;
	}

	public String getFulfillmentService() {
		return fulfillmentService;
	}

	public void setFulfillmentService(final String fulfillmentService) {
		this.fulfillmentService = fulfillmentService;
	}

	public Boolean isRequiresShipping() {
		return requiresShipping;
	}

	public void setRequiresShipping(final Boolean requiresShipping) {
		this.requiresShipping = requiresShipping;
	}

	public Boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(final Boolean taxable) {
		this.taxable = taxable;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(final String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public Long getAvailable() {
		return available;
	}

	public void setAvailable(final Long available) {
		this.available = available;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	@Override
	public String toString() {
		return "ShopifyVariant{" +
				"id='" + id + '\'' +
				", productId='" + productId + '\'' +
				", title='" + title + '\'' +
				", price=" + price +
				", compareAtPrice=" + compareAtPrice +
				", sku='" + sku + '\'' +
				", barcode='" + barcode + '\'' +
				", position=" + position +
				", grams=" + grams +
				", inventoryQuantity=" + inventoryQuantity +
				", imageId='" + imageId + '\'' +
				", inventoryPolicy=" + inventoryPolicy +
				", inventoryManagement='" + inventoryManagement + '\'' +
				", option1='" + option1 + '\'' +
				", option2='" + option2 + '\'' +
				", option3='" + option3 + '\'' +
				", fulfillmentService='" + fulfillmentService + '\'' +
				", requiresShipping=" + requiresShipping +
				", taxable=" + taxable +
				", inventoryItemId='" + inventoryItemId + '\'' +
				", available=" + available +
				", weight=" + weight +
				", weightUnit='" + weightUnit + '\'' +
				'}';
	}
}
