package com.flock.TP_server.repositories;

import com.flock.TP_server.models.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.sql.ResultSet;

import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
@Validated
public class AuthTokenRepository implements DBConstants {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean checkToken(String token) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(AuthColumns.SQL_TOKEN, token);
        boolean isMatch = Boolean.TRUE.equals(jdbcTemplate.query(AuthQueries.SQL_GET_AUTH_TOKEN_BY_TOKEN,
                params, ResultSet::next));
        return isMatch;
    }

    public void insertAuthToken(@Valid AuthToken authToken) {
        MapSqlParameterSource params = params(authToken);
        jdbcTemplate.update(AuthQueries.SQL_INSERT_AUTH_TOKEN, params);
    }

    public AuthToken getAuthTokenByToken(String token) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(AuthColumns.SQL_TOKEN, token);

        AuthToken authToken = jdbcTemplate.queryForObject(AuthQueries.SQL_GET_AUTH_TOKEN_BY_TOKEN,
                params, new SQLRowMapper.AuthTokenRowMapper());
        return authToken;
    }



}
