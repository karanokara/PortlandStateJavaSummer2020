package beginner;

import static com.sandwich.util.Assert.assertEquals;

import com.sandwich.koan.Koan;

public class AboutBitwiseOperators {
	
	@Koan
	public void fullAnd() {
		int i = 1;
		
		if (true & (++i < 8))
			i = i + 1;
		assertEquals(i, 3);
	}
	
	@Koan
	public void shortCircuitAnd() {
		int i = 1;
		if (true && (i < -28))
			i = i + 1;
		assertEquals(i, 1);
	}
	
	@Koan
	public void aboutXOR() {
		int i = 1;
		int a = 6;
		if ((a < 9) ^ false)
			i = i + 1;
		assertEquals(i, 2);
	}
	
	@Koan
	public void dontMistakeEqualsForEqualsEquals() {
		int i = 1;
		boolean a = false;
		if (a = true)
			i++;
		assertEquals(a, true);
		assertEquals(i, 2);
		// How could you write the condition 'with a twist' to avoid this trap?
	}
	
	@Koan
	public void aboutBitShiftingRightShift() {
		int rightShift = 8;
		rightShift = rightShift >> 1;	// 1000 >> 1 = 0100
		assertEquals(rightShift, 4);
	}
	
	@Koan
	public void aboutBitShiftingLeftShift() {
		int leftShift = 0x80000000; // Is this number positive or negative? negative
		leftShift = leftShift << 1;
		assertEquals(leftShift, 0x00000000);	// 10000000000000000 << 1 = 0000000000000
	}
	
	@Koan
	public void aboutBitShiftingRightUnsigned() {
		int rightShiftNegativeStaysNegative = 0x80000000;
		
		rightShiftNegativeStaysNegative = rightShiftNegativeStaysNegative >> 4;
		assertEquals(rightShiftNegativeStaysNegative, 0x80000000 / 16);
		
		int unsignedRightShift = 0x80000000; // always fills with 0
		
		unsignedRightShift >>>= 4; // 100000000000000000b >> 4 = 00001000000000000000b = 0x0800000
		assertEquals(unsignedRightShift, 0x08000000);		// this is Hex, right shift 4 time is 1 hex
	}
}
