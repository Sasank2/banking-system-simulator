package com.sasank.bank.app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sasank.bank.model.Account;
import com.sasank.bank.model.AccountType;
import com.sasank.bank.model.ComparisionType;
import com.sasank.bank.model.Customer;
import com.sasank.bank.model.Direction;
import com.sasank.bank.model.Transaction;
import com.sasank.bank.model.TransactionType;
import com.sasank.bank.service.BankingService;

public class ConsoleApp {
	
	private final BankingService service;
	private final Map<String,Account> accounts = new HashMap<>();
	Scanner sc = new Scanner(System.in);
	
	 public ConsoleApp(BankingService service) {
	        this.service = service;
	    }
		public void mainMenu() {
			boolean running = true;
			
			while(running) {
				printMainMenu();
				System.out.println("Enter your choice");
				int choice;
				try {
					choice = sc.nextInt();
				 } catch (InputMismatchException e) {
			            System.out.println("Invalid input. Please enter a number.");
			            sc.nextLine(); 
			            continue;
				 }
				sc.nextLine();
				
				switch(choice) {
				case 1 :
					openAccountHandler();
					break;
				case 2 :
					depositHandler();
					break;
				case 3 :
					withdrawHandler();
					break;
				case 4 :
					transferHandler();
					break;
				case 5 :
					transactionHandler();
					break;
				case 6 :
					running = false;
					System.out.println("Thanks for using");
					break;
				
				}
			}
		}
	private void printMainMenu() {

	    System.out.println();
	    System.out.println("======================================");
		System.out.println("          BANKING SYSTEM MENU         ");
	    System.out.println("======================================");
	    System.out.println("1. Open Account");
	    System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Transfer");
		System.out.println("5. View Account Statement");
		System.out.println("6. Exit");
		System.out.println("======================================");
		}
	
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
		Account account = getValidAccount();
		System.out.println(" Enter Amount : ");
		BigDecimal amount = sc.nextBigDecimal();
		sc.nextLine();
		try{
			service.deposit(account, amount);
			System.out.println("Amount credited successfully. Current balance : " + account.getBalance());
		} catch (IllegalArgumentException e) {
			System.out.println( e.getMessage());
		}
	}
	
	public void withdrawHandler() {
		Account account = getValidAccount();
		System.out.println(" Enter Amount : ");
		BigDecimal amount = sc.nextBigDecimal();
		sc.nextLine();
		try {
			service.withdraw(account, amount);
			System.out.println("Amount withdrawl successfully. Current balance : " + account.getBalance());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void transferHandler() {
		System.out.println(" Enter source AccountNumber : ");
		Account sourceAccount = getValidAccount();
		if(sourceAccount == null) {
			return;
		}
		System.out.println(" Enter target AccountNumber : ");
		Account targetAccount = getValidAccount();
		if(targetAccount == null) {
			return;
		}
		if(sourceAccount == targetAccount) {
			System.out.println("Invalid account number");
			return;
		}
		System.out.println(" Enter Amount : ");
		BigDecimal amount = sc.nextBigDecimal();
		sc.nextLine();
		try {
			service.transfer(sourceAccount,targetAccount, amount);
			System.out.println("Amount Credited successfully. Current balance : " + targetAccount.getBalance());
			System.out.println("Amount Debited successfully. Current balance : " + sourceAccount.getBalance());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void transactionHandler() {
		Account account = getValidAccount();
		if (account == null) return;
		System.out.println("========Account Statement========" + '\n'+
				"1. All Transactions\n" +
	            "2. Transaction Type\n" +
	            "3. Transaction IN/OUT\n" +
	            "4. Transaction by Amount\n" +
	            "5. Transaction by Time");
		
		System.out.print("Enter your choice between 1 to 5: ");
		int choice;
		try {
			choice= sc.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Invalid input. please enter a number");
			sc.nextLine();
	        return;
		}
		
		sc.nextLine();
		
		switch (choice) {
		case 1:
			accountSatement(account);
			break;
			
		case 2:
			System.out.println(" Enter AccountType - SAVINGS/CHECKING/CURRENT : ");
			String type = sc.nextLine();
						
			try {
				TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
				System.out.println("Selected : " + transactionType);
				transactionByType(account, transactionType);
			}catch (IllegalArgumentException e) {
			    System.out.println("Invalid type.");
			    return;
			}
			break;
			
		case 3:
			System.out.println(" Enter Direction - IN/OUT : ");
			String direction = sc.nextLine();
			
			try {
				Direction d = Direction.valueOf(direction.toUpperCase());
				System.out.println("Selected :" + d);
				transactionByDirection(account, d);
			}catch (IllegalArgumentException e) {
			    System.out.println("Invalid direction.");
			    return;
			}
			break;
			
		case 4:
			System.out.println(" Enter transaction amount : ");
			BigDecimal amount;
			try {
				amount = sc.nextBigDecimal();
			}catch(InputMismatchException e) {
				System.out.println("Invalit input. please enter a number");
				sc.nextLine();
				return;
			}
			sc.nextLine();
			
			System.out.println(" Enter Comparision type - GREATER_THAN/LESS_THAN: ");
			String comparissionType = sc.nextLine();
			
			try {
				ComparisionType cType = ComparisionType.valueOf(comparissionType.toUpperCase());
				System.out.println("Selected : " + cType);
				transactionByAmount(account, amount, cType);
			}catch (IllegalArgumentException e) {
			    System.out.println("Invalid type.");
			    return;
			}
			break;
			
		case 5:
			 System.out.println("Enter begin time (yyyy-MM-dd HH:mm): ");
	            String beginInput = sc.nextLine();

	            System.out.println("Enter end time (yyyy-MM-dd HH:mm): ");
	            String endInput = sc.nextLine();

	            try {
	                DateTimeFormatter formatter =
	                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	                LocalDateTime begin =
	                        LocalDateTime.parse(beginInput, formatter);

	                LocalDateTime end =
	                        LocalDateTime.parse(endInput, formatter);

	                transactionByTime(account, begin, end);

	            } catch (DateTimeParseException e) {
	                System.out.println("Invalid date format.");
	                return;
	            }
	            break;
	            
	            default :
	            	System.out.println("Invalid choice.");
		}
	}
		
	public Account getAccountbyNumber(String accountNumber) {
		return accounts.get(accountNumber);
	}
	
	private Account getValidAccount() {
		System.out.println(" Enter AccountNumber : ");
		String accountNumber = sc.nextLine();
		Account account = accounts.get(accountNumber);
		
		if(account == null) {
		    System.out.println("Invalid Account number");
		    return null;
		}
		return account;
	}
}
