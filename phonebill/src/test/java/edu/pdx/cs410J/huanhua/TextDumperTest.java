package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

/**
 * Unit tests for the {@link TextDumper} class.
 *
 */
public class TextDumperTest {
	
	private String fileReader(String filename) throws IOException {
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
	
	@Test
	public void dumpPhoneBillTextMatching() throws IOException {
		String filename = "test.txt";
		String name = "aaa";
		String time = "1/15/2020 19:39";
		
		PhoneBill bill = new PhoneBill(name);
		
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", "1/15/2020 19:35", time);
		
		bill.addPhoneCall(call);
		
		TextDumper dumper = new TextDumper(filename);
		
		
		// dump
		dumper.dump(bill);
		
		
		String result = fileReader(filename);
		assertThat(result, containsString("aaa\n" +
				"111-111-1111...111-111-1112...1/15/2020 19:35...1/15/2020 19:39"));
		
		
	}
	
	
}
