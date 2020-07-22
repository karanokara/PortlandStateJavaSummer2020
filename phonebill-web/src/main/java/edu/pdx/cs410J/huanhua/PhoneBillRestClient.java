package edu.pdx.cs410J.huanhua;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.IOException;
import java.util.Map;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

/**
 * A helper class for accessing the rest client. Note that this class provides
 * an example of how to make gets and posts to a URL. You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class PhoneBillRestClient extends HttpRequestHelper {
	
	private static final String WEB_APP = "phonebill";
	private static final String SERVLET = "calls";
	static final String CUSTOMER_PARAMETER = "customer";
	static final String CALLER_PARAMETER = "callerNumber";
	static final String CALLEE_PARAMETER = "calleeNumber";
	static final String STARTDATETIME_PARAMETER = "start";
	static final String ENDDATETIME_PARAMETER = "end";
	
	private final String url;
	
	
	/**
	 * Creates a client to the Phone Bill REST service running on the given host and port
	 * 
	 * @param hostName
	 *            The name of the host
	 * @param port
	 *            The port
	 */
	public PhoneBillRestClient(String hostName, int port) {
		this.url = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
	}
	
	/**
	 * Returns a PhoneBill from the server
	 * 
	 * @param customer
	 * @param start
	 * @param end
	 * @return PhoneBill string
	 * @throws ParserException
	 * @throws IOException
	 */
	public String getPhoneBill(String customer, String start, String end) throws IOException, PhoneBillRestException {
		Response response = null;
		if (start == null || end == null)
			response = get(this.url, Map.of(CUSTOMER_PARAMETER, customer));
		else
			response = get(this.url, Map.of(CUSTOMER_PARAMETER, customer, STARTDATETIME_PARAMETER, start, ENDDATETIME_PARAMETER, end));
		
		throwExceptionIfNotOkayHttpStatus(response);
		
		return response.getContent();
	}
	
	/**
	 * Send a PhoneBill to server
	 * 
	 * @param customer
	 * @param start
	 * @param end
	 * @return response
	 * @throws IOException
	 */
	public String postPhoneBill(String customer, String caller, String callee, String start, String end) throws IOException, PhoneBillRestException {
		Response response = post(this.url, Map.of(CUSTOMER_PARAMETER, customer, CALLER_PARAMETER, caller, CALLEE_PARAMETER, callee, STARTDATETIME_PARAMETER, start, ENDDATETIME_PARAMETER, end));
		
		throwExceptionIfNotOkayHttpStatus(response);
		
		return response.getContent();
	}
	
//	/**
//	 * Returns the definition for the given word
//	 */
//	public String getDefinition(String word) throws IOException {
//		Response response = get(this.url, Map.of("word", word));
//		throwExceptionIfNotOkayHttpStatus(response);
//		String content = response.getContent();
//		return Messages.parseDictionaryEntry(content).getValue();
//	}
	
//	public void addDictionaryEntry(String word, String definition) throws IOException {
//		Response response = postToMyURL(Map.of("word", word, "definition", definition));
//		throwExceptionIfNotOkayHttpStatus(response);
//	}
//	
//	@VisibleForTesting
//	Response postToMyURL(Map<String, String> dictionaryEntries) throws IOException {
//		return post(this.url, dictionaryEntries);
//	}
//	
//	public void removeAllDictionaryEntries() throws IOException {
//		Response response = delete(this.url, Map.of());
//		throwExceptionIfNotOkayHttpStatus(response);
//	}
	
	/**
	 * throw exception if response is not OK
	 * 
	 * @param response
	 * @return response
	 * @throws PhoneBillRestException
	 */
	private Response throwExceptionIfNotOkayHttpStatus(Response response) throws PhoneBillRestException {
		int code = response.getCode();
		
		if (code != HTTP_OK) {
			throw new PhoneBillRestException(code, response.getContent());
		}
		
		return response;
	}
	
	
	class PhoneBillRestException extends RuntimeException {
		PhoneBillRestException(int httpStatusCode, String description) {
			super("HTTP Status Code " + httpStatusCode + ": " + description);
		}
	}
	
}
