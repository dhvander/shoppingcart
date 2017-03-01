package com.dharshana;

import com.dharshana.repository.ItemRepository;
import com.dharshana.repository.PromotionRepository;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingcartApplicationTests {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private PromotionRepository promotionRepository;

    @Test
    public void contextLoads() {
    }

    /**
     * Test all seed data is present at startup
     */
    @Test
    public void dataPresent() {
        Assert.assertThat("Expecting two items to be in the database", itemRepo.count(), Is.is(2L));
        Assert.assertThat("Expecting two promotions to be in the database", promotionRepository.count(), Is.is(2L));
        
    }

    /**
     * Test all seed data is present at startup
     */
    @Test
    public void testApplePresent() {
        Assert.assertNotNull("Expecting two items to be in the database", itemRepo.findOne("Apple"));
    }

    /**
     * Test all seed data is present at startup
     */
    @Test
    public void testOrangePresent() {
        Assert.assertNotNull("Expecting two items to be in the database", itemRepo.findOne("Orange"));
    }
}
