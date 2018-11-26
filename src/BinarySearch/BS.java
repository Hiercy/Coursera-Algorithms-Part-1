package BinarySearch;

import java.util.Arrays;

import InOut.In;
import InOut.StdIn;
import InOut.StdOut;

public class BS {

	public static int binarySearch(int key, int arr[]) {
		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < arr[mid])
				hi = mid - 1;
			else if (key > arr[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static int log(int n) {
		int position = 0;
		while (n != 1) {
			n = n >> 1;
		position++;
		}
		return position;
	}

	public static int lg(int n) {
		return log(n);
	}

	public static void histogram(int[] a, int M) {
		int count = 0;
		for (int i = 0; i < M-1; i++) {
			for (int j = i + 1; j < M; j++) {
				if (a[i] == a[j]) 
					count++;
			}
		}
		System.out.println(count);
	}
	
	public static String exR51(int n) {
		if (n <= 0) 
			return " ";
		
		return exR51(n-3) + n + exR51(n-2) + n;
	}
	
	public static int mytery(int a, int b) {
		if (b == 0) 
			return 1;
		if (b % 2 == 0)
			return mytery(a+a, b/2);
		return mytery(a+a, b/2) * a;
	}

	public static void main(String[] args) {
		System.out.println(mytery(2, 25));
		
		System.out.println(exR51(6));
		
		int[] c = {1,2,3,4,5,6,7,8,1};
		histogram(c, c.length);

		System.out.println(true & false || true & true);
		System.out.println('b');

		// =========================================== //

		String s = "";
		int N = 5;
		for(int n = N; n > 0; n/=2) {
			s = (n % 2) + s;
		}
		System.out.println(s);

		// =========================================== //
		int[][] b = new int[4][4];
		int[][] a = {
				{1,2,3,4},
				{5,6,7,8},
				{9,0,1,2},
				{3,4,5,6}
		};

		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				//				a[i][j] = i+j;
				b[j][i] = a[i][j];
				System.out.print(b[i][j]);

			}
			System.out.println();
		}

		System.out.println("LOG ");

		System.out.println(lg(6));

		//		System.out.println("B");
		//		int[][] b = {
		//				{4,5,6,7},
		//				{9,1,2,3},
		//				{5,6,7,8},
		//				{1,2,3,4}
		//			};
		//		for (int i = 0; i < b.length; i++) {
		//			for (int j = 0; j < b.length; j++) {
		//				b[j][i] = a[i][j];
		//				System.out.print(b[i][j]);
		//			}
		//			System.out.println();
		//		}


		int[] whitelist = In.readInts(args[0]);
		Arrays.sort(whitelist);
		while(!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (binarySearch(key, whitelist) < 0)
				StdOut.println(key);
		}
	}
}
