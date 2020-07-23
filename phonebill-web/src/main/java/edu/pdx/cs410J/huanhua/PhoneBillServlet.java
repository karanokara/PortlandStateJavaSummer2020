package edu.pdx.cs410J.huanhua;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.
 * 
 * Usage:
 * 
 * http://host:port/phonebill/calls?customer=name
 * - GET returns all calls in the phone bill formatted using the TextDumper
 * - POST creates a new call from the HTTP request parameters customer, callerNumber,
 * calleeNumber, start, and end. If the phone bill does not exist, a new one should be
 * created.
 * 
 * 
 * http://host:port/phonebill/calls?customer=name&start=startDateTime&end=endDateTime
 * - GET returns all of given phone bill’s calls (in the format used by TextDumper)
 * that occurred between the start date/time and the end date/time. The date/time format in the
 * URL is the same as on the command line.
 * 
 */
public class PhoneBillServlet extends HttpServlet {
	
	static final String CUSTOMER_PARAMETER = "customer";
	static final String CALLER_PARAMETER = "callerNumber";
	static final String CALLEE_PARAMETER = "calleeNumber";
	static final String STARTDATETIME_PARAMETER = "start";
	static final String ENDDATETIME_PARAMETER = "end";
	
	// mapping name -> PhoneBill
	private final Map<String, PhoneBill> dictionary = new HashMap<String, PhoneBill>();
	
	/**
	 * Handles an HTTP GET request from a client by fetching PhoneCalls from
	 * an existing PhoneBill by customer name
	 * /phonebill/calls?customer=name
	 * 
	 * Can also use filter by providing start and end date
	 * /phonebill/calls?customer=name&start=startDateTime&end=endDateTime
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		
		String customer = getParameter(request, CUSTOMER_PARAMETER);
		String start = getParameter(request, STARTDATETIME_PARAMETER);
		String end = getParameter(request, ENDDATETIME_PARAMETER);
		
		if (customer == null) {
			missingRequiredParameter(response, CUSTOMER_PARAMETER);
			return;
		}
		
		if (start == null || end == null) {
			// return all calls in a phone bill
			sendPhoneBill(response, customer, null, null);
		}
		else {
			// perform search from time start -> end
			PhoneCall tempPhoneCall = null;
			try {
				tempPhoneCall = new PhoneCall("111-111-1234", "111-111-1235", start, end);
			}
			catch (IllegalArgumentException e) {
				invalidRequiredParameter(response, e.getMessage());
				return;
			}
			
			sendPhoneBill(response, customer, tempPhoneCall.getStartTime(), tempPhoneCall.getEndTime());
		}
		
	}
	
	/**
	 * Handles an HTTP POST request by storing an PhoneCall entry for a
	 * PhoneBill or create a new PhoneBill
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		
		String customer = getParameter(request, CUSTOMER_PARAMETER);
		String caller = getParameter(request, CALLER_PARAMETER);
		String callee = getParameter(request, CALLEE_PARAMETER);
		String start = getParameter(request, STARTDATETIME_PARAMETER);
		String end = getParameter(request, ENDDATETIME_PARAMETER);
		
		if (customer == null) {
			missingRequiredParameter(response, CUSTOMER_PARAMETER);
			return;
		}
		
		if (caller == null) {
			missingRequiredParameter(response, CALLER_PARAMETER);
			return;
		}
		
		if (callee == null) {
			missingRequiredParameter(response, CALLEE_PARAMETER);
			return;
		}
		
		if (start == null) {
			missingRequiredParameter(response, STARTDATETIME_PARAMETER);
			return;
		}
		
		if (end == null) {
			missingRequiredParameter(response, ENDDATETIME_PARAMETER);
			return;
		}
		
		PhoneBill bill = this.dictionary.get(customer);
		PrintWriter pw = response.getWriter();
		
		if (bill == null) {
			bill = new PhoneBill(customer);
			pw.println("Created a new PhoneBill for \"" + customer + "\"");
			this.dictionary.put(customer, bill);
		}
		
		PhoneCall call = null;
		try {
			call = new PhoneCall(caller, callee, start, end);
		}
		catch (IllegalArgumentException e) {
			invalidRequiredParameter(response, e.getMessage());
			return;
		}
		
		bill.addPhoneCall(call);
		pw.println("Added a new PhoneCall to customer " + customer + "'s PhoneBill");
		
		pw.flush();
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	
	
	
	
	/**
	 * Send a Phone Bill with filter by start and end time to the HTTP response.
	 *
	 * The text of the message is formatted with
	 * {@link Messages#formatDictionaryEntry(String, String)}
	 */
	private void sendPhoneBill(HttpServletResponse response, String customer, Date start, Date end) throws IOException {
		PhoneBill bill = this.dictionary.get(customer);
		PrintWriter pw = response.getWriter();
		
		if (bill == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Can't find customer \"" + customer + "\"");
		}
		else {
			pw.println(TextDumper.formatOutput(bill, start, end));
			response.setStatus(HttpServletResponse.SC_OK);
		}
		pw.flush();
	}
	
	/**
	 * Returns the value of the HTTP request parameter with the given name.
	 *
	 * @param request
	 * @param name
	 * @return <code>null</code> if the value of the parameter is
	 *         <code>null</code> or is the empty string
	 */
	private String getParameter(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (value == null || "".equals(value)) {
			return null;
			
		}
		else {
			return value;
		}
	}
	
	/**
	 * Writes an error message about a missing parameter to the HTTP response.
	 *
	 * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
	 * 
	 * @param response
	 * @param parameterName
	 * @throws IOException
	 */
	private void missingRequiredParameter(HttpServletResponse response, String parameterName) throws IOException {
		String message = String.format("The required parameter \"%s\" is missing", parameterName);
		
		// Error Status code (412)
		response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
	}
	
	/**
	 * Writes an error message about an invalid parameter to the HTTP response.
	 *
	 * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
	 * 
	 * @param response
	 * @param parameterName
	 * @throws IOException
	 */
	private void invalidRequiredParameter(HttpServletResponse response, String parameterName) throws IOException {
		String message = String.format("Required parameters error: \"%s\"", parameterName);
		
		// Error Status code (412)
		response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
	}
	
}
