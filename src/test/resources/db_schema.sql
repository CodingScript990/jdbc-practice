-- drop table users
DROP TABLE IF EXISTS USERS;

-- create table users
CREATE TABLE USERS (
    userId          varchar(12)     NOT NULL,
    password        varchar(12)     NOT NULL,
    name            varchar(20)     NOT NULL,
    email           varchar(50),

    PRIMARY KEY                     (userId)
);
