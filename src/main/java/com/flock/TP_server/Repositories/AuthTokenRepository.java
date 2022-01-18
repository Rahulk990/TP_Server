package com.flock.TP_server.Repositories;

import com.flock.TP_server.Models.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthTokenRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean checkToken(String token)
    {
        return Boolean.TRUE.equals(jdbcTemplate.query("select * from tokensTable where token = ?",
                ResultSet::next,
                token));
    }

    public boolean insertAuthToken(AuthToken authToken)
    {
        try{
            jdbcTemplate.update("insert into tokensTable (userId, token) values (?, ?)",
                    authToken.getUserId(), authToken.getToken());
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public AuthToken getAuthTokenByToken(String token)
    {
        return jdbcTemplate.queryForObject("select * from tokensTable where token = ?",
                new BeanPropertyRowMapper<AuthToken>(AuthToken.class),
                token);
    }

    static class AuthTokenRowMapper implements RowMapper<AuthToken> {
        @Override
        public AuthToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuthToken authToken = new AuthToken();
            authToken.setUserId(rs.getInt("userId"));
            authToken.setToken(rs.getString("token"));
            return authToken;
        }
    }

}
