/*
2.7 Intersection:
	Given two (singly) linked lists, determine if two lists intersect. Return
	the intersecting node. Note that the intersection is defined based on 
	reference, not value. That is, if the kth node of the linked list is the
	exact same node (by reference) as the jth node of the second linked list,
	then they are intersecting. 
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
	ListNode list1;
	ListNode list2;
}

public class Solution07 {
	/*------------------------------------------------------------------
	Solution 1:
		Traversal two lists and get their length difference. Let the fast pointer move
		the difference number steps first on the longer list, then slow pointer starts 
		to move on the shorter list together. If they encounter somewhere, then they 
		have interesction. Otherwise, they won't inersect with each other.
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private boolean Intersection(ListNode list1, ListNode list2) {
		if (list1 == null || list2 == null)
			return false;

		//get length difference
		int len1 = 0, len2 = 0;
		ListNode cur = list1;
		while(cur != null) {
			len1++;
			cur = cur.next;
		}
		cur = list2;
		while(cur != null) {
			len2++;
			cur = cur.next;
		}

		//always let l1 be the longer list
		ListNode l1 = len1 >= len2 ? list1 : list2;
		ListNode l2 = len1 >= len2 ? list2 : list1; 
		//move longer list's pointer
		int diff = Math.abs(len1 - len2);
		while (diff > 0) {
			l1 = l1.next;
			diff--;
		}

		//move two pointers together
		while(l1 != null && l2 != null) {
			if (l1 == l2) {
				return true;
			}
			l1 = l1.next;
			l2 = l2.next;
		}

		return false;
	}

	//------------------------------------------------------------------

	static public void main(String[] args) {
		//Input
		CInput cinput = input();
		ListNode list1 = cinput.list1;
		ListNode list2 = cinput.list2;

		//Solutions
		boolean res = Intersection(list1, list2);

		//Output
		output(res);
	}

	//------------------------------------------------------------------

	//input
	static private CInput input(){
		CInput res = new CInput();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/********  2.7 Intersection  *******/");
			System.out.println("Please input two linked lists' seperate part: ");

			String s = br.readLine();
			String[] nodes = s.split(" ");

			//list1 header
			ListNode dummyhead = new ListNode(0);
			ListNode pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}
			res.list1 = dummyhead.next;
			ListNode tail1 = pre;

			//list2 header
			s = br.readLine();
			nodes = s.split(" ");
			dummyhead.next = null;
			pre = dummyhead;
			for (int i = 0; i < nodes.length; i++) {
				ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
				pre.next = cur;
				pre = cur;
			}
			res.list2 = dummyhead.next;
			ListNode tail2 = pre;

			//common part
			System.out.println("Please input 1 to continue input their common, or 0 finish input: ");
			s = br.readLine();
			if (Integer.parseInt(s) == 1) {
				System.out.println("Please input two linked lists' intersected common part: ");
				s = br.readLine();
				nodes = s.split(" ");
				dummyhead.next = null;
				pre = dummyhead;
				for (int i = 0; i < nodes.length; i++) {
					ListNode cur = new ListNode(Integer.parseInt(nodes[i]));
					pre.next = cur;
					pre = cur;
				}

				//list common part
				tail1.next = dummyhead.next;
				tail2.next = dummyhead.next;
			}

		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return res;
	}


	//output
	static private void output(boolean b) {
		System.out.println("Result: ");
		System.out.println(b);
	}


}