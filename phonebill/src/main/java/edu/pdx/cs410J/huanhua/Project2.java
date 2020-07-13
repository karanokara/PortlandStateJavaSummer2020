package edu.pdx.cs410J.huanhua;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The main class for the CS410J Phone Bill Project
 * 
 * this program should accept times and dates that have already occurred as well as
 * ones that occur in the future.
 * 
 * Goals: Learn how to use Java’s I/O facilities and throw exceptions.
 * 
 */
public class Project2 {
	
	/**
	 * read file in project folder, and output file content
	 * 
	 * @param filename
	 *            The filename
	 * @return file String
	 */
	public static String readFile(String filename) {
		String line = "";
		try {
			InputStream readme = Project1.class.getResourceAsStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
			String l = reader.readLine();
			while (l != null) {
				line += l + "\n";
				l = reader.readLine();
			}
		}
		catch (Exception e) {
			line = e.getMessage();
		}
		
		return line;
	}
	
	/**
	 * A main() optionally reads a PhoneBill from the contents of a text file,
	 * creates a new PhoneCall as specified on the command line, adds the PhoneCall to
	 * the PhoneBill, and then optionally writes the PhoneBill back to the text file.
	 * 
	 * @param args
	 *            The arguments
	 */
	public static void main(String[] args) {
		LinkedList<String> options = new LinkedList<String>();
		String arguments[] = new String[7];
		List<String> supportOptionslist = Arrays.asList("print", "textFile", "README");
		String textFilename = "";	// for storing -textFile "file"
		PhoneBill bill = null;
		int argc = -1;
		int passArgc = args.length;
		
		// 1. parse the command line
		for (int i = 0; i < passArgc; ++i) {
//			System.out.println(arg);
			String arg = args[i];
			
			if (arg.startsWith("-")) {
				String option = arg.substring(1);
				if (option.equals("README")) {
					
					System.out.println(readFile(("README2.txt")));
					System.exit(0);
				}
				else if (option.equals("textFile")) {
					textFilename = args[(++i >= passArgc) ? (i - 1) : i];
					
					if (textFilename.startsWith("-") || textFilename.isEmpty()) {
						System.err.println("Error: " + "Need a filename for using text file for phone bill.");
						System.exit(1);
					}
					
					options.addFirst(option);
				}
				else if (supportOptionslist.contains(option)) {
					// add to option list
					options.add(option);
				}
				else {
					System.err.println("Error: " + "Using unsupported option: -" + option);
					System.exit(1);
					
				}
			}
			else {
				++argc;
				
				if (argc >= 7) {
					System.err.println("Error: " + "Too much arguments, need 7 arguements.");
					System.exit(1);
				}
				
				arguments[argc] = arg;
			}
		}
		
		
		if (argc != 6) {
			System.err.println("Error: " + "Missing command line arguments, need 7 arguements.");
			System.exit(1);
		}
		
		
		// 4. prints a description of the PhoneCall
		for (int i = 0; i < options.size(); ++i) {
			String option = options.get(i);
			
			if (option.equals("textFile")) {
				try {
					// read the file into phone bill
					TextParser parser = new TextParser(textFilename);
					bill = parser.parse();
					
					if (bill == null) {
						System.out.println("Text file \"" + textFilename + "\" couldn't find, creating a new Phone Bill...");
						bill = createPhoneBillWithArguments(arguments);
					}
					else {
						System.out.println("Succeed to import text file \"" + textFilename + "\"");
						
						if (bill.getCustomer().equals(arguments[0])) {
							System.out.println("Inserting the new Phone Call...");
							
							// save the new phone call into bill
							PhoneCall call = createPhoneCallWithArguments(arguments);
							bill.addPhoneCall(call);
						}
						else {
							System.err.println("Error: " + "File customer's name \"" + bill.getCustomer() + "\" doesn't match argument customer's name \"" + arguments[0] + "\".");
							System.exit(1);
						}
					}
					
					// save the updated phone bill into file
					System.out.println("Dumping Phone Bill into text file \"" + textFilename + "\"...");
					TextDumper dumper = new TextDumper(textFilename);
					dumper.dump(bill);
					System.out.println("Done!");
				}
				catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
					System.exit(1);
				}
			}
//			else if (option.equals("print")) {
			else {
				if (bill == null) {
					try {
						bill = createPhoneBillWithArguments(arguments);
					}
					catch (IllegalArgumentException e) {
						System.err.println("Error: " + e.getMessage());
						System.exit(1);
					}
				}
				
				System.out.println("Phone Bill: \n");
				System.out.println(((LinkedList<PhoneCall>) bill.getPhoneCalls()).getLast().toString());
				
			}
			
		}
		
		// If nothing has performed, just validate the arguemnts
		if (bill == null) {
			try {
				bill = createPhoneBillWithArguments(arguments);
			}
			catch (IllegalArgumentException e) {
				System.err.println("Error: " + e.getMessage());
				System.exit(1);
			}
		}
		
		System.exit(0);
	}
	
	/**
	 * Create a phone bill base on program arguments
	 * 
	 * @param arguments
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static PhoneBill createPhoneBillWithArguments(String arguments[]) throws IllegalArgumentException {
		String customer = arguments[0];
		
		// 2.1. creates a PhoneBill
		PhoneBill bill = new PhoneBill(customer);
		
		
		// 3. adds the PhoneCall to the	PhoneBill
		bill.addPhoneCall(createPhoneCallWithArguments(arguments));
		
		return bill;
	}
	
	/**
	 * Create a phone call base on program arguments
	 * 
	 * @param arguments
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static PhoneCall createPhoneCallWithArguments(String arguments[]) throws IllegalArgumentException {
		String callerNumber = arguments[1];
		String calleeNumber = arguments[2];
		String startDateTime = arguments[3] + " " + arguments[4];
		String endDateTime = arguments[5] + " " + arguments[6];
		
		// 2.2 creates a PhoneCall
		PhoneCall call = new PhoneCall(callerNumber, calleeNumber, startDateTime, endDateTime);
		
		return call;
	}
}