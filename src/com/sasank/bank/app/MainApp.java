package com.sasank.bank.app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.sasank.bank.model.Account;
import com.sasank.bank.model.ComparisionType;
import com.sasank.bank.model.Direction;
import com.sasank.bank.model.Transaction;
import com.sasank.bank.model.TransactionType;


public class MainApp {
	
	public void accountSatement(Account account) {
			
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
		
		for(Transaction t : trade) {		
			if(t.getSourceAccount() == account){
				System.out.println(
			            "OUT | " +
			            t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp()
			        );
			}else if(t.getTargetAccount() == account) {
				System.out.println(
			            "IN | " +
			            t.getTransactionType() + " | " +
			            t.getAmount() + " | " +
			            t.getTimeStamp()
			        );
			}
		}
			
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

}
