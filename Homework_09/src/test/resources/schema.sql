CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists users
(
    user_id       bigserial                       not null
        constraint users_pk
            primary key,
    user_name     varchar(100)                    not null,
    user_password varchar(50),
    user_uuid     uuid default uuid_generate_v4() not null,
    constraint users_user_name_uindex unique (user_name),
    constraint users_user_uuid_uindex unique (user_uuid)
);

create table if not exists tasks
(
    task_id          bigserial                           not null
        constraint tasks_pk
            primary key,
    task_name        varchar(50)                         not null,
    task_description varchar(200),
    task_status      smallint default 0,
    task_uuid        uuid     default uuid_generate_v4() not null,
    user_uuid        uuid                                not null
        constraint tasks_users_user_uuid_fk
            references users (user_uuid)
            on delete cascade,
    task_enddate     timestamp
);
