package com.way361.interview;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//���ܸĶ���Test��	
public class Test3 extends Thread {

	private TestDo testDo;
	private String key;
	private String value;

	public Test3(String key, String key2, String value) {
		this.testDo = TestDo.getInstance();
		/*
		 * ����"1"��"1"��ͬһ�������������д������Ҫ��"1"+""�ķ�ʽ�����µĶ���
		 * ��ʵ������û�иı䣬��Ȼ��ȣ�����Ϊ"1"����������ȴ������ͬһ����Ч��
		 */
		this.key = key + key2;
		/*
		 * a = "1"+""; b = "1"+""
		 */
		this.value = value;
	}

	public static void main(String[] args) throws InterruptedException {
		Test3 a = new Test3("1", "", "1");
		Test3 b = new Test3("1", "", "2");
		Test3 c = new Test3("3", "", "3");
		Test3 d = new Test3("4", "", "4");
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

class TestDo {

	private TestDo() {
	}

	private static TestDo _instance = new TestDo();

	public static TestDo getInstance() {
		return _instance;
	}

	// private ArrayList keys = new ArrayList();
	private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();

	public void doSome(Object key, String value) {
		Object o = key;
		if (!keys.contains(o)) {
			keys.add(o);
		} else {
			for (Iterator iter = keys.iterator(); iter.hasNext();) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Object oo = iter.next();
				if (oo.equals(o)) {
					o = oo;
					break;
				}
			}
		}
		synchronized (o)
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
