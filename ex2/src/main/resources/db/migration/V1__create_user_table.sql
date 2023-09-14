create table if not exists "user" (
    id serial primary key,
    username varchar(20),
    password varchar(20)
);