package core;

import static java.lang.System.out;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class ToolTest {

	@Test
	public void test1() {
		out.println(Math.abs(-100));
		out.println(Math.abs(-7.8));
		out.println(Math.max(2, 3));
		out.println(Math.min(1.2, 2.3));
		out.println(Math.pow(2, 10));
		out.println(Math.sqrt(2));
		out.println(Math.exp(2));
		out.println(Math.log(4));
		out.println(Math.log10(100));
		out.println(Math.sin(3.14));
		out.println(Math.cos(3.14));
		out.println(Math.tan(3.14));
		out.println(Math.asin(1.0));
		out.println(Math.acos(1.0));
	}

	@Test
	public void test2() {
		out.println(Math.PI);
		out.println(Math.E);
	}

	@Test
	public void test3() {
		double x = Math.random(); // x的范围是[0,1)
		double min = 10;
		double max = 50;
		double y = x * (max - min) + min; // y的范围是[10,50)
		long n = (long) y; // n的范围是[10,50)的整数
		System.out.println(y);
		System.out.println(n);
	}

	@Test
	public void test4() {
		Random r = new Random();
		out.println(r.nextInt());
		out.println(r.nextInt(10));
		out.println(r.nextLong());
		out.println(r.nextFloat());
		out.println(r.nextDouble());
		r = new Random(12345);// 制定了种子会得到完全确定的随机数序列
		for (int i = 0; i < 10; i++) {
			out.println(r.nextInt(100));
		}
	}
	
	@Test
	public void test5() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
        	System.out.println("普通");
            sr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
	}
}
