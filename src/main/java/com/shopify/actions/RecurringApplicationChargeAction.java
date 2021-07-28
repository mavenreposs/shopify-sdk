package com.shopify.actions;

import com.shopify.model.request.ShopifyRecurringApplicationChargeCreationRequest;
import com.shopify.model.structs.ShopifyRecurringApplicationCharge;

public interface RecurringApplicationChargeAction {

    ShopifyRecurringApplicationCharge createRecurringApplicationCharge(
            final ShopifyRecurringApplicationChargeCreationRequest shopifyRecurringApplicationChargeCreationRequest);

    ShopifyRecurringApplicationCharge getRecurringApplicationCharge(final String chargeId);

    ShopifyRecurringApplicationCharge activateRecurringApplicationCharge(final String chargeId);

}
