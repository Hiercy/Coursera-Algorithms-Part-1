package Sorts;

public class ShellSort {
	
	public static void sort(Comparable[] a) {
		int size = a.length;
		int h = 1;
		

		// 3x+1 increment sequence
		while (h < size/3)
			h = 3*h + 1; // 1,4,13,40,121,364,1093

		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < size; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j -=h)
					exch(a, j, j-h);
			}
			assert isHsorted(a,h);
			h = h/3;
		}
		assert isSorted(a);
	}

	private static boolean isSorted(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			if (less(a[i], a[i-1]))
				return false;
		
		return true;
	}

	// us the array h-sorted?
	private static boolean isHsorted(Comparable[] a, int h) {
		for (int i = h; i < a.length; i++)
			if (less(a[i], a[i-1]))
				return false;
		return false;
	}

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void main(String[] args) {
		Comparable[] a = {7,10,5,3,8,4,2,9,6};

		ShellSort.sort(a);

		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}