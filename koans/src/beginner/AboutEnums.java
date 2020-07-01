package beginner;

import static com.sandwich.util.Assert.assertEquals;

import com.sandwich.koan.Koan;

public class AboutEnums {
	
	
	enum Colors {
//		Red, Blue, Green, Yellow // what happens if you add a ; here?
		Red, Blue, Green, Yellow; // nothing change
		
		// What happens if you type Red() instead?
		// is a method
	}
	
	@Koan
	public void basicEnums() {
		Colors blue = Colors.Blue;
		assertEquals(blue.getClass(), Colors.class);
		assertEquals(blue, Colors.Blue);
		assertEquals(blue == Colors.Blue, true);
		assertEquals(blue == Colors.Red, false);
		assertEquals(blue instanceof Colors, true);
	}
	
	@Koan
	public void basicEnumsAccess() {
		Colors[] colorArray = Colors.values();
		assertEquals(colorArray[2], Colors.Green);
	}
	
	enum SkatSuits {
		Clubs(12), Spades(11), Hearts(10), Diamonds(9);
		
		SkatSuits(int v) {			// a constructor of above items, setting their property of "value"
			value = v;
		}
		
		private int value;
	}
	
	@Koan
	public void enumsWithAttributes() {
		// value is private but we still can access it. Why?
		// inside the same class of AboutEnum
		assertEquals(SkatSuits.Clubs.value, 12);
		
		// Try moving the enum outside the AboutEnum class... What do you expect?
		// Can't access
		
		// What happens?
		// The field SkatSuits.value is not visible
		
		assertEquals(SkatSuits.Clubs.value > SkatSuits.Spades.value, true);
	}
	
	enum OpticalMedia {
		CD(650), DVD(4300), BluRay(50000);
		
		OpticalMedia(int c) {
			capacityInMegaBytes = c;
		}
		
		int capacityInMegaBytes;
		
		int getCoolnessFactor() {
			return (capacityInMegaBytes - 1000) * 10;
		}
	}
	
	@Koan
	public void enumsWithMethods() {
		assertEquals(OpticalMedia.CD.getCoolnessFactor(), (650 - 1000) * 10);
		assertEquals(OpticalMedia.BluRay.getCoolnessFactor(), (50000 - 1000) * 10);
	}
}
