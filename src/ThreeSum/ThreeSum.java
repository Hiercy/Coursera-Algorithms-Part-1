package ThreeSum;

import java.util.Arrays;
import java.util.Scanner;

import InOut.In;
import InOut.StdOut;
import edu.princeton.cs.algs4.BinarySearch;

public class ThreeSum {
	// It's realization not so good(n^3)
	/**
	 * Returns the number of triples (i, j, k) with {@code i < j < k}
	 * such that {@code a[i] + a[j] + a[k] == 0}.
	 *
	 * @param  a the array of integers
	 * @return the number of triples (i, j, k) with {@code i < j < k}
	 *         such that {@code a[i] + a[j] + a[k] == 0}
	 */
	public static int count(int[] a) {
		// ����� 3� ����� � ������� ������
		int N = a.length;
		int count = 0;

		// ��� ����� ������ ��� 3 ���� �����(��� 2� ��� - 2 �����)
		for (int i = 0; i < N; i++) 
			for (int j = i + 1; j < N; j++) 
				for (int k = j + 1; k < N; k++)
					if (a[i] + a[j] + a[k] == 0)
						count++;
		return count;

	}

	// ��������� ������
	// �����, ��������� �������� ����� 
	public static int count2(int[] a) {
		Arrays.sort(a);
		int N = a.length;
		int count = 0;
		
		// ���� ����� �[i] � �[j] �������� ������ ���� ���� � ������� ������
		// ����� � ������ �����, ����� �������� -(�[i] + �[j]) ������������ � ������� � �� ����� �[i] ��� �[j]
		for (int i = 0; i < N; i++) 
			for (int j = i+1; j < N; j++)
				if (BinarySearch.rank(-a[i] + a[j], a) > j)
					count++;
		return count;
	}

	public static void main(String[] args) {
//		int[] a = In.readInts(args[0]);
//		StdOut.println(count2(a));
		
		int[] b = new int[10];
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i <= 8; i++) 
			b[i] = sc.nextInt();
		
		System.out.println(count2(b));
	}
}
