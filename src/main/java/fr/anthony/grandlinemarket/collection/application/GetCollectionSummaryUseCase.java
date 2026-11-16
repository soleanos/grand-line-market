
package fr.anthony.grandlinemarket.collection.application;

import fr.anthony.grandlinemarket.collection.domain.CollectionItem;
import fr.anthony.grandlinemarket.collection.domain.CollectionSummary;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GetCollectionSummaryUseCase {

    private final CalculateCollectionItemCostUseCase calculateCollectionItemCostUseCase = new CalculateCollectionItemCostUseCase();

    public CollectionSummary execute(List<CollectionItem> items) {

        BigDecimal totalInvestment =  items.stream().map(item -> calculateCollectionItemCostUseCase.execute(item.quantity(),item.purchasePrice())).reduce(
                new BigDecimal("0.00"), BigDecimal::add);

        int totalCards = items.stream().mapToInt(CollectionItem::quantity).sum();

        Optional<CollectionItem> mostExpensiveItem = items.stream().filter(item -> item.quantity() != 0).max(Comparator.comparing(
           item -> calculateCollectionItemCostUseCase.execute(item.quantity(),item.purchasePrice()
        )));


        return new CollectionSummary(totalInvestment,totalCards, mostExpensiveItem);
    }
}
