CREATE TABLE card_edition (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    game VARCHAR(30) NOT NULL
        CHECK (game IN ('ONE_PIECE', 'POKEMON')),
    card_code VARCHAR(30) NOT NULL,
    name VARCHAR(200) NOT NULL,
    set_code VARCHAR(100) NOT NULL,
    language VARCHAR(2) NOT NULL
        CHECK (language IN ('FR', 'EN', 'JP')),
    variant VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (game, set_code, card_code, language, variant)
)