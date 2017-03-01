/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.processors;

import com.dharshana.view.beans.Cart;
import com.dharshana.view.beans.CartItem;
import org.springframework.stereotype.Component;

/**
 *
 * @author dharshana
 */
@Component
public class TotalsProcessor implements CartProcessor {

    @Override
    public Cart processCart(Cart cart) {
        double total = cart.getCartItems().stream().mapToDouble(
                item -> item.getPrice() * item.getItemCount()
                - TotalsProcessor.this.calculateDiscounts(item)).sum();
        cart.setTotal(total);
        return cart;
    }

    public double calculateDiscounts(CartItem cartItem) {
        return cartItem.getDiscounts().stream().mapToDouble((discount) -> discount.getDiscountAmount()).sum();
    }

}
