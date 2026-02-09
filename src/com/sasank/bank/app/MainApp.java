package com.sasank.bank.app;

import java.util.List;

import com.sasank.bank.model.Account;
import com.sasank.bank.model.Transaction;

public class MainApp {
	
	public void accountSatement(Account account) {
			
		System.out.println("Account number : " + account.getAccountNumber() + " AccoutnHolder name : " + account.getAccountHolderName()+ "Account type : "+ account.getAccountType()+ "Account Balance" + account.getBalance());
			
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

	public static void main(String[] args) {
		
		System.out.println("Creating a new banking system simulator");

	}

}
