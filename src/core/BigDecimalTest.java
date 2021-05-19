package core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class BigDecimalTest {
	
	@Test
	public void test1() {
		BigDecimal x = new BigDecimal("10023.4560");
		System.out.println(x.multiply(x));
		System.out.println(x.scale());
		System.out.println(x.stripTrailingZeros());
		System.out.println(x.setScale(2, RoundingMode.HALF_UP));
		System.out.println(x.divide(new BigDecimal("0.12"), RoundingMode.HALF_UP));
	}
	
	@Test
	public void test2() {
		BigDecimal x = new BigDecimal("12.345");
		BigDecimal y = new BigDecimal("0.12");
		BigDecimal[] divideAndRemainder = x.divideAndRemainder(y);
		System.out.println(divideAndRemainder[0]);
		System.out.println(divideAndRemainder[1]);
	}
	
	@Test
	public void test3() {
		BigDecimal x = new BigDecimal("12.345");
		BigDecimal y = new BigDecimal("12.34500");
		System.out.println(x.equals(y));
		System.out.println(x.equals(y.stripTrailingZeros()));
		System.out.println(x.compareTo(y));
		System.out.println(x.intValue());
	}
	
	@Test
	public void test4() {
		BigDecimal x = new BigDecimal("12.345");
		System.out.println(x.negate());
	}
}
