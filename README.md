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
```
