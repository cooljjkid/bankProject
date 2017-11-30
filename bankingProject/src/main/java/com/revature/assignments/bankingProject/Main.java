package com.revature.assignments.bankingProject;

import com.revature.assignments.bankingProject.bankClasses.Bank;

public class Main 
{

	private static Bank bank;

	public static void main( String[] args )
	{
		new Program();
		
	}

	
	
	
	public static void CreateNewBank() {
		bank= new Bank();
		bank.createAccount("John", "Fincken",1000);
		
		for (int i = 0; i<10000; i++) {
			bank.createAccount("Test"+Integer.toString(i), 
					"password"+Integer.toString(i), i);
		}
	}
}
