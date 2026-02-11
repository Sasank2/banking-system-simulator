package com.sasank.bank.app;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.sasank.bank.model.Account;
import com.sasank.bank.model.AccountType;
import com.sasank.bank.model.Customer;
import com.sasank.bank.service.BankingService;

public class ConsoleApp {
	
	BankingService service;
	Scanner sc = new Scanner(System.in);
	
	private final Map<String,Account> accounts = new HashMap<>();

	public void openAccountHandler() {
		System.out.println(" Enter customer name : ");
		String name = sc.nextLine();
		System.out.println(" Enter customerID ; ");
		String id = sc.nextLine();
		System.out.println(" Enter Account type (SAVINGS / CURRENT): ");
		AccountType accountType = AccountType.valueOf(sc.nextLine().toUpperCase());
		System.out.println("Enter openning balance : ");
		BigDecimal openningBalance = sc.nextBigDecimal();
		sc.nextLine();
		Customer customer = new Customer(id,name);
		Account account = service.openAccount(customer, accountType, openningBalance);
		accounts.put(account.getAccountNumber(), account);
		customer.addAccount(account);
		System.out.println("Account created successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
	}
	
	public void depositHandler() {
		
	}
	
	public Account getAccountbyNumber(String accountNumber) {
		return accounts.get(accountNumber);
	}
	
}
