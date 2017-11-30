package com.revature.assignments.bankingProject;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Before;
import org.junit.Test;

import com.revature.assignments.bankingProject.bankClasses.Bank;
import com.revature.assignments.bankingProject.bankClasses.BankAccount;


public class BankAccountTest {

	private Bank bank = new Bank();
	@Before
	public void setUp(){

		for (int i = 0; i<10000; i++) {
			bank.createAccount("Test"+Integer.toString(i), 
					"password"+Integer.toString(i), i);
		}
	}

	@Test
	public void accountIDTest(){		
		ConcurrentHashMap<String, BankAccount> accountsList = bank.getAccountsList();

		//check to verify all accounts have an ID of their hashCode Equivalent as default account ID
		for (String s : accountsList.keySet()) {
			assertTrue(s.equals(bank.getAccount(s).getAccountID()));
		}

		//check that accounts can be assigned a new ID 
		BankAccount tempAccount = bank.getAccount("Test0");
		assertTrue(!tempAccount.getAccountID().equals("0"));
		tempAccount.setAccountID("0");
		assertTrue(tempAccount.getAccountID().equals("0"));
	}

	@Test
	public void accountPasswordTest(){
		ConcurrentHashMap<String, BankAccount> accountsList = bank.getAccountsList();

		//check to verify all accounts were assigned their appropriate password
		for (String s : accountsList.keySet()) {
			String thisPassword = "password"+s.substring(4);
			assertTrue(thisPassword.equals(bank.getAccount(s).getPassword()));
		}

		String replacementPassword = "not_the_original_password";
		BankAccount tempAccount = bank.getAccount("Test0");
		assertTrue(!tempAccount.getPassword().equals(replacementPassword));
		tempAccount.setPassword("not_the_original_password");
		assertTrue(tempAccount.getPassword().equals(replacementPassword));
	}

	@Test
	public void accountBalanceChangesTest(){
		BankAccount tempAccount = bank.getAccount("Test0");
		assertTrue(tempAccount.getBalance() == 0);

		tempAccount.setBalance(9999.99);
		assertTrue(tempAccount.getBalance() >= 9999.99 && tempAccount.getBalance() < 10000.00);
		tempAccount.setBalance(0);
		assertTrue(tempAccount.getBalance() >= 0 && tempAccount.getBalance() < 1);
		tempAccount.deposit(123.45);
		assertTrue(tempAccount.getBalance() >= 123.45 && tempAccount.getBalance() < 123.46);
		tempAccount.withdraw(100);
		assertTrue(tempAccount.getBalance() >= 23.45 && tempAccount.getBalance() < 23.46);
	}
}
