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
public class CartItem {

    private String itemName;
    private String description;
    private double price;
    private int itemCount;
    private List<Discount> discounts = new ArrayList<>();

    private CartItem() {

    }

    public CartItem(String itemName, String description, double price, int itemCount) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the itemCount
     */
    public int getItemCount() {
        return itemCount;
    }

    public void increaseCount() {
        itemCount++;
    }

    public void decreaseCount() {
        itemCount--;
    }

    /**
     * @param itemCount the itemCount to set
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public String toString() {
        return "CartItem{" + "itemName=" + itemName + ", description=" + description + ", price=" + price + ", itemCount=" + itemCount + ", discounts=" + discounts + '}';
    }

}
