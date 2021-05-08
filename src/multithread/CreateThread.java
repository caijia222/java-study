package multithread;

import org.junit.Test;

public class CreateThread {
	
	@Test
	public void test1() {
		Thread t = new Thread();
		t.start();
	}
	
	@Test
	public void test2() {
		Thread t = new MyThread();
		t.start();
	}
	
	@Test
	public void test3() {
		Thread t = new Thread(new MyRunnable());
		t.start();
	}
	
	@Test
	public void test4() {
		Thread t = new Thread(()->{
			System.out.println("lambda thread");
		});
		t.start();
	}
	
	@Test
	public void test5() {
		System.out.println("main start...");
		new Thread(()->{
			System.out.println("Thread start...");
			System.out.println("Thread end...");
			}).start();
		System.out.println("main end...");
	}
	
	@Test
	public void test6() {
		System.out.println("main start...");
		new Thread(()->{
			System.out.println("Thread start...");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread end...");
		}).start();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main end...");
	}

}

class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println("MyTherad.run()");
	}
}

class MyRunnable implements Runnable{
	@Override
	public void run() {
		System.out.println("MyRunnable.run()");
	}
}
