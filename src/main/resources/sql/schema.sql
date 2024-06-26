CREATE TABLE IF NOT EXISTS movie(
    id NUMBER(3) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    genre_id NUMBER(3)
);

CREATE TABLE IF NOT EXISTS genre(
    genre_id NUMBER(3) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS rating(
    id NUMBER(3) PRIMARY KEY AUTO_INCREMENT,
    user_id NUMBER(3),
    movie_id  NUMBER(3),
    rating NUMBER(3)
);

CREATE TABLE IF NOT EXISTS member(
    id NUMBER(3) PRIMARY KEY AUTO_INCREMENT,
    loginId VARCHAR(40) NOT NULL,
    name VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL
);



