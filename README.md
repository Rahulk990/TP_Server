# TP_Server
Training Project Server Side


## Configuring Database Schema
```
create database contact_app;
use contact_app;

create table users_table (
    user_id int auto_increment,
    full_name varchar(50) not null,
    email varchar(50) not null unique,
    password_hash varchar(100) not null,
    primary key(user_id)
);

create table tokens_table (
    user_id int not null,
    token varchar(50) not null,
    primary key(user_id, token),
    foreign key (user_id) references users_table(user_id)
);

create table contacts_table (
    user_id int not null,
    contact_id varchar(100) not null,
    full_name varchar(100) not null,
    email varchar(100) not null,
    address varchar(300),
    phone_number varchar(20) not null,
    score int not null default 0,
    foreign key (user_id) references users_table(user_id),
    primary key(user_id, contact_id)
);

create table transactions_table (
    user_id int not null,
    transaction_id bigint not null,
    transaction_string varchar(1000) not null,
    primary key(user_id, transaction_id),
    foreign key (user_id) references users_table(user_id)
);

create unique index idx_email on users_table(email);
create unique index idx_token on tokens_table(token);
```
