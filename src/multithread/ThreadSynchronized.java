package multithread;

import org.junit.Test;

public class ThreadSynchronized {
	
	@Test
	public void test1() throws InterruptedException {
		var add = new AddThread();
		var dec = new DecThread();
		add.start();
		dec.start();
		add.join();
		dec.join();
		System.out.println("end");
		System.out.println("Counter.count=" + Counter.count);
	}

}

class Counter{
	public static int count = 0;
	public static final Object lock = new Object();  
}

class AddThread extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			synchronized(Counter.lock) {
				Counter.count += 1;
			}
		}
	}
}

class DecThread extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			synchronized(Counter.lock) {
				Counter.count -= 1;
			}
		}
	}
}