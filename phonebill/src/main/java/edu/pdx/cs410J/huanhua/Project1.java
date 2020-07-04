package edu.pdx.cs410J.huanhua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The main class for the CS410J Phone Bill Project
 * 
 * * this program should accept times and dates that have already occurred as well as
 * ones that occur in the future.
 * 
 * usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>
 * args are (in this order):
 * customer Person whose phone bill we’re modeling
 * callerNumber Phone number of caller
 * calleeNumber Phone number of person who was called
 * start Date and time call began (24-hour time)
 * end Date and time call ended (24-hour time)
 * 
 * options are (options may appear in any order):
 * -print Prints a description of the new phone call
 * -README Prints a README for this project and exits
 * 
 * Date and time should be in the format: mm/dd/yyyy hh:mm
 * 
 */
public class Project1 {
	
	/**
	 * print an error msg and then exit
	 * 
	 * @param message
	 */
	private static void printErrorMessageAndExit(String message) {
		System.err.println(message);
		System.exit(1);
	}
	
	/**
	 * read file in project folder, and output file content
	 * 
	 * @param filename
	 * @return file String
	 */
	public static String readFile(String filename) {
		String line = "";
		try {
			InputStream readme = Project1.class.getResourceAsStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
			line = reader.readLine();
			
		}
		catch (IOException e) {
			line = e.getMessage();
		}
		
		return line;
	}
	
	public static void main(String[] args) {
		ArrayList<String> options = new ArrayList<String>();
		int argc = 0;
		String arguments[] = new String[7];
		
		// 1. parses the command line
		for (String arg : args) {
//			System.out.println(arg);
			
			if (arg.startsWith("-")) {
				String option = arg.substring(1);
				if (option.equals("README")) {
					
					System.out.println(readFile(("README.txt")));
					System.exit(0);
				}
				else {
					// add to option list
					options.add(option);
				}
			}
			else {
				arguments[argc] = arg;
				++argc;
			}
		}
		
		
		if (argc != 7) {
			printErrorMessageAndExit("Missing command line arguments, need 5 arguement.");
			
		}
		
		
		// 2. creates an PhoneBill and a PhoneCall
		String customer = arguments[0];
		String callerNumber = arguments[1];
		String calleeNumber = arguments[2];
		String startDateTime = arguments[3] + " " + arguments[4];
		String endDateTime = arguments[5] + " " + arguments[6];
		
		PhoneBill bill = new PhoneBill(customer);
		PhoneCall call = new PhoneCall(callerNumber, calleeNumber, startDateTime, endDateTime);
		
		
		// 3. adds the PhoneCall to the	PhoneBill
		bill.addPhoneCall(call);
		
		
		// 4. prints a description of the PhoneCall
		for (int i = 0; i < options.size(); ++i) {
			String option = options.get(i);
			if (option.equals("print")) {
				System.out.println(bill.toString());
			}
			else {
				throw new UnspportedOptionException("Using unsupported option: -" + option);
			}
		}
		
		System.exit(0);
	}
	
}