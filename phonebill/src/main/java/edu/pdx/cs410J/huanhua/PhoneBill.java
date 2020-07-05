package edu.pdx.cs410J.huanhua;

import java.util.ArrayList;
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
	
	private Collection<PhoneCall> phoneCalls;		// Collection is an Interface, need an implemented class
	
	public PhoneBill(String customer) throws IllegalArgumentException {
		if (customer == null || customer.isEmpty()) {
			throw new IllegalArgumentException("Customer name is invalid");
		}
		
		this.customer = customer;
		this.phoneCalls = new ArrayList<PhoneCall>();
	}
	
	/**
	 * Returns the name of the customer whose phone bill this is
	 */
	@Override
	public String getCustomer() {
		return this.customer;
	}
	
	/**
	 * Adds a phone call to this phone bill
	 */
	@Override
	public void addPhoneCall(PhoneCall call) {
		this.phoneCalls.add(call);
	}
	
	/**
	 * Returns all of the phone calls (as instances of AbstractPhoneCall) in this phone bill
	 */
	@Override
	public Collection<PhoneCall> getPhoneCalls() {
		return this.phoneCalls;
	}
	
}
