package com.way361.interview;

public class PrintLogsTestOrigin {

	public static void main(String[] args) {

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		/*
		 * ģ�⴦��16����־������Ĵ��������16����־���󣬵�ǰ������Ҫ����16����ܴ�ӡ����Щ��־��
		 * �޸ĳ�����룬���ĸ��߳�����16��������4���Ӵ��ꡣ
		 */
		for (int i = 0; i < 16; i++) { // ���д��벻�ܸĶ�
			final String log = "" + (i + 1);// ���д��벻�ܸĶ�
			{
				PrintLogsTest.parseLog(log);
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