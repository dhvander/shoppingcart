/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana;

import com.dharshana.domain.InventoryItem;
import com.dharshana.domain.Promotion;
import com.dharshana.repository.ItemRepository;
import com.dharshana.repository.PromotionRepository;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author dharshana
 */
@Configuration
public class AppConfig {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @PostConstruct
    public void init() {
        List<InventoryItem> items = Arrays.asList(new InventoryItem("Apple", .6d), new InventoryItem("Orange", .25d));
        itemRepository.save(items);
        List<Promotion> promotions = 
                Arrays.asList(
                        new Promotion("P1", "Apple BOGOF", 2, 100, itemRepository.findOne("Apple")), 
                        new Promotion("P2", "Orange BTGOF", 3, 100, itemRepository.findOne("Orange")));
        promotionRepository.save(promotions);
    }
}
