create table if not exists `user`
(
    id          char(19)    not null primary key,
    account      char(11)    not null unique,
    id_card     char(18)    not null unique,
    password    varchar(20) not null,
    email       varchar(50),
    name        varchar(50) not null,
    gender      int         not null check ( gender = 0 or gender = 1 ),

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
);