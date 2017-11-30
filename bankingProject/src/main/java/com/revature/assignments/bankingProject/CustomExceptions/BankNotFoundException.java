package com.revature.assignments.bankingProject.CustomExceptions;

import org.apache.log4j.Logger;

public class BankNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5947162562278835065L;

	/**
	 * Exception for when account is not found
	 * @param exception
	 */
	private static Logger logger = Logger.getLogger(AccountNotFoundException.class);
	public BankNotFoundException(){
		super();
		logger.error("Could not find the bank file");
	}
	
	/**
	 * Exception for when account is not found
	 * @param exception
	 */
	public BankNotFoundException(String exception){
		super(exception);
	}
	
}
