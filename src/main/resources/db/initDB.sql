DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT NEXTVAL('global_seq') NOT NULL,
    name       VARCHAR                                           NOT NULL,
    email      VARCHAR                                           NOT NULL,
    password   VARCHAR                                           NOT NULL,
    registered TIMESTAMP           DEFAULT NOW()                 NOT NULL,
    enabled    BOOLEAN             DEFAULT TRUE                  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER PRIMARY KEY DEFAULT NEXTVAL('global_seq') NOT NULL,
    name VARCHAR                                           NOT NULL
);
CREATE UNIQUE INDEX restaurant_name_idx ON restaurant (name);

CREATE TABLE dish
(
    id            INTEGER PRIMARY KEY DEFAULT NEXTVAL('global_seq') NOT NULL,
    name          VARCHAR                                           NOT NULL,
    price         INTEGER                                           NOT NULL,
    input_date    TIMESTAMP           DEFAULT NOW()                 NOT NULL,
    restaurant_id INTEGER                                           NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dish_unique_date_rest_name_idx ON dish (input_date, restaurant_id, name);

CREATE TABLE vote
(
    id            INTEGER PRIMARY KEY DEFAULT NEXTVAL('global_seq') NOT NULL,
    user_id       INTEGER                                           NOT NULL,
    restaurant_id INTEGER                                           NOT NULL,
    date_time     TIMESTAMP           DEFAULT NOW()                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unique_user_id_date_time_idx ON vote (user_id, date_time);




