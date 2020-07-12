package java8;

import static com.sandwich.util.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.sandwich.koan.Koan;

public class AboutStreams {
	
	String strField = "";
	
	List<String> places = Arrays.asList("Belgrade", "Zagreb", "Sarajevo", "Skopje", "Ljubljana", "Podgorica");
	
	@Koan
	public void simpleCount() {
		long count = places.stream().count();
		assertEquals(count, 6l);
	}
	
	@Koan
	public void filteredCount() {
		long count = places.stream().filter(s -> s.startsWith("S")).count();
		
		assertEquals(count, 2l);
	}
	
	@Koan
	public void max() {
		String longest = places.stream().max(Comparator.comparing(cityName -> cityName.length())).get();
		
		assertEquals(longest, "Ljubljana");
	}
	
	@Koan
	public void min() {
		String shortest = places.stream().min(Comparator.comparing(cityName -> cityName.length())).get();
		
		assertEquals(shortest, "Zagreb");
	}
	
	@Koan
	public void reduce() {
		// reduce is perform fnc on each element, but return Only 1 result
		
		String join = places.stream().reduce("", String::concat);
		
		assertEquals(join, "BelgradeZagrebSarajevoSkopjeLjubljanaPodgorica");
		
		String join2 = places.stream().reduce("ID: ", String::concat);
		
		assertEquals(join2, "ID: BelgradeZagrebSarajevoSkopjeLjubljanaPodgorica");
	}
	
	@Koan
	public void reduceWithoutStarterReturnsOptional() {
		Optional<String> join = places.stream().reduce(String::concat);		// no start string "ID: "
		
		assertEquals(join.get(), "BelgradeZagrebSarajevoSkopjeLjubljanaPodgorica");
	}
	
	@Koan
	public void join() {
		String join = places.stream().reduce((accumulated, cityName) -> accumulated + "\", \"" + cityName).get();
		
		assertEquals(join, "Belgrade\", \"Zagreb\", \"Sarajevo\", \"Skopje\", \"Ljubljana\", \"Podgorica");
	}
	
	@Koan
	public void stringJoin() {
		String join = places.stream().collect(Collectors.joining("\", \""));
		
		assertEquals(join, "Belgrade\", \"Zagreb\", \"Sarajevo\", \"Skopje\", \"Ljubljana\", \"Podgorica");
	}
	
	@Koan
	public void mapReduce() {
		OptionalDouble averageLengthOptional = places.stream().mapToInt(String::length).average();
		
		double averageLength = Math.round(averageLengthOptional.getAsDouble());
		
		assertEquals(averageLength, 8.0);
	}
	
	@Koan
	public void parallelMapReduce() {
		int lengthSum = places.parallelStream().mapToInt(String::length).sum();
		
		assertEquals(lengthSum, 46);
		
		int lengthSum2 = places.stream().mapToInt(String::length).sum();
		
		assertEquals(lengthSum2, 46);
	}
	
	@Koan
	public void limitSkip() {
		int lengthSum_Limit_3_Skip_1 = places.stream().mapToInt(String::length).limit(3).skip(1).sum();
		
		// limit 3: "Belgrade", "Zagreb", "Sarajevo"
		// skip 1: "Zagreb", "Sarajevo"
		assertEquals(lengthSum_Limit_3_Skip_1, 14);
	}
	
	@Koan
	public void lazyEvaluation() {
		Stream stream = places.stream().filter(s -> {
			strField = "hello";
			return s.startsWith("S");
		});
		
		
		assertEquals(strField, "");		// lambda hasn't run, strField is a public field
		
		assertEquals(stream.count(), 2l);	// run lambda
		
		assertEquals(strField, "hello");	// now has value
	}
	
	@Koan
	public void sumRange() {
		int sum = IntStream.range(1, 4).sum();		// sum = 1 + 2 + 3
		
		assertEquals(sum, 6);
	}
	
	@Koan
	public void rangeToList() {
		List<Integer> range = IntStream.range(1, 4).boxed().collect(Collectors.toList());
		
		assertEquals(range, Arrays.asList(1, 2, 3));
	}
}
