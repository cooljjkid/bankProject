package com.revature.assignments.bankingProject.CustomExceptions;

import org.apache.log4j.Logger;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = -5947162562278835065L;

	/**
	 * Exception for when account is not found
	 * @param exception
	 */
	private static Logger logger = Logger.getLogger(AccountNotFoundException.class);
	public AccountNotFoundException(){
		super();
		logger.warn("Attempt to access with invalid credentials");
	}
	
	/**
	 * Exception for when account is not found
	 * @param exception
	 */
	public AccountNotFoundException(String exception){
		super(exception);
		logger.warn("Attempt to access with invalid credentials");
	}
	
}
