

CREATE TABLE collection_item
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    collection_id   BIGINT      NOT NULL,
    card_edition_id BIGINT      NOT NULL,
    quantity        INTEGER     NOT NULL
        CHECK (quantity > 0),
    card_condition  VARCHAR(30) not null
        CHECK (card_condition in (
                             'MINT',
                             'NEAR_MINT',
                             'GOOD',
                             'PLAYED',
                             'POOR'
            )),
    purchase_price  numeric(10, 2)
        check (purchase_price >= 0),

    created_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (
        collection_id,
        card_edition_id,
        card_condition
    ),

    FOREIGN KEY (collection_id)
        REFERENCES card_collection (id),

    FOREIGN KEY (card_edition_id)
        REFERENCES card_edition (id)
);
