package com.flock.TP_server.repositories;

import com.flock.TP_server.models.AuthToken;
import com.flock.TP_server.models.Contact;
import com.flock.TP_server.models.Transaction;
import com.flock.TP_server.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flock.TP_server.repositories.DBConstants.AuthColumns.*;
import static com.flock.TP_server.repositories.DBConstants.ContactsColumns.*;
import static com.flock.TP_server.repositories.DBConstants.TransactionColumns.*;
import static com.flock.TP_server.repositories.DBConstants.UserColumns.*;

public class RowMappers {

    public static class AuthTokenRowMapper implements RowMapper<AuthToken> {
        @Override
        public AuthToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuthToken transaction = new AuthToken();
            transaction.setUserId(rs.getInt(DBConstants.AuthColumns.SQL_USER_ID));
            transaction.setToken(rs.getString(SQL_TOKEN));
            return transaction;
        }
    }

    public static class ContactRowMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer userId = rs.getInt(DBConstants.ContactsColumns.SQL_USER_ID);
            String contactId = rs.getString(SQL_CONTACT_ID);
            String fullName = rs.getString(DBConstants.ContactsColumns.SQL_FULL_NAME);
            String email = rs.getString(DBConstants.ContactsColumns.SQL_EMAIL);
            String address = rs.getString(SQL_ADDRESS);
            String phoneNumber = rs.getString(SQL_PHONE_NUMBER);
            Integer score = rs.getInt(SQL_SCORE);
            return new Contact(userId, contactId, fullName, email, address, phoneNumber, score);
        }
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt(DBConstants.UserColumns.SQL_USER_ID));
            user.setFullName(rs.getString(DBConstants.UserColumns.SQL_FULL_NAME));
            user.setEmail(rs.getString(DBConstants.UserColumns.SQL_EMAIL));
            user.setPassword(rs.getString(SQL_PASSWORD_HASH));
            return user;
        }
    }

    public static class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setUserId(rs.getInt(DBConstants.TransactionColumns.SQL_USER_ID));
            transaction.setTransactionId(rs.getLong(SQL_TRANSACTION_ID));
            transaction.setTransactionString(rs.getString(SQL_TRANSACTION_STRING));
            return transaction;
        }
    }
}
