package fr.anthony.grandlinemarket.card.domain;

import java.util.List;

public interface CardEditionRepository {
    List<CardEdition>  findAll();
    List<CardEdition> findByGame(Game game);
}
