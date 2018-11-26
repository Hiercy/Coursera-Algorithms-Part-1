package Stack;
// Fixed size
public class FixedCapacityStackOfStrings {

	private String[] a; // элементы стека
	private int N; // размер
	
	public FixedCapacityStackOfStrings(int capacity) {
		a = new String[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void push(String item) {
		a[N++] = item;
	}
	
	public String pop() {
		return a[--N];
	}
	
	public static void main(String[] args) {
		FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(5);
		
		System.out.println("Push in the stack");
		System.out.println("Size = " + s.size());
		s.push("H");
		System.out.println("Size = " + s.size());
		s.push("e");
		System.out.println("Size = " + s.size());
		s.push("l");
		System.out.println("Size = " + s.size());
		s.push("l");
		System.out.println("Size = " + s.size());
		s.push("o");
		System.out.println("Size = " + s.size());
		
		System.out.println("Pop from the stack");
		s.pop();
		System.out.println("Size = " + s.size());
		s.pop();
		System.out.println("Size = " + s.size());
		s.pop();
		System.out.println("Size = " + s.size());
		s.pop();
		System.out.println("Size = " + s.size());
		s.pop();
		System.out.println("Size = " + s.size());
	}
}
