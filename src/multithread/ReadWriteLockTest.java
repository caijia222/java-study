package multithread;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

}

class Counter7{
	private final Lock lock = new ReentrantLock();
	private int[] counts = new int[10];
	
	public void inc(int index) {
		lock.lock();
		try {
			counts[index] += 1;
		} finally {
			lock.unlock();
		}
	}
	
	public int[] get() {
		lock.lock();
		try {
			return Arrays.copyOf(counts, counts.length);
		} finally {
			lock.unlock();
		}
	}
}

class Counter8{
	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final Lock rLock = rwLock.readLock();
	private final Lock wLock = rwLock.writeLock();
	
	private int[] counts = new int[10];
	
	public void inc(int index) {
		wLock.lock(); // 写锁
		try {
			counts[index] += 1;
		} finally {
			wLock.unlock();
		}
	}
	
	public int[] get() {
		rLock.lock(); // 读锁
		try {
			return Arrays.copyOf(counts, counts.length);
		} finally {
			rLock.unlock();
		}
	}
}
