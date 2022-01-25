package com.flock.TP_server.repositories;

import com.flock.TP_server.models.AuthToken;
import com.flock.TP_server.models.Contact;
import com.flock.TP_server.models.Transaction;
import com.flock.TP_server.models.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import static com.flock.TP_server.repositories.DBConstants.AuthColumns.*;
import static com.flock.TP_server.repositories.DBConstants.ContactsColumns.*;
import static com.flock.TP_server.repositories.DBConstants.TransactionColumns.*;
import static com.flock.TP_server.repositories.DBConstants.UserColumns.*;

public class JDBCParams {
    public static MapSqlParameterSource params(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(DBConstants.UserColumns.SQL_USER_ID, user.getUserId())
                .addValue(DBConstants.UserColumns.SQL_FULL_NAME, user.getFullName())
                .addValue(DBConstants.UserColumns.SQL_EMAIL, user.getEmail())
                .addValue(SQL_PASSWORD_HASH, user.getPassword());
        return params;
    }
    public static MapSqlParameterSource params(Contact contact) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(DBConstants.ContactsColumns.SQL_USER_ID, contact.getUserId())
                .addValue(SQL_CONTACT_ID, contact.getContactId())
                .addValue(DBConstants.ContactsColumns.SQL_FULL_NAME, contact.getFullName())
                .addValue(DBConstants.ContactsColumns.SQL_EMAIL, contact.getEmail())
                .addValue(SQL_ADDRESS, contact.getAddress())
                .addValue(SQL_PHONE_NUMBER, contact.getPhoneNumber())
                .addValue(SQL_SCORE, contact.getScore());
        return params;
    }

    public static MapSqlParameterSource params(AuthToken authToken) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(DBConstants.AuthColumns.SQL_USER_ID, authToken.getUserId())
                .addValue(SQL_TOKEN, authToken.getToken());
        return params;
    }

    public static MapSqlParameterSource params(Transaction transaction) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(DBConstants.TransactionColumns.SQL_USER_ID, transaction.getUserId())
                .addValue(SQL_TRANSACTION_ID, Long.parseLong(transaction.getTransactionId()))
                .addValue(SQL_TRANSACTION_STRING, transaction.getTransactionString());
        return params;
    }
}
