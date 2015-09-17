/*
2.1 Remove Dups:
	Write code to remove duplicates from an unsorted linked list.
	FOLLOW UP:
	How would you solve this problem if a temporary buffer is not allowed?
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

public class Solution01 {
	/*
	Solution 1
		Using hashtable to record nodes, delete the node if the same value already exists.
	Assumption:
		None.
	Time complexity: O(n)
	Space Complexity: O(n)
	*/
	static private ListNode RemoveDups1(ListNode head) {
		if (head == null)
			return head;

		Set<Integer> set = new HashSet<Integer>();
		ListNode pre = head;
		ListNode cur = head.next;
		set.add(head.val);
		while(cur != null) {
			if (set.contains(cur.val)) {//delete dup
				pre.next = cur.next;
				cur = cur.next;
			} else {
				set.add(cur.val);
				pre = cur;
				cur = cur.next;
			}
		}

		return head;
	}

	/*
	Solution 2
		Did not use additional space, each time encounter the first unique value node, 
		traversal the rest of the list to delete the same value's nodes.
	Assumption:
		None.
	Time complexity: O(n^2)
	Space Complexity: O(1)
	*/
	static private ListNode RemoveDups2(ListNode head) {
		if (head == null)
			return head;

		ListNode pre = head;
		while(pre != null) {
			ListNode pre2 = pre;
			ListNode cur = pre.next;
			int target = pre.val;

			while (cur != null) {//delete the dups in the rest of the list
				if (cur.val == target) {
					pre2.next = cur.next;
					cur = cur.next;
				} else {
					pre2 = cur;
					cur = cur.next;
				}
			}

			//move to next different value's node
			pre = pre.next;
		}

		return head;
	}

	static public void main(String[] args) {
		//Input
		ListNode head = input();

		//Solutions
		//ListNode res = RemoveDups1(head);
		ListNode res = RemoveDups2(head);

		//Output
		output(res);
	}

	//input
	static private ListNode input(){
		ListNode dummyhead = new ListNode(0);
		ListNode pre = dummyhead;

		System.out.println("/**** 2.1 Remove Dups ****/");
		System.out.println("Please input a linked list (using space to seperate nodes): ");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			String[] nodes = s.split(" ");
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}
		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return dummyhead.next;
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