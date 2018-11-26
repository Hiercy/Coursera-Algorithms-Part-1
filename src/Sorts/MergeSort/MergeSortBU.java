package Sorts;

public class MergeSortBU {

	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// Слияние a[lo...mid] с a[mid+1...hi]
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);

		int i = lo; // Левая честь левой половины
		int j = mid+1; // Левая часть правой половины

		// lo - начало
		for (int l = lo; l <= hi; l++) // Копирование а[lo..mid] в aux[lo..hi]
			aux[l] = a[l];

		for (int l = lo; l <= hi; l++) { // Слияние обратно в a[lo..hi]
			if (i > mid) // Если левая половина закончилась, то берем из правой
				a[l] = aux[j++];
			else if (j > hi) // Если правая половина зкончилась, то берем из левой
				a[l] = aux[i++];
			else if (less(aux[j], aux[i])) // Текущий ключ из правой половины меньше текущего ключа из левой половины(брать справа)
				a[l] = aux[j++];
			else // Текущий ключ из правой половины больше текущего ключа из левой полвина(брать слева)
				a[l] = aux[i++];
		}
	}

	// simple and non recursive version of mergesort(10% slower than recursive)
	public static void sort(Comparable[] a) {
		int size = a.length;
		Comparable[] aux = new Comparable[size];
		
		for (int sz = 1; sz < size; sz = sz+sz)
			for (int lo = 0; lo < size-sz; lo += sz+sz)
				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, size-1));
	}
	
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo+1; i <= hi; i++)
			if (less(a[i], a[i-1]))
				return false;

		return true;
	}

	// is v < w
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}


}
