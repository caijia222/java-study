package core;

import java.math.BigInteger;

import org.junit.Test;

public class BigIntegerTest {
	
	@Test
	public void test1() {
		BigInteger a = new BigInteger("1234567890");
		System.out.println(a.pow(5));
	}
	
	@Test
	public void test2() {
		BigInteger x = new BigInteger("100");
		BigInteger y = new BigInteger("200");
		BigInteger sum = x.add(y);
		System.out.println(sum);
		sum = x.subtract(y);
		System.out.println(sum);
		sum = x.multiply(y);
		System.out.println(sum);
		sum = x.divide(y);
		System.out.println(sum);
	}
	
	@Test
	public void test3() {
		BigInteger x = new BigInteger("123456789000");
		System.out.println(x.longValue());
		System.out.println(x.multiply(x));
		System.out.println(x.multiply(x).intValue());
		System.out.println(x.multiply(x).floatValue());
		System.out.println(x.multiply(x).intValueExact());
	}
	
	@Test
	public void test4() {
		BigInteger x = new BigInteger("99999").pow(99);
		System.out.println(x.floatValue());
	}
	

}
