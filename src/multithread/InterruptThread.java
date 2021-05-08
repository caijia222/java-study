package multithread;

import org.junit.Test;

public class InterruptThread {
	
	@Test
	public void test1() throws InterruptedException {
		Thread t = new MyThread2();
		t.start();
		Thread.sleep(1);
		t.interrupt();
		t.join();
		System.out.println("end");
	}
	
	@Test
	public void test2() throws InterruptedException {
		Thread t = new MyThread3();
		t.start();
		Thread.sleep(1000);
		t.interrupt();
		t.join();
		System.out.println("end");
	}
	
	@Test
	public void test3() throws InterruptedException {
		HelloThread2 t = new HelloThread2();
		t.start();
		Thread.sleep(1);
		t.running = false;
	}

}

class MyThread2 extends Thread{
	@Override
	public void run() {
		int n = 0;
		while(!interrupted()) {
			n++;
			System.out.println(n + " hello!");
		}
	}
}

class MyThread3 extends Thread{
	@Override
	public void run() {
		Thread t = new HelloThread();
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			System.out.println("MyThread3 interrupted!");
		}
		t.interrupt();
	}
}

class HelloThread extends Thread{
	@Override
	public void run() {
		int n = 0;
		while(!isInterrupted()) {
			n++;
			System.out.println(n + " hello!");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("HelloThread interrupted!");
				break;
			}
		}
	}
}

class HelloThread2 extends Thread{
	public volatile boolean running = true;
	@Override
	public void run() {
		int n = 0;
		while(running){
			n++;
			System.out.println(n + " hello!");
		}
		System.out.println("end!");
	}
}