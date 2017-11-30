package com.revature.assignments.bankingProject;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.assignments.bankingProject.CustomExceptions.AccountNotFoundException;
import com.revature.assignments.bankingProject.bankClasses.Bank;
import com.revature.assignments.bankingProject.bankClasses.LoginSystem;

import FileSaver.FileSaver;


public class Program {

	private static Logger logger = Logger.getLogger(Program.class);
	private Bank bank;
	private Scanner scanner;

	/**
	 * Program reads user input as commands and processes/sends
	 * them where they need to be.
	 */
	public Program() {
		scanner = new Scanner(System.in);

		while (true) {
			bank = new Bank();
			try {
				FileSaver.load(bank);
			} catch (IOException e) {
				logger.warn("Failed to load bank");
				System.out.println("Could not find file to load bank");
				return;
			}
			LoginSystem atm = new LoginSystem();

			String username = getUsername();
			if (username.equals("\\exit")) {
				break;
			}
			String password = getPassword();//username is set to Program variable inside this function

			if(atm.login(bank, username, password)) {
				runBankCommands(atm);
			}else {
				System.out.println("invalid credentials");
			}
		}

		scanner.close();
	}

	/**
	 * Runs the commands that prompt the user for input once they are logged in
	 * and processes them accordingly
	 * @param atm the access machine that is being logged in from
	 */
	public void runBankCommands(LoginSystem atm){
		System.out.println("What action would you like to perform?:");
		String username = "";
		Double amount = 0.0;
		boolean validInput = true;
		try {
			username = atm.getLoggedInUser();
		}catch (AccountNotFoundException e) {
			System.out.println("Not Logged In. Session Voided");
			return;
		}

		boolean logoutTime = false;

		while (!logoutTime){
			System.out.println("Balance | Withdraw | Deposit | Logout" + 
					"\n(Checks first letter of input)");
			String command = getUserInput();

			//switch case to read which command is being used
			switch (Character.toLowerCase(command.charAt(0))) {

			case 'b' : 
				System.out.println("Balance of " + username + " is $" + (bank.getAccount(username).getPrintableBalance()));
				break;

			case 'w' : 
				System.out.println("How much would you like to withdraw?");
				amount = 0.0;
				validInput = true;
				try {
					amount = Double.valueOf(getUserInput());
					if (amount<0) {
						validInput = false;
					}
				}catch (NumberFormatException e){//If user enters something that is not a double
					validInput = false;
				}
				if (validInput && amount<=bank.getAccount(username).getBalance()) {
					bank.getAccount(username).withdraw(amount);
					System.out.println("Successfully withdrew $" + amount + " from account");
					System.out.println("New balance is $" + bank.getAccount(username).getPrintableBalance());
				}else {
					System.out.println("Invalid entry for withdrawal");
				}
				break;
			case 'd' : 
				System.out.println("How much would you like to deposit?");
				amount = 0.0;
				validInput = true;
				try {
					amount = Double.valueOf(getUserInput());
					if(amount <0) {
						validInput = false;
					}
				}catch (NumberFormatException e){//If user enters something that is not a double
					validInput = false;
				}
				if (validInput) {
					bank.getAccount(username).deposit(amount);
					System.out.println("Successfully deposited $" + amount + " into account");
					System.out.println("New balance is $" + bank.getAccount(username).getPrintableBalance());
				}else {
					System.out.println("Invalid entry for deposit");
				}
				break;

			case 'l' : 
				if (atm.logout(bank)) {
					System.out.println("Logout successful. Bank information has been saved.");
					logoutTime=true;
				}else {
					System.out.println("WARNING!\nCould not logout");
				}
				break;
			default:
				System.out.println("Invalid Command Entered!");
			}
		}
	}

	/**
	 * Prompts user for input and returns it
	 * @return user input
	 */
	private String getUserInput(){
		System.out.println("Enter Input:");
		String input = "";
		while(input.equals("")) {
			input = scanner.nextLine();
		}
		return input;
	}

	/**
	 * Reads input username from user
	 * @return username
	 */
	private String getUsername(){
		String username = "";
		System.out.println("Enter your username or type \"\\exit\" to exit");
		username = scanner.nextLine();
		return username;
	}

	/**
	 * Reads input password from user
	 * @return password
	 */
	private String getPassword(){
		String password = "";
		System.out.println("Enter your password");
		password = scanner.nextLine();
		return password;
	}
}
