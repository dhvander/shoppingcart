/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.view.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 *
 * @author dharshana
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartTest {

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    private final String APPLE = "Apple";
    private final String ORANGE = "Orange";
    private final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    @Autowired
    private WebApplicationContext webApplicationContext;


    /**
     * Standard test to make sure JSON conversion is present
     *
     * @param converters
     */
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCalculateTotalsEmpty() throws Exception {
        List<String> items = Arrays.asList();
        String content = mapper.writeValueAsString(items);
        mockMvc.perform(post("/cart/calculateTotals").content(content).contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cartItems", Matchers.hasSize(0)));
    }

    @Test
    public void testCalculateTotalsWithFruits() throws Exception {
        List<String> items = Arrays.asList(APPLE,ORANGE,APPLE,ORANGE,ORANGE,ORANGE,ORANGE);
        String content = mapper.writeValueAsString(items);
        mockMvc.perform(post("/cart/calculateTotals").content(content).contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cartItems", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.cartItems[0].itemName", Matchers.is(APPLE)))
                .andExpect(jsonPath("$.cartItems[0].itemCount", Matchers.is(2)))
                .andExpect(jsonPath("$.cartItems[0].price", Matchers.is(.6d)))
                .andExpect(jsonPath("$.cartItems[1].itemName", Matchers.is(ORANGE)))
                .andExpect(jsonPath("$.cartItems[1].itemCount", Matchers.is(5)))
                .andExpect(jsonPath("$.cartItems[1].price", Matchers.is(.25d)))
                 //With promotions applied Buy three get one free for orange and buy one get one free for apple
                .andExpect(jsonPath("$.total", Matchers.is(.25d*4 + .6d*1)));
    }

}
