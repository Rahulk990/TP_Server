package com.flock.TP_server.repositories;

import com.flock.TP_server.exception.DuplicateEntryException;
import com.flock.TP_server.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.sql.ResultSet;

import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
public class UserRepository implements DBConstants {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkCredentials(User user) {
        MapSqlParameterSource params = params(user);
        boolean isMatch = Boolean.TRUE.equals(jdbcTemplate.query(UserQueries.SQL_CHECK_CREDENTIALS,
                params, ResultSet::next));
        return isMatch;
    }

    public User insertUser(User user) {
        MapSqlParameterSource params = params(user);

        try {
            jdbcTemplate.update(UserQueries.SQL_INSERT_USER, params);
            return user;
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateEntryException("Email Id already exists");
        }
    }

    public User getUserByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_EMAIL, email);
        User user = jdbcTemplate.queryForObject(UserQueries.SQL_GET_USER_BY_EMAIL,
                params, new SQLRowMapper.UserRowMapper());
        return user;
    }

    public User getUserByUserId(Integer userId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(UserColumns.SQL_USER_ID, userId);
        User user = jdbcTemplate.queryForObject(UserQueries.SQL_GET_USER_BY_USER_ID,
                params, new SQLRowMapper.UserRowMapper());
        return user;
    }

}
