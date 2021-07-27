package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LambdaTest {

	@Test
	public void compareByAnonymousInnerClass() {
		String[] array = new String[] {"Apple","Orange","Banana","Lemon"};
		Arrays.sort(array, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});
		System.out.println(String.join(",", array));
	}
	
	@Test
	public void compareByLambda() {
		String[] array = new String[] {"Apple","Orange","Banana","Lemon"};
		Arrays.sort(array, (s1,s2)->s1.compareToIgnoreCase(s2));
		Arrays.sort(array, String::compareToIgnoreCase);
		Arrays.sort(array, LambdaTest::cmp);
		System.out.println(String.join(",", array));
	}
	
	public static int cmp(String s1,String s2) {
		return s1.compareToIgnoreCase(s2);
	}
	
	@Test
	public void stringToObject() {
		List<String> names = List.of("Bob", "Alice", "Tim");
		List<Person> persons = names.stream().map(Person::new).collect(Collectors.toList());
		System.out.println(persons);
	}
	
}
class Person{
	String name;
	public Person(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}
