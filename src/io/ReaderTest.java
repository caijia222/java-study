package io;

import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class ReaderTest {
	
	@Test
	public void test1() throws Exception {
		Reader reader = new FileReader("out/readme.txt");
		int n = -1;
		while((n = reader.read()) != -1) {
			System.out.print((char)n);
		}
		reader.close();
	}
	
	@Test
	public void test2() throws IOException {
		try(Reader reader = new FileReader("out/readme.txt",StandardCharsets.UTF_8)){
			char[] buffer = new char[1024];
			int n = -1;
			while((n = reader.read(buffer)) != -1) System.out.println("read " + n + " chars");
		}
	}

	@Test
	public void test3() throws Exception {
		try(Reader reader = new CharArrayReader("hello".toCharArray())){
			int n = -1;
			while((n = reader.read()) != -1) System.out.print((char)n);
		}
	}
	
	
	
}
