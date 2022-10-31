drop table if exists users;

create table users (
    id bigserial primary key,
    first_name varchar(20),
    last_name varchar(20),
    username varchar(20) unique,
    password varchar(32),
    role varchar(10) default 'user'
);

alter table users
    add constraint role_check check (role = 'admin' or role = 'user');