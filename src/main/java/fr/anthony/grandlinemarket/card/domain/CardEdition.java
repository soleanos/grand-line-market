package fr.anthony.grandlinemarket.card.domain;

public record CardEdition(
        Long id,
        Game game,
        String cardCode,
        String name,
        String setCode,
        String language,
        String variant
) {

}
