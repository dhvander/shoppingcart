/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author dharshana
 */
@Entity
public class Promotion implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private String promoId;

    @ManyToOne(optional = false)
    private InventoryItem appliedTo;

    private String description;

    private int countInBasket;

    private double discountPercent;

    public Promotion() {
    }

    public Promotion(String id, String description, int countInBasket, double discountPercent, InventoryItem appliedTo) {
        this.promoId = id;
        this.description = description;
        this.countInBasket = countInBasket;
        this.discountPercent = discountPercent;
        this.appliedTo = appliedTo;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public InventoryItem getAppliedTo() {
        return appliedTo;
    }

    public void setAppliedTo(InventoryItem appliedTo) {
        this.appliedTo = appliedTo;
    }

    public int getCountInBasket() {
        return countInBasket;
    }

    public void setCountInBasket(int countInBasket) {
        this.countInBasket = countInBasket;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
