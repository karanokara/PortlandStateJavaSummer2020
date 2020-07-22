package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneCallTest {
	
	// ------------------------------- Error Tests ------------------------------------- //
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException1() {
		String phone = "11";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException2() {
		String phone = "111-2211";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException3() {
		String phone = "111-22-11";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException4() {
		String phone = "111-2222-1111";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException5() {
		String phone = "111-222-11111";
		
		new PhoneCall("111-111-1111", phone, "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException6() {
		String phone = "111-222+1111";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException7() {
		String phone = "111-22a-1111";
		
		new PhoneCall("111-111-1111", phone, "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException8() {
		String phone = "11-222-1111";
		
		new PhoneCall("111-111-1111", phone, "1/15/2020 19:35", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException1() {
		String date = "1";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException2() {
		String date = "1/15/2020 11:11";
		
		new PhoneCall("111-111-1112", "111-111-1111", "1/15/2020 10:39 am", date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException3() {
		String date = "1/15/2020 aa";
		
		new PhoneCall("111-111-1112", "111-111-1111", "1/15/2020 10:39 am", date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException4() {
		String date = "1/15/2020 01+12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException5() {
		String date = "1/152020 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException5Letter() {
		String date = "1/15/2020 01:a2 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException6Letter() {
		String date = "1/15/202a 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException7Year() {
		String date = "1/15/0 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException8Year() {
		String date = "1/15/20000 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException9Month() {
		String date = "0/15/2020 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException10Month() {
		String date = "13/01/2020 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", "1/15/2020 10:39 am", date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException11Day() {
		String date = "1/0/2020 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException12Day() {
		String date = "12/32/2020 01:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException13Hour() {
		String date = "12/31/2020 00:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException14Hour() {
		String date = "12/31/2020 13:12 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException15Min() {
		String date = "12/1/2020 01:60 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException16Min() {
		String date = "12/1/2020 01:123 am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException17Marker() {
		String date = "12/1/2020 01:59 aa";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException18Marker() {
		String date = "12/1/2020 00:55am";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException19Marker() {
		String date = "12/1/2020 13:10 amm";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void callerPhoneEqualToCalleePhoneException() {
		new PhoneCall("111-111-1111", "111-111-1111", "1/15/2020 10:38 am", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void startTimeAfterEndTimeExceptionHour() {
		new PhoneCall("111-111-1111", "111-111-1112", "1/15/2020 10:39 am", "1/15/2020 9:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void startTimeAfterEndTimeExceptionMin() {
		new PhoneCall("111-111-1111", "111-111-1112", "1/15/2020 10:39 am", "1/15/2020 10:38 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void startTimeAfterEndTimeExceptionMarker() {
		new PhoneCall("111-111-1111", "111-111-1112", "1/15/2020 10:39 pm", "1/15/2020 10:39 am");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void startTimeAfterEndTimeExceptionDate() {
		new PhoneCall("111-111-1111", "111-111-1112", "1/15/2020 10:39 am", "1/14/2020 10:39 am");
	}
	
	@Test(expected = NullPointerException.class)
	public void compareWithNullPhoneCallException() {
		String phone = "111-111-1111";
		
		PhoneCall call = new PhoneCall(phone, "111-111-1112", "01/15/2020 11:33 am", "01/15/2020 11:35 pm");
		
		call.compareTo(null);
	}
	
	// ------------------------------- Success Tests ------------------------------------- //
	
	@Test
	public void getCallerMatching() {
		String phone = "111-111-1111";
		
		PhoneCall call = new PhoneCall(phone, "111-111-1112", "01/15/2020 11:33 am", "01/15/2020 11:35 pm");
		
		assertThat(call.getCaller(), containsString(phone));
	}
	
	@Test
	public void getCalleeMatching() {
		String phone = "111-111-1111";
		
		PhoneCall call = new PhoneCall("111-111-1112", phone, "1/15/2020 11:33 am", "1/15/2020 11:35 am");
		
		assertThat(call.getCallee(), containsString(phone));
	}
	
	@Test
	public void getStartTimeStringMatching() {
		String time = "1/14/2020 10:33 am";
		String timeShort = "1/14/20";
		
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", time, "1/15/2020 10:30 am");
		
		assertThat(call.getStartTimeString(), containsString(timeShort));
	}
	
	@Test
	public void getEndTimeStringMatching() {
		String time = "1/15/2020 10:39 am";
		String timeShort = "1/15/20";
		
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", "1/15/2020 10:35 am", time);
		
		assertThat(call.getEndTimeString(), containsString(timeShort));
	}
	
	@Test
	public void getStartTimeMatching() {
		String time = "1/14/2020 10:33 am";
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.US);
		Date date = null;
		try {
			date = dateFormat.parse(time);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException("Invalid format on the day or time: " + e.getMessage());
		}
		
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", time, "1/15/2020 10:30 am");
		
		assertThat(call.getStartTime().toString(), containsString(date.toString()));
	}
	
	@Test
	public void getEndTimeMatching() {
		String time = "1/14/2020 10:33 am";
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.US);
		Date date = null;
		try {
			date = dateFormat.parse(time);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException("Invalid format on the day or time: " + e.getMessage());
		}
		
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", "1/14/2020 10:30 am", time);
		
		assertThat(call.getEndTime().toString(), containsString(date.toString()));
	}
	
	@Test
	public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {
		PhoneCall call = new PhoneCall();
		assertThat(call.getStartTime(), is(nullValue()));
	}
	
	@Test
	public void compareWithAfterPhoneCallReturnNegative1() {
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", "01/15/2020 11:33 am", "01/15/2020 11:35 pm");
		
		PhoneCall anotherCall = new PhoneCall("111-111-1111", "111-111-1112", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		
		assertThat(call.compareTo(anotherCall), equalTo(-1));
	}
	
	@Test
	public void compareWithBeforePhoneCallReturn1() {
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", "01/15/2020 11:35 am", "01/15/2020 11:35 pm");
		
		PhoneCall anotherCall = new PhoneCall("111-111-1111", "111-111-1112", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		
		assertThat(call.compareTo(anotherCall), equalTo(1));
	}
	
	@Test
	public void compareWithAfterPhoneCallCallerReturnNegative1() {
		PhoneCall call = new PhoneCall("111-111-1110", "111-111-1113", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		PhoneCall anotherCall = new PhoneCall("111-111-1111", "111-111-1112", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		
		assertThat(call.compareTo(anotherCall), equalTo(-1));
	}
	
	@Test
	public void compareWithBeforePhoneCallCallerReturn1() {
		PhoneCall call = new PhoneCall("111-111-1112", "111-111-1113", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		PhoneCall anotherCall = new PhoneCall("111-111-1111", "111-111-1112", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		
		assertThat(call.compareTo(anotherCall), equalTo(1));
	}
	
	@Test
	public void compareWithSamePhoneCallReturn0() {
		PhoneCall call = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		PhoneCall anotherCall = new PhoneCall("111-111-1112", "111-111-1114", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		
		assertThat(call.compareTo(anotherCall), equalTo(0));
	}
}
