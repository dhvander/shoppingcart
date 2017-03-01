/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.view.beans;

/**
 *
 * @author dharshana
 */
public class Discount {

    String promoId;
    String description;
    double discountAmount;
    
    public Discount(){}
    
    public Discount(String promoId, String description, double discountAmount) {
        this.promoId = promoId;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

}
