package com.sasank.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
	
	private final String transactionId;
	private final TransactionType transactionType;
	private final BigDecimal amount;
	private final LocalDateTime timeStamp;
	private Account sourceAccount;
	private Account targetAccount;
	
	public Transaction(TransactionType transactionType,BigDecimal amount, Account sourceAccount, Account targetAccount) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.transactionId = UUID.randomUUID().toString();
		this.timeStamp = LocalDateTime.now();
	}
	
	public String getTransactionId() {
	    return transactionId;
	}

	public TransactionType getTransactionType() {
	    return transactionType;
	}

	public BigDecimal getAmount() {
	    return amount;
	}

	public LocalDateTime getTimeStamp() {
	    return timeStamp;
	}

	public Account getSourceAccount() {
	    return sourceAccount;
	}

	public Account getTargetAccount() {
	    return targetAccount;
	}

}
