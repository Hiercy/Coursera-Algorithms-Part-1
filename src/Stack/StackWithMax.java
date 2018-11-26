package Stack;

import java.util.Stack;

public class StackWithMax<Item> {

	private Stack<Integer> elements = new Stack<>();
	private int max;
	
	public StackWithMax() {
		max = 0;
	}
	
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	public int size() {
		return elements.size();
	}
	
	public void push(int item) {
		elements.push(item);
		
		if (elements.peek() > max)
			max = elements.peek();
			
	}
	
	public int pop() {
		return elements.pop();
	}
	
	public int findMax() {
		return max;
	}
	
	public static void main(String[] args) {
		StackWithMax<Integer> a = new StackWithMax<>();
		
		a.push(1);
		System.out.println("Size = " + a.size());
		a.push(21);
		System.out.println("Size = " + a.size());
		a.push(2);
		System.out.println("Size = " + a.size());
		a.push(15);
		System.out.println("Size = " + a.size());
		a.push(9);
		System.out.println("Size = " + a.size());
		
		System.out.println(a.findMax());
		System.out.println("Size = " + a.size());
		
		a.pop();
		a.pop();
		a.pop();
		a.pop();
		a.pop();
		System.out.println("Size = " + a.size());
		System.out.println(a.findMax());
		
	}
	
}
