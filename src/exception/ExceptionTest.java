package exception;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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

}
