//package edu.pdx.cs410J.huanhua;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
///**
// * The main class for the CS410J Phone Bill Project
// * 
// * this program should accept times and dates that have already occurred as well as
// * ones that occur in the future.
// * 
// * Goals: Extend classes that you did not write and perform more complex command line parsing
// * 
// */
//public class Project1 {
//	
//	/**
//	 * print an error msg and then exit
//	 * 
//	 * @param message
//	 */
//	private static void printErrorMessageAndExit(String message) {
//		System.err.println(message);
//		System.exit(1);
//	}
//	
//	/**
//	 * read file in project folder, and output file content
//	 * 
//	 * @param filename
//	 *            The filename
//	 * @return file String
//	 */
//	public static String readFile(String filename) {
//		String line = "";
//		try {
//			InputStream readme = Project1.class.getResourceAsStream(filename);
//			BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
//			String l = reader.readLine();
//			while (l != null) {
//				line += l + "\n";
//				l = reader.readLine();
//			}
//		}
//		catch (Exception e) {
//			line = e.getMessage();
//		}
//		
//		return line;
//	}
//	
//	/**
//	 * Main program that parses the command line, creates a
//	 * <code>PhoneBill</code>, add a <code>PhoneCall</code>
//	 * then prints a description of the phone bill
//	 * standard out by invoking its <code>toString</code> method.
//	 * 
//	 * @param args
//	 *            The arguments
//	 */
//	public static void main(String[] args) {
//		ArrayList<String> options = new ArrayList<String>();
//		int argc = -1;
//		String arguments[] = new String[7];
//		
//		// 1. parses the command line
//		for (String arg : args) {
////			System.out.println(arg);
//			
//			if (arg.startsWith("-")) {
//				String option = arg.substring(1);
//				if (option.equals("README")) {
//					
//					System.out.println(readFile(("README.txt")));
//					System.exit(0);
//				}
//				else {
//					// add to option list
//					options.add(option);
//				}
//			}
//			else {
//				++argc;
//				
//				if (argc >= 7) {
//					printErrorMessageAndExit("Too much arguments, need 7 arguements.");
//					
//				}
//				
//				arguments[argc] = arg;
//			}
//		}
//		
//		
//		if (argc != 6) {
//			printErrorMessageAndExit("Missing command line arguments, need 7 arguements.");
//			
//		}
//		
//		
//		String customer = arguments[0];
//		String callerNumber = arguments[1];
//		String calleeNumber = arguments[2];
//		String startDateTime = arguments[3] + " " + arguments[4];
//		String endDateTime = arguments[5] + " " + arguments[6];
//		PhoneBill bill = null;
//		PhoneCall call = null;
//		
//		// 2. creates an PhoneBill and a PhoneCall
//		try {
//			bill = new PhoneBill(customer);
//			call = new PhoneCall(callerNumber, calleeNumber, startDateTime, endDateTime);
//			
//		}
//		catch (IllegalArgumentException e) {
//			printErrorMessageAndExit(e.getMessage());
//		}
//		
//		// 3. adds the PhoneCall to the	PhoneBill
//		bill.addPhoneCall(call);
//		
//		
//		// 4. prints a description of the PhoneCall
//		try {
//			for (int i = 0; i < options.size(); ++i) {
//				String option = options.get(i);
//				if (option.equals("print")) {
//					System.out.println(bill.toString());
//				}
//				else {
//					throw new UnSupportedOptionException("Using unsupported option: -" + option);
//				}
//			}
//		}
//		catch (UnSupportedOptionException e) {
//			printErrorMessageAndExit(e.getMessage());
//		}
//		
//		
//		System.exit(0);
//	}
//	
//}