package com.way361.interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * ��ʮ���̣߳��������������ߵķ�ʽ�������ӡ��Ϣ��
 * ʹ��ͬ�����У�SynchronousQueue �ڶ����У�ֻ�е�
 * ���߳���ȡ����ʱ���ſ��Է������ݡ�
 * @author huge
 *
 */
public class Test2 {

	public static void main(String[] args) {
		
		final Semaphore semaphore = new Semaphore(1);
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();//ͬ������
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();//��ͬ������������Lock
						String input = queue.take();
						String output = TestDo2.doSome(input);
						System.out.println(Thread.currentThread().getName() + ":" + output);
						semaphore.release();//�ͷ�ͬ����
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) { // ���в��ܸĶ�
			String input = i + ""; // ���в��ܸĶ�
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// ���ܸĶ���TestDo��
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
