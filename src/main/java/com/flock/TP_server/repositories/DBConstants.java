package com.flock.TP_server.repositories;

public interface DBConstants {

    interface AuthColumns {
        String SQL_USER_ID = "user_id";
        String SQL_TOKEN = "token";
    }

    interface AuthQueries {
        String SQL_GET_AUTH_TOKEN_BY_TOKEN = "select * from tokens_table where token=:token";
        String SQL_INSERT_AUTH_TOKEN = "insert into tokens_table (user_id, token) values (:user_id,:token)";
    }

    interface UserColumns {
        String SQL_USER_ID = "user_id";
        String SQL_FULL_NAME = "full_name";
        String SQL_EMAIL = "email";
        String SQL_PASSWORD_HASH = "password_hash";
    }

    interface UserQueries {
        String SQL_CHECK_CREDENTIALS = "select * from users_table where email = :email and password_hash = :password_hash";
        String SQL_INSERT_USER = "insert into users_table (full_name, email, password_hash) values (:full_name, :email, :password_hash)";
        String SQL_GET_USER_BY_USER_ID = "select * from users_table where user_id = :user_id";
        String SQL_GET_USER_BY_EMAIL = "select * from users_table where email = :email";
    }

    interface ContactsColumns {
        String SQL_USER_ID = "user_id";
        String SQL_CONTACT_ID = "contact_id";
        String SQL_FULL_NAME = "full_name";
        String SQL_EMAIL = "email";
        String SQL_ADDRESS = "address";
        String SQL_PHONE_NUMBER = "phone_number";
        String SQL_SCORE = "score";
    }

    interface ContactsQueries {
        String SQL_GET_USER_CONTACTS = "select * from contacts_table where user_id = :user_id order by full_name";
        String SQL_ADD_USER_CONTACT = "insert into contacts_table " +
                "(user_id, contact_id, full_name, email, address, phone_number, score)" +
                " values " +
                "(:user_id, :contact_id, :full_name, :email, :address, :phone_number, :score)";
        String SQL_UPDATE_USER_CONTACT = "update contacts_table set " +
                "full_name = :full_name, " +
                "email = :email, " +
                "address = :address, " +
                "phone_number = :phone_number, " +
                "score = :score " +
                "where user_id = :user_id and contact_id=:contact_id";
        String SQL_DELETE_USER_CONTACT = "delete from contacts_table " +
                "where user_id = :user_id and contact_id = :contact_id";
    }

}

