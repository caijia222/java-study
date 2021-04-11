package io;

import java.io.CharArrayWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

public class WriterTest {
	
	@Test
	public void test1() throws Exception {
		try(Writer writer = new FileWriter("out/readme.txt", StandardCharsets.UTF_8,true)){
			writer.write('H');
			writer.write("hello".toCharArray());
			writer.write("hello");
		}
	}
	
	@Test
	public void test2() throws Exception {
		try(CharArrayWriter writer = new CharArrayWriter()){
			writer.write('H');
			writer.write("hello".toCharArray());
			writer.write("hello");
			char[] data = writer.toCharArray();
			System.out.println(writer.toString());
			System.out.println(Arrays.toString(data));
		}
	}
	
	@Test
	public void test3() throws Exception {
		try(StringWriter writer = new StringWriter()){
			writer.write('H');
			writer.write("hello".toCharArray());
			writer.write("hello");
			System.out.println(writer.toString());
		}
	}
	
	@Test
	public void test4() throws Exception {
		try(Writer writer = new OutputStreamWriter(new FileOutputStream("out/readme.txt",true), StandardCharsets.UTF_8)){
			writer.write('H');
			writer.write("hello".toCharArray());
			writer.write("hello");
		}
	}

}
