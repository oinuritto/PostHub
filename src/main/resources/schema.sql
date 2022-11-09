drop table if exists users cascade;
drop table if exists posts cascade;
drop table if exists comments cascade;
drop table if exists likes cascade;
drop table if exists img_info cascade;

create table users
(
    id         bigserial primary key,
    first_name varchar(20),
    last_name  varchar(20),
    username   varchar(20) unique,
    password   varchar(32),
    role       varchar(10) default 'user'
);

alter table users
    add constraint role_check check (role = 'admin' or role = 'user');

alter table users
    add column rating int default 0,
    add constraint rating_check check ( rating >= 0 );

create table img_info
(
    id bigserial primary key,
    size bigint,
    file_type varchar,
    original_file_name varchar,
    storage_file_name varchar
);

create table posts
(
    id        bigserial primary key,
    title     varchar,
    post_text text,
    img_id    bigint,
    foreign key (img_id) references img_info (id) on delete cascade,
    user_id   bigint,
    foreign key (user_id) references users (id) on delete cascade
);

create table comments
(
    id           bigserial primary key,
    comment_text text,
    user_id      bigint,
    foreign key (user_id) references users (id) on delete cascade,
    post_id      bigint,
    foreign key (post_id) references posts (id) on delete cascade
);

create table likes
(
    user_id bigint,
    foreign key (user_id) references users (id) on delete cascade,
    post_id bigint,
    foreign key (post_id) references posts (id) on delete cascade,
    primary key (user_id, post_id)
);
