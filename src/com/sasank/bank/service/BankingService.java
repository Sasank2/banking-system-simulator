package com.sasank.bank.service;

import java.math.BigDecimal;

import com.sasank.bank.model.Account;
import com.sasank.bank.model.AccountType;
import com.sasank.bank.model.Customer;

public class BankingService {

	public Account openAccount(Customer customer, AccountType accountType, BigDecimal initialBalance) {
		
		return null;
	}
	
	public void deposit(Account account, BigDecimal amount) {
		
	}
	
	public void withdraw(Account account, BigDecimal amount) {
		
	}
	
	public void transfer(Account sourceAccount,Account targetAccount, BigDecimal amount) {
		
	}
}
