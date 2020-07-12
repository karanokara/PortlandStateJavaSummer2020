package java8;

import static com.sandwich.util.Assert.assertEquals;

import java.util.function.Function;

import com.sandwich.koan.Koan;

public class AboutLambdas {
	
	interface Caps {
		public String capitalize(String name);
	}
	
	String fieldFoo = "Lambdas";
	
	@Override
	public String toString() {
		return "CAPS";
	}
	
	static String str = "";
	
	//lambda has access to "this", this class
	// "thisLambdaField" is an instance implementation of Caps interface, s is arg of "String name"
	Caps thisLambdaField = s -> this.toString();
	
	//lambda has access to object methods
	// "toStringLambdaField" is an instance implementation of Caps interface, s is arg of "String name"
	Caps toStringLambdaField = s -> toString();
	
	@Koan
	public void verySimpleLambda() throws InterruptedException {
		Runnable r8 = () -> str = "changed in lambda";
		
		// same as above
//		Runnable r8 = () -> {					// execute this fnc, like (args) => {...} in JS
//			str = "changed in lambda";
//		};
		
		r8.run();
		
		assertEquals(str, "changed in lambda");
	}
	
	@Koan
	public void simpleLambda() {
		Caps caps = (String n) -> {			// this implement the capitalize(String) abstract method
			return n.toUpperCase();
		};
		
		String capitalized = caps.capitalize("James");
		
		assertEquals(capitalized, "JAMES");
	}
	
	@Koan
	public void simpleSuccinctLambda() {
		//parameter type can be omitted,
		//code block braces {} and return statement can be omitted for single statement lambda
		//parameter parenthesis can be omitted for single parameter lambda
		Caps caps = s -> s.toUpperCase();
		
		String capitalized = caps.capitalize("Arthur");
		
		assertEquals(capitalized, "ARTHUR");
	}
	
	@Koan
	public void lambdaField() {
		assertEquals(thisLambdaField.capitalize(""), "CAPS");
	}
	
	@Koan
	public void lambdaField2() {
		assertEquals(toStringLambdaField.capitalize(""), "CAPS");
	}
	
	@Koan
	public void effectivelyFinal() {
		//final can be omitted like this:
		/* final */
		String effectivelyFinal = "I'm effectively final";
		
		Caps caps = s -> effectivelyFinal.toUpperCase();
		
		assertEquals(caps.capitalize(effectivelyFinal), "I'M EFFECTIVELY FINAL");
	}
	
	@Koan
	public void methodReference() {
		Caps caps = String::toUpperCase;			// assign the method from String to abstract method "capitalize"
		String capitalized = caps.capitalize("Gosling");
		
		assertEquals(capitalized, "GOSLING");
	}
	
	@Koan
	public void thisIsSurroundingClass() {
		//"this" in lambda points to the class of current this public method
		Function<String, String> foo = s -> s + this.fieldFoo + s;
		
		assertEquals(foo.apply("|"), "|Lambdas|");
	}
	
}

