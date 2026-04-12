CREATE TABLE players (
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username   VARCHAR(255) NOT NULL,
    rating     INT          NOT NULL DEFAULT 0,
    pro_rating INT          NOT NULL DEFAULT 0
);

CREATE TABLE seasons (
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    year       INT          NOT NULL,
    month      INT          NOT NULL,
    top_results_count INT      NOT NULL,
    status     VARCHAR(20)  NOT NULL,
    started_at TIMESTAMP    NOT NULL,
    ended_at   TIMESTAMP
);

CREATE TABLE tournaments (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    season_id    UUID         NOT NULL REFERENCES seasons (id),
    name         VARCHAR(255) NOT NULL,
    type         VARCHAR(20)  NOT NULL,
    player_count INT          NOT NULL,
    bounty_base  INT,
    started_at   TIMESTAMP    NOT NULL,
    ended_at     TIMESTAMP
);

CREATE TABLE tournament_participants (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tournament_id UUID             NOT NULL REFERENCES tournaments (id),
    player_id     UUID             NOT NULL REFERENCES players (id),
    position      INT,
    eliminated_at TIMESTAMP,
    reg_points    INT NOT NULL DEFAULT 0,
    pro_points    INT NOT NULL DEFAULT 0,
    bounty_points INT NOT NULL DEFAULT 0
);

CREATE TABLE knockouts (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tournament_id UUID             NOT NULL REFERENCES tournaments (id),
    killer_id     UUID             NOT NULL REFERENCES players (id),
    victim_id     UUID             NOT NULL REFERENCES players (id),
    bounty_points INT NOT NULL DEFAULT 0
);

CREATE TABLE season_ratings (
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    season_id UUID             NOT NULL REFERENCES seasons (id),
    player_id UUID             NOT NULL REFERENCES players (id),
    total_reg INT NOT NULL DEFAULT 0,
    total_pro INT NOT NULL DEFAULT 0,
    UNIQUE (season_id, player_id)
);
