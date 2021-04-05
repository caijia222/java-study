package io;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class OutputStreamTest {
	
	@Test
	public void test1() throws IOException {
		OutputStream os = new FileOutputStream("out/readme.txt");
		os.write(72);
		os.write(101);
		os.write(108);
		os.write(108);
		os.write(111);
		os.close();
	}

	@Test
	public void test2() throws IOException {
		OutputStream os = new FileOutputStream("out/readme.txt",true);
		os.write("Hello".getBytes(StandardCharsets.UTF_8));
		os.close();
	}
	
	@Test
	public void test3() throws Exception {
		try(OutputStream os = new FileOutputStream("out/readme.txt",true)){
			os.write("Hello".getBytes(StandardCharsets.UTF_8));
		}
	}
	
	@Test
	public void test4() throws Exception {
		byte[] data;
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			baos.write("Hello".getBytes(StandardCharsets.UTF_8));
			baos.write("world!".getBytes(StandardCharsets.UTF_8));
			data = baos.toByteArray();
		}
		System.out.println(new String(data,StandardCharsets.UTF_8));
	}
}
