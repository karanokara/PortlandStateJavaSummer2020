package edu.pdx.cs410J.huanhua;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;

import org.junit.Test;


/**
 * Unit tests for the Student class. In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest {
	
	private Student createStudentNamed(String name) {
		return new Student(name, new ArrayList<>(), 0.0, "other");
	}
	
	@Test
	public void studentNamedPatIsNamedPat() {
		String name = "Pat";
		var pat = createStudentNamed(name);
		assertThat(pat.getName(), equalTo(name));
	}
	
	@Test
	public void toStringContainsStudentName() {
		String name = "Pat";
		Student pat = createStudentNamed(name);
		assertThat(pat.toString(), containsString(name));
	}
	
	@Test
	public void toStringContainsGpa() {
		double gpa = 3.76;
		Student pat = new Student("Pat", new ArrayList<>(), gpa, "other");
		assertThat(pat.toString(), containsString("has a GPA of " + gpa));
	}
	
	@Test
	public void toStringForExampleInAssignment() {
		Student dave = createDaveStudent();
		
		assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating " +
				"Systems, and Java.  He says \"This class is too much work\"."));
	}
	
	private Student createDaveStudent() {
		ArrayList<String> classes = new ArrayList<>();
		classes.add("Algorithms");
		classes.add("Operating Systems");
		classes.add("Java");
		return new Student("Dave", classes, 3.64, "male");
	}
	
	@Test
	public void daveTakes3Classes() {
		Student dave = createDaveStudent();
		
		assertThat(dave.toString(), containsString("and is taking 3 classes:"));
	}
	
	@Test
	public void studentTaking1ClassHasASingularWord() {
		ArrayList<String> classes = new ArrayList<>();
		classes.add("English");
		Student student = new Student("Name", classes, 1.23, "other");
		
		assertThat(student.toString(), containsString("and is taking 1 class:"));
	}
	
	@Test
	public void studentTaking0ClassesHasNoColonInSentence() {
		ArrayList<String> classes = new ArrayList<>();
		Student student = new Student("Name", classes, 1.23, "other");
		
		assertThat(student.toString(), containsString("and is taking 0 classes.  "));
	}
	
	@Test
	public void studentTaking1ClassHasNoComma() {
		ArrayList<String> classes = new ArrayList<>();
		classes.add("English");
		Student student = new Student("Name", classes, 1.23, "other");
		
		assertThat(student.toString(), containsString("class: English.  "));
	}
	
	@Test
	public void studentTaking2ClassHasNoComma() {
		ArrayList<String> classes = new ArrayList<>();
		classes.add("English");
		classes.add("History");
		Student student = new Student("Name", classes, 1.23, "other");
		
		assertThat(student.toString(), containsString("classes: English and History."));
	}
	
	@Test
	public void davesToStringHasAllClassNames() {
		Student dave = createDaveStudent();
		
		assertThat(dave.toString(), containsString("classes: Algorithms, Operating Systems, and Java."));
	}
	
	@Test
	public void femaleStudentHasShePronoun() {
		Student female = new Student("her", new ArrayList<>(), 3.78, "female");
		
		assertThat(female.toString(), containsString("She says"));
	}
	
	@Test
	public void otherStudentHasTheyPronoun() {
		Student other = new Student("Other", new ArrayList<>(), 3.78, "other");
		
		assertThat(other.toString(), containsString("They say"));
	}
	
	@Test
	public void maleStudentHasHePronoun() {
		Student male = new Student("Him", new ArrayList<>(), 3.78, "male");
		
		assertThat(male.toString(), containsString("He says"));
	}
	
	@Test(expected = UnsupportedGenderException.class)
	public void studentWithUnsupportedGenderThrowsUnsupportedGenderException() {
		new Student("Unsupported", new ArrayList<>(), 3.78, "Unsupported");
	}
	
	@Test
	public void allStudentsSayThisClassIsTooMuchWork() {
		Student dave = createDaveStudent();
		
		assertThat(dave.says(), equalTo("This class is too much work"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void studentWithAnEmptyNameThrowIllegalArgumentException() {
		new Student("", new ArrayList<>(), 3.45, "other");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void gpaGreaterThan40ThrowsIllegalArgumentException() {
		new Student("Name", new ArrayList<>(), 4.5, "other");
	}
}