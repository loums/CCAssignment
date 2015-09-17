/*
2.3 Delete Middle Node:
	Implement an algorithm to delete a node in the middle of a singly linked list,
	given only access to that node.
	EXAMPLE:
	Input: the node c from the linked list a->b->c->d->e
	Result: nothing is returned but the new linked list looks like a->b->d->e
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

class CInput {
	ListNode head;
	ListNode target;
}

public class Solution03 {
	/*
	Solution 1
		From the given node, over write its value with the next node's value, then 
		delete the next node. 
	Assumption:
		The given node won't be the last node in list. If it's the last node, then
		return false.
	Time complexity: O(1)
	Space Complexity: O(1)
	*/
	static private boolean DeleteMiddleNode(ListNode target) {
		if (target == null || target.next == null)//the given node can't be the last one
			return false;

		target.val = target.next.val;
		target.next = target.next.next;

		return true;
	}


	static public void main(String[] args) {
		//Input
		CInput cinput = input();
		ListNode head = cinput.head;
		ListNode target = cinput.target;

		//Solutions
		Boolean res = DeleteMiddleNode(target);

		//Output
		if (res == false) {
			System.out.println("Error input.");
		} else {
			output(head);
		}
	}

	//input
	static private CInput input(){
		CInput cinput = new CInput();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/***** 2.3 Delete Middle Node *****/");
			System.out.println("Please input a linked list and the node (sequence number) you want to delete (using space to seperate nodes): ");
			String s = br.readLine();
			String[] nodes = s.split(" ");

			ListNode dummyhead = new ListNode(0);
			ListNode pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}
			cinput.head = dummyhead.next;

			String num = br.readLine();
			int k = Integer.parseInt(num);
			ListNode node = dummyhead.next;
			for (int i = 1; i <= k-1; i++) {
				node = node.next;
			}
			cinput.target = node;

		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return cinput;
	}

	//output
	static private void output(ListNode head) {
		System.out.println("Result: ");
		while (head != null) {
			System.out.print(head.val);
			System.out.print(" ");
			head = head.next;
		}
		System.out.print("\n");
	}
}