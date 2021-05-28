package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class StreamToCollectionTest {
	private static final Logger logger = LogManager.getLogger();
	
	@Test
	public void streamToList() {
		Stream<String> stream = Stream.of("Apple","",null,"Pear","  ","Orange");
		List<String> list = stream.filter(str -> str != null && !str.isBlank()).collect(Collectors.toList());
		logger.info(list);
	}

	@Test
	public void streamToSet() {
		Stream<String> stream = Stream.of("Apple","",null,"Pear","  ","Orange");
		Set<String> set = stream.filter(str -> str != null && !str.isBlank()).collect(Collectors.toSet());
		logger.info(set);
	}
	
	@Test
	public void streamToArray() {
		List<String> list = List.of("Apple","Banana","Orange");
		String[] array = list.stream().toArray(String[]::new);
		logger.info(Arrays.toString(array));
	}
	
	@Test
	public void streamToMap() {
		Stream<String> stream = Stream.of("APPL:Apple","MSFT:Microsoft");
		Map<String,String> map = stream
				.collect(Collectors.toMap(
				s -> s.substring(0,s.indexOf(':')), // 把元素s映射为key:
				s -> s.substring(s.indexOf(':') + 1))); // 把元素s映射为value:
		logger.info(map);
	}
	
	@Test
	public void streamToGroup() {
		Stream<String> stream = Stream.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
		Map<String, List<String>> groups = stream.collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
		logger.info(groups);
	}

}
