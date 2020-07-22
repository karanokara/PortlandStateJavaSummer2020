package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.pdx.cs410J.InvokeMainTestCase;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
	
	private static final String HOSTNAME = "localhost";
	
	private static final String PORT = System.getProperty("http.port", "8080");
	
	private static final String THIS_IS_A_README_FILE = "usage: java Project4 [options] <args>";
	
	private static final String MISSING_COMMAND_LINE_ARGUMENTS = "Missing command line arguments, need 9 arguements.";
	
	private static final String TOOMUCH_COMMAND_LINE_ARGUMENTS = "Too much arguments, need only 9 arguements.";
//	@Test
//	public void test0RemoveAllMappings() throws IOException {
//		PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
//		client.removeAllDictionaryEntries();
//	}
	
	
	// ------------------------------- Error Tests ------------------------------------- //
	@Test
	public void invokingMainWith0argHasError() {
		MainMethodResult result = invokeMain(Project4.class);
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith2argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith3argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith4argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "4");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith6argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "4", "5", "6");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith5argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "4", "5");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith7argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "4", "5", "6", "7");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith8argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "4", "5", "6", "7", "8");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith10argHasError() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		assertThat(result.getTextWrittenToStandardError(), containsString(TOOMUCH_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}