package com.shopify.model.structs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class Shop {

	private Long id;
	private String name;
	private String email;
	private String domain;
	private String province;
	private String country;
	private String address1;
	private String zip;
	private String city;
	private String source;
	private String phone;
	private String latitude;
	private String longitude;
	@XmlElement(name = "primary_locale")
	private String primaryLocale;
	private String address2;
	@XmlElement(name = "created_at")
	private String createdAt;
	@XmlElement(name = "updated_at")
	private String updatedAt;
	@XmlElement(name = "country_code")
	private String countryCode;
	@XmlElement(name = "country_name")
	private String countryName;
	@XmlElement(name = "customer_email")
	private String customerEmail;
	private String currency;
	private String timezone;
	@XmlElement(name = "iana_timezone")
	private String ianaTimezone;
	@XmlElement(name = "shop_owner")
	private String shopOwner;
	@XmlElement(name = "money_format")
	private String moneyFormat;
	@XmlElement(name = "money_with_currency_format")
	private String moneyWithCurrencyFormat;
	@XmlElement(name = "weight_unit")
	private String weightUnit;
	@XmlElement(name = "province_code")
	private String provinceCode;
	@XmlElement(name = "force_ssl")
	private boolean forceSsl;
	@XmlElement(name = "taxes_included")
	private boolean taxesIncluded;
	@XmlElement(name = "auto_configure_tax_inclusivity")
	private String autoConfigureTaxInclusivity;
	@XmlElement(name = "tax_shipping")
	private String taxShipping;
	@XmlElement(name = "county_taxes")
	private boolean countyTaxes;
	@XmlElement(name = "plan_display_name")
	private String planDisplayName;
	@XmlElement(name = "plan_name")
	private String planName;
	@XmlElement(name = "has_discounts")
	private boolean hasDiscounts;
	@XmlElement(name = "has_gift_cards")
	private boolean hasGiftCards;
	@XmlElement(name = "myshopify_domain")
	private String myshopifyDomain;
	@XmlElement(name = "google_apps_domain")
	private String googleAppsDomain;
	@XmlElement(name = "google_apps_login_enabled")
	private String googleAppsLoginEnabled;
	@XmlElement(name = "money_in_emails_format")
	private String moneyInEmailsFormat;
	@XmlElement(name = "money_with_currency_in_emails_format")
	private String moneyWithCurrencyInEmailsFormat;
	@XmlElement(name = "eligible_for_payments")
	private boolean eligibleForPayments;
	@XmlElement(name = "requires_extra_payments_agreement")
	private boolean requiresExtraPaymentsAgreement;
	@XmlElement(name = "password_enabled")
	private boolean passwordEnabled;
	@XmlElement(name = "has_storefront")
	private boolean hasStorefront;
	@XmlElement(name = "eligible_for_card_reader_giveaway")
	private boolean eligibleForCardReaderGiveaway;
	@XmlElement(name = "finances")
	private boolean finances;
	@XmlElement(name = "primary_location_id")
	private long primaryLocationId;
	@XmlElement(name = "cookie_consent_Level")
	private String cookieConsentLevel;
	@XmlElement(name = "visitor_tracking_consent_preference")
	private String visitorTrackingConsentPreference;
	@XmlElement(name = "checkout_api_supported")
	private boolean checkoutApiSupported;
	@XmlElement(name = "multi_location_enabled")
	private boolean multiLocationEnabled;
	@XmlElement(name = "setup_required")
	private boolean setupRequired;
	@XmlElement(name = "pre_launch_enabled")
	private boolean preLaunchEnabled;
	@XmlElement(name = "enabled_presentment_currencies")
	private List<String> enabledPresentmentCurrencies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrimaryLocale() {
		return primaryLocale;
	}

	public void setPrimaryLocale(String primaryLocale) {
		this.primaryLocale = primaryLocale;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getIanaTimezone() {
		return ianaTimezone;
	}

	public void setIanaTimezone(String ianaTimezone) {
		this.ianaTimezone = ianaTimezone;
	}

	public String getShopOwner() {
		return shopOwner;
	}

	public void setShopOwner(String shopOwner) {
		this.shopOwner = shopOwner;
	}

	public String getMoneyFormat() {
		return moneyFormat;
	}

	public void setMoneyFormat(String moneyFormat) {
		this.moneyFormat = moneyFormat;
	}

	public String getMoneyWithCurrencyFormat() {
		return moneyWithCurrencyFormat;
	}

	public void setMoneyWithCurrencyFormat(String moneyWithCurrencyFormat) {
		this.moneyWithCurrencyFormat = moneyWithCurrencyFormat;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public boolean isForceSsl() {
		return forceSsl;
	}

	public void setForceSsl(boolean forceSsl) {
		this.forceSsl = forceSsl;
	}

	public boolean isTaxesIncluded() {
		return taxesIncluded;
	}

	public void setTaxesIncluded(boolean taxesIncluded) {
		this.taxesIncluded = taxesIncluded;
	}

	public String getAutoConfigureTaxInclusivity() {
		return autoConfigureTaxInclusivity;
	}

	public void setAutoConfigureTaxInclusivity(String autoConfigureTaxInclusivity) {
		this.autoConfigureTaxInclusivity = autoConfigureTaxInclusivity;
	}

	public String getTaxShipping() {
		return taxShipping;
	}

	public void setTaxShipping(String taxShipping) {
		this.taxShipping = taxShipping;
	}

	public boolean isCountyTaxes() {
		return countyTaxes;
	}

	public void setCountyTaxes(boolean countyTaxes) {
		this.countyTaxes = countyTaxes;
	}

	public String getPlanDisplayName() {
		return planDisplayName;
	}

	public void setPlanDisplayName(String planDisplayName) {
		this.planDisplayName = planDisplayName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public boolean isHasDiscounts() {
		return hasDiscounts;
	}

	public void setHasDiscounts(boolean hasDiscounts) {
		this.hasDiscounts = hasDiscounts;
	}

	public boolean isHasGiftCards() {
		return hasGiftCards;
	}

	public void setHasGiftCards(boolean hasGiftCards) {
		this.hasGiftCards = hasGiftCards;
	}

	public String getMyshopifyDomain() {
		return myshopifyDomain;
	}

	public void setMyshopifyDomain(String myshopifyDomain) {
		this.myshopifyDomain = myshopifyDomain;
	}

	public String getGoogleAppsDomain() {
		return googleAppsDomain;
	}

	public void setGoogleAppsDomain(String googleAppsDomain) {
		this.googleAppsDomain = googleAppsDomain;
	}

	public String getGoogleAppsLoginEnabled() {
		return googleAppsLoginEnabled;
	}

	public void setGoogleAppsLoginEnabled(String googleAppsLoginEnabled) {
		this.googleAppsLoginEnabled = googleAppsLoginEnabled;
	}

	public String getMoneyInEmailsFormat() {
		return moneyInEmailsFormat;
	}

	public void setMoneyInEmailsFormat(String moneyInEmailsFormat) {
		this.moneyInEmailsFormat = moneyInEmailsFormat;
	}

	public String getMoneyWithCurrencyInEmailsFormat() {
		return moneyWithCurrencyInEmailsFormat;
	}

	public void setMoneyWithCurrencyInEmailsFormat(String moneyWithCurrencyInEmailsFormat) {
		this.moneyWithCurrencyInEmailsFormat = moneyWithCurrencyInEmailsFormat;
	}

	public boolean isEligibleForPayments() {
		return eligibleForPayments;
	}

	public void setEligibleForPayments(boolean eligibleForPayments) {
		this.eligibleForPayments = eligibleForPayments;
	}

	public boolean isRequiresExtraPaymentsAgreement() {
		return requiresExtraPaymentsAgreement;
	}

	public void setRequiresExtraPaymentsAgreement(boolean requiresExtraPaymentsAgreement) {
		this.requiresExtraPaymentsAgreement = requiresExtraPaymentsAgreement;
	}

	public boolean isPasswordEnabled() {
		return passwordEnabled;
	}

	public void setPasswordEnabled(boolean passwordEnabled) {
		this.passwordEnabled = passwordEnabled;
	}

	public boolean isHasStorefront() {
		return hasStorefront;
	}

	public void setHasStorefront(boolean hasStorefront) {
		this.hasStorefront = hasStorefront;
	}

	public boolean isEligibleForCardReaderGiveaway() {
		return eligibleForCardReaderGiveaway;
	}

	public void setEligibleForCardReaderGiveaway(boolean eligibleForCardReaderGiveaway) {
		this.eligibleForCardReaderGiveaway = eligibleForCardReaderGiveaway;
	}

	public boolean isFinances() {
		return finances;
	}

	public void setFinances(boolean finances) {
		this.finances = finances;
	}

	public long getPrimaryLocationId() {
		return primaryLocationId;
	}

	public void setPrimaryLocationId(long primaryLocationId) {
		this.primaryLocationId = primaryLocationId;
	}

	public String getCookieConsentLevel() {
		return cookieConsentLevel;
	}

	public void setCookieConsentLevel(String cookieConsentLevel) {
		this.cookieConsentLevel = cookieConsentLevel;
	}

	public String getVisitorTrackingConsentPreference() {
		return visitorTrackingConsentPreference;
	}

	public void setVisitorTrackingConsentPreference(String visitorTrackingConsentPreference) {
		this.visitorTrackingConsentPreference = visitorTrackingConsentPreference;
	}

	public boolean isCheckoutApiSupported() {
		return checkoutApiSupported;
	}

	public void setCheckoutApiSupported(boolean checkoutApiSupported) {
		this.checkoutApiSupported = checkoutApiSupported;
	}

	public boolean isMultiLocationEnabled() {
		return multiLocationEnabled;
	}

	public void setMultiLocationEnabled(boolean multiLocationEnabled) {
		this.multiLocationEnabled = multiLocationEnabled;
	}

	public boolean isSetupRequired() {
		return setupRequired;
	}

	public void setSetupRequired(boolean setupRequired) {
		this.setupRequired = setupRequired;
	}

	public boolean isPreLaunchEnabled() {
		return preLaunchEnabled;
	}

	public void setPreLaunchEnabled(boolean preLaunchEnabled) {
		this.preLaunchEnabled = preLaunchEnabled;
	}

	public List<String> getEnabledPresentmentCurrencies() {
		return enabledPresentmentCurrencies;
	}

	public void setEnabledPresentmentCurrencies(List<String> enabledPresentmentCurrencies) {
		this.enabledPresentmentCurrencies = enabledPresentmentCurrencies;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", domain='" + domain + '\'' +
				", province='" + province + '\'' +
				", country='" + country + '\'' +
				", address1='" + address1 + '\'' +
				", zip='" + zip + '\'' +
				", city='" + city + '\'' +
				", source='" + source + '\'' +
				", phone='" + phone + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", primaryLocale='" + primaryLocale + '\'' +
				", address2='" + address2 + '\'' +
				", createdAt='" + createdAt + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				", countryCode='" + countryCode + '\'' +
				", countryName='" + countryName + '\'' +
				", customerEmail='" + customerEmail + '\'' +
				", currency='" + currency + '\'' +
				", timezone='" + timezone + '\'' +
				", ianaTimezone='" + ianaTimezone + '\'' +
				", shopOwner='" + shopOwner + '\'' +
				", moneyFormat='" + moneyFormat + '\'' +
				", moneyWithCurrencyFormat='" + moneyWithCurrencyFormat + '\'' +
				", weightUnit='" + weightUnit + '\'' +
				", provinceCode='" + provinceCode + '\'' +
				", forceSsl='" + forceSsl + '\'' +
				", taxesIncluded='" + taxesIncluded + '\'' +
				", autoConfigureTaxInclusivity='" + autoConfigureTaxInclusivity + '\'' +
				", taxShipping='" + taxShipping + '\'' +
				", countyTaxes='" + countyTaxes + '\'' +
				", planDisplayName='" + planDisplayName + '\'' +
				", planName='" + planName + '\'' +
				", hasDiscounts='" + hasDiscounts + '\'' +
				", hasGiftCards='" + hasGiftCards + '\'' +
				", myshopifyDomain='" + myshopifyDomain + '\'' +
				", googleAppsDomain='" + googleAppsDomain + '\'' +
				", googleAppsLoginEnabled='" + googleAppsLoginEnabled + '\'' +
				", moneyInEmailsFormat='" + moneyInEmailsFormat + '\'' +
				", moneyWithCurrencyInEmailsFormat='" + moneyWithCurrencyInEmailsFormat + '\'' +
				", eligibleForPayments='" + eligibleForPayments + '\'' +
				", requiresExtraPaymentsAgreement='" + requiresExtraPaymentsAgreement + '\'' +
				", passwordEnabled='" + passwordEnabled + '\'' +
				", hasStorefront='" + hasStorefront + '\'' +
				", eligibleForCardReaderGiveaway='" + eligibleForCardReaderGiveaway + '\'' +
				", finances='" + finances + '\'' +
				", primaryLocationId='" + primaryLocationId + '\'' +
				", cookieConsentLevel='" + cookieConsentLevel + '\'' +
				", visitorTrackingConsentPreference='" + visitorTrackingConsentPreference + '\'' +
				", checkoutApiSupported='" + checkoutApiSupported + '\'' +
				", multiLocationEnabled='" + multiLocationEnabled + '\'' +
				", setupRequired='" + setupRequired + '\'' +
				", preLaunchEnabled='" + preLaunchEnabled + '\'' +
				", enabledPresentmentCurrencies=" + enabledPresentmentCurrencies +
				'}';
	}
}
