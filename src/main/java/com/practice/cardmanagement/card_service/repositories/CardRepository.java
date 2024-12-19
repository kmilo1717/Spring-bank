package com.practice.cardmanagement.card_service.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.practice.cardmanagement.card_service.model.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {

    Card findByCardNumber(String cardNumber);
}
