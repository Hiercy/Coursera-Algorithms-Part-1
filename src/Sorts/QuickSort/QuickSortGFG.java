package Sorts.QuickSort;

public class QuickSortGFG {

	private static int partition(int[] a, int lo, int hi) {
		int pivot = a[hi]; // pivot the last element
		int i = lo-1; // the first element

		for (int j = lo; j < hi; j++) {
			// if current element less than pivot
			if (a[j] < pivot) {
				i++;
				// swap
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		int temp = a[i+1];
		a[i+1] = a[hi];
		a[hi] = temp;

		return i+1;
	}
	
	public static void sort(int[] a, int lo, int hi) {
		if (lo < hi) {
			// pi is partitioning index, arr[pi] is now at right place
			int pi = partition(a, lo, hi);
			
			sort(a, lo, pi-1);
			sort(a, pi+1, hi);
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[1000000];
		
		for (int i = 0; i < a.length; i++)
			a[i] = (int)(Math.random()*1000000);
		
//		System.out.println("===================BEFORE===================");
//		for (int i = 0; i < a.length; i++)
//			System.out.println(a[i]);
		
		QuickSortGFG.sort(a, 0, a.length-1);
		
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
