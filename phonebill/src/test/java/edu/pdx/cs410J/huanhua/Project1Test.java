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
// * A unit test for code in the <code>Project1</code> class. This is different
// * from <code>Project1IT</code> which is an integration test (and can handle the calls
// * to {@link System#exit(int)} and the like.
// */
//public class Project1Test extends InvokeMainTestCase {
//	
//	private static final String THIS_IS_A_README_FILE = "This is a PhoneBill project";
//	
//	@Test
//	public void readmeCanBeReadAsResource() throws IOException {
//		try (InputStream readme = Project1.class.getResourceAsStream("README.txt");) {
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
//		String line = Project1.readFile("README.txt");
//		assertThat(line, containsString(THIS_IS_A_README_FILE));
//	}
//	
//	@Test
//	public void testReadFileWithoutExistFile() {
//		
//		String line = Project1.readFile("READM.txt");
//		assertThat(line, is(nullValue()));
//	}
//}
