package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import org.junit.Test;

public class SerializableTest {
	
	@Test
	public void test1() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
			oos.writeInt(123456);
			oos.writeUTF("Hello");
			oos.writeObject(Double.valueOf(123.456));
			oos.writeObject(new Person("zhangsan","18"));
		}
		System.out.println(Arrays.toString(baos.toByteArray()));
	}
	
	@Test
	public void test2() throws Exception {
		byte[] data = {-84, -19, 0, 5, 119, 11, 0, 1, -30, 64, 0, 5, 72, 101, 108, 108, 111, 115, 114, 0, 16, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 68, 111, 117, 98, 108, 101, -128, -77, -62, 74, 41, 107, -5, 4, 2, 0, 1, 68, 0, 5, 118, 97, 108, 117, 101, 120, 114, 0, 16, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 78, 117, 109, 98, 101, 114, -122, -84, -107, 29, 11, -108, -32, -117, 2, 0, 0, 120, 112, 64, 94, -35, 47, 26, -97, -66, 119, 115, 114, 0, 9, 105, 111, 46, 80, 101, 114, 115, 111, 110, -109, 86, -66, -87, -76, 32, 115, 43, 2, 0, 2, 76, 0, 3, 97, 103, 101, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 0, 4, 110, 97, 109, 101, 113, 0, 126, 0, 4, 120, 112, 116, 0, 2, 49, 56, 116, 0, 8, 122, 104, 97, 110, 103, 115, 97, 110};
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		try(ObjectInputStream ois = new ObjectInputStream(bais)){
			System.out.println(ois.readInt());
			System.out.println(ois.readUTF());
			System.out.println((Double)ois.readObject());
			System.out.println((Person)ois.readObject());
		}
	}

}

class Person implements Serializable{
	private static final long serialVersionUID = -7829861266056252629L;
	public Person() {}
	public Person(String name,String age) {
		this.name = name;
		this.age = age;
	}
	private String name;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}