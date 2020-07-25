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
	public void provideWithNoHostError() {
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
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a hostname"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithEmptyHostError() {
		String hostOption = "-host";
//		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, date1, time1, hostOption, "", marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a hostname"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithEmptyHostError2() {
		String hostOption = "-host";
//		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2, hostOption);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a hostname"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithNoPortError() {
		String hostOption = "-host";
//		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, date1, time1, hostOption, HOSTNAME, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a port number"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithEmptyPortError() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, name, portOption, "", phone1, phone2, date1, time1, hostOption, HOSTNAME, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a port number"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithEmptyPortError2() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, date1, time1, hostOption, HOSTNAME, marker1, date2, time2, marker2, portOption);
		assertThat(result.getTextWrittenToStandardError(), containsString("Need a port number"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithWrongHostError() {
		String hostOption = "-host";
		String portOption = "-port";
		String wrongHoString = "wrong";
		
		String name = "abc";
//		String phone1 = "111-111-1112";
//		String phone2 = "111-222-2222";
//		String date1 = "111/15/2020";
//		String time1 = "12:35";
//		String marker1 = "am";
//		String date2 = "1/15/2020";
//		String time2 = "12:33";
//		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, wrongHoString, portOption, PORT, name);
		assertThat(result.getTextWrittenToStandardError(), containsString(wrongHoString));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	
	@Test
	public void provideWithWrongPortStringError() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, "port", name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("must be an integer"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithWrongPortNumberError() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, "11", name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Connection refused"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithWrongCustomerHasNoPhoneCall() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "jsfjasidjfodsi";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name);
		assertThat(result.getTextWrittenToStandardError(), containsString("Can't find customer \"" + name + "\""));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidMonthArgWithNoOptionError() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "111/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid month argument"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidPhoneArgHasError() {
		String hostOption = "-host";
		String portOption = "-port";
		
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
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid phone argument"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithInvalidFormatDateArgHasError() {
		String hostOption = "-host";
		String portOption = "-port";
		
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
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid format of date argument"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidYearArgHasError() {
		String hostOption = "-host";
		String portOption = "-port";
		
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, hostOption, HOSTNAME, portOption, PORT, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid year argument"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void provideWithInvalidNameArgHasError() {
		String hostOption = "-host";
		String portOption = "-port";
		
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
		
		MainMethodResult result = invokeMain(Project4.class, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2, hostOption, HOSTNAME, portOption, PORT);
		assertThat(result.getTextWrittenToStandardError(), containsString("The required parameter \"customer\" is missing"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void provideWithInvalidMarkerArgHasError() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String option2 = "-print";
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "12:35";
		String marker1 = "aa";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid format on the day or time"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	@Test
	public void failToGetPhoneCallWithPrint() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String option2 = "-print";
		String name = "abc";
//		String phone1 = "111-111-1112";
//		String phone2 = "111-222-2222";
//		String date1 = "1/5/2020";
//		String time1 = "10:35";
//		String marker1 = "am";
//		String date2 = "1/5/2020";
//		String time2 = "10:45";
//		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, option2);
		assertThat(result.getTextWrittenToStandardError(), containsString("need 9 arguments"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	
	@Test
	public void provideWrongFormatGetPhoneCallWithSearch() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String option2 = "-search";
		String name = "abc";
//		String phone1 = "111-111-1112";
//		String phone2 = "111-2222222";
		String date1 = "1/52020";
		String time1 = "10:30";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "10:40";
		String marker2 = "am";
		
//		String timeT = "10:35";
		
		MainMethodResult result = invokeMain(Project4.class, option2, hostOption, HOSTNAME, portOption, PORT, name, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), containsString("Invalid format of date argument"));
		assertThat(result.getExitCode(), equalTo(1));
		
	}
	
	
	// ------------------------------- Success Tests ------------------------------------- //
	
	@Test
	public void projectOutputREADMEWithOption4() {
		MainMethodResult result = invokeMain(Project4.class, "-README");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void projectOutputREADMEWithOption3() {
		MainMethodResult result = invokeMain(Project4.class, "4", "-README", "4");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void projectOutputREADMEWithOption2() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "-README", "4");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void projectOutputREADMEWithOption1() {
		MainMethodResult result = invokeMain(Project4.class, "1", "2", "3", "4", "5", "-README");
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString(THIS_IS_A_README_FILE));
	}
	
	@Test
	public void successGetPhoneCall0Call() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-search";
		String name = "abc";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name);
		assertThat(result.getTextWrittenToStandardOut(), containsString("Total calls: 0"));
		assertThat(result.getExitCode(), equalTo(0));
		
	}
	
	@Test
	public void ssuccessAddPhoneCall() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:35";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "10:55";
		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Added a new PhoneCall to customer " + name));
		
	}
	
	@Test
	public void ssuccessAddPhoneCallWithPrint() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:35";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "10:45";
		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2));
		
	}
	
	@Test
	public void ssuccessAddPhoneCallWithPrintPrint() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:35";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "10:45";
		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, option2, date1, time1, marker1, date2, time2, marker2, option2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from " + phone1 + " to " + phone2));
		
	}
	
	@Test
	public void ssuccessGetPhoneCall() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
//		String date1 = "1/5/2020";
//		String time1 = "10:35";
//		String marker1 = "am";
//		String date2 = "1/5/2020";
//		String time2 = "10:45";
//		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Called from " + phone1 + " to " + phone2));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Total calls: 1"));
		
	}
	
	@Test
	public void tssuccessAddPhoneCall1() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:05";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "10:45";
		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Added a new PhoneCall to customer " + name));
		
	}
	
	@Test
	public void tssuccessAddPhoneCall2() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:25";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "10:45";
		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Added a new PhoneCall to customer " + name));
		
	}
	
	@Test
	public void tssuccessAddPhoneCall3() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:45";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "11:45";
		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Added a new PhoneCall to customer " + name));
		
	}
	
	@Test
	public void tssuccessAddPhoneCall4() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "abcde";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:45";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "11:45";
		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name, phone1, phone2, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Added a new PhoneCall to customer " + name));
		
	}
	
	@Test
	public void tsuccessGetPhoneCall() {
		String hostOption = "-host";
		String portOption = "-port";
		
//		String option2 = "-print";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
//		String date1 = "1/5/2020";
//		String time1 = "10:35";
//		String marker1 = "am";
//		String date2 = "1/5/2020";
//		String time2 = "10:45";
//		String marker2 = "am";
		
		MainMethodResult result = invokeMain(Project4.class, hostOption, HOSTNAME, portOption, PORT, name);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Called from " + phone1 + " to " + phone2));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Total calls: " + 4));
		
	}
	
	@Test
	public void tsuccessGetPhoneCallWithSearch() {
		String hostOption = "-host";
		String portOption = "-port";
		
		String option2 = "-search";
		String name = "abcd";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/5/2020";
		String time1 = "10:30";
		String marker1 = "am";
		String date2 = "1/5/2020";
		String time2 = "10:40";
		String marker2 = "am";
		
		String timeT = "10:35";
		
		MainMethodResult result = invokeMain(Project4.class, option2, hostOption, HOSTNAME, portOption, PORT, name, date1, time1, marker1, date2, time2, marker2);
		assertThat(result.getTextWrittenToStandardError(), equalTo(""));
		assertThat(result.getExitCode(), equalTo(0));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Called from " + phone1 + " to " + phone2));
		assertThat(result.getTextWrittenToStandardOut(), containsString("Total calls: " + 1));
		assertThat(result.getTextWrittenToStandardOut(), containsString(timeT));
		
	}
}