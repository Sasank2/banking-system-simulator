package com.sasank.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private final String accountNumber;
	private final String accountHolderName;
	private BigDecimal balance;
	private final AccountType accountType;
	
	public Account(String accountNumber, String accountHolderName, BigDecimal balance, AccountType accountType) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.accountType = accountType;
	}
	
	public final List<Transaction> transactions = new ArrayList<>();
	
	public String getAccountNumber() {
		
		return accountNumber;
	}
	
	public String getAccountHolderName() {
		return accountHolderName;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void credit(BigDecimal amount) {
		this.balance = this.balance.add(amount);
	}
	
	public void debit(BigDecimal amount) {
		this.balance = this.balance.subtract(amount);
	}
	
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	

}
