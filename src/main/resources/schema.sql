create table if not exists `user`
(
    account     char(10)    not null primary key,
    password    varchar(20) not null,
    email       varchar(50),
    name        varchar(45) not null,
    gender      int         not null check ( gender = 0 or gender = 1 ),
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
)