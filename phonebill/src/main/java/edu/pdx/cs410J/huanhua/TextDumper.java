package edu.pdx.cs410J.huanhua;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import edu.pdx.cs410J.PhoneBillDumper;

/**
 * A TextDumper dumps the contents of a phone bill 4 (including its calls) to a text file.
 * 
 * @author KANRA
 *
 */
public class TextDumper implements PhoneBillDumper<PhoneBill> {
	
	private String filename;
	
	public TextDumper(String filename) {
		this.filename = filename;
	}
	
	@Override
	public void dump(PhoneBill bill) throws IOException {
		File file = new File(filename);
//		file.deleteOnExit();
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		String customer = bill.getCustomer();
		pw.println(customer);
		
		Collection<PhoneCall> calls = bill.getPhoneCalls();
		
		for (PhoneCall call : calls) {
			pw.println(call.getCaller() + "..." + call.getCallee() + "..." + call.getStartTimeString() + "..." + call.getEndTimeString());
		}
		
		pw.close();
		
		
	}
	
}
