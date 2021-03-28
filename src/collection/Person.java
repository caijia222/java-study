package collection;

import java.util.Objects;

public class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	public Person() {}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person) {
			Person p = (Person) obj;
			return Objects.equals(name, p.name) && this.age==p.age;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
        return Objects.hash(name,age);
	}

	@Override
	public int compareTo(Person o) {
		int ret = Integer.compare(age, o.age);
		return  ret== 0 ? name.compareTo(o.name) : ret;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
