package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import edu.pdx.cs410J.InvokeMainTestCase;

/**
 * Tests the functionality in the {@link Project3} main class.
 */
public class Project3IT extends InvokeMainTestCase {
	
	private static final String THIS_IS_A_README_FILE = "This is a PhoneBill project";
	private static final String MISSING_COMMAND_LINE_ARGUMENTS = "Missing command line arguments, need 9 arguements.";
	private static final String TOOMUCH_COMMAND_LINE_ARGUMENTS = "Too much arguments, need only 9 arguements.";
	
	/**
	 * Invokes the main method of {@link Project3} with the given arguments.
	 */
	private MainMethodResult invokeMain(String... args) {
		return invokeMain(Project3.class, args);
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
	public void invokingMainWith0argHasError() {
		MainMethodResult result = invokeMain(Project3.class);
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith1argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith2argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith3argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith4argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "4");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith5argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "4", "5");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith6argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "4", "5", "6");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith7argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "4", "5", "6", "7");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith8argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "4", "5", "6", "7", "8");
		assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith10argHasError() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		assertThat(result.getTextWrittenToStandardError(), containsString(TOOMUCH_COMMAND_LINE_ARGUMENTS));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidMonthArgWithNoOptionError() {
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
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
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, option, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
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
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, option1, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
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
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid phone argument"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithInvalidFormatDateArgHasError() {
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/152020";
		String time1 = "19:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid format of date argument"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidYearArgHasError() {
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/0";
		String time1 = "19:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid year argument"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidNameArgHasError() {
		String option2 = "-print";
		String name = "";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Customer name is invalid"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithInvalidMarkerArgHasError() {
		String option2 = "-print";
		String name = "11";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "12:35";
		String marker1 = "aa";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid format on the day or time"));
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
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option1, option2, date1, time1, marker1, date2, time2, marker2);
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
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option1, "", option2, date1, time1, marker1, date2, time2, marker2);
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
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2, option1);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a filename for using text file for phone bill."));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithFileNotMatchCustomerNameHasError() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "ab\n" +
				"111-111-1113...111-111-1112...2/15/2020 11:35 am...2/15/2020 11:39 am";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, option1, filename, date1, time1, marker1, date2, time2, marker2);
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
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Customer name is invalid"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithMalformattedFileCallHasError() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...2/15/2020 12:35 am...1/15/10000 12:39 am";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid year argument"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithCorrectFormattedFileWrongArgError() throws FileNotFoundException {
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
		String time1 = "12:35";
		String date2 = "1/16/2020";
		String time2 = "12:33";
		String marker1 = "am";
		String marker2 = "P";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid format on the day or time"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void prettyPrintWithWrongArgError() throws IOException {
//		String prettyFilename = "pretty.txt";
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:39 am...2/15/2020 11:35 am\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:50 am...2/15/2020 11:35 am\n";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-pretty";
//		String option3 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
		
		MainMethodResult result = invokeMain(Project3.class, option2, "", name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a filename for pretty output"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void prettyPrintError() throws IOException {
		String prettyFilename = "pretty.txt";
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:39 am...2/15/2020 11:35 am\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:50 am...2/15/2020 11:35 am\n";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-pretty";
		String option3 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, option2, prettyFilename, option3, name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		
		String resultPrettyStirngString = PrettyPrinterTest.fileReader(prettyFilename);
		assertThat(resultPrettyStirngString, containsString("Called from " + phone1 + " to " + phone2));
		assertThat(resultPrettyStirngString, containsString("Total calls: 3"));
		
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2));
		
		// clear temp file
		File toDeleteFile = new File(prettyFilename);
		toDeleteFile.delete();
	}
	
	// ------------------------------- Success Tests ------------------------------------- //
	
	@Test
	public void projectOutputREADMEWithOption1() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "4", "5", "-README");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void projectOutputREADMEWithOption2() {
		MainMethodResult result = invokeMain(Project3.class, "1", "2", "3", "-README", "4");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void projectOutputREADMEWithOption3() {
		MainMethodResult result = invokeMain(Project3.class, "-README", "4");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void projectOutputREADMEWithOption4() {
		MainMethodResult result = invokeMain(Project3.class, "-README");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void projectOutputREADMEWithTwoOption() {
		String name = "abc";
		String option1 = "-README";
		String option2 = "-print";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "10:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, option2, phone1, phone2, date1, time1, marker1, date2, time2, marker2, option1);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void createPhoneBillWithPhoneCallMatchingToString() {
		String name = "abc";
		String option = "-print";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "10:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "Pm";
		String timeShort1 = "1/15/20";
		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, name, option, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + timeShort1 + " to " + timeShort2));
	}
	
	
	@Test
	public void createPhoneBillWithPhoneCallMatchingToStringWithoutOption() {
		String name = "abc";
//		String option = "-print";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "10:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(""));
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
		String time1 = "12:35";
		String date2 = "1/16/2020";
		String time2 = "12:33";
		String marker1 = "am";
		String marker2 = "Pm";
		String timeShort1 = "1/15/20";
		String timeShort2 = "1/16/20";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + timeShort1 + " to " + timeShort2));
	}
	
	@Test
	public void provideWithCorrectFormattedFileSuccess2() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...2/15/1020 11:35 am...1/15/1021 11:35 am";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "12:35";
		String date2 = "2/15/2020";
		String time2 = "12:33";
		String marker1 = "am";
		String marker2 = "Pm";
		String timeShort1 = "1/15/20";
		String timeShort2 = "2/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + timeShort1 + " to " + timeShort2));
	}
	
	@Test
	public void provideWithCorrectFormattedFileSuccess3() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...2/15/2020 11:35 am...1/15/2100 11:39 am";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "10:33";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
		String timeShort1 = "1/15/20";
		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2, option1, filename);
		
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + timeShort1 + " to " + timeShort2));
	}
	
	@Test
	public void provideWithCorrectFormattedFileWithoutPrintSuccess() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:39 am...2/15/2020 11:35 am";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
//		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), not(containsString(name + "'s phone bill with " + 2 + " phone calls")));
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
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "11:35";
		String marker1 = "am";
		String marker2 = "am";
		String timeShort1 = "1/15/20";
		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2, option2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2 + " from " + timeShort1 + " to " + timeShort2));
		
		File toDeleteFile = new File(filename);
		toDeleteFile.delete();
	}
	
	@Test
	public void prettyPrintToConsoleSuccess() throws FileNotFoundException {
		
		String option2 = "-pretty";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, option2, "-", name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Called from " + phone1 + " to " + phone2));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Total calls: 1"));
	}
	
	@Test
	public void prettyPrintToFileSuccess() throws IOException {
		String prettyFilename = "pretty.txt";
		
		String option2 = "-pretty";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, option2, prettyFilename, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		
		String resultPrettyStirngString = PrettyPrinterTest.fileReader(prettyFilename);
		assertThat(resultPrettyStirngString, containsString("Called from " + phone1 + " to " + phone2));
		assertThat(resultPrettyStirngString, containsString("Total calls: 1"));
		
		assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
		
		// clear temp file
		File toDeleteFile = new File(prettyFilename);
		toDeleteFile.delete();
	}
	
	@Test
	public void prettyPrintToFileAndPrintSuccess() throws IOException {
		String prettyFilename = "pretty.txt";
		
		String option2 = "-pretty";
		String option3 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, option2, prettyFilename, option3, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		
		String resultPrettyStirngString = PrettyPrinterTest.fileReader(prettyFilename);
		assertThat(resultPrettyStirngString, containsString("Called from " + phone1 + " to " + phone2));
		assertThat(resultPrettyStirngString, containsString("Total calls: 1"));
		
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2));
		
		// clear temp file
		File toDeleteFile = new File(prettyFilename);
		toDeleteFile.delete();
	}
	
	@Test
	public void prettyPrintToConsoleAfterReadingFileSuccess() throws FileNotFoundException {
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:39 am...2/15/2020 11:35 am\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:50 am...2/15/2020 11:35 am\n";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-pretty";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, option2, "-", name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Called from " + phone1 + " to " + phone2));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Total calls: 3"));
	}
	
	@Test
	public void prettyPrintToFileAfterReadingFileSuccess() throws IOException {
		String prettyFilename = "pretty.txt";
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:39 am...2/15/2020 11:35 am\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:50 am...2/15/2020 11:35 am\n";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-pretty";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, option2, prettyFilename, name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		
		String resultPrettyStirngString = PrettyPrinterTest.fileReader(prettyFilename);
		assertThat(resultPrettyStirngString, containsString("Called from " + phone1 + " to " + phone2));
		assertThat(resultPrettyStirngString, containsString("Total calls: 3"));
		
		// clear temp file
		File toDeleteFile = new File(prettyFilename);
		toDeleteFile.delete();
	}
	
	@Test
	public void prettyPrintToFileAfterReadingFileAndPrintSuccess() throws IOException {
		String prettyFilename = "pretty.txt";
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:39 am...2/15/2020 11:35 am\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:50 am...2/15/2020 11:35 am\n";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-pretty";
		String option3 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
//		String timeShort1 = "1/15/20";
//		String timeShort2 = "1/15/20";
		
		MainMethodResult result = invokeMain(Project3.class, option2, prettyFilename, option3, name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		
		String resultPrettyStirngString = PrettyPrinterTest.fileReader(prettyFilename);
		assertThat(resultPrettyStirngString, containsString("Called from " + phone1 + " to " + phone2));
		assertThat(resultPrettyStirngString, containsString("Total calls: 3"));
		
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2));
		
		// clear temp file
		File toDeleteFile = new File(prettyFilename);
		toDeleteFile.delete();
	}
	
	@Test
	public void prettyPrintWithOptionAsFilenameSuccess() throws IOException {
//		String prettyFilename = "pretty.txt";
		String filename = "temp.txt";
		String content = "abc\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:39 am...2/15/2020 11:35 am\n" +
				"111-111-1113...111-111-1112...1/15/1000 11:50 am...2/15/2020 11:35 am\n";
		
		// write content
		TextParserTest.fileWriter(content);
		
		String option1 = "-textFile";
		String option2 = "-pretty";
		String option3 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "11:35";
		String date2 = "1/15/2020";
		String time2 = "10:33";
		String marker1 = "am";
		String marker2 = "pm";
		
		MainMethodResult result = invokeMain(Project3.class, option2, option3, name, phone1, phone2, option1, filename, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		
		String resultPrettyStirngString = PrettyPrinterTest.fileReader(option3);
		assertThat(resultPrettyStirngString, containsString("Called from " + phone1 + " to " + phone2));
		assertThat(resultPrettyStirngString, containsString("Total calls: 3"));
		
		
		// clear temp file
		File toDeleteFile = new File(option3);
		toDeleteFile.delete();
	}
	
}