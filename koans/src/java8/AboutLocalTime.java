package java8;

import static com.sandwich.util.Assert.assertEquals;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.sandwich.koan.Koan;

public class AboutLocalTime {
	
	@Koan
	public void localTime() {
		LocalTime t1 = LocalTime.of(7, 30);
		
		assertEquals(t1, LocalTime.parse("07:30"));
	}
	
	@Koan
	public void localTimeMinus() {
		LocalTime t1 = LocalTime.parse("10:30");
		LocalTime t2 = t1.minus(2, ChronoUnit.HOURS);
		
		assertEquals(t2, LocalTime.parse("08:30"));
	}
	
}
