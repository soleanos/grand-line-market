package fr.anthony.grandlinemarket.card.infrastructure.persistence;

import fr.anthony.grandlinemarket.card.domain.CardEdition;
import fr.anthony.grandlinemarket.card.domain.CardEditionRepository;
import fr.anthony.grandlinemarket.card.domain.Game;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCardEditionRepository implements CardEditionRepository {
    private final JdbcClient client;

    public JdbcCardEditionRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public List<CardEdition> findAll() {
        return this.client.sql("""
                            SELECT
                                                id,
                                                game,
                                                card_code,
                                                name,
                                                set_code,
                                                language,
                                                variant
                                            FROM card_edition
                                            ORDER BY id
                        """).query((rs, rowNum) -> new CardEdition(
                        rs.getLong("id"),
                        Game.valueOf(rs.getString("game")),
                        rs.getString("card_code"),
                        rs.getString("name"),
                        rs.getString("set_code"),
                        rs.getString("language"),
                        rs.getString("variant")
                ))
                .list();
    }

    @Override
    public List<CardEdition> findByGame(Game game) {
        return this.client.sql("""
                            SELECT
                                                id,
                                                game,
                                                card_code,
                                                name,
                                                set_code,
                                                language,
                                                variant
                                            FROM card_edition
                                            where game = :game
                                            ORDER BY id
                        """).param("game",game.name())
                .query((rs, rowNum) -> new CardEdition(
                rs.getLong("id"),
                Game.valueOf(rs.getString("game")),
                rs.getString("card_code"),
                rs.getString("name"),
                rs.getString("set_code"),
                rs.getString("language"),
                rs.getString("variant")
                ))
                .list();
    }
}
