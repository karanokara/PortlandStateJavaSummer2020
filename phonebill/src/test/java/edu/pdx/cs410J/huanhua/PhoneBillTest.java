package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.TreeSet;

import org.junit.Test;

/**
 * Unit tests for the {@link PhoneBill} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneBillTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void phoneBillCreateWithNullCustomerNameThrowsException() {
		new PhoneBill(null);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void phoneBillCreateWithEmptyCustomerNameThrowsException() {
		new PhoneBill("");
		
	}
	
	@Test
	public void getCustomerNameMatching() {
		String name = "abc";
		
		PhoneBill bill = new PhoneBill(name);
		
		assertThat(bill.getCustomer(), containsString(name));
	}
	
	@Test
	public void addOnePhoneCallGetOnePhoneCall() {
		PhoneCall call1 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneBill bill = new PhoneBill("a");
		bill.addPhoneCall(call1);
		
		assertThat(bill.getPhoneCalls().size(), equalTo(1));
	}
	
	@Test
	public void phoneBillToStirngMatching1() {
		String name = "aaa";
		PhoneBill bill = new PhoneBill(name);
		PhoneCall call1 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		bill.addPhoneCall(call1);
		
		assertThat(bill.toString(), containsString(name + "'s phone bill with " + 1 + " phone calls"));
		
	}
	
	@Test
	public void phoneBillToStirngMatching2() {
		String name = "aaa";
		PhoneBill bill = new PhoneBill(name);
		PhoneCall call1 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call2 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		// 1 is the same as 2
		bill.addPhoneCall(call1);
		bill.addPhoneCall(call2);
		
		assertThat(bill.toString(), containsString(name + "'s phone bill with " + 1 + " phone calls"));
		
	}
	
	@Test
	public void phoneBillToStirngMatching3() {
		String name = "aaa";
		PhoneBill bill = new PhoneBill(name);
		PhoneCall call1 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call2 = new PhoneCall("111-111-1113", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		
		bill.addPhoneCall(call1);
		bill.addPhoneCall(call2);
		
		assertThat(bill.toString(), containsString(name + "'s phone bill with " + 2 + " phone calls"));
		
	}
	
	@Test
	public void phoneBillPhoneCallInOrder() {
		String name = "aaa";
		PhoneBill bill = new PhoneBill(name);
		
		PhoneCall call1 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call2 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:30 am", "01/15/2020 11:35 pm");
		PhoneCall call3 = new PhoneCall("111-111-1112", "111-111-1113", "01/14/2020 11:34 pm", "01/15/2020 11:35 pm");
		PhoneCall call4 = new PhoneCall("111-111-1112", "111-111-1114", "01/14/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call5 = new PhoneCall("111-111-1111", "111-111-1115", "01/14/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call6 = new PhoneCall("111-111-1110", "111-111-1116", "01/14/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall callArr[] = { call1, call2, call3, call4, call5, call6 };
		
		bill.addPhoneCall(call5);
		bill.addPhoneCall(call3);
		bill.addPhoneCall(call1);
		bill.addPhoneCall(call6);
		bill.addPhoneCall(call4);
		bill.addPhoneCall(call2);
		
		assertThat(bill.getPhoneCalls().size(), equalTo(6));
		
		TreeSet<PhoneCall> calls = (TreeSet<PhoneCall>) bill.getPhoneCalls();
		
		int i = 0;
		for (PhoneCall call : calls) {
			assertThat(call, equalTo(callArr[i]));
			++i;
		}
	}
	
	@Test
	public void phoneBillPhoneCallInOrder2() {
		String name = "aaa";
		PhoneBill bill = new PhoneBill(name);
		
		PhoneCall call1 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call2 = new PhoneCall("111-111-1112", "111-111-1110", "01/15/2020 11:30 am", "01/15/2020 11:35 pm");
		PhoneCall call3 = new PhoneCall("111-111-1112", "111-111-1113", "01/14/2020 11:34 pm", "01/15/2020 11:35 pm");
		PhoneCall call4 = new PhoneCall("111-111-1112", "111-111-1114", "01/14/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call5 = new PhoneCall("111-111-1111", "111-111-1115", "01/14/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall call6 = new PhoneCall("111-111-1110", "111-111-1116", "01/14/2020 11:34 am", "01/15/2020 11:35 pm");
		PhoneCall callArr[] = { call1, call2, call3, call4, call5, call6 };
		
		bill.addPhoneCall(call2);
		bill.addPhoneCall(call5);
		bill.addPhoneCall(call4);
		bill.addPhoneCall(call3);
		bill.addPhoneCall(call6);
		bill.addPhoneCall(call1);
		
		assertThat(bill.getPhoneCalls().size(), equalTo(6));
		
		TreeSet<PhoneCall> calls = (TreeSet<PhoneCall>) bill.getPhoneCalls();
		
		int i = 0;
		for (PhoneCall call : calls) {
			assertThat(call, equalTo(callArr[i]));
			++i;
		}
		
	}
	
}
