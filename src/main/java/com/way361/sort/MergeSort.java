package com.way361.sort;

/**
 * 归并排序
 * 
 * @author huge
 *
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] array = { 3, 4, 5, 753, 2, 21, 5, 7, 899, 1 };
		System.out.println("排序前：");
		print(array);
		mergeSort(array);
		System.out.println("排序后：");
		print(array);
	}

	/**
	 * 归并排序
	 * 
	 * @param X
	 */
	public static void mergeSort(int[] X) {
		int[] Y = new int[X.length];
		int n = 1;
		while (n < X.length) {
			mergepass(X, Y, n);
			n *= 2;
			if (n < X.length) {
				mergepass(Y, X, n);
				n *= 2;
			}
		}
	}

	/**
	 * 一趟归并
	 * 
	 * @param X
	 * @param Y
	 * @param n
	 */
	private static void mergepass(int[] X, int[] Y, int n) {
		int i = 0;
		while (i < X.length - 2 * n + 1) {
			merge(X, Y, i, i + n, n);
			i += 2 * n;
		}
		if (i + n < X.length)
			merge(X, Y, i, i + n, n);
		else
			for (int j = i; j < X.length; j++) {
				Y[j] = X[j];
			}
	}

	/**
	 * 一次归并
	 * 
	 * @param X
	 * @param Y
	 * @param m
	 * @param r
	 * @param n
	 */
	private static void merge(int[] X, int[] Y, int m, int r, int n) {
		int i = m, j = r, k = m;
		while (i < r && j < r + n && j < X.length) {
			if (X[i] < X[j])
				Y[k++] = X[i++];
			else
				Y[k++] = X[j++];
		}
		while (i < r) {
			Y[k++] = X[i++];
		}
		while (j < r + n && j < X.length) {
			Y[k++] = X[j++];
		}
	}

	private static void print(int[] array) {
		for (int temp : array) {
			System.out.print(temp + ", ");
		}
		System.out.println();
	}
}
