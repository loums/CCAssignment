/*
2.2 Return Kth to Last:
	Implement an algorithm to find the kth to last element of a single linked list.
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
	ListNode node;
	int k;
}

public class Solution02 {
	/*
	Solution 1
		Use two pointers, the fast pointer is k nodes faster than the slow pointer, 
		so when the fast one reach the end, the slow one just points at the last Kth node.
	Assumption:
		K is possitive.
		And k is a valid length for the input linked list.
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private ListNode LastKthNode(ListNode head, int k) {
		if (head == null)
			return head;

		ListNode fast = head;
		ListNode slow = head;
		for (int i = 0; i < k; i++) {
			fast = fast.next;
		}

		while (fast != null) { 
			slow = slow.next;
			fast = fast.next; 
		}

		return slow;
	}


	static public void main(String[] args) {
		//Input
		CInput cinput = input();
		int k = cinput.k;
		ListNode head = cinput.node;

		//Solutions
		ListNode res = LastKthNode(head, k);

		//Output
		output(res);
	}

	//input
	static private CInput input(){
		CInput res = new CInput();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/**** 2.2 Return Kth to Last ****/");
			System.out.println("Please input K value and a linked list (using space to seperate nodes): ");
			
			String num = br.readLine();
			res.k = Integer.parseInt(num);

			String s = br.readLine();
			String[] nodes = s.split(" ");

			ListNode dummyhead = new ListNode(0);
			ListNode pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}

			res.node = dummyhead.next;
		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return res;
	}

	//output
	static private void output(ListNode node) {
		System.out.println("Result: ");
		System.out.println(node.val);
	}
}