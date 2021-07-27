package lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

	@Test
	public void createStream() {
		Stream<Integer> naturals = Stream.generate(new NatualSupplier()); // 全体自然数
		naturals.filter(n -> n % 2 == 0).map(n -> n * n) // 不计算
				.limit(20) // 不计算
				.forEach(System.out::println); // 计算
	}

	class NatualSupplier implements Supplier<Integer> {
		int n = 0;

		@Override
		public Integer get() {
			n++;
			return n;
		}
	}

	@Test
	public void createStreamByFile() throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get("src/lambda/book.xml"))) {
			lines.forEach(System.out::println);
		}
	}

	@Test
	public void createStreamByPattern() {
		Pattern pattern = Pattern.compile("\\s+");
		Stream<String> stream = pattern.splitAsStream("The quick brown fox jumps over the lazy dog");
		stream.forEach(System.out::println);
	}

	@Test
	public void createStreamByBaseType() {
		IntStream is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		is.forEach(System.out::println);
		LongStream ls = List.of("1", "2", "3", "4", "5").stream().mapToLong(Long::parseLong);
		ls.forEach(System.out::println);
	}

	@Test
	public void mapOperation() {
		List.of("  Apple ", " pear ", " ORANGE", " BaNaNa ").stream().map(String::trim).map(String::toLowerCase)
				.forEach(System.out::println);
	}

	@Test
	public void filterOperation() {
		IntStream is = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		is.filter(n -> n % 2 != 0).forEach(System.out::println);
	}

	@Test
	public void reduceOperation() {
		int sum = Stream.of(1, 2, 3, 4, 59).reduce(0, (acc, n) -> acc + n);
		System.out.println(sum);
		Optional<Integer> opt = Stream.of(1, 2, 3, 4, 5).reduce((acc, n) -> acc + n);
		if (opt.isPresent()) {
			System.out.println(opt.get());
		}
		sum = Stream.of(1, 2, 3, 4, 5).reduce(1, (acc, n) -> acc * n);
		System.out.println(sum);
	}

	@Test
	public void streamOperation() {
		Stream.of("profile=native", "debug=true", "logging=warn", "interval=500")
		.map(kv -> {
			String[] sa = kv.split("\\=", 2);
			return Map.of(sa[0], sa[1]);
		}).reduce(new HashMap<String, String>(), (m, kv) -> {
			m.putAll(kv);
			return m;
		})
		.forEach((k,v)->System.out.println(k + "=" + v));
	}
}