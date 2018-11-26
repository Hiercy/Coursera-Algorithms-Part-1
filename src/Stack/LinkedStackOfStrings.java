package Stack;

public class LinkedStackOfStrings {

	private Node first = null;
	
	class Node {
		String item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void push (String item) {
		Node oldFirst = first;
		first = new Node();
		
		// Set the instance variables in the new node
		first.item = item; 
		first.next = oldFirst;
	}
	
	public String pop() {
		String item = first.item; // save item to return
		first = first.next; // delete first node
		return item; // return saved item
	}
	
}
