package fr.anthony.grandlinemarket.collection;

import fr.anthony.grandlinemarket.collection.application.GetCollectionSummaryUseCase;
import fr.anthony.grandlinemarket.collection.domain.CollectionItem;
import fr.anthony.grandlinemarket.collection.domain.CollectionSummary;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GetCollectionSummaryUseCaseTest {

    GetCollectionSummaryUseCase getCollectionSummaryUseCase = new GetCollectionSummaryUseCase();

   @Test
    void shouldCalculateSummaryForValidCollection(){
        List<CollectionItem> collectionItems  = Arrays.asList(
            new CollectionItem(1L,"Luffy SEC", 2, new BigDecimal("45.00")),
            new CollectionItem(2L,"Pikachu EX", 3, new BigDecimal("12.50")),
            new CollectionItem(3L,"Zoro SR", 1, new BigDecimal("80.00")),
            new CollectionItem(4L,"Nami R", 0, new BigDecimal("5.00"))
        );

        CollectionSummary summary= getCollectionSummaryUseCase.execute(collectionItems);

        assertEquals(
                6,
                summary.totalCards()
        );

        assertEquals(
                new BigDecimal("207.50"),
                summary.totalInvestment()
        );

       CollectionItem expectedMostExpensiveItem = new CollectionItem(1L,"Luffy SEC", 2, new BigDecimal("45.00"));
       assertEquals(Optional.of(expectedMostExpensiveItem), summary.mostExpensiveItem());
    }

    @Test
    void shouldReturnEmptySummaryForEmptyCollection(){
        CollectionSummary summary= getCollectionSummaryUseCase.execute(new ArrayList<>());

        assertEquals(
                0,
                summary.totalCards()
        );

        assertEquals(
                new BigDecimal("0.00"),
                summary.totalInvestment()
        );

        assertEquals(Optional.empty(), summary.mostExpensiveItem());
    }

    @Test
    void shouldReturnNoMostExpensiveItemWhenQuantityIsZero(){
        List<CollectionItem> collectionItems  = List.of(
                new CollectionItem(1L, "Luffy SEC", 0, new BigDecimal("45.00"))
        );

        CollectionSummary summary= getCollectionSummaryUseCase.execute(collectionItems);

        assertEquals(
                0,
                summary.totalCards()
        );

        assertEquals(
                new BigDecimal("0.00"),
                summary.totalInvestment()
        );

        assertEquals(Optional.empty(), summary.mostExpensiveItem());
    }

    @Test
    void rejectNegativeQuantity() {
        List<CollectionItem> collectionItems = List.of(
                new CollectionItem(1L, "Luffy SEC", -1, new BigDecimal("45.00")),
                new CollectionItem(2L,"Pikachu EX", 3, new BigDecimal("12.50"))
                );

        assertThrows(IllegalArgumentException.class,() -> getCollectionSummaryUseCase.execute(collectionItems));
    }


    @Test
    void rejectNegativePrice() {
        List<CollectionItem> collectionItems = List.of(
                new CollectionItem(1L, "Luffy SEC", 1, new BigDecimal("-3.00")),
                new CollectionItem(2L,"Pikachu EX", 3, new BigDecimal("12.50"))
        );

        assertThrows(IllegalArgumentException.class,() -> getCollectionSummaryUseCase.execute(collectionItems));
    }

    @Test
    void shouldHandleEqualInvestments() {
        List<CollectionItem> collectionItems = List.of(
                new CollectionItem(3L,"Zoro SR", 1, new BigDecimal("80.00")),
                new CollectionItem(4L,"Zara SR", 1, new BigDecimal("80.00"))
        );

        CollectionSummary summary= getCollectionSummaryUseCase.execute(collectionItems);

        assertTrue(collectionItems.contains(
                summary.mostExpensiveItem().orElseThrow())
        );
    }


    @Test
    void shouldReturnTotalInvestmentWithTwoDecimalPlaces(){
        List<CollectionItem> collectionItems  = Arrays.asList(
                new CollectionItem(1L,"Luffy SEC", 1, new BigDecimal("45.00")),
                new CollectionItem(4L,"Nami R", 1, new BigDecimal("5"))
        );

        CollectionSummary summary= getCollectionSummaryUseCase.execute(collectionItems);

        assertEquals(
                new BigDecimal("50.00"),
                summary.totalInvestment()
        );

    }


    }
