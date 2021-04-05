package io;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class InputStreamTest {
	
	@Test
	public void test1() throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream("README.md");
			for(;;) {
				int n = in.read();
				if(n==-1) break;
				System.out.println(n);
			}
		} finally {
			if(in != null) in.close();
		}
	}
	
	@Test
	public void test2() throws IOException {
		try (InputStream in = new FileInputStream("README.md")){
			int n = -1;
			while((n = in.read()) != -1) {
				System.out.println(n);
			}
		}
	}
	
	@Test
	public void test3() throws IOException {
		try (InputStream in = new FileInputStream("README.md")){
			byte[] bytes = new byte[1024];
			int n = -1;
			while((n = in.read(bytes)) != -1) {
				System.out.println("读取了"+ n +"字节");
			}
		}
	}
	
	@Test
	public void test4() throws Exception {
		byte[] data = {72, 101, 108, 108, 111, 33};
		try(InputStream in = new ByteArrayInputStream(data)){
			int n = -1;
			StringBuilder sb = new StringBuilder();
			while((n=in.read()) != -1) sb.append((char)n);
			System.out.println(sb.toString());
		}
	}

}
