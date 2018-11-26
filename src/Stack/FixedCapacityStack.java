package Stack;

public class FixedCapacityStack<Item> {
	private Item[] a; // элементы стека
	private int N; // размер
	
	public FixedCapacityStack(int cap) {
//		a = new Item[cap]; - нельзя. Java не поддерживает Generic arrays
		a = (Item[]) new Object[cap];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void push(Item item) {
		a[N++] = item;
	}
	
	public Item pop() {
		return a[--N]; 
	}
	
	public static void main(String[] args) {
		FixedCapacityStack<Integer> s = new FixedCapacityStack(5);
		
		System.out.println("Push in the stack");
		System.out.println("Size = " + s.size());
		s.push(1);
		System.out.println("Size = " + s.size());
		s.push(2);
		System.out.println("Size = " + s.size());
		s.push(3);
		System.out.println("Size = " + s.size());
		s.push(4);
		System.out.println("Size = " + s.size());
		s.push(6);
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
