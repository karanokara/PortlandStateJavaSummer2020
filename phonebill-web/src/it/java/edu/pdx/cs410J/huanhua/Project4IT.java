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
	
	private static final String MISSING_COMMAND_LINE_ARGUMENTS = "Wrong number of arguments, need 1 arguments for customer name, or need 9 arguements for adding phone call";
	
	private static final String TOOMUCH_COMMAND_LINE_ARGUMENTS = "Too much arguments";
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
		
		MainMethodResult result = invokeMain(Project4.class, name, option, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2, option1);
		assertThat(result.getTextWrittenToStandardError(), containsString("Using unsupported option: " + option1));
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid month argument"));
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid format on the day or time"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void noHostError() {
//		String hostOption = "-host";
//		String portOption = "-port";
//		String host = "localhost";
//		String port = "8080";
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a hostname"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void noPortError() {
		String hostOption = "-host";
//		String portOption = "-port";
		String host = "localhost";
//		String port = "8080";
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
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, host, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a port number"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	
	@Test
	public void provideWithWrongCustomerHasNoPhoneCall() {
		String hostOption = "-host";
		String portOption = "-port";
		String host = "localhost";
		String port = "8080";
//		String option2 = "-print";
		String name = "11";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, host, portOption, port, name);
		assertThat(result.getTextWrittenToStandardError(), containsString("Can't find customer \"" + name + "\""));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	
	
	
	
	
}