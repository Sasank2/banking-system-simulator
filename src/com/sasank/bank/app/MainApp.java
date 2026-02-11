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


public class MainApp {
	
	public void accountSatement(Account account) {

	    System.out.println("=================================================");
	    System.out.println("Account Number : " + account.getAccountNumber());
	    System.out.println("Holder         : " + account.getAccountHolderName());
	    System.out.println("Type           : " + account.getAccountType());
	    System.out.println("Balance        : " + account.getBalance());
	    System.out.println("=================================================");

	    List<Transaction> transactions = account.getTransaction();

	    if (transactions.isEmpty()) {
	        System.out.println("No transactions found for this account.");
	        return;
	    }

	    BigDecimal totalIn = BigDecimal.ZERO;
	    BigDecimal totalOut = BigDecimal.ZERO;

	    DateTimeFormatter formatter =
	            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	    for (Transaction t : transactions) {

	        String direction;
	        if (account == t.getTargetAccount()) {
	            direction = "IN";
	            totalIn = totalIn.add(t.getAmount());
	        } else {
	            direction = "OUT";
	            totalOut = totalOut.add(t.getAmount());
	        }

	        System.out.println("-------------------------------------------------");
	        System.out.println("Type       : " + t.getTransactionType());
	        System.out.println("Amount     : " + t.getAmount());
	        System.out.println("Direction  : " + direction);
	        System.out.println("Time       : " + t.getTimeStamp().format(formatter));
	    }

	    System.out.println("-------------------------------------------------");
	    System.out.println("Total Transactions : " + transactions.size());
	    System.out.println("Total IN           : " + totalIn);
	    System.out.println("Total OUT          : " + totalOut);
	    System.out.println("-------------------------------------------------");
	}
	
	public void transactionByType(Account account, TransactionType type) {
		
		System.out.println(
				"Account number : " + account.getAccountNumber() +" | " +
				"AccoutHolder name : " + account.getAccountHolderName()+" | " +
				"Account type : "+ account.getAccountType()+" | " +
				"Account Balance : " + account.getBalance());
		
		List<Transaction> trade = account.getTransaction();
		if(trade.isEmpty()) {
			System.out.println("No transactions");
			return;
		}
		boolean foundTransaction = false;
		for(Transaction t : trade) {
			if(t.getTransactionType() == type) {
				foundTransaction = true;
				System.out.println(
			            t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp() + " | "+
			            t.getTargetAccount()
			        );
			}
		}
		if(!foundTransaction) {
			System.out.println("No deposit transactions found");
		}
			
		
	}
	
	public void transactionByDirection(Account account, Direction direction ) {
		System.out.println(
				"Account number : " + account.getAccountNumber() +" | " +
				"AccoutHolder name : " + account.getAccountHolderName()+" | " +
				"Account type : "+ account.getAccountType()+" | " +
				"Account Balance : " + account.getBalance());
		
		List<Transaction> trade = account.getTransaction();
		if(trade.isEmpty()) {
			System.out.println("No transactions");
			return;
		}
		
		boolean foundTransaction = false;
		for(Transaction t : trade) {
			if(account == t.getSourceAccount() && direction == Direction.OUT){
				foundTransaction = true;
				System.out.println(
						"OUT | "+
			            t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp()
			        );
			}else if(account == t.getTargetAccount() && direction == Direction.IN){
				foundTransaction = true;
				System.out.println(
						"IN | "+
			            t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp()
			        );
			}
		}
		if(!foundTransaction) {
			System.out.println("No deposit transactions found");
		}
	}
	
	public void transactionByAmount(Account account, BigDecimal amount, ComparisionType type ) {
		System.out.println(
				"Account number : " + account.getAccountNumber() +" | " +
				"AccoutHolder name : " + account.getAccountHolderName()+" | " +
				"Account type : "+ account.getAccountType()+" | " +
				"Account Balance : " + account.getBalance());
		
		List<Transaction> trade = account.getTransaction();
		if(trade.isEmpty()) {
			System.out.println("No transactions");
			return;
		}
		
		boolean foundTransaction = false;
		for(Transaction t : trade) {
			if(t.getAmount().compareTo(amount)>=0 && type == ComparisionType.GREATER_THAN) {
				foundTransaction = true;
				System.out.println(
						t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp()
			            );
			}
			if(t.getAmount().compareTo(amount)<=0 && type == ComparisionType.LESS_THAN) {
				foundTransaction = true;
				System.out.println(
						t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp()
			            );
			}
		}
		if(!foundTransaction) {
			System.out.println("No transactions found");
		}
	}
	
	public void transactionByTime(Account account,LocalDateTime start,  LocalDateTime end) {
		if (start == null || end == null) {
	        System.out.println("Invalid time range.");
	        return;
	    }

	    if (start.isAfter(end)) {
	        System.out.println("Start time cannot be after end time.");
	        return;
	    }

		System.out.println(
				"Account number : " + account.getAccountNumber() +" | " +
				"AccoutHolder name : " + account.getAccountHolderName()+" | " +
				"Account type : "+ account.getAccountType()+" | " +
				"Account Balance : " + account.getBalance());
		
		List<Transaction> trade = account.getTransaction();
		if(trade.isEmpty()) {
			System.out.println("No transactions");
			return;
		}
		
		boolean foundTransaction = false;
		for(Transaction t : trade) {
			LocalDateTime ts = t.getTimeStamp();
			boolean inRange = (ts.isAfter(start) || ts.isEqual(start)) &&
					(ts.isBefore(end) || ts.isEqual(end));
			if(inRange) {
				foundTransaction = true;
				System.out.println(
						t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp()
			            );
			}
		}
		if(!foundTransaction) {
			System.out.println("No transactions found between these time ranges");
		}
	}

	public static void main(String[] args) {
		
		System.out.println("Creating a new banking system simulator");

	}
//	public void menuHandler() {
//		boolean running = true;
//
//		while(running) {
//			System.out.println("========BANKING SYSTEM========" + '\n'+
//			        "1. Open New Account" + '\n' +
//			        "2. deposit Money" + '\n' +
//			        "3. Withdraw Money" + '\n' +
//			        "4. Transfer Money" + '\n' +
//			        "5. View Account Statement" + '\n' +
//			        "6. Filter Transaction" + '\n' +
//			        "7. Exit" + '\n'+
//			        "=============================="
//			);
//			System.out.print("Enter your choice : ");
//			int choice = sc.nextInt();
//			
//			switch (choice) {
//			case 1:
//				//service.account.openAccount();
//				break;
//			case 2:
//				service.deposit(account, null );
//				break;
//			case 3:
//				service.withdraw(account, null);
//				break;
//			case 4:
//				service.transfer(account, account, null);
//				break;
//			case 5:
//				app.accountSatement(account);
//				break;
//			case 6:
//			case 7:
//				running = false;
//				System.out.println("Thank you for using Banking System");
//		        break;
//			}
//		}
//		
//		}

}
