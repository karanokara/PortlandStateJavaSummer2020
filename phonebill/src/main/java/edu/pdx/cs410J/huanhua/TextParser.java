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
	
	public TextParser(String filename) {
		this.filename = filename;
	}
	
	@Override
	public PhoneBill parse() throws ParserException {
		FileReader fr = null;
		PhoneBill bill = null;
		
		try {
			File file = new File(this.filename);
			fr = new FileReader(file);
		}
		catch (FileNotFoundException e) {
			throw new ParserException("Error on finding target file to read");
		}
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null) {
				
				line = br.readLine();
				if (line != null) {
					// end of stream
				}
				
			}
		}
		catch (IOException e) {
			throw new ParserException("Error on reading file content");
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
		
		
		return null;
	}
	
}
