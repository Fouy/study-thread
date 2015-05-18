package com.way361.heima2;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程类屏障点测试
 * 一共三个线程，三个屏障点。当线程1到达集合点1时等待后面的线程到达，当三个线程
 * 都到达时，在进行下面的程序。
 * @author huge
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();//创建线程池
		final  CyclicBarrier cb = new CyclicBarrier(3);
		
		for(int i=0;i<3;i++){//启动三个线程
			Runnable runnable = new Runnable(){
				public void run() {
					try {
						// 屏障点1
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点1，当前已有"
								+ (cb.getNumberWaiting() + 1)
								+ "个已经到达，"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊"
										: "正在等候"));
						cb.await();

						// 屏障点2
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点2，当前已有"
								+ (cb.getNumberWaiting() + 1)
								+ "个已经到达，"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊"
										: "正在等候"));
						cb.await();

						// 屏障点3
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点3，当前已有"
								+ (cb.getNumberWaiting() + 1)
								+ "个已经到达，"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊"
										: "正在等候"));
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
