package com.way361.heima2;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �߳������ϵ����
 * һ�������̣߳��������ϵ㡣���߳�1���Ｏ�ϵ�1ʱ�ȴ�������̵߳���������߳�
 * ������ʱ���ڽ�������ĳ���
 * @author huge
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();//�����̳߳�
		final  CyclicBarrier cb = new CyclicBarrier(3);
		
		for(int i=0;i<3;i++){//���������߳�
			Runnable runnable = new Runnable(){
				public void run() {
					try {
						// ���ϵ�1
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�1����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���Ѿ����"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						cb.await();

						// ���ϵ�2
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�2����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���Ѿ����"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						cb.await();

						// ���ϵ�3
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�3����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���Ѿ����"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
