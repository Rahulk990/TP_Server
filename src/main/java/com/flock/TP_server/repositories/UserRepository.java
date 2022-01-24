package com.flock.TP_server.repositories;

import com.flock.TP_server.exception.DuplicateEntryException;
import com.flock.TP_server.models.User;
import com.flock.TP_server.repositories.RowMappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.sql.ResultSet;

import static com.flock.TP_server.repositories.DBConstants.UserColumns.*;
import static com.flock.TP_server.repositories.DBConstants.UserQueries.*;
import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkCredentials(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_EMAIL, user.getEmail());
        params.addValue(SQL_PASSWORD_HASH, user.getPassword());
        return Boolean.TRUE.equals(jdbcTemplate.query(SQL_CHECK_CREDENTIALS,
                params, ResultSet::next));

    }

    public void insertUser(@Valid User user) {
        try {
            jdbcTemplate.update(SQL_INSERT_USER, params(user));
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateEntryException("Email Id already exists");
        }
    }

    public User getUserByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_EMAIL, email);
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_EMAIL,
                params, new UserRowMapper());
    }

    public User getUserByUserId(Integer userId) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_USER_ID, userId);
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_USER_ID,
                params, new UserRowMapper());
    }
}
