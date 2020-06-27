package edu.pdx.cs410J.huanhua;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;


/**
 * Unit tests for the Student class. In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest {
    
	private Student createStudentName(String name) {
		return new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
	}
	
    @Test
    public void studentNamedPatIsNamedPat() {
        String name = "Pat";
        var pat = createStudentName(name);
        assertThat(pat.getName(), equalTo(name));
    }

    
    @Test
    public void toStringContainsStudentName() {
        String name = "Pat";
        var pat = createStudentName(name);
        assertThat(pat.toString(), containsString(name));
    }
    
    @Test
    public void toStringContainsStudentGPA() {
        String name = "Pat";
        double gpa = 3.7;
		var pat = new Student(name, new ArrayList<>(), gpa, "Doesn't matter");
        assertThat(pat.toString(), containsString("has a GPA of " + gpa));
    }
    
}
