package com.way361.sort;

/**
 * ������
 * 
 * @author huge
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		int[] array = { 3, 4, 5, 753, 2, 21, 5, 7, 899, 1 };
		System.out.println("����ǰ��");
		print(array);
		heapSort(array);
		System.out.println("�����");
		print(array);
	}

	public static void heapSort(int[] array) {
		int n = array.length;
		for (int j = n / 2 - 1; j >= 0; j--) {
			sift(array, j, n - 1);
		}
		for (int j = n - 1; j > 0; j--) {//����Сֵ���������Ȼ����������С��
			int temp = array[0];
			array[0] = array[j];
			array[j] = temp;
			sift(array, 0, j - 1);
		}
	}

	/**
	 * ��begin��endΪ���ޣ�������С��
	 * @param array ����
	 * @param begin
	 * @param end
	 */
	private static void sift(int[] array, int begin, int end) {
		int i = begin, j = 2 * i + 1;
		int temp = array[i];
		while (j <= end) {
			if (j < end && array[j] > array[j + 1])
				j++;
			if (temp > array[j]) {
				array[i] = array[j];
				i = j;
				j = 2 * i + 1;
			} else
				break;
		}
		array[i] = temp;
	}

	private static void print(int[] array) {
		for (int temp : array) {
			System.out.print(temp + ", ");
		}
		System.out.println();
	}

}
