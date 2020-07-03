package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import edu.pdx.cs410J.InvokeMainTestCase;

/**
 * A unit test for code in the <code>Project1</code> class. This is different
 * from {@link Project1IT} which is an integration test (and can handle the calls
 * to {@link System#exit(int)} and the like.
 */
public class Project1Test extends InvokeMainTestCase {
	
	@Test
	public void readmeCanBeReadAsResource() throws IOException {
		try (InputStream readme = Project1.class.getResourceAsStream("README.txt");) {
			assertThat(readme, not(nullValue()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
			String line = reader.readLine();
			assertThat(line, containsString("This is a README file!"));
		}
	}
	
	@Test
	public void invokingMainWith1argHasError() {
		MainMethodResult result = invokeMain(Project1.class, "1");
		assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith2argHasError() {
		MainMethodResult result = invokeMain(Project1.class, "1", "2");
		assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith3argHasError() {
		MainMethodResult result = invokeMain(Project1.class, "1", "2", "3");
		assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith4argHasError() {
		MainMethodResult result = invokeMain(Project1.class, "1", "2", "3", "4");
		assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith5argHasError() {
		MainMethodResult result = invokeMain(Project1.class, "1", "2", "3", "4", "5");
		assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void invokingMainWith6argHasError() {
		MainMethodResult result = invokeMain(Project1.class, "1", "2", "3", "4", "5", "6");
		assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
		assertThat(result.getExitCode(), equalTo(1));
	}
	
	@Test
	public void projectOutputREADMEWithOption() {
		MainMethodResult result = invokeMain(Project1.class, "1", "2", "3", "4", "5", "-README");
		assertThat(result.getTextWrittenToStandardOut(), containsString("This is a README file!"));
		assertThat(result.getExitCode(), equalTo(0));
		
		
	}
	
	
}
