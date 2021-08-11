package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * https://shopify.dev/api/admin/rest/reference/inventory/location?api%5Bversion%5D=2021-04
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyLocation {

	private String id;
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String zip;
	private String phone;
	private String province;
	@XmlElement(name = "province_code")
	private String provinceCode;
	/**
	 * The localized name of the location's region. Typically a province, state, or prefecture.
	 */
	@XmlElement(name = "localized_province_name")
	private String localizedProvinceName;
	private String country;
	@XmlElement(name = "country_code")
	private String countryCode;
	/**
	 * The localized name of the location's country.
	 */
	@XmlElement(name = "localized_country_name")
	private String localizedCountryName;

	/**
	 * Whether the location is active. If true, then the location can be used to sell products, stock inventory, and fulfill orders.
	 * Merchants can deactivate locations from the Shopify admin.
	 * Deactivated locations don't contribute to the shop's location limit.
	 */
	private boolean active;
	@XmlElement(name = "created_at")
	private String createdAt;
	@XmlElement(name = "updated_at")
	private String updatedAt;
	private boolean legacy;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}

	public String getPhone() {
		return phone;
	}

	public String getProvince() {
		return province;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getLocalizedProvinceName() {
		return localizedProvinceName;
	}

	public String getLocalizedCountryName() {
		return localizedCountryName;
	}

	public boolean isActive() {
		return active;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public boolean isLegacy() {
		return legacy;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setZip(final String zip) {
		this.zip = zip;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setProvince(final String province) {
		this.province = province;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public void setProvinceCode(final String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public void setLocalizedProvinceName(String localizedProvinceName) {
		this.localizedProvinceName = localizedProvinceName;
	}

	public void setLocalizedCountryName(String localizedCountryName) {
		this.localizedCountryName = localizedCountryName;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}

	@Override
	public String toString() {
		return "ShopifyLocation{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", address1='" + address1 + '\'' +
				", address2='" + address2 + '\'' +
				", city='" + city + '\'' +
				", zip='" + zip + '\'' +
				", phone='" + phone + '\'' +
				", province='" + province + '\'' +
				", provinceCode='" + provinceCode + '\'' +
				", localizedProvinceName='" + localizedProvinceName + '\'' +
				", country='" + country + '\'' +
				", countryCode='" + countryCode + '\'' +
				", localizedCountryName='" + localizedCountryName + '\'' +
				", active=" + active +
				", createdAt='" + createdAt + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				", legacy=" + legacy +
				'}';
	}
}
