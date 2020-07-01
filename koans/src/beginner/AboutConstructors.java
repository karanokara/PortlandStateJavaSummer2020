package beginner;


import static com.sandwich.util.Assert.assertEquals;

import com.sandwich.koan.Koan;

public class AboutConstructors {
	
	class A {
		String someString = "a";
		
		public A() {
			someString += "x";
		}
		
	}
	
	class B extends A {
		public B() {
//			super();			// is called by default
			someString += "g";
		}
		
	}
	
	@Koan
	public void simpleConstructorOrder() {
		assertEquals(new B().someString, "axg");
	}
	
	class Aa {
		String someString = "a";
		
		public Aa() {
			someString += "x";
		}
		
		public Aa(String s) {
			someString += s;
		}
	}
	
	class Bb extends Aa {
		public Bb() {
			super("Boo");
			someString += "g";
		}
		
	}
	
	@Koan
	public void complexConstructorOrder() {
		assertEquals(new Bb().someString, "aBoog");
	}
	
}
