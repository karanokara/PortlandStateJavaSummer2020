package edu.pdx.cs410J.huanhua;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.pdx.cs410J.web.HttpRequestHelper;


/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 * 
 * Goals:
 * Write a web application in Java
 * Work with HTTP-based network communication
 */
public class Project4 {
	
	/**
	 * A main() for interacting with the REST-ful Phone Bill Web Service
	 * Can do get and post PhoneBill from/to the running Phone Bill Web server
	 * 
	 * @param args
	 *            The arguments
	 */
	public static void main(String[] args) {
		int argc = -1;
		int requireArgc = 1;
		int passArgc = args.length;
		LinkedList<String> options = new LinkedList<String>();
		String arguments[] = new String[9];
		List<String> supportOptionslist = Arrays.asList("host", "port", "search", "README", "print");
		PhoneBill bill = null;
		PhoneCall newCall = null;
		String host = null;
		int port;
		String responseString = null;
		
		// 1. parse the command line
		for (int i = 0; i < passArgc; ++i) {
//			System.out.println(arg);
			String arg = args[i];
			
			if (arg.startsWith("-")) {
				String option = arg.substring(1);
				if (option.equals("README")) {
					usage();
				}
				else if (option.equals("host")) {
					host = (++i >= passArgc) ? "" : args[i];
					
					if (host.isEmpty()) {
						System.err.println("Error: " + "Need a hostname.");
						System.exit(1);
					}
				}
				else if (option.equals("port")) {
					String portString = (++i >= passArgc) ? "" : args[i];
					
					if (portString.isEmpty()) {
						System.err.println("Error: " + "Need a port number.");
						System.exit(1);
					}
					
					try {
						port = Integer.parseInt(portString);
					}
					catch (NumberFormatException ex) {
						System.err.println("Port \"" + portString + "\" must be an integer");
						System.exit(1);
					}
				}
				else if (option.equals("print")) {
					requireArgc = 9;
				}
				else if (option.equals("search")) {
					requireArgc = 7;
				}
				else if (!supportOptionslist.contains(option)) {
					System.err.println("Error: " + "Using unsupported option: -" + option);
					System.exit(1);
				}
				
				// add to option list
				if (!options.contains(option))
					options.add(option);
			}
			else {
				++argc;
				
				if (argc >= requireArgc) {
					System.err.println("Error: " + "Too much arguments, need maximum " + requireArgc + " arguements");
					System.exit(1);
				}
				
				arguments[argc] = arg;
			}
		}
		
		// no option, 1 for customer name, or 9 for all parameters
		if (requireArgc == 1 && (argc != 0 && argc != 8)) {
			System.err.println("Error: " + "Wrong number of arguments, need 1 arguments for customer name, or need 9 arguements for adding phone call");
			System.exit(1);
		}
		// with option, need exact # of args
		else if (argc != (requireArgc - 1)) {
			System.err.println("Error: " + "Wrong number of arguments, need " + requireArgc + " arguements.");
			System.exit(1);
		}
		
		
		PhoneBillRestClient client = new PhoneBillRestClient(host, port);
		
		if (argc == 0) {
			// get all phone calls
			responseString = client.getPhoneBill(arguments[0], null, null);
			
			// read the file into phone bill
			File file = new File("phonebilltempfile");
			file.deleteOnExit();
			
			PrintWriter pw = new PrintWriter(file);
			pw.print(responseString);
			pw.close();
			
			bill = TextParser.parseFile(file);
		}
		else if (argc == 6) {
			// search phone calls
		}
		else if (argc == 8) {
			// add phone call
			
		}
		
		// perform operation on arguemnts and options
		for (int i = 0; i < options.size(); ++i) {
			String option = options.get(i);
			
			// -print option
			if (option.equals("print")) {
				try {
					newCall = createPhoneCallWithArguments(arguments);
					bill = createPhoneBillWithArguments(arguments, newCall);
				}
				catch (IllegalArgumentException e) {
					System.err.println("Error: " + e.getMessage());
					System.exit(1);
				}
				
				System.out.println("New Phone Call:");
				System.out.println(newCall.toString());
				System.exit(0);
			}
			
			
			// pretty-print all calls
			if (option.equals("pretty")) {
				// print to console
				System.out.println(PrettyPrinter.constructPrettyOutput(bill));
				
			}
			
			if (option.equals("textFile")) {
				try {
					// read the file into phone bill
					TextParser parser = new TextParser(textFilename);
					bill = parser.parse();
					
					if (bill == null) {
						System.out.println("Text file \"" + textFilename + "\" couldn't find, creating a new Phone Bill...");
						newCall = createPhoneCallWithArguments(arguments);
						bill = createPhoneBillWithArguments(arguments, newCall);
					}
					else {
						System.out.println("Succeed to import text file \"" + textFilename + "\"");
						
						if (bill.getCustomer().equals(arguments[0])) {
							System.out.println("Inserting the new Phone Call...");
							
							// save the new phone call into bill
							newCall = createPhoneCallWithArguments(arguments);
							bill.addPhoneCall(newCall);
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
					System.out.println("Done!\n");
				}
				catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
					System.exit(1);
				}
			}
//			else if (option.equals("print" or "pretty")) {
			else {
				// no PhoneBill loaded from file
				if (bill == null) {
					try {
						newCall = createPhoneCallWithArguments(arguments);
						bill = createPhoneBillWithArguments(arguments, newCall);
					}
					catch (IllegalArgumentException e) {
						System.err.println("Error: " + e.getMessage());
						System.exit(1);
					}
				}
				
				if (option.equals("pretty")) {
					if (prettyFilename.equals("-")) {
						// print to console
						System.out.println(PrettyPrinter.constructPrettyOutput(bill));
					}
					else {
						// print to file
						try {
							PrettyPrinter prettyPrinter = new PrettyPrinter(prettyFilename);
							prettyPrinter.dump(bill);
						}
						catch (IOException e) {
							System.err.println("Error: " + e.getMessage());
							System.exit(1);
						}
					}
				}
				else {
					// -print option
					System.out.println("New Phone Call:");
					System.out.println(newCall.toString());
				}
			}
			
		}
		
		// If nothing has performed, just validate the arguments
		if (bill == null) {
			try {
				bill = createPhoneBillWithArguments(arguments, null);
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
	public static PhoneBill createPhoneBillWithArguments(String arguments[], PhoneCall call) throws IllegalArgumentException {
		String customer = arguments[0];
		
		// 2.1. creates a PhoneBill
		PhoneBill bill = new PhoneBill(customer);
		
		// 3. adds the PhoneCall to the	PhoneBill
		if (call == null)
			bill.addPhoneCall(createPhoneCallWithArguments(arguments));
		else
			bill.addPhoneCall(call);
		
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
		String startDateTime = arguments[3] + " " + arguments[4] + " " + arguments[5];
		String endDateTime = arguments[6] + " " + arguments[7] + " " + arguments[8];
		
		// 2.2 creates a PhoneCall
		PhoneCall call = new PhoneCall(callerNumber, calleeNumber, startDateTime, endDateTime);
		
		return call;
	}
	
	
	
	/**
	 * Makes sure that the give response has the expected HTTP status code
	 * 
	 * @param code
	 *            The expected status code
	 * @param response
	 *            The response from the server
	 */
	private static void checkResponseCode(int code, HttpRequestHelper.Response response) {
		if (response.getCode() != code) {
			outor(String.format("Expected HTTP code %d, got code %d.\n\n%s", code, response.getCode(), response.getContent()));
		}
	}
	
	private static void outor(String message) {
		PrintStream out = System.out;
		out.println("** " + message);
		
		System.exit(1);
	}
	
	/**
	 * Prints usage information for this program and exits
	 */
	private static void usage() {
		PrintStream out = System.out;
		out.println();
		out.println("usage: java Project4 [options] <args>");
		out.println("  args are (in this order):");
		out.println("    customer      Person whose phone bill we’re modeling");
		out.println("    callerNumber  Phone number of caller");
		out.println("    calleeNumber  Phone number of person who was called");
		out.println("    start         Date and time call began");
		out.println("    end           Date and time call ended");
		out.println("  options are (options may appear in any order):");
		out.println("    -host         Hostname Host computer on which the server runs");
		out.println("    -port         Port Port on which the server is listening");
		out.println("    -search       Phone calls should be searched for");
		out.println("    -print        Prints a description of the new phone call");
		out.println("    -README       Prints a README for this project and exits");
		out.println();
		
		System.exit(0);
	}
}