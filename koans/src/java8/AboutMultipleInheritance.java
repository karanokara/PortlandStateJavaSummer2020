package java8;

import static com.sandwich.util.Assert.assertEquals;

import com.sandwich.koan.Koan;

public class AboutMultipleInheritance {
	
	interface Human {
		default String sound() {
			return "hello";
		}
	}
	
	interface Bull {
		default String sound() {
			return "moo";
		}
	}
	
	class Minotaur implements Human, Bull {
		//both interfaces implement same default method that has to be overridden
		
		@Override
		public String sound() {
			return Bull.super.sound();
		}
	}
	
	@Koan
	public void multipleInheritance() {
		Minotaur minotaur = new Minotaur();
		assertEquals(minotaur.sound(), "moo");
	}
	
}
