package edu.pdx.cs410J.huanhua;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import edu.pdx.cs410J.AbstractPhoneCall;

/**
 * A PhoneCall is initiated by a person with a given phone number at a given time
 * 
 * 
 * @author KANRA SU
 *
 */
public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {
	
	public final static String PARSE_DATE_PATTERN = "MM/dd/yyyy h:mm a";
	
	private String callerPhoneNumber;
	
	private String calleePhoneNumber;
	
	private Date startTime;
	
	private Date endTime;
	
	public PhoneCall() {
		
	}
	
	public PhoneCall(String callerPhone, String calleePhone, String startTime, String endTime) throws IllegalArgumentException {
		this.callerPhoneNumber = validatePhone(callerPhone);
		this.calleePhoneNumber = validatePhone(calleePhone);
		
		String startTimeStr = validateTime(startTime);
		String endTimeStr = validateTime(endTime);
		
		this.startTime = parseDate(startTimeStr);
		this.endTime = parseDate(endTimeStr);
		
		if (this.callerPhoneNumber.equals(this.calleePhoneNumber)) {
			throw new IllegalArgumentException("Caller phone number equals to callee phone number");
		}
		
		if (this.endTime.compareTo(this.startTime) < 0) {
			throw new IllegalArgumentException("Phone call's end time is before its starts time");
		}
	}
	
	/**
	 * parse a date String into a java.util.Date obj
	 * 
	 * @param dateString
	 *            A date's format string
	 * @return A Date obj
	 * @throws ParseException
	 */
	public Date parseDate(String dateString) throws IllegalArgumentException {
		// need locale, otherwise not work on other machines
		DateFormat dateFormat = new SimpleDateFormat(PARSE_DATE_PATTERN, Locale.US);
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException("Invalid format on the day or time: " + e.getMessage());
		}
		
//		assertEquals(date2.toString(), "Sat Jan 01 00:00:00 PST 2000");
		
		return date;
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
		String formattedDate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US).format(this.startTime);
		
//		assertEquals(formattedDate, "1973/03/03"); // in windows
//		assertEquals(formattedDate, "3/3/73"); // in linux
		
		return formattedDate;
	}
	
	/**
	 * Returns a textual representation of the time that this phone call
	 * was completed.
	 * 
	 * @return String
	 */
	@Override
	public String getEndTimeString() {
		String formattedDate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US).format(this.endTime);
		
//		assertEquals(formattedDate, "1973/03/03"); // in windows
//		assertEquals(formattedDate, "3/3/73"); // in linux
		
		return formattedDate;
	}
	
	/**
	 * Returns the time that this phone call was originated as a
	 * {@link Date}.
	 */
	public Date getStartTime() {
		return this.startTime;
	}
	
	/**
	 * Returns the time that this phone call was completed as a
	 * {@link Date}.
	 */
	public Date getEndTime() {
		return this.endTime;
	}
	
	/**
	 * compare this PhoneCall with another PhoneCall
	 * 
	 * @param call
	 *            another call to be compared.
	 * @return 0 if the argument phone call is equal to this phone call;
	 *         -1 if this call is before the call argument;
	 *         1 if this call is after the call argument.
	 * @throws NullPointerException
	 *             if another call is null.
	 */
	@Override
	public int compareTo(PhoneCall call) throws NullPointerException {
		Objects.requireNonNull(call);
		
		int timeDiff = this.startTime.compareTo(call.getStartTime());
		if (timeDiff > 0) {
			return 1;
		}
		else if (timeDiff < 0) {
			return -1;
		}
		else {
			int phoneDiff = this.callerPhoneNumber.compareTo(call.getCaller());
			if (phoneDiff > 0)
				return 1;
			else if (phoneDiff < 0)
				return -1;
			else
				return 0;
		}
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
		if (dateTimeStr.length != 3) {
			throw new IllegalArgumentException("Need date, time and am/pm marker argument");
		}
		
		String date = dateTimeStr[0];
		String dateStr[] = date.split("/");
		if (dateStr.length != 3) {
			throw new IllegalArgumentException("Invalid format of date argument");
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
			if (y.length() > 4 || yy > 9999 || yy == 0) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid year argument");
		}
		
		
		
		String time = dateTimeStr[1];
		String timeStr[] = time.split(":");
		if (timeStr.length != 2) {
			throw new IllegalArgumentException("Invalid format of time argument ");
		}
		
		String h = timeStr[0];
		String mi = timeStr[1];
		
		try {
			int hh = Integer.parseInt(h);
			if (hh > 12 || hh < 1) {
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
