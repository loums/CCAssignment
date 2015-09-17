/*
2.8 Loop Detection: 
	Given a circular linked list, implement an algorithm that returns the node
	at the beginning of the loop.
	DEFINITION:
	Circulat linked list: A (corrupt) linked list in which a node's next pointer 
	points to an earler node, so as to make a loop in the linked list.
	EXAMPLE:
	Input: A->B->C->D->E->C [the same C as earlier]
	Output: C
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

public class Solution08 {
	/*
	Solution 1
	  |<-------------------------
	  |<----- a ---->|	         |
	  o -- o -- o -- o -- o -- o |
					 |		   | |  s
				  	 o		   o |
				  	 |		   | |
				  x	 o -- o -- o |
			              |<-----	 

		1). first detect the loop use two pointers, each time fast pointer have one more
		step than the slow pointer. Then they will encounter somewhere. Assume the slow 
		pointer went s steps, then the fast pointer went through 2s steps.
		2). Assume fast has alredy pass through n circles, and the circle length is r, 
		then
			2s = s + nr
		so
			s = nr
		3). Assume the distance between entry point of loop and start point of the list
		is a; the distance from enter point to encounter point is x.
			a = s - x = nr - x = (n-1)r + (r-x)
		so 'a' equals to n-1 circles and the distance from encounter point to entry point.
		So we can have two pointers, one start from the beginning of the list, and the 
		other start from the encounter. They will encounter at the entry point.

	Assumption:
		None.
	Time complexity: O(n)
	Space Complexity: O(n)
	*/
	static private ListNode LoopDetection(ListNode head) {
		if (head == null)
			return head;

		ListNode entryNode = null;
		ListNode encounterNode = null;
		ListNode fast = head;
		ListNode slow = head;

		//find the first encounter point
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				encounterNode = slow;
				break;
			}
		}

		//get the entry point
		ListNode start = head;
		while (slow != null) {
			start = start.next;
			slow = slow.next;
			if (start == slow) {
				entryNode = start;
				break;
			}
		}

		return entryNode;
	}

	static public void main(String[] args) {
		//Input
		ListNode head = input();

		//Solutions
		ListNode res = LoopDetection(head);

		//Output
		output(res);
	}

	//input
	static private ListNode input(){
		ListNode dummyhead = new ListNode(0);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/******* 2.8 Loop Detection *******/");
			System.out.println("Please input the circle's entry point (entry cannot be the first node): ");
			String s = br.readLine();
			int entry = Integer.parseInt(s);

			System.out.println("Please input a linked list with a circle: (values should be unique and contain the entry)");
			s = br.readLine();
			String[] nodes = s.split(" ");

			ListNode entryNode = null;
			ListNode pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				int value = Integer.parseInt(nodes[i]);
				ListNode cur = new ListNode(value);
				if (value == entry) {
					entryNode = cur;
				}
				pre.next = cur;
				pre = cur;
			}
			pre.next = entryNode;

		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return dummyhead.next;
	}

	//output
	static private void output(ListNode node) {
		System.out.println("Result: ");
		System.out.println(node.val);
	}
}