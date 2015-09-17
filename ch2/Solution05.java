/*
2.5 Sum Lists:
	You have two numbers represented by a linked list, where each node contains
	a single digit. The digits are stored in reverse order, such that the 1's 
	digit is at the head of the list. Write a function that adds the two numbers
	and returns the sum as a linked list.
	EXAMPLE:
	Input: (7->1->6) + (5->9->2). That is 617 + 295
	Output: 2->1->9.That is, 912.
	FOLLOW UP:
	Suppose the digits are stored in forward order. Repeat the above problem.
	EXAMPLE:
	Input: (6->1->7) + (2->9->5). That is, 617 + 295
	Output: 9->1->2. That is, 912.
*/


import java.io.*;
import java.util.*;

class ListNode {
	int val;
	ListNode next;
	ListNode(int input) {
		val = input;
	}
}

//to store different types of input values
class CInput {
	ListNode num1;
	ListNode num2;
}

public class Solution05 {
	/*------------------------------------------------------------------
	Solution 1
		add each digit in the two numbers, record carry bit on the way.
	Assumption:
		if one number is null, it's be considered as 0, then return the other number.
	Time complexity: O(n)
	Space Complexity: O(n)
	*/
	static private ListNode SumLists1(ListNode num1, ListNode num2) {
		if (num1 == null)
			return num2;
		if (num2 == null)
			return num1;

		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		int carryBit = 0;
		while (num1 != null || num2 != null) {
			int sum = carryBit;

			if (num1 != null) {
				sum += num1.val;
				num1 = num1.next;
			}
			if (num2 != null) {
				sum += num2.val;
				num2 = num2.next;
			}
			
			carryBit = sum / 10;
			ListNode newNode = new ListNode(sum % 10);
			pre.next = newNode;
			pre = pre.next;

		}

		return dummy.next;
	}

	/*------------------------------------------------------------------
	Follow up
		using two stacks to get the digits in reverse order.
	Assumption:
		if one number is null, it's be considered as 0, then return the other number.
	Time complexity: O(n)
	Space Complexity: O(n)
	*/
	static private ListNode SumListsFollowup(ListNode num1, ListNode num2) {
		if (num1 == null)
			return num2;
		if (num2 == null)
			return num1;

		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		int carryBit = 0;
		ArrayDeque<Integer> stack1 = new ArrayDeque<Integer>();
		ArrayDeque<Integer> stack2 = new ArrayDeque<Integer>();

		//push digits into stack
		while (num1 != null) {
			stack1.push(num1.val);
			num1 = num1.next;
		}
		while (num2 != null) {
			stack2.push(num2.val);
			num2 = num2.next;
		}
 
 		//pop digits out of stack and sum them up.
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			int sum = carryBit;

			if (!stack1.isEmpty()) {
				sum += stack1.pop();
			}
			if (!stack2.isEmpty()) {
				sum += stack2.pop();
			}
			
			carryBit = sum / 10;
			ListNode newNode = new ListNode(sum % 10);
			newNode.next = pre.next;//list result nodes in reverse order
			pre.next = newNode;

		}

		return dummy.next;
	}

	//------------------------------------------------------------------

	static public void main(String[] args) {
		//Input
		CInput cinput = input();
		ListNode num1 = cinput.num1;
		ListNode num2 = cinput.num2;

		//Solutions
		//ListNode res = SumLists1(num1, num2);
		//Followup
		ListNode res = SumListsFollowup(num1, num2);

		//Output
		output(res);
	}

	//------------------------------------------------------------------

	//input
	static private CInput input(){
		CInput res = new CInput();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/***** 2.5 Sum Lists *****/");
			System.out.println("Please input two nums in reverse linked lists (using space to seperate nodes): ");

			String s = br.readLine();
			String[] nodes = s.split(" ");

			//num1
			ListNode dummyhead = new ListNode(0);
			ListNode pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}
			res.num1 = dummyhead.next;

			//num2
			s = br.readLine();
			nodes = s.split(" ");
			dummyhead.next = null;
			pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}
			res.num2 = dummyhead.next;

		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return res;
	}


	//output
	static private void output(ListNode head) {
		System.out.println("Result: ");
		while (head != null) {
			System.out.print(head.val);
			System.out.print(" ");
			head = head.next;
		}
		System.out.println();
	}


}