package multithread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class WaitAndNotify {
	
	public static void main(String[] args) throws InterruptedException {
		var q = new TaskQueue();
		var ts = new ArrayList<Thread>();
		for (int i = 0; i < 5; i++) {
			var t = new Thread() {
				@Override
				public void run() {
					while(true) {
						try {
							String s = q.getTask();
							System.out.println("excute task: " + s);
						} catch (InterruptedException e) {
							System.out.println("q.getTask()中断异常了");
							return;
						}
					}
				}
			};
			t.start();
			ts.add(t);
		}
		
		var add = new Thread(()->{
			for (int i = 0; i < 10; i++) {
				String s = "t-" + Math.random();
				System.out.println("add task: " + s);
				q.addTask(s);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(s + " Thread.sleep(100)中断异常了");
				}
			}
		});
		add.start();
		add.join();
		Thread.sleep(100);
		for (Thread thread : ts) {
			thread.interrupt();
		}
	}

}

class TaskQueue{
	Queue<String> queue = new LinkedList<>();
	
	public synchronized void addTask(String s) {
		this.queue.add(s);
		this.notifyAll();
	}
	
	public synchronized String getTask() throws InterruptedException {
		while(queue.isEmpty()) {
			this.wait();
		}
		return this.queue.remove();
	}
}