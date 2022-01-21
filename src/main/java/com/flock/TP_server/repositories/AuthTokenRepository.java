package com.flock.TP_server.repositories;

import com.flock.TP_server.exception.DBException;
import com.flock.TP_server.models.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthTokenRepository implements DBConstants {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkToken(String token) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(AuthColumns.SQL_TOKEN, token);
        try {
            boolean isMatch = Boolean.TRUE.equals(jdbcTemplate.query(AuthQueries.SQL_GET_AUTH_TOKEN_BY_TOKEN,
                    params, ResultSet::next));
            return isMatch;
        } catch(DataAccessException dataAccessException) {
            throw new DBException(dataAccessException.getMessage());
        }
    }

    public void insertAuthToken(AuthToken authToken) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(AuthColumns.SQL_USER_ID, authToken.getUserId())
                .addValue(AuthColumns.SQL_TOKEN, authToken.getToken());
        try {
            jdbcTemplate.update(AuthQueries.SQL_INSERT_AUTH_TOKEN, params);
        } catch(DataAccessException dataAccessException) {
            throw new DBException(dataAccessException.getMessage());
        }
    }

    public AuthToken getAuthTokenByToken(String token) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(AuthColumns.SQL_TOKEN, token);
        try {
            AuthToken authToken = jdbcTemplate.queryForObject(AuthQueries.SQL_GET_AUTH_TOKEN_BY_TOKEN,
                    params, new AuthTokenRowMapper());
            return authToken;
        } catch(DataAccessException dataAccessException) {
            throw new DBException(dataAccessException.getMessage());
        }
    }

    static class AuthTokenRowMapper implements RowMapper<AuthToken> {
        @Override
        public AuthToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuthToken authToken = new AuthToken();
            authToken.setUserId(rs.getInt(AuthColumns.SQL_USER_ID));
            authToken.setToken(rs.getString(AuthColumns.SQL_TOKEN));
            return authToken;
        }
    }

}
