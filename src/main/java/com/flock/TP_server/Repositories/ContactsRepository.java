package com.flock.TP_server.Repositories;

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
import com.flock.TP_server.repositories.DBConstants.ContactsColumns;
@Repository
public class ContactsRepository implements DBConstants {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Contact> getUserContacts(int userId) {

    }

    public boolean insertUser(com.flock.TP_server.models.User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_FULL_NAME, user.getFullName())
                .addValue(UserColumns.SQL_EMAIL, user.getEmail())
                .addValue(UserColumns.SQL_PASSWORD_HASH, user.getPasswordHash());

        try {
            jdbcTemplate.update(UserQueries.SQL_INSERT_USER, params);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public com.flock.TP_server.models.User getUserByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_EMAIL, email);
        return jdbcTemplate.queryForObject(UserQueries.SQL_GET_USER_BY_EMAIL,
                params, new com.flock.TP_server.repositories.UserRepository.UserRowMapper());
    }

    public com.flock.TP_server.models.User getUserByUserId(int userId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_USER_ID, userId);
        return jdbcTemplate.queryForObject(UserQueries.SQL_GET_USER_BY_USER_ID,
                params, new com.flock.TP_server.repositories.UserRepository.UserRowMapper());
    }

    static class ContactRowMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            int userId = rs,getInt()
            Contact contact = new Contact();
            contact.setUserId(rs.getInt(ContactsColumns.SQL_USER_ID));
            user.setFullName(rs.getString(UserColumns.SQL_FULL_NAME));
            user.setEmail(rs.getString(UserColumns.SQL_EMAIL));
            user.setPasswordHash(rs.getString(UserColumns.SQL_PASSWORD_HASH));
            return user;
        }
    }
}