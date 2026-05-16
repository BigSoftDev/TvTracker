-- =========================
-- DROP TABLES (safe reset)
-- =========================

DROP TABLE IF EXISTS watched_episodes;
DROP TABLE IF EXISTS user_shows;
DROP TABLE IF EXISTS episodes;
DROP TABLE IF EXISTS shows;
DROP TABLE IF EXISTS users;

-- =========================
-- CREATE TABLES
-- =========================

-- USERS
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT NOW()
);

-- SHOWS
CREATE TABLE shows (
    id BIGSERIAL PRIMARY KEY,
    tvdb_id BIGINT UNIQUE,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

-- EPISODES
CREATE TABLE episodes (
    id BIGSERIAL PRIMARY KEY,
    series_id BIGINT NOT NULL,
    season_number INT NOT NULL,
    episode_number INT NOT NULL,
    name VARCHAR(255),
    air_date DATE,
    created_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_series
        FOREIGN KEY(series_id)
        REFERENCES shows(id)
        ON DELETE CASCADE
);

-- USER ↔ SHOW
CREATE TABLE user_shows (
    user_id BIGINT NOT NULL,
    show_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT NOW(),

    PRIMARY KEY (user_id, show_id),

    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_show
        FOREIGN KEY(show_id)
        REFERENCES shows(id)
        ON DELETE CASCADE
);

-- WATCHED EPISODES
CREATE TABLE watched_episodes (
    user_id BIGINT NOT NULL,
    episode_id BIGINT NOT NULL,
    watched_at TIMESTAMP DEFAULT NOW(),

    PRIMARY KEY (user_id, episode_id),

    CONSTRAINT fk_user_we
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_episode_we
        FOREIGN KEY(episode_id)
        REFERENCES episodes(id)
        ON DELETE CASCADE
);

-- =========================
-- INDEXES
-- =========================

CREATE INDEX idx_episodes_series_airdate
ON episodes(series_id, air_date);

CREATE INDEX idx_user_shows_user
ON user_shows(user_id);

CREATE INDEX idx_watched_user
ON watched_episodes(user_id);