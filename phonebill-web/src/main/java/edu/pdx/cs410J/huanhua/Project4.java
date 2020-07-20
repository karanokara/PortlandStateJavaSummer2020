package edu.pdx.cs410J.huanhua;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

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
	
	public static final String MISSING_ARGS = "Missing command line arguments";
	
	public static void main(String... args) {
		String hostName = null;
		String portString = null;
		String word = null;
		String definition = null;
		
		for (String arg : args) {
			if (hostName == null) {
				hostName = arg;
				
			}
			else if (portString == null) {
				portString = arg;
				
			}
			else if (word == null) {
				word = arg;
				
			}
			else if (definition == null) {
				definition = arg;
				
			}
			else {
				usage("Extraneous command line argument: " + arg);
			}
		}
		
		if (hostName == null) {
			usage(MISSING_ARGS);
			
		}
		else if (portString == null) {
			usage("Missing port");
		}
		
		int port;
		try {
			port = Integer.parseInt(portString);
			
		}
		catch (NumberFormatException ex) {
			usage("Port \"" + portString + "\" must be an integer");
			return;
		}
		
		PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
		
		String message;
		try {
			if (word == null) {
				// Print all word/definition pairs
				Map<String, String> dictionary = client.getAllDictionaryEntries();
				StringWriter sw = new StringWriter();
				Messages.formatDictionaryEntries(new PrintWriter(sw, true), dictionary);
				message = sw.toString();
				
			}
			else if (definition == null) {
				// Print all dictionary entries
				message = Messages.formatDictionaryEntry(word, client.getDefinition(word));
				
			}
			else {
				// Post the word/definition pair
				client.addDictionaryEntry(word, definition);
				message = Messages.definedWordAs(word, definition);
			}
			
		}
		catch (IOException ex) {
			outor("While contacting server: " + ex);
			return;
		}
		
		System.out.println(message);
		
		System.exit(0);
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
		
		System.exit(1);
	}
}