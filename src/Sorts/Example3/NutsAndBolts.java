package Sorts.Example3;

// https://www.geeksforgeeks.org/nuts-bolts-problem-lock-key-problem/
public class NutsAndBolts {
	private static void pairs(char[] nut, char[] bolt, int lo, int hi) {
		if (lo < hi) { 
            // Choose last character of bolts array for nuts partition. 
            int pivot = partition(nut, lo, hi, bolt[hi]); 
  
            // Now using the partition of nuts choose that for bolts 
            // partition. 
            partition(bolt, lo, hi, nut[pivot]); 
  
            // Recur for [low...pivot-1] & [pivot+1...high] for nuts and 
            // bolts array. 
            pairs(nut, bolt, lo, pivot-1); 
            pairs(nut, bolt, pivot+1, hi); 
        } 
	}

	private static int partition(char[] arr, int lo, int hi, char pivot) {
		int i = lo;
		char temp1;
		char temp2;
		
		for (int j = lo; j < hi; j++) {
			if (arr[j] < pivot) {
				temp1 = arr[i];
				arr[i] = arr[j];
				arr[j] = temp1;
				i++;
			} else if (arr[j] == pivot) {
				temp1 = arr[j];
				arr[j] = arr[hi];
				arr[hi] = temp1;
				j--;
			}
		}
		
		temp2 = arr[i];
		arr[i] = arr[hi];
		arr[hi] = temp2;
		
		return i;
	}

    // Method to print the array 
    private static void printArray(char[] arr) { 
        for (char ch : arr){ 
            System.out.print(ch + " "); 
        } 
        System.out.print("\n"); 
    } 

    public static void main(String[] args) { 
        // Nuts and bolts are represented as array of characters 
        char nuts[] = {'@', '#', '$', '%', '^', '&'}; 
        char bolts[] = {'$', '%', '&', '^', '@', '#'}; 
  
        // Method based on quick sort which matches nuts and bolts 
        pairs(nuts, bolts, 0, 5); 
  
        System.out.println("Matched nuts and bolts are : "); 
        printArray(nuts); 
        printArray(bolts); 
    } 
}
