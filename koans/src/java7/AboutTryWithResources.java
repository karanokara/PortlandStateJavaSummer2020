package java7;

import static com.sandwich.util.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.sandwich.koan.Koan;

public class AboutTryWithResources {
	
	class AutoClosableResource implements AutoCloseable {
		
		public void foo() throws WorkException {
			throw new WorkException("Exception thrown while working");
		}
		
		public void close() throws CloseException {
			throw new CloseException("Exception thrown while closing");
		}
		
	}
	
	class WorkException extends Exception {
		
		public WorkException(String message) {
			super(message);
		}
		
	}
	
	class CloseException extends Exception {
		
		public CloseException(String message) {
			super(message);
		}
		
	}
	
	@Koan
	public void lookMaNoClose() {
		String str = "first line"
				+ System.lineSeparator()
				+ "second line";
		
		InputStream is = new ByteArrayInputStream(str.getBytes());
		String line;
		
		/* BufferedReader implementing @see java.lang.AutoCloseable interface */
		// try (resource) { do something }
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(is))) {
			
			line = br.readLine();
			//br guaranteed to be closed
		}
		catch (IOException e) {
			line = "error";
		}
		
		assertEquals(line, "first line");
	}
	
	@Koan
	public void lookMaNoCloseWithException() throws IOException {
		String line = "no need to close readers";
		
		try (BufferedReader br = new BufferedReader(
				new FileReader("I do not exist!"))) {
			
			line = br.readLine();
		}
		catch (FileNotFoundException e) {
			line = "no more leaking!";
		}
		
		assertEquals(line, "no more leaking!");
	}
	
	@Koan
	public void lookMaNoCloseWithMultipleResources() throws IOException {
		String str = "first line"
				+ System.lineSeparator()
				+ "second line";
		
		InputStream is = new ByteArrayInputStream(str.getBytes());
		String line;
		
		// multiple resources in the same try declaration
		// with 2 recource
		try (BufferedReader br = new BufferedReader(
				new FileReader("I do not exist!"));
				
				BufferedReader brFromString = new BufferedReader(
						new InputStreamReader(is))) {
			
			line = br.readLine();
			line += brFromString.readLine();
		}
		catch (IOException e) {
			line = "error";
		}
		
		assertEquals(line, "error");
	}
	
	@Koan
	public void supressException() {
		String message = "";
		
		try {
			bar();
		}
		catch (WorkException e) {	// hit here
			// e.getSuppressed(), get all exceptions from an obj that throw this exception
			message += e.getMessage() + " " + e.getSuppressed()[0].getMessage();
		}
		catch (CloseException e) {
			message += e.getMessage();
		}
		
		assertEquals(message, "Exception thrown while working" + " " + "Exception thrown while closing");
	}
	
	
	public void bar() throws CloseException, WorkException {
		try (AutoClosableResource autoClosableResource = new AutoClosableResource()) {
			autoClosableResource.foo();
		}
	}
}