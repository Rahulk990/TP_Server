package com.flock.TP_server.Repositories;

import com.flock.TP_server.Models.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthTokenRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkToken(String token)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", token);

        return Boolean.TRUE.equals(jdbcTemplate.query(SQL_QUERIES.SQL_GET_AUTH_TOKEN_BY_TOKEN,
                params, ResultSet::next));
    }

    public boolean insertAuthToken(AuthToken authToken)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", authToken.getUserId())
                .addValue("token", authToken.getToken());

        try
        {
            jdbcTemplate.update(SQL_QUERIES.SQL_INSERT_AUTH_TOKEN, params);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public AuthToken getAuthTokenByToken(String token)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", token);

        return jdbcTemplate.queryForObject(SQL_QUERIES.SQL_GET_AUTH_TOKEN_BY_TOKEN,
                params, new AuthTokenRowMapper());
    }

    static class AuthTokenRowMapper implements RowMapper<AuthToken> {
        @Override
        public AuthToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuthToken authToken = new AuthToken();
            authToken.setUserId(rs.getInt("user_id"));
            authToken.setToken(rs.getString("token"));
            return authToken;
        }
    }

}
