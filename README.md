# TP_Server
Training Project Server Side


## Configuring Database Schema
```
create database contactApp;
use contactApp;

create table usersTable (
    userId int auto_increment,
    fullName varchar(50) not null,
    email varchar(50) not null unique,
    passwordHash varchar(100) not null,
    primary key(userId)
);

create table tokensTable (
    userId int not null,
    token varchar(50) not null,
    primary key(userId, token),
    foreign key (userId) references usersTable(userId)
);
```
