package io;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class PrintTest {
	
	@Test
	public void test() throws Exception {
		PrintStream out = System.out;
		out.println("hello world");
		PrintStream err = System.err;
		err.println("cuowu");

		try(PrintStream printStream = new PrintStream("out/readme.txt", StandardCharsets.UTF_8)){
			printStream.println("增加第一行");
			printStream.println("增加第二行");
			printStream.write('H');
		}
	}
	
	@Test
	public void test2() throws Exception {
		try(PrintWriter printWriter = new PrintWriter("out/readme.txt", StandardCharsets.UTF_8)){
			printWriter.println("第一行");
			printWriter.println("第二行");
			printWriter.write('H');
		}
	}
	
	@Test
	public void test3() {
		StringWriter sw = new StringWriter();
		try(PrintWriter printWriter = new PrintWriter(sw)){
			printWriter.println("哇哇哇");
			printWriter.println("哈哈哈");
			printWriter.write('H');
			System.out.println(sw.toString());
		}
	}

}
