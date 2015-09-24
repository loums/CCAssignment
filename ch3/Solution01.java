/* 
3.1 Three in One:
	Descirbe how you could use a single array to implement three stacks.
*/

import java.io.*;
import java.util.*;

/*
Solution 
	Use a array and equally divid into three parts. Maintain these three sub arrays.
	There is another way to make their spaces more flexiable, but it sacrifice time
	for changing space. 

Assumption
	Stacks will be in fix size.
	stack only stores integer.

Time Complexity: O(1) for push and pop
Space complexity: O(1)
*/

class MyStacks {
	private int[] array;
	private int capacity;//per stack
	private int[] size;//each stack

	public MyStacks (int num, int capacity) {
		this.capacity = capacity;
		array = new int[num * capacity];
		size = new int[num];
	}

	public boolean push(int stackIndex, int a) {//
		if (size[stackIndex] >= capacity) {
			return false;
		}

		int index = getLastIndex(stackIndex);
		array[index + 1] = a;
		size[stackIndex]++;
		return true;
	}

	public int pop(int stackIndex) {
		if (size[stackIndex] == 0) {
			throw new EmptyStackException();
		}

		int index = getLastIndex(stackIndex);
		int res = array[index];
		size[stackIndex]--;

		return res;
	}

	public int peek (int stackIndex) {
		if (size[stackIndex] == 0) {
			throw new EmptyStackException();
		}

		int index = getLastIndex(stackIndex);
		int res = array[index];

		return res;
	}

	private int getLastIndex (int stackIndex) {
		int index = stackIndex * capacity + size[stackIndex] - 1;
		return index;	
	}
}


public class Solution01 {

	static public void main(String[] args) {
		MyStacks myStacks = new MyStacks(3, 2);

		myStacks.push(0, 1);
		myStacks.push(0, 2);
		myStacks.push(1, 3);
		myStacks.push(2, 5);

		System.out.println("----------- 3.1 Three in One -----------");
		System.out.println("pop for stack 0 (should be 2): ");
		System.out.println(myStacks.pop(0));
		System.out.println("peek for stack 2 (should be 5): ");
		System.out.println(myStacks.peek(2));
	}

}