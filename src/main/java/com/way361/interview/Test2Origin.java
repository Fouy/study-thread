package com.way361.interview;

public class Test2Origin {

	public static void main(String[] args) {

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) { // ���в��ܸĶ�
			String input = i + ""; // ���в��ܸĶ�
			String output = TestDo1.doSome(input);
			System.out.println(Thread.currentThread().getName() + ":" + output);
		}
	}
}

// ���ܸĶ���TestDo��
class TestDo1 {
	
	public static String doSome(String input) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":" + (System.currentTimeMillis() / 1000);
		return output;
	}
}