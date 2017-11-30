package com.revature.assignments.bankingProject.bankClasses;

import com.revature.assignments.bankingProject.CustomExceptions.AccountNotFoundException;
import com.revature.assignments.bankingProject.CustomExceptions.BankNotFoundException;

import FileSaver.FileSaver;

public class LoginSystem {

	private String loggedInUser = "";
	
	public LoginSystem() {
	}

	public boolean login(Bank bank, String username, String password) {
		
		if (username.equals(null) ||password.equals(null)
				||username.length()<=0||password.length()<=0) {
			return false;
		}
		
		boolean ret = false;
		try {
			ret = bank.checkPassword(username, password);
			if(ret) {
				loggedInUser = username;
			}
		}catch (AccountNotFoundException e){
			System.out.println("Account not found");
		}
		return ret;
	}



	public boolean logout(Bank bank){
		try {
			FileSaver.save(bank);
		} catch (BankNotFoundException e) {
			System.out.println("Could not logout");
			return false;
		}
		return true;
	}

	public String getLoggedInUser() throws AccountNotFoundException {
		if (loggedInUser.length()<=0) {
			throw new AccountNotFoundException("Not logged in; Cannot provide a logged in username");
		}
		return loggedInUser;
	}
}
