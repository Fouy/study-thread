package com.way361.heima2;

/**
 * 多线程共享数据
 * @author huge
 *
 */
public class MultiThreadShareData {

	private static ShareData1 data1 = new ShareData1();
	
	public static void main(String[] args) {
		//方式一
		ShareData1 data2 = new ShareData1();
		new Thread(new MyRunnable1(data2)).start();
		new Thread(new MyRunnable2(data2)).start();
		
		//方式二
		final ShareData1 data1 = new ShareData1();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.decrement();
				
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.increment();
				
			}
		}).start();

	}

}
	
	//自增线程
	class MyRunnable1 implements Runnable{
		private ShareData1 data1;
		public MyRunnable1(ShareData1 data1){
			this.data1 = data1;
		}
		public void run() {
			data1.decrement();
			
		}
	}
	//自减线程
	class MyRunnable2 implements Runnable{
		private ShareData1 data1;
		public MyRunnable2(ShareData1 data1){
			this.data1 = data1;
		}
		public void run() {
			data1.increment();
		}
	}

	/**
	 * 共享的数据
	 * @author huge
	 *
	 */
	class ShareData1 /*implements Runnable*/{
/*		private int count = 100;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				count--;
			}
		}*/
		
		
		private int j = 0;
		public synchronized void increment(){
			j++;
		}
		
		public synchronized void decrement(){
			j--;
		}
	}