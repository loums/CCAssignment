/* 
3.3 Stack of Plates:
	Imagine a (literal) stack of plates. If the stack gets too high, it might 
	topple. Therefore, in real life, we would likely start a new stack when
	the previous stack exceeds some threshold. Implement a data structure
	SetOfStacks that mimics this. SetOfStacks should be composed of several
	stacks and should create a new stack once the previous one exceeds capacity.
	SetOfStacks.push() and SetOfStacks.pop() should behave identically to a 
	single stack (that is, pop() should return the same values as it would if
	there were just a single stack).
	FOLLOW UP:
	Implement a function popAt(int index) which performs a pop operation on a 
	specific sub-stack.
*/

import java.io.*;
import java.util.*;

/*
Solution
	Use an ArrayList to record the stacks, push and pop to the last substack;
	when stack becomes full, need to create a new stack; when becomes empty
	need to delete it. 

Follow up
	Assume some sub stacks can be not full, but cannot be empty.
	PopAt() just pop the mapping substack, and check if it's empty, remove
	that stack; if it's not empty, we don't need additional operations.
	Because the relative orders don't change, won't affect the whole stack.

Assumption:
	Assume the total capacity is infinite.
	And the sub stacks int he middle can be not full.

Corner cases:
	the very first element, also need to add new sub stack.

Time complexity: O(1) for push and pop
Space Complexity: No extra space 
*/

class SetOfStacks {
	List<Stack<Integer>> setOfStacks;
	int threshold; //the threshold for each sub stack

	SetOfStacks(int threshold) {
		setOfStacks = new ArrayList<Stack<Integer>>();
		this.threshold = threshold;
	}

	public void push(int value) {
		Stack<Integer> lastStack = getLastStack();
		if (lastStack == null || lastStack.size() == threshold) {
			Stack<Integer> newStack = new Stack<Integer>();
			newStack.push(value);
			setOfStacks.add(newStack);
		} else {
			lastStack.push(value);
		}
	}

	public int pop() {
		Stack<Integer> lastStack = getLastStack();
		if (lastStack == null) //can be the first stack but empty
			throw new EmptyStackException();

		int value = lastStack.pop();
		if (lastStack.size() == 0 && setOfStacks.size() > 1)
			setOfStacks.remove(setOfStacks.size()-1);

		return value;
	}

	public int popAt(int stackNum) { //stackNum = index + 1
		if (setOfStacks.size() == 0)
			throw new EmptyStackException();

		Stack<Integer> targetStack = setOfStacks.get(stackNum - 1);
		int value = targetStack.pop();
		if (targetStack.isEmpty()) {
			setOfStacks.remove(stackNum - 1);
		}

		return value;
	}

	public Stack<Integer> getLastStack() {
		if (setOfStacks.size() == 0)
			return null;
		else
			return setOfStacks.get(setOfStacks.size()-1);
	}

	//for testing purpose
	public void printStackNum () {
		System.out.print("Number of sub stacks: ");
		System.out.print(setOfStacks.size());
		System.out.print("\n");
	}

}

public class Solution03 {

	static public void main(String[] args) {
		//running test cases
		System.out.println("----------- 3.3 Stack of Plates -----------");
		testCase();
	}

	static private void testCase() {
		System.out.println("----------- Test case 1 : -----------");
		
		SetOfStacks setOfStacks = new SetOfStacks(2);//each stack's capacity is 2
		System.out.println("Each sub stack's capacity is 2.");

		//push 6 values: from 1 to 6
		System.out.println("Call push() for value from 1 to 6");
		for (int i = 1; i < 7; i++) {
			setOfStacks.push(i);
		}
		setOfStacks.printStackNum();

		//push 7th value: will create a new sub stack
		setOfStacks.push(7);
		System.out.println("Call push() for value 7");
		setOfStacks.printStackNum();

		//pop from the whole stack: the value shoule be 7
		int value = setOfStacks.pop();
		System.out.println("Call pop() get value: " + String.valueOf(value));
		setOfStacks.printStackNum();

		//pop from the 2nd substack: the value should be 4
		value = setOfStacks.popAt(2);
		System.out.println("Call popAt(2) get value:  " + String.valueOf(value));
		setOfStacks.printStackNum();

		//pop from the 2nd substack: the value should be 3
		//and the number of substacks should be 2
		value = setOfStacks.popAt(2);
		System.out.println("Call popAt(2) get value:  " + String.valueOf(value));
		setOfStacks.printStackNum();

	}
}