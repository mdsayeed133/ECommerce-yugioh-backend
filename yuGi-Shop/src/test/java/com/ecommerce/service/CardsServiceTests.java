package com.ecommerce.service;

import com.ecommerce.model.Card;
import com.ecommerce.model.CardType;
import com.ecommerce.repositories.CardsRepository;
import com.ecommerce.services.CardsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@SpringBootTest
class CardsServiceTests {

    @Mock
    private CardsRepository cardsRepository;

    @InjectMocks
    private CardsService cardsService;

    @Test
    void getAllCardsReturnsSortedList() {
        Card card1 = new Card(1, "Card1", "Card 1 description", 1.99, "https://example.com/card1.jpg", new CardType(1, "Type1"));
        Card card2 = new Card(2, "Card2", "Card 2 description", 2.99, "https://example.com/card2.jpg", new CardType(2, "Type2"));
        when(cardsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(Arrays.asList(card1, card2));
        List<Card> result = cardsService.getAllCards();
        assertEquals(Arrays.asList(card1, card2), result);
        assertEquals("Card1", result.get(0).getName());
        assertEquals("Card2", result.get(1).getName());
    }

    @Test
    void getAllCardsReturnsEmptyList() {
        when(cardsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(Collections.emptyList());
        List<Card> result = cardsService.getAllCards();
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllCardsReturnsListWithSingleCard() {
        Card card = new Card(1, "Card1", "Card 1 description", 1.99, "https://example.com/card1.jpg", new CardType(1, "Type1"));
        when(cardsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(Collections.singletonList(card));
        List<Card> result = cardsService.getAllCards();
        assertEquals(Collections.singletonList(card), result);
        assertEquals("Card1", result.get(0).getName());
    }
}