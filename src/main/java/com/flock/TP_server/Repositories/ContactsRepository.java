package com.flock.TP_server.repositories;

import com.flock.TP_server.exception.DuplicateEntryException;
import com.flock.TP_server.exception.ResourceNotFoundException;
import com.flock.TP_server.models.Contact;
import com.flock.TP_server.repositories.RowMappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static com.flock.TP_server.repositories.DBConstants.ContactsColumns.*;
import static com.flock.TP_server.repositories.DBConstants.ContactsQueries.*;
import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
@Validated
public class ContactsRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Contact> getUserContacts(Integer userId) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_USER_ID, userId);
        return jdbcTemplate.query(SQL_GET_USER_CONTACTS,
                params, new ContactRowMapper());

    }

    public Contact addUserContact(@Valid Contact contact) {
        try {
            jdbcTemplate.update(SQL_ADD_USER_CONTACT, params(contact));
            return contact;
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateEntryException("Contact Already Exists.");
        }
    }

    public Contact updateUserContact(@Valid Contact contact) {

        int rowCount = jdbcTemplate.update(SQL_UPDATE_USER_CONTACT, params(contact));
        if (rowCount == 0) {
            throw new ResourceNotFoundException("Contact Not Found");
        }
        return contact;

    }

    public void deleteUserContact(Integer userId, String contactId) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_USER_ID, userId);
        params.addValue(SQL_CONTACT_ID, contactId);
        int rowCount = jdbcTemplate.update(SQL_DELETE_USER_CONTACT, params);
        if (rowCount == 0) {
            throw new ResourceNotFoundException("Contact Not Found");
        }
    }
}