package multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

}

class Counter6{
	private final Lock lock = new ReentrantLock();
	private int count = 0;
	public void add(int n) {
		lock.lock();
		try {
			count += n;
		} finally {
			lock.unlock();
		}
	}
	
	public void dec(int n) {
		try {
			if(lock.tryLock(1, TimeUnit.SECONDS)) {
				try {
					count -= n;
				} finally {
					lock.unlock();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
}