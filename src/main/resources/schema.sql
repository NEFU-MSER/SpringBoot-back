# 用户表 用于登陆，该表主键可以绑定到课室表
create table if not exists `user`
(
    id          char(19)    not null primary key,
    account     char(11)    not null unique,
    id_card     char(18)    not null unique,
    password    varchar(20) not null,
    email       varchar(50),
    name        varchar(50) not null,
    gender      int         not null check ( gender = 0 or gender = 1 ),

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
);

# 科室表
create table if not exists `department`
(
    id          char(19)    not null primary key,
    parent_id   char(19)    not null,
    name        varchar(50) not null,
    description mediumtext,

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
);
# insert into department (id, parent_id, name)
# values ('0000000000000000000', '0000000000000000000', '无科室');

# 职务表
create table if not exists `role`
(
    id            char(19) not null primary key,
    department_id char(19) not null,
    name          varchar(50),
    expenses      double,

    create_time   datetime not null default current_timestamp,
    update_time   datetime not null default current_timestamp on update current_timestamp
);
# insert into role (id, department_id, name)
# values ('0000000000000000000', '0000000000000000000', '无职务');

# 用户职务表
create table if not exists `user_role`
(
    id          char(19) not null primary key,
    user_id     char(19) not null,
    role_id     char(19) not null,

    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    index (user_id),
    index (role_id)
);

# 医生表
create table if not exists `doctor`
(
    id          char(19)    not null primary key,
    role_id     char(19)    not null,
    id_card     char(18)    not null unique,
    email       varchar(50),
    name        varchar(50) not null,
    gender      int         not null check ( gender = 0 or gender = 1 ),

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,

    index (role_id)
);

# 患者表
create table if not exists `patient`
(
    id          char(19)    not null primary key,
    id_card     char(18)    not null unique,
    email       varchar(50),
    phone       char(11)    not null unique ,
    name        varchar(50) not null,
    gender      int         not null check ( gender = 0 or gender = 1 ),

    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
);

# 就诊卡表
create table if not exists `patient_card`
(
    id          char(19) not null primary key,
    patient_id  char(19) not null,
    balance     double   not null,
    enable      boolean           default true,

    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);

# 交易表
create table if not exists `trade_log`
(
    id      char(19) not null primary key,
    card_id char(19) not null,


)