package core;

import org.junit.Test;

public class RecordTest {

	@Test
	public void test1() {
		Point1 p = new Point1(10, 10);
		System.out.println(p.x());
		System.out.println(p.y());
		System.out.println(p);
	}
	
	@Test
	public void test2() {
		Point2 p = new Point2(-1, 10);
		System.out.println(p.x());
		System.out.println(p.y());
		System.out.println(p);
	}
	
	@Test
	public void test3() {
		var x = Point2.of();
		var y = Point2.of(3,4);
		System.out.println(x);
		System.out.println(y);
	}

}

record Point1(int x, int y) {}

record Point2(int x, int y) {
	
	public Point2{
		if(x<0 || y<0) {
			throw new IllegalArgumentException();
		}
	}
	
	public static Point2 of() {
		return new Point2(0,0);
	}
	
	public static Point2 of(int x, int y) {
		return new Point2(x,y);
	}
	
}

//final class Point extends Record {
//    private final int x;
//    private final int y;
//
//    public Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public int x() {
//        return this.x;
//    }
//
//    public int y() {
//        return this.y;
//    }
//
//    public String toString() {
//        return String.format("Point[x=%s, y=%s]", x, y);
//    }
//
//    public boolean equals(Object o) {
//        ...
//    }
//    public int hashCode() {
//        ...
//    }
//}