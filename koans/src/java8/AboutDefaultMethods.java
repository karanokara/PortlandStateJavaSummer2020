package java8;

import static com.sandwich.util.Assert.assertEquals;

import com.sandwich.koan.Koan;

public class AboutDefaultMethods {
	
	@Koan
	public void interfaceDefaultMethod() {
		
		StringUtil stringUtil = new StringUtil() {
			
			@Override
			public String reverse(String s) {
				return new StringBuilder(s).reverse().toString();
			}
			
		};
		
		// capitalize() is a default method
		String capitalizedReversed = stringUtil.capitalize(stringUtil.reverse("gnirut"));
		
		assertEquals(capitalizedReversed, "TURING");
	}
	
	@Koan
	public void interfaceStaticMethod() {
		assertEquals(StringUtil.enclose("me"), "[me]");
	}
	
	interface StringUtil {
		
		int ab = 1;
		
		//static method in interface
		static String enclose(String in) {
			return "[" + in + "]";
		}
		
		String reverse(String s);
		
		//interface can contain non-abstract method implementations marked by "default" keyword
		default String capitalize(String s) {
			return s.toUpperCase();
		}
		
		default String capitalizeFirst(String s) {
			return s.substring(0, 1).toUpperCase() + s.substring(1);
		}
	}
	
}
