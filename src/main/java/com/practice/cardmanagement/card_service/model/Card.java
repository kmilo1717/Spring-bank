package com.practice.cardmanagement.card_service.model;

import com.practice.cardmanagement.card_service.validations.CreateGroup;
import com.practice.cardmanagement.card_service.validations.ExpiryDateValidation;
import com.practice.cardmanagement.card_service.validations.NameValidation;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "cards")
@DynamicUpdate
public class Card {

    @Id
    private String cardNumber;

    @NotEmpty(message ="cannot be empty")
    @Column(nullable=false)
    @Size(min = 6, max = 6, message = "must be 6 digits")
    private String productId;
    @NotEmpty(message ="cannot be empty")
    @Column(nullable=false)
    @NameValidation(groups = CreateGroup.class)
    private String name;

    @NotEmpty(message ="cannot be empty")
    @Column(nullable=false)
    @ExpiryDateValidation(onlyFutureDates = true, yearRange = 3, groups = CreateGroup.class)
    private String expiryDate;

    private Float balance = 0.0f;

    @NotNull(message = "cannot be empty")
    @Pattern(regexp = "^(CREDIT|DEBIT)$", message = "must be CREDIT or DEBIT")
    private String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
