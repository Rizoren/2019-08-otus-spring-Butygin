create table if not exists authority
(
    authority_name   varchar(50)    not null
        constraint authority_pk
            primary key
);

create table if not exists users
(
    user_id       bigserial                       not null
        constraint users_pk
            primary key,
    user_name     varchar(100)                    not null,
    user_password varchar(50),
    constraint users_user_name_uindex unique (user_name)
);

create table if not exists users_authority
(
    user_id          bigint         not null
        constraint users_user_id_fk
            references users (user_id),
    authority_name   varchar(50)    not null
        constraint authority_authority_name_fk
            references authority (authority_name)
);

create table if not exists tasks
(
    task_id          bigserial                           not null
        constraint tasks_pk
            primary key,
    task_name        varchar(50)                         not null,
    task_description varchar(200),
    task_status      smallint default 0,
    task_enddate     timestamp,
    user_id          bigserial                           not null
        constraint tasks_users_user_id_fk
            references users (user_id)
            on delete cascade
);
