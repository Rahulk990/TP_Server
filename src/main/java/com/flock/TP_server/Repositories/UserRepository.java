package com.flock.TP_server.Repositories;

import com.flock.TP_server.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean checkCredentials(User user)
    {
        return Boolean.TRUE.equals(jdbcTemplate.query("select * from usersTable where email = ? and passwordHash = ?",
                ResultSet::next,
                user.getEmail(), user.getPasswordHash()));
    }

    public boolean insertUser(User user)
    {
        try{
            jdbcTemplate.update("insert into usersTable (fullName, email, passwordHash) values (?, ?, ?)",
                    user.getFullName(), user.getEmail(), user.getPasswordHash());
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public User getUserByEmail(String email)
    {
        return jdbcTemplate.queryForObject("select * from usersTable where email = ?",
                new BeanPropertyRowMapper<User>(User.class),
                email);
    }

    public User getUserByUserId(int userId)
    {
        return jdbcTemplate.queryForObject("select * from usersTable where userId = ?",
                new BeanPropertyRowMapper<User>(User.class),
                userId);
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setFullName(rs.getString("fullName"));
            user.setEmail(rs.getString("email"));
            user.setPasswordHash(rs.getString("passwordHash"));
            return user;
        }
    }
}
