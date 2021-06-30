package com.shopify;

import com.shopify.model.structs.Shop;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.client.WebTarget;

public class ShopifyEndpoint {

    static final String OAUTH = "oauth";
    static final String REVOKE = "revoke";
    static final String ACCESS_TOKEN = "access_token";
    static final String PRODUCTS = "products";
    static final String VARIANTS = "variants";
    static final String CUSTOM_COLLECTIONS = "custom_collections";
    static final String RECURRING_APPLICATION_CHARGES = "recurring_application_charges";
    static final String ORDERS = "orders";
    static final String FULFILLMENTS = "fulfillments";
    static final String ACTIVATE = "activate";
    static final String IMAGES = "images";
    static final String SHOP = "shop";
    static final String COUNT = "count";
    static final String CLOSE = "close";
    static final String CANCEL = "cancel";
    static final String METAFIELDS = "metafields";
    static final String RISKS = "risks";
    static final String LOCATIONS = "locations";
    static final String INVENTORY_LEVELS = "inventory_levels";
    static final String JSON = ".json";
    static final String LIMIT_QUERY_PARAMETER = "limit";
    static final String PAGE_INFO_QUERY_PARAMETER = "page_info";
    static final String STATUS_QUERY_PARAMETER = "status";
    static final String ANY_STATUSES = "any";
    static final String CREATED_AT_MIN_QUERY_PARAMETER = "created_at_min";
    static final String CREATED_AT_MAX_QUERY_PARAMETER = "created_at_max";
    static final String UPDATED_AT_MIN_QUERY_PARAMETER = "updated_at_min";
    static final String UPDATED_AT_MAX_QUERY_PARAMETER = "updated_at_max";
    static final String ATTRIBUTION_APP_ID_QUERY_PARAMETER = "attribution_app_id";
    static final String IDS_QUERY_PARAMETER = "ids";
    static final String SINCE_ID_QUERY_PARAMETER = "since_id";
    static final String QUERY_QUERY_PARAMETER = "query";
    static final String CALCULATE = "calculate";
    static final String REFUNDS = "refunds";
    static final String TRANSACTIONS = "transactions";
    static final String GIFT_CARDS = "gift_cards";
    static final String REFUND_KIND = "refund";
    static final String SET = "set";

    // new
    static final String DEPRECATED_API_CALLS = "deprecated_api_calls";

}
