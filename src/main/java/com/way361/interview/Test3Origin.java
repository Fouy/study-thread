package com.way361.interview;

//���ܸĶ���Test��	
public class Test3Origin extends Thread {

	private TestDo3 testDo;
	private String key;
	private String value;

	public Test3Origin(String key, String key2, String value) {
		this.testDo = TestDo3.getInstance();
		/*
		 * ����"1"��"1"��ͬһ�������������д������Ҫ��"1"+""�ķ�ʽ�����µĶ���
		 * ��ʵ������û�иı䣬��Ȼ��ȣ�����Ϊ"1"����������ȴ������ͬһ����Ч��
		 */
		this.key = key + key2;
		this.value = value;
	}

	public static void main(String[] args) throws InterruptedException {
		Test3Origin a = new Test3Origin("1", "", "1");
		Test3Origin b = new Test3Origin("1", "", "2");
		Test3Origin c = new Test3Origin("3", "", "3");
		Test3Origin d = new Test3Origin("4", "", "4");
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		a.start();
		b.start();
		c.start();
		d.start();

	}

	public void run() {
		testDo.doSome(key, value);
	}
}

class TestDo3 {
	
	private static TestDo3 _instance = new TestDo3();

	private TestDo3() {
	}

	public static TestDo3 getInstance() {
		return _instance;
	}

	public void doSome(Object key, String value) {

		// �Դ������ڵ�����Ҫ�ֲ�ͬ���Ĵ��룬���ܸĶ�!
		{
			try {
				Thread.sleep(1000);
				System.out.println(key + ":" + value + ":" + (System.currentTimeMillis() / 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}