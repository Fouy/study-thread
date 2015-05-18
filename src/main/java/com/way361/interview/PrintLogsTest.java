package com.way361.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ʹ�ö��̴߳�ӡ��־
 * ʹ������������ʵ�֣�����֮���ͨ�š�
 * @author huge
 *
 */
public class PrintLogsTest {

	public static void main(String[] args) {
		
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							String log = queue.take();
							parseLog(log);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		/*
		 * ģ�⴦��16����־������Ĵ��������16����־���󣬵�ǰ������Ҫ����16����ܴ�ӡ����Щ��־��
		 * �޸ĳ�����룬���ĸ��߳�����16��������4���Ӵ��ꡣ
		 */
		for (int i = 0; i < 16; i++) { // ���д��벻�ܸĶ�
			final String log = "" + (i + 1);// ���д��벻�ܸĶ�
			{
				try {
					queue.put(log);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Test.parseLog(log);
			}
		}
	}

	// parseLog�����ڲ��Ĵ��벻�ܸĶ�
	public static void parseLog(String log) {
		System.out.println(log + ":" + (System.currentTimeMillis() / 1000));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}