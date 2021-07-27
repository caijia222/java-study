package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class StreamOperation {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Test
	public void sortByStream() {
		List<String> list = List.of("Orange", "apple", "Banana");
		List<String> collect = list.stream().sorted().collect(Collectors.toList());
		logger.info(collect);
	}

	@Test
	public void sortByStream2() {
		List<String> list = List.of("Orange", "apple", "Banana");
		List<String> collect = list.stream().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
		logger.info(collect);
	}
	
	@Test
	public void distinctByStream() {
		List<String> list = List.of("A", "B", "A", "C", "B", "D");
		List<String> collect = list.stream().distinct().collect(Collectors.toList());
		logger.info(collect);
	}
	
	@Test
	public void interceptByStream() {
		List<String> list = List.of("A", "B", "C", "D", "E", "F");
		List<String> collect = list.stream().skip(2).limit(3).collect(Collectors.toList());
		logger.info(collect);
	}
	
	@Test
	public void concatByStream() {
		List<String> listA = List.of("A", "B", "C");
		List<String> listB = List.of("D", "E", "F");
		List<String> collect = Stream.concat(listA.stream(), listB.stream()).collect(Collectors.toList());
		logger.info(collect);
	}
	
	@Test
	public void flatMapByStream() {
		Stream<List<Integer>> s = Stream.of(
				Arrays.asList(1, 2, 3),
				Arrays.asList(4, 5, 6),
				Arrays.asList(7, 8, 9)
		);
		Stream<Integer> flatMap = s.flatMap(list -> list.stream());
		flatMap.forEach(System.out::println);
	}
	
	@Test
	public void parallel() {
		Stream<String> s = Stream.of("A", "B", "A", "C", "B", "D");
		String[] array = s.parallel()
		.sorted((s1,s2) -> s2.compareTo(s1))
		.toArray(String[]::new);
		System.out.println(Arrays.toString(array));
	}
}
