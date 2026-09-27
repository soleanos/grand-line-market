package fr.anthony.grandlinemarket.card.api;

import fr.anthony.grandlinemarket.card.application.FindAllCardsUseCase;
import fr.anthony.grandlinemarket.card.domain.CardEdition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardEditionController {

    private FindAllCardsUseCase findAllCardsUseCase;

    public CardEditionController(FindAllCardsUseCase findAllCardsUseCase) {
        this.findAllCardsUseCase = findAllCardsUseCase;
    }

    @GetMapping
    public List<CardEdition> findAll(){
        return this.findAllCardsUseCase.execute();
    }
}
