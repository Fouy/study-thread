package com.way361.heima2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �̼߳���������
 * ģ��������ܣ�cdOrder Ϊ��ʼ����������������Ϊ0ʱ����ʼ������
 * cdAnswer Ϊ��������������������Ϊ0ʱ����ʾ����ѡ���Ѿ����������
 * @author huge
 *
 */
public class CountdownLatchTest {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);//�����ڼ�����
		final CountDownLatch cdAnswer = new CountDownLatch(3);//�ȴ����������
		
		//�����������̣߳�ģ�����ѡ��
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						System.out.println("�߳�" + Thread.currentThread().getName() + "��׼����������");
						cdOrder.await();
						System.out.println("�߳�" + Thread.currentThread().getName() + "�ѽ�������");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "��Ӧ�������");
						cdAnswer.countDown();//��������һ
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		
		//���߳���Ϊ���У�������ʼ�ͽ�������
		try {
			Thread.sleep((long) (Math.random() * 10000));

			System.out.println("�߳�" + Thread.currentThread().getName() + "������������");
			cdOrder.countDown();
			System.out.println("�߳�" + Thread.currentThread().getName() + "�ѷ���������ڵȴ����");
			cdAnswer.await();//�̵߳ȴ����ȵ��̼߳�����Ϊ��ʱ����������ִ��
			System.out.println("�߳�" + Thread.currentThread().getName() + "���յ�������Ӧ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
