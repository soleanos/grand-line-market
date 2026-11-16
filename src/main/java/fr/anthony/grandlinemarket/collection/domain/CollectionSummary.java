package fr.anthony.grandlinemarket.collection.domain;

import java.math.BigDecimal;
import java.util.Optional;

public record CollectionSummary(
        BigDecimal totalInvestment,
        int totalCards,
        Optional<CollectionItem> mostExpensiveItem
) {}