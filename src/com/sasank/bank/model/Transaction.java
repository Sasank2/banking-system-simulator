package com.sasank.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
	
	private String transactionId;
	private TransactionType transactiontype;
	private BigDecimal amount;
	private LocalDateTime timeStamp;
	private Account sourceAccount;
	private Account targetAccount;
	
	public Transaction(TransactionType transactiontype,BigDecimal amount, Account sourceAccount, Account targetAccount) {
		this.transactiontype = transactiontype;
		this.amount = amount;
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.transactionId = UUID.randomUUID().toString();
		this.timeStamp = LocalDateTime.now();
	}
}
