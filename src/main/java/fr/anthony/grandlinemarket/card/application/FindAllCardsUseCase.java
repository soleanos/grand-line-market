package fr.anthony.grandlinemarket.card.application;

import fr.anthony.grandlinemarket.card.domain.CardEdition;
import fr.anthony.grandlinemarket.card.domain.CardEditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCardsUseCase {
    private final CardEditionRepository cardEditionRepository;

    public FindAllCardsUseCase(CardEditionRepository cardEditionRepository) {
        this.cardEditionRepository = cardEditionRepository;
    }

    public List<CardEdition> execute (){
        return this.cardEditionRepository.findAll();
    }
}
