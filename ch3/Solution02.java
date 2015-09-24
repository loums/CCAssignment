/* 
3.2 Stack Min:
	How would you design a stack which, in addition to push and pop, has a 
	function min which returns the minimum element? Push, pop and min should
	all operate in O(1) time.
*/

import java.io.*;
import java.util.*;

/*
Solution 1
	Record the min of the sub stack below cur node.
	Have a min value within node class.
	Implement by extends stack class.
	Pros: pop() keeps the same as the original one
Time complexity: O(1)
Space Complexity: O(n)
*/
class Node {
	int val;
	int min;
	public Node (int val , int min) {
		this.val = val;
		this.min = min;
	}
}

class MyStack1 extends Stack<Node>  {
	public void push(int value) {
		int curMin = Math.min(value, this.getMin());
		super.push(new Node(value, curMin));
	}

	public int getMin() {
		if (this.isEmpty()) { //invalid
			return Integer.MAX_VALUE;
		} else {
			return peek().min;
		}
	}
}


/*
Solution 2
	Use an additional stack, name stack2, as a member of the extended class;
	Only push new minimal values for saving space;
	So need to implement pop(), when the pop().val is the same as cur minimal value, 
	then need to pop this minimal value in stack2.

	Pros: save more space for storing min.

Time complexity: O(1) for push, pop and getMin
Space Complexity: O(n) for storing min, but may be much better than first solution
*/
class MyStack2 extends Stack<Integer>  {
	Stack<Integer> minStack = new Stack<Integer>(); //record min

	public void push(int value) {
		//ture stack
		super.push(value);

		//min stack
		if (this.isEmpty() || value < getMin()) {//first node or new min value
			minStack.push(value);
		}
	}

	public Integer pop() {
		//true stack
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		int curValue = super.pop();

		//min stack
		int curMin = getMin();
		if (curMin == curValue) { //cur min node popped, so update minStack
			minStack.pop();
		}

		return curValue;
	}

	public int getMin() {
		if (minStack.isEmpty()) { //invalid
			return Integer.MAX_VALUE;
		} else {
			return minStack.peek();
		}
	}
}


public class Solution02 {

	static public void main(String[] args) {
		//running test cases
		System.out.println("----------- 3.2 Stack Min -----------");
		testCase1();
		testCase2();
	}

	static private void testCase1() {
		System.out.println("----------- Test case 1 (for Solution 1): -----------");
		MyStack1 mystack = new MyStack1();

		System.out.println("Push values {5, 3, 6, 2}");
		int[] values = {5, 3, 6, 2};
		for (int i = 0; i < 4; i++) {
			mystack.push(values[i]);
		}

		//getMin()
		int min = mystack.getMin();
		System.out.println("Call getMin() get value (should be 2): " + String.valueOf(min));

		//pop()
		mystack.pop();
		min = mystack.getMin();
		System.out.println("Call pop() and getMin() get current minimal (should be 3): " + String.valueOf(min));

		mystack.pop();
		min = mystack.getMin();
		System.out.println("Call pop() and getMin() get current minimal (should be 3): " + String.valueOf(min));

		mystack.pop();
		min = mystack.getMin();
		System.out.println("Call pop() and getMin() get value (should be 5): " + String.valueOf(min));

	}

	static private void testCase2() {
		System.out.println("----------- Test case 2 (for Solution 2): -----------");
		MyStack2 mystack = new MyStack2();

		System.out.println("Push values {5, 3, 6, 2}");
		int[] values = {5, 3, 6, 2};
		for (int i = 0; i < 4; i++) {
			mystack.push(values[i]);
		}

		//getMin()
		int min = mystack.getMin();
		System.out.println("Call getMin() get value (should be 2): " + String.valueOf(min));

		//pop()
		mystack.pop();
		min = mystack.getMin();
		System.out.println("Call pop() and getMin() get current minimal (should be 3): " + String.valueOf(min));

		mystack.pop();
		min = mystack.getMin();
		System.out.println("Call pop() and getMin() get current minimal (should be 3): " + String.valueOf(min));

		mystack.pop();
		min = mystack.getMin();
		System.out.println("Call pop() and getMin() get value (should be 5): " + String.valueOf(min));

	}

}