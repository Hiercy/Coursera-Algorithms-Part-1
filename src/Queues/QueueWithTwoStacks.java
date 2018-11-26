package Queues;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks<Item> {

	private Stack<Item> stack1; // back of queue
	private Stack<Item> stack2; // front of queue
	
	public QueueWithTwoStacks() {
		stack1 = new Stack<>();
		stack2 = new Stack<>();
	}
	
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}
	
	public int size() {
		return stack1.size() + stack2.size();
	}
	
	// add the item to the queue
	public void enqueue(Item item) {
		stack1.push(item);
	}
	
	// remove and return the item on the queue least recently added
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		
		if (stack2.isEmpty())
			moveStack1ToStack2();
		
		return stack2.pop();
	}
	
	// return the item least recently added to the queue.
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		
		if (stack2.isEmpty())
			moveStack1ToStack2();
		
		return stack2.peek();
	}

	// move all items from stack1 to stack2
	private void moveStack1ToStack2() {
		while (!stack1.isEmpty())
			stack2.push(stack1.pop());
	}
}
