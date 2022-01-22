package com.flock.TP_server.repositories;

import com.flock.TP_server.models.AuthToken;
import com.flock.TP_server.models.Contact;
import com.flock.TP_server.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLRowMapper {

    static class AuthTokenRowMapper implements RowMapper<AuthToken> {
        @Override
        public AuthToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuthToken authToken = new AuthToken();
            authToken.setUserId(rs.getInt(DBConstants.AuthColumns.SQL_USER_ID));
            authToken.setToken(rs.getString(DBConstants.AuthColumns.SQL_TOKEN));
            return authToken;
        }
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt(DBConstants.UserColumns.SQL_USER_ID));
            user.setFullName(rs.getString(DBConstants.UserColumns.SQL_FULL_NAME));
            user.setEmail(rs.getString(DBConstants.UserColumns.SQL_EMAIL));
            user.setPassword(rs.getString(DBConstants.UserColumns.SQL_PASSWORD_HASH));
            return user;
        }
    }
    
    static class ContactRowMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer userId = rs.getInt(DBConstants.ContactsColumns.SQL_USER_ID);
            String contactId = rs.getString(DBConstants.ContactsColumns.SQL_CONTACT_ID);
            String fullName = rs.getString(DBConstants.ContactsColumns.SQL_FULL_NAME);
            String email = rs.getString(DBConstants.ContactsColumns.SQL_EMAIL);
            String address = rs.getString(DBConstants.ContactsColumns.SQL_ADDRESS);
            String phoneNumber = rs.getString(DBConstants.ContactsColumns.SQL_PHONE_NUMBER);
            Integer score = rs.getInt(DBConstants.ContactsColumns.SQL_SCORE);
            Contact contact = new Contact(userId, contactId, fullName, email, address, phoneNumber, score);
            return contact;
        }
    }
}
