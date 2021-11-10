package com.shopify.model.roots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.ShopifyInventoryItem;
import com.shopify.model.structs.ShopifyInventoryLevel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ClassName ShopifyInventoryItemRoot
 *
 * @author Zhengdong Wang
 * @version jdk11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopifyInventoryItemRoot {

    @XmlElement(name = "inventory_item")
    private ShopifyInventoryItem inventoryItem;

    public ShopifyInventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(ShopifyInventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    @Override
    public String toString() {
        return "ShopifyInventoryItemRoot{" +
                "inventoryItem=" + inventoryItem +
                '}';
    }
}
