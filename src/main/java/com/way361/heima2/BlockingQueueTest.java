package com.way361.heima2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列测试
 * @author huge
 *
 */
public class BlockingQueueTest {
	
	public static void main(String[] args) {
		
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);//阻塞队列空间为3
		
		//启动两个线程，放数据
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep((long)(Math.random()*1000));
							System.out.println(Thread.currentThread().getName() + "准备放数据!");							
							queue.put(1);//当没有空间时，线程会阻塞，直到有空间存放为止
							System.out.println(Thread.currentThread().getName() + "已经放了数据，" + "队列目前有" + queue.size() + "个数据");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
		
		//开启一个线程，用来拿出数据
		new Thread(){
			public void run(){
				while(true){
					try {
						//将此处的睡眠时间分别改为100和1000，观察运行结果
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "准备取数据!");
						queue.take();//当没有数据时，线程阻塞，直到有数据为止
						System.out.println(Thread.currentThread().getName() + "已经取走数据，" + "队列目前有" + queue.size() + "个数据");					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();			
	}
}
