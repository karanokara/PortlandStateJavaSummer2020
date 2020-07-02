package edu.pdx.cs410J.huanhua;

import java.util.Collection;

import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * Project#1
 * 
 * Goals: Extend classes that you did not write and perform more complex command line parsing
 * 
 * @author KANRA SU
 *
 */
public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
	
	private String customer;
	
	private Collection<PhoneCall> phoneCalls;
	
	public PhoneBill(String customer, String times, String dates) {
		
	}
	
	/**
	 * 
	 */
	@Override
	public String getCustomer() {
		return this.customer;
	}
	
	@Override
	public void addPhoneCall(PhoneCall call) {
		this.phoneCalls.add(call);
	}
	
	@Override
	public Collection<PhoneCall> getPhoneCalls() {
		return this.phoneCalls;
	}
	
}
