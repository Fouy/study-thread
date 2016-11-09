package com.way361.interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * 开十个线程，以生产者消费者的方式来处理打印信息。
 * 使用同步队列，SynchronousQueue 在队列中，只有当
 * 有线程来取数据时，才可以放入数据。
 * @author huge
 *
 */
public class Test2 {

	public static void main(String[] args) {
		
		final Semaphore semaphore = new Semaphore(1);
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();//同步队列
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();//加同步锁，类似于Lock
						String input = queue.take();
						String output = TestDo2.doSome(input);
						System.out.println(Thread.currentThread().getName() + ":" + output);
						semaphore.release();//释放同步锁
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) { // 这行不能改动
			String input = i + ""; // 这行不能改动
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// 不能改动此TestDo类
class TestDo2 {
	
	public static String doSome(String input) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":" + (System.currentTimeMillis() / 1000);
		return output;
	}
}
