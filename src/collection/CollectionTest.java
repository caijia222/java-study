package collection;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class CollectionTest {
	
	@Test
	public void test1() {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add(null);
		list.add("ccc");
		System.out.println(list.size());
		System.out.println(list.get(1));
	}

	@Test
	public void test2() {
		List<String> list = new LinkedList<String>();
		list.add("aaa");
		list.add(null);
		list.add("ccc");
		System.out.println(list.size());
		System.out.println(list.get(1));
	}
	
	@Test
	public void test3() {
		List<String> list = List.of("aaa","bbb","ccc");
		System.out.println(list.size());
		System.out.println(list.get(1));
		System.out.println(list.getClass().getName());
	}
	
	@Test
	public void test4() {
		List<String> list = List.of("aaa","bbb","ccc");
		for(Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	@Test
	public void test5() {
		List<String> list = List.of("aaa","bbb","ccc");
		Object[] array = list.toArray();
		System.out.println(Arrays.toString(array));
		String[] array2 = list.toArray(new String[3]);
		System.out.println(Arrays.toString(array2));
		String[] array3 = list.toArray(String[]::new);
		System.out.println(Arrays.toString(array3));
	}
	
	@Test
	public void test6() {
		Integer[] array = {1,2,3};
		List<Integer> list = List.of(array);
		System.out.println(list.getClass().getName());
		List<Integer> asList = Arrays.asList(array);
		System.out.println(asList.getClass().getName());
	}
	
	@Test
	public void test7() {
		List<String> list = List.of("A","B","C");
		System.out.println(list.contains("A"));
		System.out.println(list.contains(new String("C")));
		System.out.println(list.contains("X"));
		System.out.println(list.indexOf("A"));
		System.out.println(list.indexOf("X"));
	}
	
	@Test
	public void test8() {
		Map<String, Integer> map = new HashMap<>();
		map.put("apple", 123);
		map.put("pear", 456);
		System.out.println(map.put("apple", 789));
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			System.out.println(key+"="+map.get(key));
		}
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}
	
	@Test
	public void test9() {
		Person p1 = new Person("zhangsan",1);
		Person p2 = new Person("lisi",2);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println("a".hashCode());
		System.out.println(1024 & 0x1f);
	}
	
	@Test
	public void test10() {
		Map<DayOfWeek,String> map = new EnumMap<>(DayOfWeek.class);
		map.put(DayOfWeek.MONDAY, "星期一");
		map.put(DayOfWeek.TUESDAY, "星期二");
		map.put(DayOfWeek.WEDNESDAY, "星期三");
		map.put(DayOfWeek.THURSDAY, "星期四");
		map.put(DayOfWeek.FRIDAY, "星期五");
		map.put(DayOfWeek.SATURDAY, "星期六");
		map.put(DayOfWeek.SUNDAY, "星期日");
		System.out.println(map.get(DayOfWeek.MONDAY));
		System.out.println(map);
	}
	
	@Test
	public void test11() {
		Map<String,Integer> map = new TreeMap<>();
		map.put("orange", 1);
		map.put("apple", 2);
		map.put("pear", 3);
		for (String key : map.keySet()) {
			System.out.println(key);
		}
	}
	
	@Test
	public void test12() {
		Map<Person,Integer> map = new TreeMap<>();
		map.put(new Person("zhangsan",15), 80);
		map.put(new Person("lisi",16), 85);
		map.put(new Person("wangwu",13), 67);
		for (Person person : map.keySet()) {
			System.out.println(person);
		}
	}
	
	@Test
	public void test13() throws Exception {
		Properties pros = new Properties();
		pros.load(Files.newBufferedReader(Paths.get("conf/setting.properties"), StandardCharsets.UTF_8));
		System.out.println(pros.getProperty("openFile"));
		System.out.println(pros.getProperty("fileName"));
		pros.setProperty("saveValue", "保存的值");
		pros.store(Files.newBufferedWriter(Paths.get("conf/setting.properties"), StandardCharsets.UTF_8), "这是写入的properties注释");
	}

}
