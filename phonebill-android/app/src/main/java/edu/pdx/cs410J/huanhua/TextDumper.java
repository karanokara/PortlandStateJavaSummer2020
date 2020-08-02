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
 * A TextDumper dumps the contents of a phone bill 4 (including its calls) to a text file.
 * 
 * @author KANRA
 *
 */
public class TextDumper implements PhoneBillDumper<PhoneBill> {
	
	private String filename;
	
	/**
	 * create a TextDumper with a filename
	 * 
	 * @param filename
	 * @throws IllegalArgumentException
	 */
	public TextDumper(String filename) throws IllegalArgumentException {
		if (filename == null || filename.isEmpty()) {
			throw new IllegalArgumentException("Filename is invalid in text dumper");
		}
		
		this.filename = filename;
	}
	
	/**
	 * Dumps a phone bill to some destination.
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
		
		pw.println(formatOutput(bill, null, null));
		
		pw.close();
	}
	
	/**
	 * Format the PhoneBill output that can be parsed later
	 * 
	 * @param bill
	 * @return self-formatted string of PhoneBill
	 */
	public static String formatOutput(PhoneBill bill, Date start, Date end) {
		String reString = "";
		
		String customer = bill.getCustomer();
		reString += customer + "\n";
		
		Collection<PhoneCall> calls = bill.getPhoneCalls();
		DateFormat dateFormat = new SimpleDateFormat(PhoneCall.PARSE_DATE_PATTERN, Locale.US);
		
		for (PhoneCall call : calls) {
			Date callStartDate = call.getStartTime();
			Date callEnDate = call.getEndTime();
			
			// filter by start and end Date if provide
			if ((start != null && end != null && callStartDate.compareTo(start) >= 0 && callStartDate.compareTo(end) <= 0) || (start == null && end == null)) {
				reString += call.getCaller() + "..." + call.getCallee() + "..." + dateFormat.format(callStartDate) + "..." + dateFormat.format(callEnDate) + "\n";
			}
		}
		
		return reString;
	}
	
}
