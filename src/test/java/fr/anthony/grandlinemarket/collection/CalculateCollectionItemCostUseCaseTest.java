package fr.anthony.grandlinemarket.collection;

import fr.anthony.grandlinemarket.collection.application.CalculateCollectionItemCostUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class CalculateCollectionItemCostUseCaseTest {

   private final CalculateCollectionItemCostUseCase calculateCollectionItemCostUseCase = new CalculateCollectionItemCostUseCase();

    @ParameterizedTest(name = "{index} - {0} cartes à {1} euros = {2} euros")
    @MethodSource("validPurchaseCosts")
    void shouldCalculatePurchaseCostForValidQuantityAndPrice(int quantity, BigDecimal purchasePrice, BigDecimal expectedTotal) {

        BigDecimal resulat = calculateCollectionItemCostUseCase.execute(quantity, purchasePrice );

        Assertions.assertEquals(
                expectedTotal,
                resulat
        );

    }

    @Test
    void shouldRejectNegativeQuantity() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculateCollectionItemCostUseCase.execute(-2, new BigDecimal("5.00") ));
    }

    @Test
    void shouldRejectNegativePurchasePrice() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculateCollectionItemCostUseCase.execute(3, new BigDecimal("-4.50")));
    }

    @Test
    void shouldReturnZeroWhenPurchasePriceIsZero() {
        BigDecimal resulat = calculateCollectionItemCostUseCase.execute(2, new BigDecimal("0.00") );

        Assertions.assertEquals(
                new BigDecimal("0.00"),
                resulat
        );
    }


    static Stream<Arguments> validPurchaseCosts(){
        return Stream.of(
                Arguments.of(3, new BigDecimal("4.50"), new BigDecimal("13.50")),
                Arguments.of(1, new BigDecimal("125.90"), new BigDecimal("125.90")),
                Arguments.of(0, new BigDecimal("7.50"), new BigDecimal("0.00")),
                Arguments.of(
                        2,
                        new BigDecimal("5"),
                        new BigDecimal("10.00")
                )
        );
    }

}
