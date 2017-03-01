/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.view.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dharshana
 */
public class Cart {

    private List<CartItem> cartItems = new ArrayList<>();
    private double total;

    /**
     * @return the cartItems
     */
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * @param cartItems the cartItems to set
     */
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartItems=" + cartItems + ", total=" + total + '}';
    }

    
    
}
