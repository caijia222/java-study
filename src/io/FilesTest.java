package io;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilesTest {
	
	/**
	 * Files不可一次读入几个G的大文件
	 */
	@Test
	public void test() throws Exception {
		Path path = Paths.get("out/readme.txt");
		byte[] data = Files.readAllBytes(path);
		System.out.println(Arrays.toString(data));
		System.out.println("#################################");
		String str =Files.readString(path, StandardCharsets.UTF_8);
		System.out.println(str);
		System.out.println("#################################");
		List<String> lines = Files.readAllLines(Paths.get("out/readme.txt"), StandardCharsets.UTF_8);
		for (String line : lines) System.out.println(line);
	}
	
	@Test
	public void test2() throws Exception {
		Path path = Paths.get("out/readme.txt");
		Files.write(path, "Files真好用".getBytes(StandardCharsets.UTF_8));
		
		Files.writeString(path, "Files确实还可以", StandardCharsets.UTF_8,StandardOpenOption.APPEND);
		
		List<String> lines = List.of("第一行Files", "第er行Files");
		Files.write(path, lines, StandardCharsets.UTF_8,StandardOpenOption.APPEND);
	}
	
	@Test
	public void test3() throws Exception {
		Files.copy(null, null);
		Files.delete(null);
//		Files.exists(null, null);
//		Files.move(null, null, null);
	}
}
