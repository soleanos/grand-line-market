package fr.anthony.grandlinemarket.collection.domain;

import java.math.BigDecimal;

public record CollectionItem(
        Long cardId,
        String cardName,
        int quantity,
        BigDecimal purchasePrice
) {}