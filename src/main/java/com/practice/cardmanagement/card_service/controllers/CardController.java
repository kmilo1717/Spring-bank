package com.practice.cardmanagement.card_service.controllers;

import com.practice.cardmanagement.card_service.dto.CardChargeDTO;
import com.practice.cardmanagement.card_service.model.Card;
import com.practice.cardmanagement.card_service.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    public ResponseEntity<?> createCard(@Validated @RequestBody Card card, BindingResult result) {

        Card cardNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            cardNew = cardService.createCard(card);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Card created with success");
        response.put("card", cardNew);
        return new ResponseEntity<>(cardNew, HttpStatus.CREATED);
    }

    @PutMapping("/{cardNumber}")
    public ResponseEntity<?> updateCard(@PathVariable String cardNumber, @RequestBody @Validated CardChargeDTO card) {
        Card cardUpdated = null;
        Map<String, Object> response = new HashMap<>();

        try {
            Card existingCard = cardService.getCardByCardNumber(cardNumber);

            if (existingCard == null) {
                response.put("message", "Card not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            existingCard.setBalance(card.getAmount() + existingCard.getBalance());
            cardUpdated = cardService.updateCard(existingCard);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la actualización en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Card updated with success");
        response.put("card", cardUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
