/*
2.6 Palindrome:
	Implement a function to check if a linked list is a palindrome.
*/

import java.io.*;
import java.util.*;

class ListNode {
	String string;
	ListNode next;
	ListNode(String str) {
		string = str;
	}
}

public class Solution06 {
	/*------------------------------------------------------------------
	Solution 1
		use stack to record the first half list nodes in reverse order.
		Then each time pop nodes from stack and compare with the second half.
	Assumption:
		assume the input will contain contains single letters in each node.
	Time complexity: O(n)
	Space Complexity: O(n)
	*/
	static private boolean Palindrome(ListNode head) {
		if (head == null)
			return false;

		ArrayDeque<String> stack = new ArrayDeque<String>();

		//get total length
		int len = 0;
		ListNode cur = head;
		while (cur != null) {
			len++;
			cur = cur.next;
		}

		//push the first half into stack
		int n = len / 2;
		cur = head;
		for (int i = 0; i < n; i++) {
			stack.push(cur.string);
			cur = cur.next;
		}
		//if odd number of nodes, skip the one in the middle
		if (len % 2 == 1) {
			cur = cur.next;
		}
		//compare two halfs
		while (cur != null) {
			if ( cur.string.equals(stack.pop()) ) {
				cur = cur.next;
			} else {
				return false;
			}
		}
 		
		return true;
	}

	//------------------------------------------------------------------
	static public void main(String[] args) {
		//Input
		ListNode head = input();

		//Solutions
		boolean res = Palindrome(head);

		//Output
		output(res);
		
	}

	//------------------------------------------------------------------
	//input
	static private ListNode input(){
		ListNode dummyhead = new ListNode("0");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/***** 2.6 Palindrome *****/");
			System.out.println("Please input a linked list(using space to seperate nodes): ");
			
			String s = br.readLine();
			String[] nodes = s.split(" ");

			ListNode pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(nodes[i]);
				pre.next = cur;
				pre = cur;
			}

		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return dummyhead.next;
	}


	//output
	static private void output(boolean b) {
		System.out.println("Result: ");
		System.out.println(b);
	}
}