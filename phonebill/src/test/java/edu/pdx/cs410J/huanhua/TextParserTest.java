package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

import edu.pdx.cs410J.ParserException;

/**
 * Unit tests for the {@link TextParser} class.
 *
 */
public class TextParserTest {
	
	private void fileWriter(String content) throws FileNotFoundException {
		File file = new File("temp.txt");
		file.deleteOnExit();
		
		PrintWriter pw = new PrintWriter(file);
		
		pw.println(content);
		
		pw.close();
	}
	
	// ------------------------------- Error Tests ------------------------------------- //
	
	@Test(expected = IllegalArgumentException.class)
	public void filenameEmptyThrowsException() throws IllegalArgumentException {
		new TextParser("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void filenameNullThrowsException() throws IllegalArgumentException {
		new TextParser(null);
	}
	
	
	@Test(expected = ParserException.class)
	public void filenameIsFoundThrowsException() throws ParserException {
		String filename = "not.exist";
		String name = "aaa";
		
		TextParser parser = new TextParser(filename);
		parser.parse();
	}
	
	// ------------------------------- Success Tests ------------------------------------- //
	
	@Test
	public void fileExistFormatCorrectPassTest() throws ParserException, FileNotFoundException {
		String content = "abc\n" +
				"111-111-1111...111-111-1112...1/15/2020 19:35...1/15/2020 19:39";
		
		// write content
		fileWriter(content);
		
		String filename = "temp.txt";
		
		TextParser parser = new TextParser(filename);
		PhoneBill bill = parser.parse();
		
		assertThat(bill.toString(), containsString("abc's phone bill with 1 phone calls"));
		
		
	}
	
	
}
