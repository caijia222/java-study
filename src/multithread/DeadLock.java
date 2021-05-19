package multithread;

public class DeadLock {

	/**
	 * @param args
	 * 死锁案例，想要避免死锁，就应该把获取锁的顺序改成一致
	 */
	public static void main(String[] args) {
		var c = new Counter5();
		new Thread(()->c.add(10)).start();
		new Thread(()->c.dec(10)).start();
	}
}

class Counter4{
	private int count = 0;
	
	public synchronized void add(int n) {
		if(n < 0) {
			dec(-n); // 可重入锁
		}else {
			count += n;
		}
	}
	
	public synchronized void dec(int n) {
		count += n;
	}
	
	public int getCount() {
		return count;
	}
}

class Counter5{
	private Object lockA = new Object();
	private Object lockB = new Object();
	
	public void add(int n){
		synchronized(lockA) {
			System.out.println("add获得lockA");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lockB) {
				System.out.println("add获得lockB");
			}
		}
	}
	
	public void dec(int n){
		synchronized(lockB) { // 改成lockA
			System.out.println("dec获得lockB");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lockA) { // 改成lockB
				System.out.println("dec获得lockA");
			}
		}
	}
}