package com.flock.TP_server.repositories;

import com.flock.TP_server.models.Transaction;
import com.flock.TP_server.repositories.RowMappers.TransactionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.flock.TP_server.repositories.DBConstants.TransactionQueries.*;
import static com.flock.TP_server.repositories.DBConstants.TransactionColumns.*;
import static com.flock.TP_server.repositories.JDBCParams.params;

@Repository
public class TransactionRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void insertTransaction(Transaction transaction) {
        jdbcTemplate.update(SQL_INSERT_TRANSACTION, params(transaction));
    }

    public List<Transaction> getNewTransactions(Integer userId, Long latestId) {
        MapSqlParameterSource params = new MapSqlParameterSource(SQL_USER_ID, userId);
        params.addValue(SQL_LATEST_ID, latestId);
        return jdbcTemplate.query(SQL_GET_NEW_TRANSACTIONS, params, new TransactionRowMapper());
    }
}
