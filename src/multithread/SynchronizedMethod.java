package multithread;

import org.junit.Test;

public class SynchronizedMethod {

	@Test
	public void test1() throws InterruptedException {
		var c1 = new Counter2();
		var c2 = new Counter2();
		new Thread(()-> c1.add()).start();
		new Thread(()-> c1.dec()).start();
		new Thread(()-> c2.add()).start();
		new Thread(()-> c2.dec()).start();
		Thread.sleep(100);
		System.out.println(c1.get());
		System.out.println(c2.get());
	}
}

class Counter2{
	private int count = 0;
	
	public void add() {
		synchronized (this) {
			count += 1;
		}
	}
	
	public void dec() {
		synchronized (this) {
			count -= 1;
		}
	}
	
	public int get() {
		return count;
	}
}