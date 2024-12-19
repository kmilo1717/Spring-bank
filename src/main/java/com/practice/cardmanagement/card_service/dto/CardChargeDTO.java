package com.practice.cardmanagement.card_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CardChargeDTO {

    @NotNull(message = "Balance cannot be null")
    @Positive(message = "Balance must be a positive value")
    private Float amount;


    public CardChargeDTO(Float amount) {
        this.amount = amount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
