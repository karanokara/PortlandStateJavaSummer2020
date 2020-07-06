package beginner;

import static com.sandwich.util.Assert.assertEquals;

import com.sandwich.koan.Koan;

public class AboutMethodPreference {
	
	class A {
		public String doStuff(int i) {
			return "int";
		}
		
		public String doStuff(Integer i) {
			return "Integer";
		}
		
		public String doStuff(Object i) {
			return "Object";
		}
		
		public String doStuff(int... i) {
			return "int vararg";
		}
	}
	
	@Koan
	public void methodPreferenceInt() {
		assertEquals(new A().doStuff(1), "int");
	}
	
	@Koan
	public void methodPreferenceInteger() {
		assertEquals(new A().doStuff(Integer.valueOf(1)), "Integer");
	}
	
	@Koan
	public void methodPreferenceLong() {
		long l = 1;
		assertEquals(new A().doStuff(l), "Object");
	}
	
	@Koan
	public void methodPreferenceBoxedLong() {
		Long l = Long.valueOf(1);
		assertEquals(new A().doStuff(l), "Object");
	}
	
	@Koan
	public void methodPreferenceDouble() {
		Double l = Double.valueOf(1);
		assertEquals(new A().doStuff(l), "Object");
	}
	
	@Koan
	public void methodPreferenceMore() {
		assertEquals(new A().doStuff(1, Integer.valueOf(2)), "int vararg");
		
		// What happens if you change 'Integer' to 'Double'
		// Does this explain 'methodPreferenceDouble'?
		// Think about why this happens?
		//assertEquals(new A().doStuff(1, Double.valueOf(2)), "no applicable method for the arguments ");
	}
}
