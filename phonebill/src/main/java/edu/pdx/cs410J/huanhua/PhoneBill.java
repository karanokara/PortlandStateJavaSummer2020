package edu.pdx.cs410J.huanhua;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * A PhoneBill has a customer name and consists of multiple PhoneCalls.
 * 
 * @author KANRA SU
 *
 */
public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
	
	private String customer;
	
	private Collection<PhoneCall> phoneCalls;		// Collection is an Interface, need an implemented class
	
	// a comparator for comparing 2 PhoneCall
	static class phoneCallComparator implements Comparator<PhoneCall> {
		public int compare(PhoneCall o1, PhoneCall o2) {
			// sort by small to large, earliest to latest
			return o1.compareTo(o2);
		}
	}
	
	public PhoneBill(String customer) throws IllegalArgumentException {
		if (customer == null || customer.isEmpty()) {
			throw new IllegalArgumentException("Customer name is invalid");
		}
		
		this.customer = customer;
		this.phoneCalls = new TreeSet<PhoneCall>(new phoneCallComparator());	// auto sort when adding
		
		// this.phoneCalls = new LinkedList<PhoneCall>();		// can add first or last, keep insertion order
	}
	
	/**
	 * Returns the name of the customer whose phone bill this is
	 * 
	 * @return String
	 */
	@Override
	public String getCustomer() {
		return this.customer;
	}
	
	/**
	 * Adds a phone call to this phone bill
	 * phone calls are sorted chronologically by their begin time + caller phone #
	 * 
	 * @param call
	 *            A PhoneCall obj
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
