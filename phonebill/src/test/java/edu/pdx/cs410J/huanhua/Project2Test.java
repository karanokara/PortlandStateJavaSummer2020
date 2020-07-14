//package edu.pdx.cs410J.huanhua;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import org.junit.Test;
//
//import edu.pdx.cs410J.InvokeMainTestCase;
//
///**
// * A unit test for code in the <code>Project2</code> class. This is different
// * from <code>Project2IT</code> which is an integration test (and can handle the calls
// * to {@link System#exit(int)} and the like.
// */
//public class Project2Test extends InvokeMainTestCase {
//	
//	private static final String THIS_IS_A_README_FILE = "This is a PhoneBill project";
//	
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void testCreatePhoneBillWithWrongBillArgumentsException() {
//		String name = "";
//		String arguments[] = { name, "111-111-1112", "111-222-2222", "1/15/2020", "19:35", "1/15/2020", "19:60" };
//		
//		
//		PhoneBill bill = Project2.createPhoneBillWithArguments(arguments);
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void testCreatePhoneBillWithWrongCallArgumentsException() {
//		String name = "abc";
//		String arguments[] = { name, "111-111-1112", "111-222-2222", "1/15/2020", "19:35", "1/15/2020", "19:60" };
//		
//		
//		PhoneBill bill = Project2.createPhoneBillWithArguments(arguments);
//	}
//	
//	
//	@Test
//	public void readmeCanBeReadAsResource() throws IOException {
//		try (InputStream readme = Project2.class.getResourceAsStream("README2.txt");) {
//			assertThat(readme, not(nullValue()));
//			BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
//			String line = reader.readLine();
//			assertThat(line, containsString(THIS_IS_A_README_FILE));
//		}
//	}
//	
//	
//	@Test
//	public void testReadFileWithExistFile() {
//		
//		String line = Project2.readFile("README2.txt");
//		assertThat(line, containsString(THIS_IS_A_README_FILE));
//	}
//	
//	@Test
//	public void testReadFileWithoutExistFile() {
//		
//		String line = Project2.readFile("READM.txt");
//		assertThat(line, is(nullValue()));
//	}
//	
//	@Test
//	public void testCreatePhoneBillWithArguments() {
//		String name = "abc";
//		String arguments[] = { name, "111-111-1112", "111-222-2222", "1/15/2020", "19:35", "1/15/2020", "19:33" };
//		
//		
//		PhoneBill bill = Project2.createPhoneBillWithArguments(arguments);
//		assertThat(bill.toString(), containsString(name + "'s phone bill with " + 1 + " phone calls"));
//	}
//	
//}
