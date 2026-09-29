package fr.anthony.grandlinemarket.card.api;

import fr.anthony.grandlinemarket.card.application.FindAllCardsUseCase;
import fr.anthony.grandlinemarket.card.application.FindCardsByGameUsecase;
import fr.anthony.grandlinemarket.card.domain.CardEdition;
import fr.anthony.grandlinemarket.card.domain.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardEditionController {

    private final FindAllCardsUseCase findAllCardsUseCase;
    private final FindCardsByGameUsecase findCardsByGameUsecase;

    public CardEditionController(FindAllCardsUseCase findAllCardsUseCase,
                                 FindCardsByGameUsecase findCardsByGameUsecase
    ) {
        this.findAllCardsUseCase = findAllCardsUseCase;
        this.findCardsByGameUsecase = findCardsByGameUsecase;
    }

    @GetMapping
    public List<CardEdition> findAll(@RequestParam(required = false) Game game){
        if(game != null){
            return this.findCardsByGameUsecase.execute(game);
        }
        return this.findAllCardsUseCase.execute();
    }

}
