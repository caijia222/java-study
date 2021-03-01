package generic;

import java.util.function.Supplier;

/**
 * 泛型类
 * @author 93652
 *
 */
public class Pair<T> {
	
    private T first;
    private T last;
    public Pair() {
	}
    
    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }
    
    public Pair(Class<T> clz) throws Exception {
    	this.first = clz.getDeclaredConstructor().newInstance();
    	this.last = clz.getDeclaredConstructor().newInstance();
	}
    
    public Pair(Supplier<T> s) {
    	this.first = s.get();
    	this.last = s.get();
    }
    
    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }
    public void setFirst(T first) {
        this.first = first;
    }
    public void setLast(T last) {
        this.last = last;
    }   
    public static <K> Pair<K> create(K first, K last) {
        return new Pair<K>(first, last);
    }
    
    @Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	return super.equals(obj);
    }
 
//    public boolean equals(T t){
//    	return this == t;
//    }
    
    public boolean same(T t){
    	return this == t;
    }

}

class IntPair extends Pair<Integer> {

}

