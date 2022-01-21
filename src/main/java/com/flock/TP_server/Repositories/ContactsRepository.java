package com.flock.TP_server.repositories;

import com.flock.TP_server.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
public class ContactsRepository implements DBConstants {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

//    private MapSqlParameterSource getContactParamsObjForJDBC(Contact contact) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue(ContactsColumns.SQL_USER_ID, contact.getUserId())
//                .addValue(ContactsColumns.SQL_CONTACT_ID, contact.getContactId())
//                .addValue(ContactsColumns.SQL_FULL_NAME, contact.getFullName())
//                .addValue(ContactsColumns.SQL_EMAIL, contact.getEmail())
//                .addValue(ContactsColumns.SQL_ADDRESS, contact.getAddress())
//                .addValue(ContactsColumns.SQL_PHONE_NUMBER, contact.getPhoneNumber())
//                .addValue(ContactsColumns.SQL_SCORE, contact.getScore());
//        return params;
//    }

    public List<Contact> getUserContacts(Integer userId) {
        MapSqlParameterSource jdbcParams = new MapSqlParameterSource();
        jdbcParams.addValue(ContactsColumns.SQL_USER_ID, userId);
        return jdbcTemplate.query(ContactsQueries.SQL_GET_USER_CONTACTS,
                jdbcParams, new ContactRowMapper());
    }

    public Contact addUserContact(Contact contact) {
        MapSqlParameterSource jdbcParams = params(contact);
//        try {
            jdbcTemplate.update(ContactsQueries.SQL_ADD_USER_CONTACT, jdbcParams);
            return contact;
//        } catch (DataAccessException dataAccessException) {
//            return false;
//        }

    }

    public Contact updateUserContact(Contact contact) {
        MapSqlParameterSource jdbcParams = params(contact);
//        try {
            int rowCount = jdbcTemplate.update(ContactsQueries.SQL_UPDATE_USER_CONTACT, jdbcParams);
            if(rowCount == 0) {
                return
            }
            return contact;
//        } catch (DataAccessException dataAccessException) {
//            return false;
//        }
    }

    public boolean deleteUserContact(Integer userId, String contactId) {
        MapSqlParameterSource jdbcParams = new MapSqlParameterSource();
        jdbcParams.addValue(ContactsColumns.SQL_USER_ID, userId)
                .addValue(ContactsColumns.SQL_CONTACT_ID, contactId);
        try {
            jdbcTemplate.update(ContactsQueries.SQL_DELETE_USER_CONTACT, jdbcParams);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }

    }


    static class ContactRowMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer userId = rs.getInt(ContactsColumns.SQL_USER_ID);
            String contactId = rs.getString(ContactsColumns.SQL_CONTACT_ID);
            String fullName = rs.getString(ContactsColumns.SQL_FULL_NAME);
            String email = rs.getString(ContactsColumns.SQL_EMAIL);
            String address = rs.getString(ContactsColumns.SQL_ADDRESS);
            String phoneNumber = rs.getString(ContactsColumns.SQL_PHONE_NUMBER);
            Integer score = rs.getInt(ContactsColumns.SQL_SCORE);
            Contact contact = new Contact(userId, contactId, fullName, email, address, phoneNumber, score);
            return contact;
        }
    }
}