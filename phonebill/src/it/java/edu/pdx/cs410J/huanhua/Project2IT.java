package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import edu.pdx.cs410J.InvokeMainTestCase;

/**
 * Tests the functionality in the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {
	
	private static final String THIS_IS_A_README_FILE = "This is a PhoneBill project";
	private static final String MISSING_COMMAND_LINE_ARGUMENTS = "Missing command line arguments, need 7 arguements.";
	
	
	/**
	 * Invokes the main method of {@link Project2} with the given arguments.
	 */
	private MainMethodResult invokeMain(String... args) {
		return invokeMain(Project2.class, args);
	}
	
	/**
	 * Tests that invoking the main method with no arguments issues an error
	 */
	@Test
	public void testNoCommandLineArguments() {
		MainMethodResult result = invokeMain();
		assertThat(result.getExitCode(), equalTo(1));
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
	}
	
	
	// ------------------------------- Error Tests ------------------------------------- //
	
	@Test
	public void invokingMainWith1argHasError() {
		MainMethodResult result = invokeMain(Project2.class, "1");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith2argHasError() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith3argHasError() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2", "3");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith4argHasError() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2", "3", "4");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith5argHasError() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2", "3", "4", "5");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith6argHasError() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2", "3", "4", "5", "6");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith8argHasError() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2", "3", "4", "5", "6", "7", "8");
		assertThat(result.getTextWrittenToStandardError(), containsString("Too much arguments, need 7 arguements."));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidMonthArgWithNoOptionError() {
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid month argument"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithUnSupportedOptionHasError1() {
		String option = "-abc";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, option, phone1, phone2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Using unsupported option: " + option));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithUnSupportedOptionHasError2() {
		String option1 = "-abc";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-11";
		String phone2 = "111-222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, option1, phone1, phone2, option2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Using unsupported option: " + option1));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithInvalidPhoneArgHasError() {
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-11-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid phone argument"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithInvalidDateArgHasError() {
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid date argument"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithInvalidNameArgHasError() {
		String option2 = "-print";
		String name = "";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Customer name is invalid"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithFileOptionWithoutFilenameHasError1() throws FileNotFoundException {
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option1, option2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a filename for using text file for phone bill."));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithFileOptionWithoutFilenameHasError2() throws FileNotFoundException {
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option1, "", option2, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a filename for using text file for phone bill."));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithFileOptionWithoutFilenameHasError3() throws FileNotFoundException {
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, date1, time1, date2, time2, option1);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a filename for using text file for phone bill."));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithFileNotMatchCustomerNameHasError() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "ab\n" +
				"111-111-1113...111-111-1112...2/15/2020 19:35...1/15/2000 19:39";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, option1, filename, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("doesn't match argument customer's name"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithMalformattedFileCustomerNameHasError() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "\n" +
				"111-111-1113...111-111-1112...2/15/2020 19:35...1/15/10000 19:39";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "aa";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, option1, filename, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Customer name is invalid"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithMalformattedFileCallHasError() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...2/15/2020 19:35...1/15/10000 19:39";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, option1, filename, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid year argument"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	
	
	// ------------------------------- Success Tests ------------------------------------- //
	
	@Test
	public void projectOutputREADMEWithOption1() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2", "3", "4", "5", "-README");
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void projectOutputREADMEWithOption2() {
		MainMethodResult result = invokeMain(Project2.class, "1", "2", "3", "-README", "4");
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void projectOutputREADMEWithOption3() {
		MainMethodResult result = invokeMain(Project2.class, "-README", "4");
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void projectOutputREADMEWithOption4() {
		MainMethodResult result = invokeMain(Project2.class, "-README");
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void projectOutputREADMEWithTwoOption() {
		String name = "abc";
		String option1 = "-README";
		String option2 = "-print";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, option2, phone1, phone2, date1, time1, date2, time2, option1);
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void createPhoneBillWithPhoneCallMatchingToString() {
		String name = "abc";
		String option = "-print";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, option, phone1, phone2, date1, time1, date2, time2);
		
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + date1 + " " + time1 + " to " + date2 + " " + time2));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	
	@Test
	public void createPhoneBillWithPhoneCallMatchingToStringWithoutOption() {
		String name = "abc";
//		String option = "-print";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, date1, time1, date2, time2);
		
		assertThat(result.getTextWrittenToStandardOut(), containsString(""));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void provideWithCorrectFormattedFileSuccess1() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, option1, filename, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + date1 + " " + time1 + " to " + date2 + " " + time2));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void provideWithCorrectFormattedFileSuccess2() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...2/15/2020 19:35...1/15/1000 19:39";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, option1, filename, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + date1 + " " + time1 + " to " + date2 + " " + time2));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void provideWithCorrectFormattedFileSuccess3() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...2/15/2020 19:35...1/15/1000 19:39";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option2, date1, time1, date2, time2, option1, filename);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + date1 + " " + time1 + " to " + date2 + " " + time2));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void provideWithCorrectFormattedFileWithoutPrintSuccess() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...2/15/2020 19:35...1/15/1000 19:39";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
//		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option1, filename, date1, time1, date2, time2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getTextWrittenToStandardOut(), not(containsString(name + "'s phone bill with " + 2 + " phone calls")));
		assertThat(result.getExitCode(), equalTo(0));
	}
	
	@Test
	public void provideWithNotExistFilePrintSuccess() throws FileNotFoundException {
		String filename = "not.exist";
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "19:35";
		String date2 = "1/15/2020";
		String time2 = "19:33";
		
		MainMethodResult result = invokeMain(Project2.class, name, phone1, phone2, option1, filename, date1, time1, date2, time2, option2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + date1 + " " + time1 + " to " + date2 + " " + time2));
		assertThat(result.getExitCode(), equalTo(0));
		
		File toDeleteFile = new File(filename);
		toDeleteFile.delete();
	}
	
}