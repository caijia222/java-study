package exception;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;

public class ExceptionTest {
	
	@Test
	public void test1() {
		String msg = "我";
		byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
		System.out.println(Arrays.toString(bytes));
		try {
			bytes = msg.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(bytes));
	}
	
	@Test
	public void test2() {
		System.out.println(getNum());
	}
	
	@SuppressWarnings("finally")
	public static int getNum() {
		int x = 1;
		try {
			return x++;
		} catch (Exception e) {
			return x++;
		}finally {
			return ++x;
		}
	}
	
	@Test
	public void test3() {
		try {
			process1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void process1() {
		try {
			process2();
		} catch (NullPointerException e) {
			IllegalArgumentException le = new IllegalArgumentException(e);
			System.out.println("获取子异常="+le.getCause());
			throw le;
		}
	}
	
	static void process2() {
		throw new NullPointerException();
	}
	
	@Test
	public void test4() {
		try {
			Integer.parseInt("abd");
		} catch (Exception e) {
			System.out.println("catch");
			throw new RuntimeException(e);
		}finally {
			System.out.println("finally");
		}
	}
	
	@SuppressWarnings("finally")
	@Test
	public void test5() throws Exception {
		Exception origin = null;
		try {
			Integer.parseInt("abc");
		} catch (Exception e) {
			origin = e;
			throw e;
		}finally {
			Exception e = new IllegalArgumentException();
			if(origin != null) e.addSuppressed(origin);
			throw e;
		}
	}
	
	@Test
	public void test6() {
		Optional<String> readFromFile = readFromFile("accc");
		System.out.println(readFromFile.isPresent());
	}

	static Optional<String> readFromFile(String file){
		if(!Files.exists(Paths.get("file"))) {
			return Optional.empty();
		}
		return Optional.of("result");
	}
}

class BaseException extends RuntimeException{

	private static final long serialVersionUID = 1411869357571964819L;
	public BaseException() {
		super();
	}
	public BaseException(Throwable e) {
		super(e);
	}
	public BaseException(String message) {
		super(message);
	}
	public BaseException(String message, Throwable e) {
		super(message,e);
	}
}

class LoginFailedException extends BaseException{

	private static final long serialVersionUID = 3830721778119749535L;
	public LoginFailedException(String message) {
		super(message);
	}
}
