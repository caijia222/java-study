package collection;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class CollectionTest{
	
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
		String settings = "# test" + "\n" + "course=Java" + "\n" + "last_open_date=2019-08-07T12:35:01";
		Properties pros = new Properties();
		StringReader sr = new StringReader(settings);
		pros.load(sr);
		ByteArrayInputStream bais = new ByteArrayInputStream(settings.getBytes(StandardCharsets.UTF_8));
		pros.load(bais);
		System.out.println("course=" + pros.getProperty("course"));
	}
	
	@Test
	public void test14() throws Exception {
		Properties pros = new Properties();
		pros.load(Files.newBufferedReader(Paths.get("conf/setting.properties"), StandardCharsets.UTF_8));
		System.out.println(pros.getProperty("openFile"));
		System.out.println(pros.getProperty("fileName"));
		pros.setProperty("saveValue", "保存的值");
		pros.store(Files.newBufferedWriter(Paths.get("conf/setting.properties"), StandardCharsets.UTF_8), "这是写入的properties注释");
	}
	
	@Test
	public void test15() {
		Set<String> set = new HashSet<>();
		System.out.println(set.add("abc"));
		System.out.println(set.add("xyz"));
		System.out.println(set.add("xyz"));
		System.out.println(set.contains("xyz"));
		System.out.println(set.contains("XYZ"));
		System.out.println(set.remove("hello"));
		System.out.println(set.size());
	}
	
	@Test
	public void test16() {
		Set<String> set = new TreeSet<>();
		set.add("orange");
		set.add("banana");
		set.add("apple");
		for (String string : set) {
			System.out.println(string);
		}
	}
	
	@Test
	public void test17() {
		Queue<String> queue = new LinkedList<>();
		queue.add("apple");
		queue.offer("banana");
		queue.offer("orange");
		System.out.println(queue.poll());
		System.out.println(queue.remove());
		System.out.println(queue.peek());
		System.out.println(queue.element());
	}
	
	@Test
	public void test18() {
		Queue<String> queue = new PriorityQueue<>();
		queue.offer("apple");
		queue.offer("pear");
		queue.offer("banana");
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
	
	@Test
	public void test19() {
		Deque<String> deque = new LinkedList<>();
		deque.offerLast("A");
		deque.offerLast("B");
		deque.offerFirst("C");
		System.out.println(deque.pollFirst());
		System.out.println(deque.pollLast());
		System.out.println(deque.pollFirst());
		System.out.println(deque.pollFirst());
	}
	
	@Test
	public void test20() {
		Deque<String> stack = new LinkedList<>();
		int value = 12500;
		do{
			stack.push(Integer.toHexString(value%16));
		}while((value = value/16) !=0);
		StringBuilder hexValue = new StringBuilder();
		while(stack.peek()!=null) {
			hexValue.append(stack.pop());
		}
		System.out.println(hexValue.toString());
	}
	
	@Test
	public void test21() {
		ReverseList<String> rlist = new ReverseList<>();
		rlist.add("apple");
		rlist.add("orange");
		rlist.add("banana");
		for (String string : rlist) {
			System.out.println(string);
		}
	}
	
	@Test
	public void test22() {
		List<Object> emptyList = Collections.emptyList();
		List<Object> of = List.of();
		System.out.println(emptyList.getClass());
		System.out.println(of.getClass());
		Map<Object, Object> emptyMap = Collections.emptyMap();
		Map<Object, Object> of2 = Map.of();
		System.out.println(emptyMap.getClass());
		System.out.println(of2.getClass());
		Set<Object> emptySet = Collections.emptySet();
		Set<Object> of3 = Set.of();
		System.out.println(emptySet.getClass());
		System.out.println(of3.getClass());
		List<String> of4 = List.of("apple");
		System.out.println(of4.get(0));
	}
	
	@Test
	public void test23() {
		List<String> list = new ArrayList<>();
		list.add("apple");
		list.add("orange");
		list.add("banana");
		Collections.sort(list);
		for (String string : list) {
			System.out.println(string);
		}
		Collections.shuffle(list);
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	@Test
	public void test24() {
		List<String> list = new ArrayList<>();
		list.add("apple");
		list.add("orange");
		List<String> unmodifiableList = Collections.unmodifiableList(list);
//		unmodifiableList.add("1");//UnsupportedOperationException
		list.add("banana");
		for (String string : unmodifiableList) {
			System.out.println(string);
		}
		list = null;
	}
	
	@Test
	public void test25() {
		List<String> list = new ArrayList<>();
		list.add("apple");
		list.add("orange");
		List<String> synchronizedList = Collections.synchronizedList(list);
		System.out.println(synchronizedList.getClass());
	}
	
	
	
}

class ReverseList<T> implements Iterable<T>{
	private List<T> list = new ArrayList<>();
	public void add(T e) {
		list.add(e);
	}
	@Override
	public Iterator<T> iterator() {
		return new ReverseIterator(list.size()) ;
	}
	class ReverseIterator implements Iterator<T>{
		int index;
		public ReverseIterator(int index) {
			this.index = index;
		}
		@Override
		public boolean hasNext() {
			return index>0;
		}
		@Override
		public T next() {
			index--;
			return list.get(index);
		}
	}
}

