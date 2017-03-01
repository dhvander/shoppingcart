/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.transformers;

import com.dharshana.domain.InventoryItem;
import com.dharshana.view.beans.CartItem;
import org.springframework.stereotype.Component;

/**
 *
 * @author dharshana
 */
@Component
public class InventoryToCartItemTransformer {

    public CartItem transform(InventoryItem inventoryItem, int count) {
        return (new CartItem(inventoryItem.getName(), inventoryItem.getDescription(), inventoryItem.getPrice(), count));
    }
}
