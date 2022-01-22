package com.flock.TP_server.repositories;

import com.flock.TP_server.exception.DuplicateEntryException;
import com.flock.TP_server.exception.ResourceNotFoundException;
import com.flock.TP_server.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
@Validated
public class ContactsRepository implements DBConstants {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public List<Contact> getUserContacts(Integer userId) {
        MapSqlParameterSource jdbcParams = new MapSqlParameterSource();
        jdbcParams.addValue(ContactsColumns.SQL_USER_ID, userId);

        List<Contact> userContacts = jdbcTemplate.query(ContactsQueries.SQL_GET_USER_CONTACTS,
                jdbcParams, new SQLRowMapper.ContactRowMapper());
        return userContacts;
    }

    public Contact addUserContact(@Valid Contact contact) {
        MapSqlParameterSource jdbcParams = params(contact);
        try {
            jdbcTemplate.update(ContactsQueries.SQL_ADD_USER_CONTACT, jdbcParams);
            return contact;
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateEntryException("Contact Already Exists.");
        }
    }

    public Contact updateUserContact(@Valid Contact contact) {
        MapSqlParameterSource jdbcParams = params(contact);
        int rowCount = jdbcTemplate.update(ContactsQueries.SQL_UPDATE_USER_CONTACT, jdbcParams);
        if(rowCount == 0) {
            throw new ResourceNotFoundException("Contact Not Found");
        }
        return contact;
    }

    public void deleteUserContact(Integer userId, String contactId) {
        MapSqlParameterSource jdbcParams = new MapSqlParameterSource();
        jdbcParams.addValue(ContactsColumns.SQL_USER_ID, userId)
                .addValue(ContactsColumns.SQL_CONTACT_ID, contactId);
        int rowCount = jdbcTemplate.update(ContactsQueries.SQL_DELETE_USER_CONTACT, jdbcParams);
        if(rowCount == 0) {
            throw new ResourceNotFoundException("Contact Not Found");
        }
    }


}