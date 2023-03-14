package com.ecommerce.controllers;

import com.ecommerce.model.Card;
import com.ecommerce.services.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
@CrossOrigin
public class CardsController {

    private final CardsService cardsService;

    @Autowired
    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    /**
     * get all card to be displayed in the front end
     */
    @GetMapping()
    public ResponseEntity<List<Card>> getAllCards(){
        List<Card> cards = cardsService.getAllCards();
        return ResponseEntity.ok(cards);
    }
}
