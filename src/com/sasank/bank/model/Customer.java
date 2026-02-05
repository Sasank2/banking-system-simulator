package com.sasank.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private final String customerId;
	private final String name;
	private final List<Account> accounts;
	
	public Customer(String customerId, String name) {
		this.customerId = customerId;
		this.name = name;
		this.accounts = new ArrayList<>();
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public String getName() {
		return name;
	}
	 
}
