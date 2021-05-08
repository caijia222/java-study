package multithread;

import java.time.LocalTime;

import org.junit.Test;

public class DaemonThread {

	@Test
	public void test1() throws InterruptedException {
		Thread t = new TimerThread();
		t.setDaemon(true);
		t.start();
		Thread.sleep(10000);
		
	}
}

class TimerThread extends Thread{
	@Override
	public void run() {
		while(true) {
			System.out.println(LocalTime.now());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}