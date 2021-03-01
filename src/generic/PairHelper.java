package generic;

import java.lang.reflect.Array;

public class PairHelper {
	
	public static int add(Pair<? extends Number> pair) {
		Number first = pair.getFirst();
		Number last = pair.getLast();
		return first.intValue() + last.intValue();
	}
	
	public static void setSame(Pair<? super Integer> pair, Integer in) {
		pair.setFirst(in);
		pair.setLast(in);
	}
	
	public static boolean isNull(Pair<?> pair) {
		return pair.getFirst() == null || pair.getLast() == null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Pair<T>[] create(Class<T> clz, int length){
		return (Pair<T>[]) Array.newInstance(clz, length);
	}
	
	@SafeVarargs
	public static <T> Pair<T>[] asArray(Pair<T>... objs){
		return objs;
	}
	
	/**
	 * 会抛出ClassCastException
	 */
	public static <K> K[] pickTwo(K k1, K k2, K k3) {
        return asArray(k1, k2);
    }

	@SafeVarargs
	public static <T> T[] asArray(T... objs) {
        return objs;
    }

}
