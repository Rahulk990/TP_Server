package com.flock.TP_server.Repositories;

import com.flock.TP_server.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkCredentials(User user)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail())
                .addValue("password_hash", user.getPasswordHash());

        return Boolean.TRUE.equals(jdbcTemplate.query(SQL_QUERIES.SQL_CHECK_CREDENTIALS,
                params, ResultSet::next));
    }

    public boolean insertUser(User user)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("full_name", user.getFullName())
                .addValue("email", user.getEmail())
                .addValue("password_hash", user.getPasswordHash());

        try
        {
            jdbcTemplate.update(SQL_QUERIES.SQL_INSERT_USER, params);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public User getUserByEmail(String email)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        return jdbcTemplate.queryForObject(SQL_QUERIES.SQL_GET_USER_BY_EMAIL,
                params, new UserRowMapper());
    }

    public User getUserByUserId(int userId)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);

        return jdbcTemplate.queryForObject(SQL_QUERIES.SQL_GET_USER_BY_USER_ID,
                params, new UserRowMapper());
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setFullName(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setPasswordHash(rs.getString("password_hash"));
            return user;
        }
    }
}
