package com.revature.assignments.bankingProject;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.assignments.bankingProject.bankClasses.Bank;
import com.revature.assignments.bankingProject.bankClasses.LoginSystem;

public class LoginSystemTest {
	
	private Bank bank = new Bank();
	private Scanner scanner;
	@Before
	public void setUp(){		
		for (int i = 0; i<10000; i++) {
	    	bank.createAccount("Test"+Integer.toString(i), 
	    			"password"+Integer.toString(i), i);
	    	}
		scanner = new Scanner(System.in);
	}
	
	@Test
	public void loginTest(){
		LoginSystem atm = new LoginSystem();
		
		assertTrue(atm.login(bank, "Test0", "password0"));
		
		//checks all logins made in setup
		for (String username : bank.getAccountsList().keySet()) {
			assertTrue(atm.login(bank,username, "password" + username.substring(4) ));
		}
		
		
		
	}
	
	@After
	public void closeDown(){
		scanner.close();
	}
}
