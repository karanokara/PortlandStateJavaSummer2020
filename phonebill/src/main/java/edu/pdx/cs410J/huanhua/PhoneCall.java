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
	
	public PhoneCall() {
		
	}
	
	public PhoneCall(String callerPhone, String calleePhone, String startTime, String endTime) throws IllegalArgumentException {
		this.callerPhoneNumber = validatePhone(callerPhone);
		this.calleePhoneNumber = validatePhone(calleePhone);
		this.startTime = validateTime(startTime);
		this.endTime = validateTime(endTime);
		
		if (this.callerPhoneNumber.equals(this.calleePhoneNumber)) {
			throw new IllegalArgumentException("Caller phone number equals to callee phone number");
		}
	}
	
	/**
	 * Returns the phone number of the person who originated this phone call.
	 * 
	 * @return String
	 */
	@Override
	public String getCaller() {
		return this.callerPhoneNumber;
	}
	
	/**
	 * Returns the phone number of the person who received this phone call.
	 * 
	 * @return String
	 */
	@Override
	public String getCallee() {
		return this.calleePhoneNumber;
	}
	
	/**
	 * Returns a textual representation of the time that this phone call was originated.
	 * 
	 * @return String
	 */
	@Override
	public String getStartTimeString() {
		return this.startTime;
	}
	
	/**
	 * Returns a textual representation of the time that this phone call
	 * was completed.
	 * 
	 * @return String
	 */
	@Override
	public String getEndTimeString() {
		return this.endTime;
	}
	
	/**
	 * validate phone number of format nnn-nnn-nnnn
	 * 
	 * @param phone
	 * @return original phone number
	 * @throws IllegalArgumentException
	 */
	private String validatePhone(String phone) throws IllegalArgumentException {
		String phones[] = phone.split("-");
		if (phones.length != 3) {
			throw new IllegalArgumentException("Invalid phone argument");
		}
		
		String fi = phones[0];
		String se = phones[1];
		String th = phones[2];
		
		try {
			int fir = Integer.parseInt(fi);
			if (fi.length() != 3) {
				throw new Exception();
			}
			
			int sec = Integer.parseInt(se);
			if (se.length() != 3) {
				throw new Exception();
			}
			
			int thi = Integer.parseInt(th);
			if (th.length() != 4) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid phone argument");
		}
		
		
		return phone;
	}
	
	/**
	 * validate time of format mm/dd/yyyy hh:mm
	 * 
	 * @param dateTime
	 * @return original datatime
	 * @throws IllegalArgumentException
	 */
	private String validateTime(String dateTime) throws IllegalArgumentException {
		String dateTimeStr[] = dateTime.split(" ");
		if (dateTimeStr.length != 2) {
			throw new IllegalArgumentException("Need date and time argument");
		}
		
		String date = dateTimeStr[0];
		String dateStr[] = date.split("/");
		if (dateStr.length != 3) {
			throw new IllegalArgumentException("Invalid date argument");
		}
		
		String m = dateStr[0];
		String d = dateStr[1];
		String y = dateStr[2];
		
		try {
			int mm = Integer.parseInt(m);
			if (m.length() > 2 || mm > 12 || mm < 1) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid month argument");
		}
		
		try {
			int dd = Integer.parseInt(d);
			if (d.length() > 2 || dd > 31 || dd < 1) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid day argument");
		}
		
		try {
			int yy = Integer.parseInt(y);
			if (y.length() > 4 || yy > 9999 || yy < 1000) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid year argument");
		}
		
		
		
		String time = dateTimeStr[1];
		String timeStr[] = time.split(":");
		if (timeStr.length != 2) {
			throw new IllegalArgumentException("Invalid time argument");
		}
		
		String h = timeStr[0];
		String mi = timeStr[1];
		
		try {
			int hh = Integer.parseInt(h);
			if (hh > 23 || hh < 0) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid hour argument");
		}
		
		try {
			int min = Integer.parseInt(mi);
			if (min > 59 || min < 0) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid minute argument");
		}
		
		return dateTime;
	}
}
