package com.flock.TP_server.repositories;

import com.flock.TP_server.models.AuthToken;
import com.flock.TP_server.repositories.RowMappers.AuthTokenRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.sql.ResultSet;

import static com.flock.TP_server.repositories.DBConstants.AuthColumns.*;
import static com.flock.TP_server.repositories.DBConstants.AuthQueries.*;
import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
@Validated
public class AuthTokenRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkToken(String token) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_TOKEN, token);
        return Boolean.TRUE.equals(jdbcTemplate.query(SQL_GET_AUTH_TOKEN_BY_TOKEN,
                params, ResultSet::next));
    }

    public void insertAuthToken(@Valid AuthToken authToken) {
        jdbcTemplate.update(SQL_INSERT_AUTH_TOKEN, params(authToken));
    }

    public AuthToken getAuthTokenByToken(String token) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_TOKEN, token);
        return jdbcTemplate.queryForObject(SQL_GET_AUTH_TOKEN_BY_TOKEN,
                params, new AuthTokenRowMapper());
    }

}
