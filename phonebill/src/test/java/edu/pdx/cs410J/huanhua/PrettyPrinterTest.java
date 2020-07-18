package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

/**
 * Unit tests for the {@link PrettyPrinter} class.
 *
 */
public class PrettyPrinterTest {
	
	public static String fileReader(String filename) throws IOException {
		File file = new File(filename);
		String re = "";
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			re += line;
			line = br.readLine();
			if (line != null)
				re += "\n";
		}
		
		br.close();
		
		return re;
	}
	
	// ------------------------------- Error Tests ------------------------------------- //
	
	@Test(expected = IllegalArgumentException.class)
	public void filenameEmptyThrowsException() throws IllegalArgumentException {
		new PrettyPrinter("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void filenameNullThrowsException() throws IllegalArgumentException {
		new PrettyPrinter(null);
	}
	
	
	@Test(expected = IOException.class)
	public void filenameIsFolderThrowsException() throws IOException {
		String filename = "target";
		String name = "aaa";
		
		PhoneBill bill = new PhoneBill(name);
		PrettyPrinter dumper = new PrettyPrinter(filename);
		
		// dump
		dumper.dump(bill);
	}
	
	// ------------------------------- Success Tests ------------------------------------- //
	
	@Test
	public void dumpPhoneBillTextMatching() throws IOException {
		String filename = "test.txt";
		String name = "aaa";
		
		PhoneBill bill = new PhoneBill(name);
		
		PhoneCall call1 = new PhoneCall("111-111-1111", "111-111-1110", "1/15/2020 12:35 am", "1/15/2020 12:39 am");
		PhoneCall call2 = new PhoneCall("111-111-1112", "111-111-1110", "1/16/2020 12:05 am", "1/16/2020 12:39 am");
		PhoneCall call3 = new PhoneCall("111-111-1113", "111-111-1110", "2/16/2020 1:35 am", "2/16/2020 1:35 pm");
		
		bill.addPhoneCall(call2);
		bill.addPhoneCall(call1);
		bill.addPhoneCall(call3);
		
		PrettyPrinter dumper = new PrettyPrinter(filename);
		
		// dump
		dumper.dump(bill);
		
		String result = fileReader(filename);
		assertThat(result, containsString("Total calls: " + 3));
		
		File toDeleteFile = new File(filename);
		toDeleteFile.delete();
	}
	
	
}
