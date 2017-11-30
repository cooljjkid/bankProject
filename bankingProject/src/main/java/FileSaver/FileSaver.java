package FileSaver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;

import com.revature.assignments.bankingProject.CustomExceptions.BankNotFoundException;
import com.revature.assignments.bankingProject.bankClasses.Bank;



public class FileSaver {

	public final static String filePath = "src/main/java/com.revature.assignments.bankingProject.files/bankSave.txt";
	
	
	/**
	 * Loads bank from default filePath
	 * @param bank
	 * @throws IOException
	 */
	public static void load(Bank bank) throws IOException {
		load(bank, filePath);
	}
	
	/**
	 * Loads to bank from given filePath
	 * @param bank
	 * @param filePath
	 * @throws IOException
	 */
	public static void load(Bank bank, String filePath) throws IOException{

		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		StringBuilder content = new StringBuilder();
		
		String row = "";
		while ((row = reader.readLine()) != null) {
			int firstTab = row.indexOf("\t");
			int secondTab = row.indexOf("\t", firstTab+1);
			String id = ""+row.subSequence(0, firstTab);
			double balance = Double.parseDouble(""+row.subSequence(firstTab+1, secondTab-1));
			String password = ""+row.substring(secondTab+1, row.length());
			
			bank.createAccount(id, password, balance);
		}

		reader.close();
	}


	/**
	 * Saves bank to default filePath
	 * @param bank
	 * @throws IOException
	 */
	public static void save(Bank bank) throws BankNotFoundException{
		save(bank,filePath);
	}
	
	/**
	 * Saves bank to given filePath
	 * @param bank
	 * @param filePath
	 * @throws IOException
	 * @throws BankNotFoundException 
	 */
	public static void save(Bank bank, String filePath) throws BankNotFoundException{
		String content = "";

		for (String username : bank.getAccountsList().keySet()) {
			content+=bank.getAccount(username).toString()+"\n";
		}
		
		try(Writer writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(content.toString());
		} catch (IOException e) {
			throw new BankNotFoundException();
		} 
	}
}
