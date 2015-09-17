/*
2.4 Partition:
	Write code to partition a linked list around a value x, such that all nodes
	less than x come before all nodes greater than or equal to x. If x is
	contained within the list, the values of x only need to be after the elements
	less than x.
	EXAMPLE:
	Input: 3->5->8->5->10->2->1 [partition = 5]
	Output: 3->1->2->10->5->5->8
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
	ListNode head;
	int value;
}

public class Solution04 {
	/*------------------------------------------------------------------
	Solution 1
		find the first node that is greater or equal to x, record its previous node as 
		flag node. Then move the smaller nodes insert before the recorded flag. The 
		nodes' original order are preserved.
	Assumption:
		input will contain both kinds of nodes smaller or greater than x.
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private boolean Partition1(ListNode head, int value) {
		if (head == null)
			return false;

		//find the first node greater or equal than value, record its previous node.
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = head;
		ListNode insertPos = dummy;
		while (cur != null) {
			if (cur.val >= value) {
				insertPos = pre;
				pre = cur;
				cur = cur.next;
				break;
			}
			pre = cur;
			cur = cur.next;
 		}

 		//continue traversal, insert nodes smaller nodes after the insertPos node
 		while (cur != null) {
 			if (cur.val < value) {
 				//delete
 				pre.next = cur.next;
 				//insert
 				cur.next = insertPos.next;
 				insertPos.next = cur;
 				//update pointers
 				cur = pre.next;
 			} else {
 				pre = cur;
 				cur = cur.next;
 			}
 		}

		return true;
	}

	//------------------------------------------------------------------
	static public void main(String[] args) {
		//Input
		CInput cinput = input();
		ListNode head = cinput.head;
		int value = cinput.value;

		//Solutions
		boolean res = Partition1(head, value);
		//boolean res = Partition2(head);

		//Output
		if (res == false) {
			System.out.println("Invalid input.");
		} else {
			output(head);
		}
		
	}

	//------------------------------------------------------------------
	//input
	static private CInput input(){
		CInput res = new CInput();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/***** 2.4 Partition *****/");
			System.out.println("Please input a linked list and a partition value (using space to seperate nodes): ");
			
			String s = br.readLine();
			String[] nodes = s.split(" ");

			ListNode dummyhead = new ListNode(0);
			ListNode pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}
			res.head = dummyhead.next;

			String num = br.readLine();
			res.value = Integer.parseInt(num);

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
		System.out.print("\n");
	}
}