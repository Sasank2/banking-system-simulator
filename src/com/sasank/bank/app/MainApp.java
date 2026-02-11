package com.sasank.bank.app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.sasank.bank.model.Account;
import com.sasank.bank.model.ComparisionType;
import com.sasank.bank.model.Direction;
import com.sasank.bank.model.Transaction;
import com.sasank.bank.model.TransactionType;
import com.sasank.bank.service.BankingService;


public class MainApp {

	public static void main(String[] args) {
		
		BankingService service = new BankingService();
		ConsoleApp consoleApp = new ConsoleApp(service);
		consoleApp.mainMenu();
	}
	
}