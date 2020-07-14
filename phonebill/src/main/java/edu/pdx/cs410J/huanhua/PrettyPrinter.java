package edu.pdx.cs410J.huanhua;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import edu.pdx.cs410J.PhoneBillDumper;

/**
 * A PrettyPrinter creates a nicely formatted textual presentation of the
 * phone calls in a phone bill
 * 
 * It pretty print the duration of each phone call in minutes.
 * 
 * @author KANRA
 *
 */
public class PrettyPrinter implements PhoneBillDumper<PhoneBill> {
	
	private String filename;
	private final static String DATE_PATTERN = "MM/dd/yyyy h:mm a";
	
	/**
	 * create a PrettyPrinter with a filename
	 * 
	 * @param filename
	 * @throws IllegalArgumentException
	 */
	public PrettyPrinter(String filename) throws IllegalArgumentException {
		if (filename == null || filename.isEmpty()) {
			throw new IllegalArgumentException("Filename is invalid in Pretty Printer");
		}
		
		this.filename = filename;
	}
	
	/**
	 * Pretty print the phone bill to a text file or standard out
	 * 
	 * @param bill
	 *            PhoneBill
	 * @throws IOException
	 */
	@Override
	public void dump(PhoneBill bill) throws IOException {
		File file = new File(filename);
//		file.deleteOnExit();
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		String customer = bill.getCustomer();
		pw.println(customer);
		
		Collection<PhoneCall> calls = bill.getPhoneCalls();
		
		DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);
		
		for (PhoneCall call : calls) {
			Date start = call.getStartTime();
			Date end = call.getEndTime();
			int duration = getDurationInMinutes(start, end);
			String startString = dateFormat.format(start);
			String endString = dateFormat.format(end);
			
			pw.println("\n"
					+ "Call from " + call.getCaller() + " to " + call.getCallee() + "\n"
					+ "+ " + startString
					+ "| " + duration + " minutes"
					+ "| " + endString
					+ "\n");
		}
		
		pw.close();
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public int getDurationInMinutes(Date start, Date end) {
		return 0;
	}
	
}
