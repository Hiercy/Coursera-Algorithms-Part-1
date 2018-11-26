package ElementarySymbolTables;

// implements Comparable<ST> - dont need
// we can using equals()
public class ArrayST<Key extends Comparable<Key> , Value>{
	private final int SIZE = 10;

	private Key[] keys; // ST key
	private Value[] values; // ST value
	private int n; // number of symbols in ST

	// creating ST
	public ArrayST() {
		keys = (Key[]) new Comparable[SIZE];
		values = (Value[]) new Object[SIZE];
	}

	// number of key-value pair in the table
	public int size() {
		return n;
	}

	// is the table empty?
	public boolean isEmpty() {
		return n == 0;
	}

	// put key-value pair into the table(remove key from table if value is null)
	public void put(Key key, Value val) {
		delete(key);
		// add new key and new value at the end
		keys[n] = key;
		values[n] = val;
		n++;
	}

	// value paired with key
	public Value get(Key key) {
		for (int i = 0; i < n; i++) {
			if (keys[i].equals(key)) 
				return values[i];
		}
		return null;
	}

	// remove key(and its value) from table
	public void delete(Key key) {
		for (int i = 0; i < n; i++) {
			if (key.equals(keys[i])) {
				keys[i] = keys[n-1];
				values[i] = values[n-1];
				keys[n-1] = null;
				values[n-1] = null;
				n--;
				return;
			}
		}
	}

	// is there a value paired with key?
	public boolean contains(Key key) {
		//		for (int i = 0; i < n; i++) {
		//			if (key.equals(keys[i])) 
		//				return true;
		//		}
		//		return false;
		//		OR
		return get(key) != null;
	}

	// number of keys less than key
	public int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("rank() arg is null");
		//		int count = 0;
		//		// bad practice
		//		for (int i = 0; i < n; i++) 
		//			if (keys[i].compareTo(key) < 0)
		//				count++;

		// with binary search
		int lo = 0;
		int hi = n-1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			int cmp = key.compareTo(keys[mid]); // key сравнить с keys[mid]
			if (cmp < 0)
				hi = mid-1;
			else if (cmp > 0)
				lo = mid+1;
			else
				return mid;
		}
		return lo;
	}
	
	// returns the number of keys in this symbol table in the specified range
	public int size(Key lo, Key hi) {
		if (lo.compareTo(hi) > 0)
			return 0;
		if (contains(hi))
			return rank(hi) - rank(lo) + 1;
		else
			return rank(hi) - rank(lo);
	}

	public static void main(String[] args) {
		ArrayST<Integer, String> st = new ArrayST<>();

		//		System.out.println(st.size());

		st.put(1, "A");
		st.put(1, "A");
		st.put(1, "Y");
		st.put(2, "B");
		st.put(3, "C");
		st.put(4, "D");
		st.put(5, "E");
		st.put(6, "F");

		//		st.put("A", 1);
		//		st.put("A", 1);
		//		st.put("Y", 1);
		//		st.put("B", 2);
		//		st.put("C", 3);
		//		st.put("D", 4);
		//		st.put("E", 5);
		//		st.put("F", 6);

		System.out.println(st.get(6));
		System.out.println(st.get(1));

		System.out.println(st.contains(1));
		System.out.println(st.rank(5));
		System.out.println(st.size(2, 5));

		//		System.out.println(st.size());
		//		System.out.println(st.isEmpty());
		//		System.out.println(st.get("F"));
		//		
		//		st.delete("C");
		//		System.out.println("looking for f");
		//		System.out.println(st.get("F"));
		//		st.delete("F");
		//		System.out.println(st.size());
		//		
		//		
		//		st.put("G", 1);
		//		System.out.println(st.get("G"));
		//		System.out.println(st.get("A"));
		//		System.out.println(st.get("B"));
	}
}
