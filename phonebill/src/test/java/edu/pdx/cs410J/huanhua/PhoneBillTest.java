package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;

/**
 * Unit tests for the {@link PhoneBill} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneBillTest {
	
	@Test
	public void getCustomerNameMatching() {
		String name = "abc";
		
		PhoneBill bill = new PhoneBill(name);
		
		assertThat(bill.getCustomer(), containsString(name));
	}
	
	@Test
	public void addOnePhoneCallGetOnePhoneCall() {
		PhoneCall call1 = new PhoneCall();
		PhoneBill bill = new PhoneBill("");
		bill.addPhoneCall(call1);
		
		assertThat(bill.getPhoneCalls().size(), equalTo(1));
	}
	
	@Test
	public void phoneBillToStirngMatching1() {
		String name = "aaa";
		PhoneBill bill = new PhoneBill(name);
		PhoneCall call1 = new PhoneCall();
		bill.addPhoneCall(call1);
		
		assertThat(bill.toString(), containsString(name + "'s phone bill with " + 1 + " phone calls"));
		
	}
	
	@Test
	public void phoneBillToStirngMatching2() {
		String name = "aaa";
		PhoneBill bill = new PhoneBill(name);
		PhoneCall call1 = new PhoneCall();
		PhoneCall call2 = new PhoneCall();
		bill.addPhoneCall(call1);
		bill.addPhoneCall(call2);
		
		assertThat(bill.toString(), containsString(name + "'s phone bill with " + 2 + " phone calls"));
		
	}
}
