package com.ecommerce.controller;

import com.ecommerce.controllers.CardsController;
import com.ecommerce.model.Card;
import com.ecommerce.model.CardType;
import com.ecommerce.services.CardsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CardsService cardsService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CardsController(cardsService))
                .build();
    }

    @Test
    void getAllCardsReturnsListOfCards() throws Exception {
        // Create some sample cards
        Card card1 = new Card(1, "Card1", "Card 1 description", 1.99, "https://example.com/card1.jpg", new CardType(1, "Type1"));
        Card card2 = new Card(2, "Card2", "Card 2 description", 2.99, "https://example.com/card2.jpg", new CardType(2, "Type2"));
        List<Card> cards = Arrays.asList(card1, card2);

        // Set up the mock cards service to return the cards
        when(cardsService.getAllCards()).thenReturn(cards);

        // Send a GET request to the /cards endpoint
        mockMvc.perform(get("/cards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Card1")))
                .andExpect(jsonPath("$[0].description", equalTo("Card 1 description")))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].name", equalTo("Card2")))
                .andExpect(jsonPath("$[1].description", equalTo("Card 2 description")));
    }

    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
