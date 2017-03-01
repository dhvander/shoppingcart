/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.processors;

import com.dharshana.domain.Promotion;
import com.dharshana.repository.PromotionRepository;
import com.dharshana.view.beans.Cart;
import com.dharshana.view.beans.CartItem;
import com.dharshana.view.beans.Discount;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dharshana
 */
@Component
public class PromotionsProcessor implements CartProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(PromotionsProcessor.class);

    @Autowired
    private PromotionRepository promotionsRepo;

    @Override
    public Cart processCart(Cart cart) {

        //Load all current promotions to the list
        List<Promotion> currentPromotions = new ArrayList<>();
        promotionsRepo.findAll().forEach(currentPromotions::add);

        cart.getCartItems().stream().forEach((final CartItem cartItem) -> {
            //For each item in the list, check whether there is a matching promotion
            //Maybe promotions should be ordered in the future so that we can apply multiple promotions
            currentPromotions.stream()
                    .filter((promotion) -> promotion.getAppliedTo().getName().equals(cartItem.getItemName())).
                    forEachOrdered((Promotion matchedPromotion) -> {
                        //Apply the promotion to the cart item;
                        LOGGER.debug("Checking current promotion {}", matchedPromotion);
                        if (cartItem.getItemCount() >= matchedPromotion.getCountInBasket()) {
                            LOGGER.debug("Promotion {} is applicable to {}", matchedPromotion, cartItem);
                            //See whether item coount matches and see how many times it should be applied
                            int timesToApply = cartItem.getItemCount() / matchedPromotion.getCountInBasket();
                            if (timesToApply > 0) {
                                double discountAmount = cartItem.getPrice() * timesToApply * matchedPromotion.getDiscountPercent() / 100d;
                                Discount discount = new Discount(matchedPromotion.getPromoId(), matchedPromotion.getDescription(), discountAmount);
                                LOGGER.debug("Applying discount {}", discount);
                                cartItem.getDiscounts().add(discount);
                            }
                        }
                    });

        });

        return cart;
    }

}
