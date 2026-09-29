package fr.anthony.grandlinemarket.card.application;

import fr.anthony.grandlinemarket.card.domain.CardEdition;
import fr.anthony.grandlinemarket.card.domain.CardEditionRepository;
import fr.anthony.grandlinemarket.card.domain.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCardsByGameUsecase {
    private final CardEditionRepository cardEditionRepository;

    public FindCardsByGameUsecase(CardEditionRepository cardEditionRepository) {
        this.cardEditionRepository = cardEditionRepository;
    }

    public List<CardEdition> execute (Game game){
        return this.cardEditionRepository.findByGame(game);

    }

}
