drop table if exists users;

create table users (
    id bigserial primary key,
    first_name varchar(20),
    last_name varchar(20),
    username varchar(20) unique,
    password varchar(20),
    role varchar(10) default 'user'
);