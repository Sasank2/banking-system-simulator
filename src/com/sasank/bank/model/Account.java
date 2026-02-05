package com.sasank.bank.model;

import java.math.BigDecimal;

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

}
