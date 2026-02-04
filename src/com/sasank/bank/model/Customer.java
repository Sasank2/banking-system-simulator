package com.sasank.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private String customerId;
	private String name;
	private List<Account> accounts;
	
	public Customer(String customerId, String name) {
		this.customerId = customerId;
		this.name = name;
		this.accounts = new ArrayList<>();
	}
	
}
