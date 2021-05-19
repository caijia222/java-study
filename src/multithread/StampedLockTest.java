package multithread;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
	public static void main(String[] args) {
		double d1 = 1.0;
		double d2 = 0.1;
		double d3 = d1 - d2;
		
		float f1 = 1.0f;
		float f2 = 0.1f;
		float f3 = f1 - f2;
		System.out.println(d3);
		System.out.println(f3);
		System.out.println(1.0-0.9);
	}

}

class Point{
	private StampedLock stampedLock = new StampedLock();
	
	private double x;
	private double y;
	
	public void move(double deltaX, double deltaY) {
		
	}
}