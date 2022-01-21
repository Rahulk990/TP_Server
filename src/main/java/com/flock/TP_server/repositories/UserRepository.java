package com.flock.TP_server.repositories;

import com.flock.TP_server.exception.DBException;
import com.flock.TP_server.exception.DuplicateEntryException;
import com.flock.TP_server.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository implements DBConstants {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkCredentials(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_EMAIL, user.getEmail())
                .addValue(UserColumns.SQL_PASSWORD_HASH, user.getPasswordHash());
        try {
            boolean isMatch = Boolean.TRUE.equals(jdbcTemplate.query(UserQueries.SQL_CHECK_CREDENTIALS,
                    params, ResultSet::next));
            return isMatch;
        } catch(DataAccessException dataAccessException) {
            throw new DBException(dataAccessException.getMessage());
        }
    }

    public User insertUser(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_FULL_NAME, user.getFullName())
                .addValue(UserColumns.SQL_EMAIL, user.getEmail())
                .addValue(UserColumns.SQL_PASSWORD_HASH, user.getPasswordHash());

        try {
            jdbcTemplate.update(UserQueries.SQL_INSERT_USER, params);
            return user;
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateEntryException("Email Id already exists");
        } catch(DataAccessException dataAccessException) {
            throw new DBException(dataAccessException.getMessage());
        }
    }

    public User getUserByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_EMAIL, email);
        try {
            User user = jdbcTemplate.queryForObject(UserQueries.SQL_GET_USER_BY_EMAIL,
                    params, new UserRowMapper());
            return user;
        } catch(DataAccessException dataAccessException) {
            throw new DBException(dataAccessException.getMessage());
        }
    }

    public User getUserByUserId(Integer userId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_USER_ID, userId);
        try {
            User user = jdbcTemplate.queryForObject(UserQueries.SQL_GET_USER_BY_USER_ID,
                    params, new UserRowMapper());
            return user;
        } catch(DataAccessException dataAccessException) {
            throw new DBException(dataAccessException.getMessage());
        }
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt(UserColumns.SQL_USER_ID));
            user.setFullName(rs.getString(UserColumns.SQL_FULL_NAME));
            user.setEmail(rs.getString(UserColumns.SQL_EMAIL));
            user.setPasswordHash(rs.getString(UserColumns.SQL_PASSWORD_HASH));
            return user;
        }
    }
}
