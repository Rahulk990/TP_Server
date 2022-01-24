package com.flock.TP_server.services;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.models.Transaction;
import com.flock.TP_server.repositories.TransactionRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flock.TP_server.utils.SequenceGenerator.nextId;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Gson gson;

    public void insertTransaction(Integer userId, Contact contact) {
        Transaction transaction = new Transaction(userId, nextId(), gson.toJson(contact));
        transactionRepository.insertTransaction(transaction);
    }

    public List<Transaction> getNewTransactions(Integer userId, Long latestId) {
        return transactionRepository.getNewTransactions(userId, latestId);
    }

    public Long getLatestId(Integer userId) {
        return nextId();
    }
}
