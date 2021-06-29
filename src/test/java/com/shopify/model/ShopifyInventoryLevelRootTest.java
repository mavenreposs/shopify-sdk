package com.shopify.model;

import static org.junit.Assert.assertSame;

import com.shopify.model.roots.ShopifyInventoryLevelRoot;
import com.shopify.model.structs.ShopifyInventoryLevel;
import org.junit.Test;

public class ShopifyInventoryLevelRootTest {

	@Test
	public void givenSomeValuesWhenCreatingShopifyInventoryLevelUpdateRequestThenExpectCorrectValues() {
		final ShopifyInventoryLevel shopifyInventoryLevel = new ShopifyInventoryLevel();
		shopifyInventoryLevel.setAvailable(2L);
		shopifyInventoryLevel.setInventoryItemId("12387734");
		shopifyInventoryLevel.setLocationId("983482934");

		final ShopifyInventoryLevelRoot shopifyInventoryLevelRoot = new ShopifyInventoryLevelRoot();
		shopifyInventoryLevelRoot.setInventoryLevel(shopifyInventoryLevel);
		assertSame(shopifyInventoryLevelRoot.getInventoryLevel(), shopifyInventoryLevel);
	}

}
