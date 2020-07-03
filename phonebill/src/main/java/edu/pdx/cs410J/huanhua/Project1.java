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
	
	private static void printErrorMessageAndExit(String message) {
		System.err.println(message);
		System.exit(1);
	}
	
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
		
		// 1. parses the command line
		for (String arg : args) {
			System.out.println(arg);
			
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
				++argc;
			}
		}
		
		
		if (argc != 7) {
			printErrorMessageAndExit("Missing command line arguments, need 5 arguement.");
			
		}
		
		
		// 2. creates an PhoneBill and a PhoneCall 
//		PhoneBill bill = new PhoneBill(customer, times, dates);
//		PhoneCall call = new PhoneCall();
		
		
		// 3. adds the PhoneCall to the	PhoneBill
		
		
		// 4. prints a description of the PhoneCall
		for (int i = 0; i < options.size(); ++i) {
			String option = options.get(i);
			if (option.equals("print")) {
				
			}
			else {
				throw new UnspportedOptionException("Using unsupported option: -" + option);
			}
		}
		
		System.exit(0);
	}
	
}