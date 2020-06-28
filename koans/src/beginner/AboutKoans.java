package beginner;

import static com.sandwich.util.Assert.fail;

import com.sandwich.koan.Koan;

public class AboutKoans {
	
	@Koan
	public void findAboutKoansFile() {
//        fail("delete this line to advance");
	}
	
	@Koan
	public void definitionOfKoanCompletion() {
		boolean koanIsComplete = true;
		if (!koanIsComplete) {
			fail("what if koanIsComplete variable was true?");
		}
	}
	
}
