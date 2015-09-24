/* 
3.4 Queue via Stacks:
	Implement a MyQueue class which implements a queue using two stacks.
*/

import java.io.*;
import java.util.*;

/*
Solution 1
	Name the two stacks stack1 and stack2. 
	When doing push(): just push it to stack1;
	When doing pop(): If stack2 is empty, pop all the nodes in stack1 to stack2,
	and pick the top one in stack2 and return it; If stack2 is not empty, just 
	return stack2.pop();

Time complexity: When stack2 is not empty O(1) for pop(), otherwise, O(n).
				O(1) for push()
Space Complexity: No extra space besides the two stacks
*/

class MyQueue {
	private Stack<Integer> stack1 = new Stack<Integer>();
	private Stack<Integer> stack2 = new Stack<Integer>();

	public void offer(int value) {
		stack1.push(value);
	}

	public int poll() {
		if (stack2.isEmpty()) { //stack2 don't have buffered values.
			if (stack1.isEmpty()) { //both of them are empty
				throw new EmptyStackException();
			}
			//pop from stack1 to stack2
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}

		return stack2.pop();
	}

	public boolean isEmpty() {
		if (stack1.isEmpty() && stack2.isEmpty())
			return true;
		return false;
	}

	public int size() {
		return stack1.size() + stack2.size();
	}

	//for testing purpose
	public void printSizeOfStack1() {
		int size = stack1.size();
		System.out.println("Size of stack1: " + String.valueOf(size));
	}

	public void printSizeOfStack2() {
		int size = stack2.size();
		System.out.println("Size of stack2: " + String.valueOf(size));
	}

}



public class Solution04 {

	static public void main(String[] args) {
		//running test cases
		System.out.println("----------- 3.4 Queue vis Stacks -----------");
		testCase();
	}

	//get input
	static private void testCase() {
		System.out.println("----------- Test case 1 : -----------");
		MyQueue queue = new MyQueue();

		//push 6 values: from 1 to 6
		System.out.println("Call offer() for value from 1 to 3");
		for (int i = 1; i < 4; i++) {
			queue.offer(i);
		}
		queue.printSizeOfStack1();
		queue.printSizeOfStack2();

		int value = queue.poll();
		System.out.println("Call poll() get value (should be 1): " + String.valueOf(value));
		queue.printSizeOfStack1();
		queue.printSizeOfStack2();

		System.out.println("Call offer() for value 4");
		queue.offer(4);
		queue.printSizeOfStack1();
		queue.printSizeOfStack2();

		value = queue.poll();
		System.out.println("Call poll() get value (should be 1): " + String.valueOf(value));
		queue.printSizeOfStack1();
		queue.printSizeOfStack2();

	}
	

}