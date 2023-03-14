package com.ecommerce.services;

import com.ecommerce.model.Card;
import com.ecommerce.repositories.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardsService {

    public final CardsRepository cardsRepository;

    @Autowired
    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    /**
     * This is used to get all card to be displayed in the front end
     *
     * @return All the cards in the database
     */
    public List<Card> getAllCards() {
        return cardsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

}
