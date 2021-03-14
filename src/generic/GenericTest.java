package generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class GenericTest {

	@Test
	void test() {
        var ps = new Person[] {
                new Person("Bob", 61),
                new Person("Alice", 88),
                new Person("Lily", 75),
            };
            Arrays.sort(ps);
            System.out.println(Arrays.toString(ps));
	}
	
	@Test
	void test2() {
        Pair<String> p1 = new Pair<>("Hello", "world");
        Pair<Integer> p2 = new Pair<>(123, 456);
        Class<?> c1 = p1.getClass();
        Class<?> c2 = p2.getClass();
        System.out.println(c1==c2); // true
        System.out.println(c1==Pair.class); // true
	}
	
	@Test
	void test3(){
		Class<IntPair> clz = IntPair.class;
		Type genericSuperclass = clz.getGenericSuperclass();
		if(genericSuperclass instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) genericSuperclass;
			Type[] actualTypeArguments = type.getActualTypeArguments();
			Type firstType = actualTypeArguments[0];
			Class<?> typeClass =  (Class<?>) firstType;
			System.out.println(typeClass);
		}
	}
	
	@Test
	public void test4() {
		Pair<Integer> pair = new Pair<Integer>(11, 12);
		int add = PairHelper.add(pair);
		System.out.println(add);
	}
	
	@Test
	public void test5() throws Exception {
		Class<Integer> clz = Integer.class;
		Constructor<Integer> cons = clz.getConstructor(int.class);
		Integer integer = cons.newInstance(123);
		System.out.println(integer);
	}
	@Test
	void test6(){
        String[] arr = PairHelper.asArray("one", "two", "three");
        System.out.println(Arrays.toString(arr));
        // ClassCastException:
        String[] firstTwo = PairHelper.pickTwo("one", "two", "three");
        System.out.println(Arrays.toString(firstTwo));
	}


}
