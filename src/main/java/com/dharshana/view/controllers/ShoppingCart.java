/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.view.controllers;

import com.dharshana.processors.OrderProcessor;
import com.dharshana.processors.PromotionsProcessor;
import com.dharshana.processors.TotalsProcessor;
import com.dharshana.view.beans.Cart;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dharshana
 */
@RestController()
@RequestMapping("/cart")
public class ShoppingCart {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShoppingCart.class);

    /**
     * Cart level processors
     */

    @Autowired
    private PromotionsProcessor promotionsProcessor;
            
    @Autowired
    private TotalsProcessor totalsProcessor;

    @Autowired
    private OrderProcessor orderProcessor;

    @RequestMapping(method = RequestMethod.POST, path = "/calculateTotals")
    public Cart calculateTotals(@RequestBody List<String> items) {
        LOGGER.debug("\n==========\nProcessing items {}\n======================", items);
        Cart cart = orderProcessor.processOrder(items);
        cart = promotionsProcessor.processCart(cart);
        cart = totalsProcessor.processCart(cart);
        return cart;
    }

}
