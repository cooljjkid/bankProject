package com.revature.assignments.bankingProject.bankClasses;

import java.text.DecimalFormat;

public class BankAccount {
	
	private String accountID;
	private double balance;
	private String password;
	
	public BankAccount(String accountID, double balance, String password){
		this.accountID = accountID;
		this.balance = balance;
		this.password = password;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * Gets password for the account
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password for the account
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the balance of the account
	 * @return double containing balance of the account 
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Get the string balance of the account rounded to 2 decimals 
	 * @return double containing balance of the account 
	 */
	public String getPrintableBalance() {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return (decimalFormat.format(balance));
	}

	/**
	 * Set the balance of the account. 
	 * Please note that this changes the balance in the account completely,
	 * it does NOT simply add funds to the account
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * Deposit funds into account
	 * @param amount
	 */
	public void deposit(double amount) {
		balance += amount;
	}
	
	/**
	 * Withdraw funds from account
	 * @param amount
	 */
	public void withdraw(double amount) {
		balance -= amount;
	}

	@Override
	public String toString() {
		return "" + accountID + "\t" + balance + "\t" + password;
	}
	
	
	
}
