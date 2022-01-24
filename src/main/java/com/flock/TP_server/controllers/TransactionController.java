package com.flock.TP_server.controllers;

import com.flock.TP_server.models.LongWrapper;
import com.flock.TP_server.models.Transaction;
import com.flock.TP_server.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/latestId")
    public LongWrapper getLatestId(@RequestAttribute Integer userId) {
        return new LongWrapper(transactionService.getLatestId(userId));
    }

    @GetMapping("/updates/{latestId}")
    public List<Transaction> getUpdates(@RequestAttribute Integer userId, @PathVariable Long latestId) {
        return transactionService.getNewTransactions(userId, latestId);
    }
}
