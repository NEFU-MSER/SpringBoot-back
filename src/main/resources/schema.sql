create table if not exists `user`
(
    id          char(19)    not null primary key,
    account     char(10)    not null unique,
    password    varchar(20) not null,
    email       varchar(50),
    name        varchar(50) not null,
    gender      int         not null check ( gender = 0 or gender = 1 ),

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
);

create table if not exists `lib`
(
    id          char(19)    not null primary key,
    name        varchar(20) not null unique ,
    type        varchar(50) not null,

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
);

create table if not exists `course`
(
    id          char(19)    not null primary key,
    name        varchar(20) not null,
    credit      double      not null,
    time        double      not null,
    user_id     char(19)    not null,

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,

    index (user_id)
);

create table if not exists `occupation`
(
    id          char(19) not null primary key,
    lib_id      char(19) not null,
    course_id   char(19) not null,
    user_id     char(19) not null,
    start_week  int      not null check ( start_week > 0 ),
    end_week    int      not null check ( end_week > 0),
    day         int      not null check ( day > 0 and day <= 7 ),
    start_time int      not null check ( start_time > 0 and start_time <= 12),
    end_time   int      not null check ( end_time > 0 and end_time <= 12),

    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    index (course_id),
    index (user_id),
    index (lib_id)
);