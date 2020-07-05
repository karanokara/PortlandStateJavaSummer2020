package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

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
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException2() {
		String phone = "111-2211";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException3() {
		String phone = "111-22-11";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException4() {
		String phone = "111-2222-1111";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException5() {
		String phone = "111-222-11111";
		
		new PhoneCall("111-111-1111", phone, "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException6() {
		String phone = "111-222+1111";
		
		new PhoneCall(phone, "111-111-1111", "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException7() {
		String phone = "111-22a-1111";
		
		new PhoneCall("111-111-1111", phone, "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalPhoneNumberThrowException8() {
		String phone = "11-222-1111";
		
		new PhoneCall("111-111-1111", phone, "1/15/2020 19:35", "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException1() {
		String date = "1";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException2() {
		String date = "1/15/2020";
		
		new PhoneCall("111-111-1112", "111-111-1111", "1/15/2020 19:39", date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException3() {
		String date = "1/15/2020 aa";
		
		new PhoneCall("111-111-1112", "111-111-1111", "1/15/2020 19:39", date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException4() {
		String date = "1/15/2020 00+12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException5() {
		String date = "1/15/2020 00:a2";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException6() {
		String date = "1/15/202a 00:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException7() {
		String date = "1/15/202 00:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException8() {
		String date = "0/15/2020 00:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException9() {
		String date = "1/0/2020 00:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException10() {
		String date = "13/01/2020 00:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", "1/15/2020 19:39", date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException11() {
		String date = "12/32/2020 00:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException13() {
		String date = "12/32/2020-00:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException14() {
		String date = "12/31/2020 25:12";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException15() {
		String date = "12/0/2020 00:60";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalDateThrowException16() {
		String date = "12/1/2020 00:60";
		
		new PhoneCall("111-111-1112", "111-111-1111", date, "1/15/2020 19:39");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void callerPhoneEqualToCalleePhoneException() {
		new PhoneCall("111-111-1111", "111-111-1111", "1/15/2020 19:39", "1/15/2020 19:39");
	}
	
	
	// ------------------------------- Error Tests ------------------------------------- //
	
	@Test
	public void getCallerMatching() {
		String phone = "111-111-1111";
		
		PhoneCall call = new PhoneCall(phone, "111-111-1112", "1/15/2020 19:35", "1/15/2020 19:33");
		
		assertThat(call.getCaller(), containsString(phone));
	}
	
	@Test
	public void getCalleeMatching() {
		String phone = "111-111-1111";
		
		PhoneCall call = new PhoneCall("111-111-1112", phone, "1/15/2020 19:35", "1/15/2020 19:33");
		
		assertThat(call.getCallee(), containsString(phone));
	}
	
	@Test
	public void getStartTimeStringMatching() {
		String time = "1/15/2020 19:39";
		
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", time, "1/15/2020 19:35");
		
		assertThat(call.getStartTimeString(), containsString(time));
	}
	
	@Test
	public void getEndTimeStringMatching() {
		String time = "1/15/2020 19:39";
		
		PhoneCall call = new PhoneCall("111-111-1111", "111-111-1112", "1/15/2020 19:35", time);
		
		assertThat(call.getEndTimeString(), containsString(time));
	}
	
	@Test
	public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {
		PhoneCall call = new PhoneCall();
		assertThat(call.getStartTime(), is(nullValue()));
	}
	
	
	
}
