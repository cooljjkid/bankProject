package com.revature.assignments.bankingProject.bankClasses;

import java.util.concurrent.ConcurrentHashMap;

import com.revature.assignments.bankingProject.CustomExceptions.AccountNotFoundException;

public class Bank {
	
	private ConcurrentHashMap<String, BankAccount> accounts = new ConcurrentHashMap<String, BankAccount>();
	
	public Bank() {
		
	}
	
	/**
	 * Creates a new account if name is not already in the system. 
	 * @param name
	 * @return boolean if new account was made
	 */
	public boolean createAccount(String name, String password) {
		if(!accounts.containsKey(name)) {
			accounts.put(name, new BankAccount(name, 0, password));
			return true;
		}else return false;
	}
	
	/**
	 * Creates a new account if name is not already in the system. 
	 * @param name of client, amount to set up account with
	 * @return boolean if new account was made
	 */
	public boolean createAccount(String name, String password, double amount) {
		if(createAccount(name, password)){
			accounts.get(name).setBalance(amount);
			return true;
		}
		return false;
	}

	public boolean checkPassword(String name, String password) throws AccountNotFoundException{
		if(!accounts.containsKey(name)) {
			throw new AccountNotFoundException("Account not found while checking password");
		}
		
		return (accounts.get(name).getPassword().equals(password));
	}
	
	public BankAccount getAccount(String name) {
		return accounts.get(name);
	}
	
	public ConcurrentHashMap<String, BankAccount> getAccountsList(){
		return accounts;
	}

}