package multithread;

import org.junit.Test;

/**
 * @author 93652
 *	new 新创建的线程，尚未执行<br>
 *	runnable 运行中的线程，正在执行run()方法的Java代码<br>
 *	blocked 运行中的线程，因为某些操作被阻塞而挂起<br>
 *	waiting 运行中的线程，因为某些操作在等待中<br>
 *	timed waiting 运行中的线程，因为执行sleep()方法正在计时等待<br>
 *	terminated 线程已终止，因为run()方法执行完毕<br>
 */
public class ThreadStatus {
	
	@Test
	public void test1() throws InterruptedException {
		Thread t = new Thread(()->{
			System.out.println("hello");
		});
		System.out.println("start");
		t.start();
		t.join();
		System.out.println("end");
	}
	
	@Test
	public void test2() {
		
	}

}
