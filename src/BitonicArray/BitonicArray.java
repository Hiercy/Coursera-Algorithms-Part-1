package BitonicArray;

public class BitonicArray {

	private void swap(int[] a, int i, int j, int dir) {
		if ((a[i] > a[j] && dir == 1) || (a[i] < a[j] && dir == 0)) { 
			// Swapping elements 
			int temp = a[i]; 
			a[i] = a[j]; 
			a[j] = temp; 
		} 
	}

	private void bitonicMerge(int[] a, int low, int count, int dir) {
		if (count > 1) { 
			int k = count / 2; 
			for (int i = low; i < low + k; i++) 
				swap(a, i, i + k, dir); 
			bitonicMerge(a, low, k, dir); 
			bitonicMerge(a, low + k, k, dir); 
		} 
	}

	private void bitonicSort(int[] a, int low, int count, int dir) {
		if (count > 1) 
		{ 
			int k = count / 2; 

			// sort in ascending order since dir here is 1 
			bitonicSort(a, low, k, 1); 

			// sort in descending order since dir here is 0 
			bitonicSort(a,low+k, k, 0); 

			// Will merge wole sequence in ascending order 
			// since dir=1. 
			bitonicMerge(a, low, count, dir); 
		} 
	}

	public void sort(int[] a, int n, int up) {
		bitonicSort(a, 0, n, up);
	}

	public int binarySearch(int[] arr, int left, int right) {
		if (left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1])
				return mid;

			if (arr[mid] < arr[mid + 1])
				return binarySearch(arr, mid + 1, right);
			else
				return binarySearch(arr, left, mid - 1);
		}
		return -1;
	}

	public static void main(String[] args) {
		int a[] = {1, 2, 3, 4, 5, 15, 10, 9, 8, 7, 6}; 
//		//	        int up = 1; 
		BitonicArray ob = new BitonicArray(); 
//		ob.sort(a, a.length, 15); 
//		System.out.println("\nSorted array"); 
//
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(a[i] + " ");
//		}

		System.out.println();

		int index = ob.binarySearch(a, 20, a.length-1);
		System.out.println(a[index]);
	}
}
