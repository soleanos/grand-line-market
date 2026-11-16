package fr.anthony.grandlinemarket.collection.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateCollectionItemCostUseCase {

    public BigDecimal execute(int quantity, BigDecimal purchasePrice) {
        rejectNegativeQuantity(quantity);
        rejectPurchasePrice(purchasePrice);

        return purchasePrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.DOWN);

    }

    private void rejectNegativeQuantity(int quantity){
        if (quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

    private void rejectPurchasePrice(BigDecimal purchasePrice){
        if (purchasePrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Purchase price cannot be negative");
        }
    }

}
