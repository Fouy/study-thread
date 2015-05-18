package com.way361.heima2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �߳����ݽ�������
 * �����߳���Ҫ�������ݣ���һ���̵߳���ʱ����Ҫ�ȴ���һ���̡߳�
 * ����һ���̵߳���󣬻�������ݽ�����
 * @author huge
 *
 */
public class ExchangerTest {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger<String> exchanger = new Exchanger<String>();//�߳̽������ߣ�����Ϊ��Ҫ�������ݵ�����
		
		service.execute(new Runnable(){
			public void run() {
				try {				
					String data1 = "zxx";
					System.out.println("�߳�" + Thread.currentThread().getName() +  "���ڰ�����" + data1 +"����ȥ");
					Thread.sleep((long)(Math.random()*10000));
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() +  "���ص�����Ϊ" + data2);
				}catch(Exception e){
				}
			}
		});
		
		service.execute(new Runnable(){
			public void run() {
				try {				
					String data1 = "lhm";
					System.out.println("�߳�" + Thread.currentThread().getName() +  "���ڰ�����" + data1 +"����ȥ");
					Thread.sleep((long)(Math.random()*10000));					
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() +  "���ص�����Ϊ" + data2);
				}catch(Exception e){
				}	
			}
		});
		
		service.shutdown();
	}
}
