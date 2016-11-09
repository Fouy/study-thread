package com.way361.heima2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用两个空间为1的阻塞队列，实现线程同步通信
 * 子线程运行10次，跟着主线程运行100次。以此规律，往复交替50次。
 * @author huge
 *
 */
public class BlockingQueueCommunication {

	public static void main(String[] args) {

		final Business business = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	static class Business {

		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

		{// 匿名构造方法，在所有构造方法前面执行
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 子线程执行的方法
		 * @param i
		 */
		public void sub(int i) {
			try {
				queue1.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (int j = 1; j <= 10; j++) {
				System.out.println("sub thread sequece of " + j + ",loop of " + i);
			}
			
			try {
				queue2.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 主线程执行的方法
		 * @param i
		 */
		public void main(int i) {
			
			try {
				queue2.put(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			for (int j = 1; j <= 100; j++) {
				System.out.println("main thread sequece of " + j + ",loop of " + i);
			}
			
			try {
				queue1.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
