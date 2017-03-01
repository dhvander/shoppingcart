/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.processors;

import com.dharshana.domain.InventoryItem;
import com.dharshana.repository.ItemRepository;
import com.dharshana.transformers.InventoryToCartItemTransformer;
import com.dharshana.transformers.ItemNotfoundException;
import com.dharshana.view.beans.Cart;
import com.dharshana.view.beans.CartItem;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Process the orde and prepare the cart for checkout
 *
 * @author dharshana
 */
@Component
public class OrderProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderProcessor.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryToCartItemTransformer inventoryToCartItemTransformer;

    public Cart processOrder(List<String> items) throws ItemNotfoundException{
        Cart cart = new Cart();
        //Go through the items and create the Cart..
        items.stream().forEach((final String itemName) -> {
            LOGGER.debug("Searching for item {} in database..", itemName);
            InventoryItem inventoryItem = itemRepository.findOne(itemName);
            LOGGER.debug("Current item from database is {}", inventoryItem);
            if (inventoryItem != null) {
                Optional<CartItem> existingItem = cart.getCartItems().stream().filter((final CartItem cartItem) -> {
                    return cartItem.getItemName().equals(itemName);
                }).findFirst();

                CartItem currentItem = existingItem.orElseGet(() -> {
                    CartItem itemToAdd = inventoryToCartItemTransformer.transform(inventoryItem, 0);
                    cart.getCartItems().add(itemToAdd);
                    return itemToAdd;
                });
                currentItem.increaseCount();

            } else {
                throw new ItemNotfoundException();
            }

        });

        return cart;
    }

}
