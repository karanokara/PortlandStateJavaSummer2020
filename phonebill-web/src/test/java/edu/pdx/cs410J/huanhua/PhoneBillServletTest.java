package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;

/**
 * A unit test for the {@link PhoneBillServlet}. It uses mockito to
 * provide mock http requests and responses.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneBillServletTest {
	
	static final String CUSTOMER_PARAMETER = "customer";
	static final String CALLER_PARAMETER = "callerNumber";
	static final String CALLEE_PARAMETER = "calleeNumber";
	static final String STARTDATETIME_PARAMETER = "start";
	static final String ENDDATETIME_PARAMETER = "end";
	
	// need to be static final to preserve data
	static final private PhoneBillServlet servlet = new PhoneBillServlet();
	
	@Test
	public void aInitiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter pw = mock(PrintWriter.class);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doGet(request, response);
		
		verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"customer\" is missing");
//		verify(response).setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
//		verify(pw).println();
	}
	
	@Test
	public void aGetNoCustomerFound() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		String customer = "abc";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter pw = mock(PrintWriter.class);
		
		when(response.getWriter()).thenReturn(pw);
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
		
		servlet.doGet(request, response);
		
		verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Can't find customer \"" + customer + "\"");
//		verify(response).setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
//		verify(pw).println();
	}
	
	@Test
	public void aGetNoCustomerFoundInvalidSearch() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		String customer = "abc";
		String date1 = "1/152020";
		String time1 = "12:45";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:59";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter pw = mock(PrintWriter.class);
		
		when(response.getWriter()).thenReturn(pw);
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		servlet.doGet(request, response);
		
		verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Required parameters error");
//		verify(response).setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
//		verify(pw).println();
	}
	
	
	@Test
	public void aPostMissArgPhoneBill1() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
	
//		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
//		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		
		verify(response).sendError(statusCode.capture(), msg.capture());
		
		assertThat(msg.getValue(), containsString("The required parameter"));
		assertThat(msg.getValue(), containsString("is missing"));
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_PRECONDITION_FAILED));
	}
	
	
	@Test
	public void aPostMissArgPhoneBill2() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
//		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
//		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		
		verify(response).sendError(statusCode.capture(), msg.capture());
		
		assertThat(msg.getValue(), containsString("The required parameter"));
		assertThat(msg.getValue(), containsString("is missing"));
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_PRECONDITION_FAILED));
	}
	
	
	@Test
	public void aPostMissArgPhoneBill3() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
		String phone1 = "111-111-1112";
//		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
//		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		
		verify(response).sendError(statusCode.capture(), msg.capture());
		
		assertThat(msg.getValue(), containsString("The required parameter"));
		assertThat(msg.getValue(), containsString("is missing"));
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_PRECONDITION_FAILED));
	}
	
	@Test
	public void aPostMissArgPhoneBill4() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
//		String date1 = "1/15-2020";
//		String time1 = "12:35";
//		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
//		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		
		verify(response).sendError(statusCode.capture(), msg.capture());
		
		assertThat(msg.getValue(), containsString("The required parameter"));
		assertThat(msg.getValue(), containsString("is missing"));
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_PRECONDITION_FAILED));
	}
	
	@Test
	public void aPostMissArgPhoneBill5() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";
		String time1 = "12:35";
		String marker1 = "am";
//		String date2 = "1/15/2020";
//		String time2 = "12:33";
//		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
//		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		
		verify(response).sendError(statusCode.capture(), msg.capture());
		
		assertThat(msg.getValue(), containsString("The required parameter"));
		assertThat(msg.getValue(), containsString("is missing"));
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_PRECONDITION_FAILED));
	}
	
	@Test
	public void aPostWongFormatPhoneBill() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15-2020";			// wrong format
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<String> msg = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		
		verify(response).sendError(statusCode.capture(), msg.capture());
		
		assertThat(msg.getValue(), containsString("Required parameters error"));
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_PRECONDITION_FAILED));
	}
	
	@Test
	public void bAddPhoneBill() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
		String phone1 = "111-111-1111";
		String phone2 = "111-222-2221";
		String date1 = "1/15/2020";
		String time1 = "12:35";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		assertThat(stringWriter.toString(), containsString("Added a new PhoneCall to customer " + name + "'s PhoneBill"));
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		verify(response).setStatus(statusCode.capture());
		
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
		
	}
	
	@Test
	public void bGetCustomerFound1() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String customer = "abc";
		String phone1 = "111-111-1111";
		String phone2 = "111-222-2221";
//		String date1 = "1/15/2020";
//		String time1 = "12:35";
//		String marker1 = "am";
//		String date2 = "1/15/2020";
//		String time2 = "12:33";
//		String marker2 = "Pm";
		
		HttpServletRequest request1 = mock(HttpServletRequest.class);
		HttpServletResponse response1 = mock(HttpServletResponse.class);
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response1.getWriter()).thenReturn(pw);
		when(request1.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
		
		servlet.doGet(request1, response1);
		
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		verify(response1).setStatus(statusCode.capture());
		
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
		assertThat(stringWriter.toString(), containsString(customer));
		assertThat(stringWriter.toString(), containsString(phone1));
		assertThat(stringWriter.toString(), containsString(phone2));
	}
	
	@Test
	public void cAddPhoneBill() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
		String phone1 = "111-111-1112";
		String phone2 = "111-222-2222";
		String date1 = "1/15/2020";
		String time1 = "12:45";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:59";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		assertThat(stringWriter.toString(), containsString("Added a new PhoneCall to customer " + name + "'s PhoneBill"));
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		verify(response).setStatus(statusCode.capture());
		
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
		
	}
	
	@Test
	public void cGetCustomerFound2() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String phone1 = "111-111-1111";
		String phone2 = "111-222-2221";
		
//		String date1 = "1/15/2020";
//		String time1 = "12:35";
//		String marker1 = "am";
//		String date2 = "1/15/2020";
//		String time2 = "12:33";
//		String marker2 = "Pm";
		
		String name = "abc";
		String phone11 = "111-111-1112";
		String phone22 = "111-222-2222";
//		String date1 = "1/15/2020";
		String time11 = "12:35";
//		String marker1 = "am";
//		String date2 = "1/15/2020";
		String time22 = "12:45";
//		String marker2 = "Pm";
		
		HttpServletRequest request1 = mock(HttpServletRequest.class);
		HttpServletResponse response1 = mock(HttpServletResponse.class);
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response1.getWriter()).thenReturn(pw);
		when(request1.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		
		servlet.doGet(request1, response1);
		
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		verify(response1).setStatus(statusCode.capture());
		
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
		assertThat(stringWriter.toString(), containsString(name));
		assertThat(stringWriter.toString(), containsString(phone1));
		assertThat(stringWriter.toString(), containsString(phone2));
		assertThat(stringWriter.toString(), containsString(phone11));
		assertThat(stringWriter.toString(), containsString(phone22));
		assertThat(stringWriter.toString(), containsString(time11));
		assertThat(stringWriter.toString(), containsString(time22));
	}
	
	@Test
	public void dAddPhoneBill() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String name = "abc";
		String phone1 = "111-111-1113";
		String phone2 = "111-222-2223";
		String date1 = "1/15/2020";
		String time1 = "12:55";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:33";
		String marker2 = "Pm";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(CALLER_PARAMETER)).thenReturn(phone1);
		when(request.getParameter(CALLEE_PARAMETER)).thenReturn(phone2);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		
		// Use a StringWriter to gather the text from multiple calls to println()
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		
		servlet.doPost(request, response);
		
		assertThat(stringWriter.toString(), containsString("Added a new PhoneCall to customer " + name + "'s PhoneBill"));
		
		// Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		verify(response).setStatus(statusCode.capture());
		
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
		
	}
	
	
	
	@Test
	public void dGetCustomerFoundSearch() throws ServletException, IOException {
//		PhoneBillServlet servlet = new PhoneBillServlet();
		
		String phone1 = "111-111-1111";
		String phone2 = "111-222-2221";
		
		String phone11 = "111-111-1112";
		String phone22 = "111-222-2222";
		
		String phone111 = "111-111-1113";
		String phone222 = "111-222-2223";
		
		String name = "abc";
		
		String date1 = "1/15/2020";
		String time1 = "12:40";
		String marker1 = "am";
		String date2 = "1/15/2020";
		String time2 = "12:50";
		String marker2 = "am";
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter, true);
		
		when(response.getWriter()).thenReturn(pw);
		when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(name);
		when(request.getParameter(STARTDATETIME_PARAMETER)).thenReturn(date1 + " " + time1 + " " + marker1);
		when(request.getParameter(ENDDATETIME_PARAMETER)).thenReturn(date2 + " " + time2 + " " + marker2);
		
		servlet.doGet(request, response);
		
		ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
		verify(response).setStatus(statusCode.capture());
		
		assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
		assertThat(stringWriter.toString(), containsString(name));
		assertThat(stringWriter.toString(), containsString(phone11));
		assertThat(stringWriter.toString(), containsString(phone22));
		assertThat(stringWriter.toString(), not(containsString(phone1)));
		assertThat(stringWriter.toString(), not(containsString(phone2)));
		assertThat(stringWriter.toString(), not(containsString(phone111)));
		assertThat(stringWriter.toString(), not(containsString(phone222)));
	}
}
