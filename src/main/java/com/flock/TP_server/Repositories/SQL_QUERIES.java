package com.flock.TP_server.Repositories;

public class SQL_QUERIES {

    final static public String SQL_GET_AUTH_TOKEN_BY_TOKEN = "select * from tokens_table where token=:token";
    final static public String SQL_INSERT_AUTH_TOKEN = "insert into tokens_table (user_id, token) values (:user_id, :token)";

    final static public String SQL_CHECK_CREDENTIALS = "select * from users_table where email = :email and password_hash = :password_hash";
    final static public String SQL_INSERT_USER = "insert into users_table (full_name, email, password_hash) values (:full_name, :email, :password_hash)";
    final static public String SQL_GET_USER_BY_USER_ID = "select * from users_table where user_id = :user_id";
    final static public String SQL_GET_USER_BY_EMAIL = "select * from users_table where email = :email";
}
