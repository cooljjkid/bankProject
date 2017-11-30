package com.revature.assignments.bankingProject;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.assignments.bankingProject.CustomExceptions.AccountNotFoundException;
import com.revature.assignments.bankingProject.bankClasses.Bank;

public class BankTest {
	
	 	private Bank bank;
	 	
	    @Before
	    public void setUp(){
	    	bank = new Bank();
	    	
	    }
	    
	    @Test
	    public void addAccountTest(){
	    	assertTrue(bank.createAccount("Test", "password"));
	    	assertTrue(!bank.createAccount("Test", "password"));
	    	for (int i = 0; i<10000; i++) {
	    	assertTrue(bank.createAccount("Test"+Integer.toString(i), 
	    			"password"+Integer.toString(i), i));
	    	}
	    	assertTrue(bank.createAccount("Test01", "password"));
	    	
	    }
	    
	    @Test
	    public void passwordCheckTest(){
	    	bank.createAccount("Test", "password");
	    	try {
				bank.checkPassword("Test", "password");
			} catch (AccountNotFoundException e) {
				assertTrue(false);
			}
	    	
	    	boolean passFailed = false;
	    	try {
				bank.checkPassword("Test1", "password1");
			} catch (AccountNotFoundException e) {
				passFailed = true;
			}finally {
				assertTrue(passFailed);
			}
	    	
	    	try {
	    		assertTrue(!bank.checkPassword("Test", "wrong_password"));
			} catch (AccountNotFoundException e) {
				assertTrue(false);
			}
	    	
	    }

}
