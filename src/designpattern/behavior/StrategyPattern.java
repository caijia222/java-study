package designpattern.behavior;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class StrategyPattern {
	
	@Test
	public void test1() {
		String[] array = { "apple", "Pear", "Banana", "orange" };
		
		Arrays.sort(array, String::compareTo);
		System.out.println(Arrays.toString(array));
		
		Arrays.sort(array, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, (s1,s2)->s2.compareTo(s1));
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s2.compareToIgnoreCase(s1);
			}
		});
		System.out.println(Arrays.toString(array));
	}

}
