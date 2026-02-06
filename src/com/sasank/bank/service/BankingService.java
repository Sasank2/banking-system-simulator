package com.sasank.bank.service;

import java.math.BigDecimal;

import com.sasank.bank.model.Account;
import com.sasank.bank.model.AccountType;
import com.sasank.bank.model.Customer;
import com.sasank.bank.model.Transaction;
import com.sasank.bank.model.TransactionType;

public class BankingService {
	

	public Account openAccount(Customer customer, AccountType accountType, BigDecimal initialBalance) {
		
		return null;
	}
	
	public void deposit(Account account, BigDecimal amount) {
		if (account == null) {
	        throw new IllegalArgumentException("Account cannot be null");
	    }
		
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0 ) {
			throw new IllegalArgumentException("Invalid amount");
		}
		  account.credit(amount);
		  Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, null, account);
		  account.addTransaction(transaction);
		
	}
	
	public void withdraw(Account account, BigDecimal amount) {
		if (account == null) {
	        throw new IllegalArgumentException("Account cannot be null");
	    }
		
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Invalid amount");
		}
		if(account.getBalance().compareTo(amount) < 0) {
			throw new IllegalArgumentException("Insufficient Funds");
		}
		account.debit(amount);
		Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, null, account);
		  account.addTransaction(transaction);
	}
	
	public void transfer(Account sourceAccount,Account targetAccount, BigDecimal amount) {
		
	}
}
