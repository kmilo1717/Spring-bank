package com.practice.cardmanagement.card_service.services;

import com.practice.cardmanagement.card_service.dto.CardChargeDTO;
import com.practice.cardmanagement.card_service.model.Card;
import com.practice.cardmanagement.card_service.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return (List<Card>) cardRepository.findAll();
    }

    public Card createCard(Card card) {
        String generatedPart = generateUniquePart();
        String cardNumber = card.getProductId() + generatedPart;
        card.setCardNumber(cardNumber);
        card.setBalance(0.0f);
        return cardRepository.save(card);
    }


    public Card getCardByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }



    private String generateUniquePart() {
        String randomNumber = "";

        for (int i = 0; i < 10; i++) {
            randomNumber += (int) (Math.random() * 10);
        }

        return randomNumber;
    }

}
