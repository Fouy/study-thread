package com.way361.heima2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程数据交换测试
 * 两个线程需要交换数据，当一个线程到达时，需要等待另一个线程。
 * 当另一个线程到达后，会进行数据交换。
 * @author huge
 *
 */
public class ExchangerTest {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger<String> exchanger = new Exchanger<String>();//线程交换工具，泛型为所要交换数据的类型
		
		service.execute(new Runnable(){
			public void run() {
				try {				
					String data1 = "zxx";
					System.out.println("线程" + Thread.currentThread().getName() +  "正在把数据" + data1 +"换出去");
					Thread.sleep((long)(Math.random()*10000));
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() +  "换回的数据为" + data2);
				}catch(Exception e){
				}
			}
		});
		
		service.execute(new Runnable(){
			public void run() {
				try {				
					String data1 = "lhm";
					System.out.println("线程" + Thread.currentThread().getName() +  "正在把数据" + data1 +"换出去");
					Thread.sleep((long)(Math.random()*10000));					
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() +  "换回的数据为" + data2);
				}catch(Exception e){
				}	
			}
		});
		
		service.shutdown();
	}
}
