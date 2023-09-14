create table if not exists authority(
    id serial primary key,
    name varchar(20)
);

create table if not exists user_authority(
    user_id int references "user"(id),
    authority_id int references authority(id),
    primary key(user_id, authority_id)
);