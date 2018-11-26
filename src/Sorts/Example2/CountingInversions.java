package Sorts.Example2;

/*
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i < j but a[i] > a[j]. 
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 */
public class CountingInversions {

	public static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
		long inv = 0;
		
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		int i = lo;
		int j = mid + 1;
		
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[i] > aux[j]) {
				a[k] = aux[j++];
				inv += (mid - i + 1);
			} else 
				a[k] = aux[i++];
				
		}
		return inv;
	}
	
	private static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
        long inv = 0;
        
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        
        inv += count(a, b, aux, lo, mid);  
        inv += count(a, b, aux, mid+1, hi);
        
        inv += merge(b, aux, lo, mid, hi);

        return inv;
    }
	
    public static long count(int[] a) {
        int[] b   = new int[a.length];
        int[] aux = new int[a.length];
        
        for (int i = 0; i < a.length; i++)
            b[i] = a[i];
        
        long inv = count(a, b, aux, 0, a.length - 1);
        
        return inv;
    }


	
	public static void main(String[] args) {
		int[] a = {5,4,3,2,1};
		int[] b = new int[a.length];

		for (int i = 0; i < a.length; i++)
			b[i] = a[i];
		
		System.out.println(CountingInversions.count(a));
		System.out.println(CountingInversions.count(b));
	}
}
