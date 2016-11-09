package com.way361.heima2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程计数器测试
 * 模拟百米赛跑，cdOrder 为开始计数器。当计数器为0时，开始比赛。
 * cdAnswer 为结束计数器，当计数器为0时，表示三个选手已经跑完比赛。
 * @author huge
 *
 */
public class CountdownLatchTest {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);//吹口哨计数器
		final CountDownLatch cdAnswer = new CountDownLatch(3);//等待结果计数器
		
		//开启三个子线程，模拟参赛选手
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
						cdOrder.await();
						System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
						cdAnswer.countDown();//计数器减一
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		
		//主线程作为裁判，发出开始和结束口令
		try {
			Thread.sleep((long) (Math.random() * 10000));

			System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
			cdOrder.countDown();
			System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");
			cdAnswer.await();//线程等待，等到线程计数器为零时，继续向下执行
			System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
