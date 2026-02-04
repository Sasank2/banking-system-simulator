package com.sasank.bank.model;

import java.math.BigDecimal;

public class Account {
	
	private String accountNumber;
	private String name;
	private BigDecimal balance;
	private AccountType accountType;
	
	public Account(String accountNumber, String name, BigDecimal balance, AccountType accountType) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.accountType = accountType;
	}

}
