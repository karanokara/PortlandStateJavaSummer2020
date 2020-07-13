package edu.pdx.cs410J.huanhua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

/**
 * A TextParser reads the contents of a text file and creates a phone bill with phone calls.
 * 
 * @author KANRA
 *
 */
public class TextParser implements PhoneBillParser<PhoneBill> {
	
	private String filename;
	
	/**
	 * Create a TextParser by passing a filename to parse
	 * 
	 * @param filename
	 * @throws IllegalArgumentException
	 */
	public TextParser(String filename) throws IllegalArgumentException {
		if (filename == null || filename.isEmpty()) {
			throw new IllegalArgumentException("Filename is invalid in text parser");
		}
		
		this.filename = filename;
	}
	
	/**
	 * Parses some source and returns a phone bill
	 * 
	 * @throws ParserException
	 * @return PhoneBill obj or null if no file found
	 */
	@Override
	public PhoneBill parse() throws ParserException {
		FileReader fr = null;
		PhoneBill bill = null;
		
		try {
			File file = new File(this.filename);
			fr = new FileReader(file);
		}
		catch (FileNotFoundException e) {
//			throw new ParserException("Error on finding target file to read");
			// file doesn't exist, return null
			return bill;
		}
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			if (line != null) {
				bill = new PhoneBill(line);
			}
			
			line = br.readLine();
			
			while (line != null) {
				String callParams[] = line.split("\\.\\.\\.");
				if (callParams.length != 4) {
					throw new IllegalArgumentException("Need 4 arguemnts to create a PhoneCall");
				}
				
				PhoneCall call = new PhoneCall(callParams[0], callParams[1], callParams[2], callParams[3]);
				bill.addPhoneCall(call);
				
				line = br.readLine();
				while (line != null && line.isEmpty()) {
					line = br.readLine();
				}
			}
		}
		catch (IOException e) {
			throw new ParserException("Error on reading file content: " + e.getMessage());
		}
		catch (IllegalArgumentException e) {
			throw new ParserException("Error on parsing file content: " + e.getMessage());
		}
		finally {
			// closing 
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException x) {
					throw new ParserException("Error on closing buffer reader");
				}
			}
		}
		
		
		return bill;
	}
	
}
