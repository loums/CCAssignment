/* 
3.5 Sort Stack:
	Write a program to sort a stack such that the smallest items are on the top.
	You can use an additional temporary stack, but you may not copy the elements into
	any other data structure (such as an array). The stack supports the following 
	operations: push, pop, peek, and isEmpty.
*/

import java.io.*;
import java.util.*;

public class Solution05 {
	/*
	Solution 1
		Name the two stacks stack1 and stack2.
		Consider the numbers in the additional stack, stack2, is sorted. Pop the top 
		element and find its insert place in stack2, the popped elements from stack2
		can be pushed back to stack1.
	Time complexity: O(n^2)
	Space Complexity: O(n) - the additional stack
	*/
	static private void Solution(Stack<Integer> stack1) {
		Stack<Integer> stack2 = new Stack<Integer>();

		while (!stack1.isEmpty()) {
			int value = stack1.pop();
			//stack2: keep increasing order from bottom to top 
			while(!stack2.isEmpty() && stack2.peek() > value) { 
				stack1.push(stack2.pop());
			}
			stack2.push(value);
		}

		//push back to stack1, in decreasing order from botton to top
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
	}

	//for testing purpose
	static public void printStack(Stack<Integer> stack) {
		Stack<Integer> tmp = new Stack<Integer>();
		while(!stack.isEmpty()) {
			int value = stack.pop();
			System.out.println(value);
			tmp.push(value);
		}
		while(!tmp.isEmpty()) {
			stack.push(tmp.pop());
		}
	}


	static public void main(String[] args) {
		//running test cases
		System.out.println("----------- 3.5 Sort Stack -----------");
		testCase();
	}

	static private void testCase() {
		System.out.println("----------- Test case 1 : -----------");
		Stack<Integer> stack = new Stack<Integer>();

		stack.push(3);
		stack.push(2);
		stack.push(5);
		stack.push(1);
		stack.push(4);
		stack.push(6);

		System.out.println("Before sorting, the elements from top to bottom: ");
		printStack(stack);

		Solution(stack);
		System.out.println("After sorting, the elements from top to bottom: ");
		printStack(stack);

	}
}