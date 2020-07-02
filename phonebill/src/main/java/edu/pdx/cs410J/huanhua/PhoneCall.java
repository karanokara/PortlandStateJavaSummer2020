package edu.pdx.cs410J.huanhua;

import edu.pdx.cs410J.AbstractPhoneCall;

/**
 * A PhoneCall is initiated by a person with a given phone number at a given time
 * 
 * 
 * @author KANRA SU
 *
 */
public class PhoneCall extends AbstractPhoneCall {
	
	private String callerPhoneNumber;
	
	private String calleePhoneNumber;
	
	private String startTime;
	
	private String endTime;
	
	public PhoneCall(String callerPhone, String calleePhone, String startTime, String endTime) {
		this.callerPhoneNumber = callerPhone;
		this.calleePhoneNumber = calleePhone;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	@Override
	public String getCaller() {
		return this.calleePhoneNumber;
	}
	
	@Override
	public String getCallee() {
		return this.calleePhoneNumber;
	}
	
	@Override
	public String getStartTimeString() {
		return this.startTime;
	}
	
	@Override
	public String getEndTimeString() {
		return this.endTime;
	}
}
