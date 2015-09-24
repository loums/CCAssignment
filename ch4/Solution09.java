/* 
4.9 BST Sequences:
	A binary search tree was created by traversing through an array from left 
	to right and inserting each element. Given a binary search tree with distinct
	elements, print all possible arrays that could have led to this tree.
	EXAMPLE
	Input:
			2
		  /   \
		1		3
	Output:
		{2, 1, 3}, {2, 3, 1}
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	TreeNode (int value) {
		this.value = value;
	}
}

public class Solution09 {
	/*
	Solution:
		Observation: if a value smaller than root node, it goes to left sub tree; 
		if a value larger than root node, it goes to right sub tree. 

		So that all the left nodes won't affect all the right nodes. 

		But the order within all the left nodes matters. (same for right nodes). Since
		the one comes earlier will take the root's left child place. (same for right nodes)

		So assume we have the root, the left subtree's order and right subtree's order, 
		so the task now is to assign root at front of all the interleavings of left and right
		orders. (Main recursion) 

		The interleaving means all the left nodes should keep in the relative order, same 
		for right nodes. But the order between left and right nodes can change. Get all the
		possible orders. (Helper recursion for interleaving) 

		Doing it recursively will get the final answer.

	Time complexity: O(n^2) for interleaving, O(n) for traversal each node
	Space Complexity: depends on the answers' size. 
	*/

	private static List<List<Integer>> BSTSequences(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return res;
		}

		List<List<Integer>> leftPart = BSTSequences(root.left);
		List<List<Integer>> rightPart = BSTSequences(root.right);

		//two parts are empty
		if (leftPart.size() == 0 && rightPart.size() == 0) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(root.value);
			res.add(list);
			return res;
		}
		//one of them is empty
		if (leftPart.size() == 0 || rightPart.size() == 0) {
			List<List<Integer>> tmp = leftPart.size() == 0 ? rightPart : leftPart;
			for (List<Integer> l : tmp) {
				l.add(0, root.value);
				res.add(new ArrayList<Integer>(l));
			}
			return res;
		}

		//both of them are not empty, interleaving all the pairs of left and right lists
		for (List<Integer> l1 : leftPart) {
			for (List<Integer> l2 : rightPart) {
				List<List<Integer>> tmp = getInterleaving(l1, l2);
				//add results
				for (List<Integer> l : tmp) {
					l.add(0, root.value);//add the root at head
					res.add(new ArrayList<Integer>(l));//??
				}
			}
		}

		return res;
	}

	//the depth will be m + n (the original length of list1 and list2)
	private static List<List<Integer>> getInterleaving(List<Integer> list1, List<Integer> list2) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (list1 == null || list2 == null) 
			return res;
		if (list1.size() == 0 && list2.size() == 0) 
			return res;

		//if one of the list is empty, but the other is not, just return the other list
		if (list1.size() == 0) {
			res.add(new ArrayList<Integer>(list2));
			return res;
		} else if (list2.size() == 0) {
			res.add(new ArrayList<Integer>(list1));
			return res;
		}

		//interleaving
		//{1, 2} * {3, 4} = {1} + {2} * {3, 4}  and {3} + {1, 2} * {4};   
		int node11 = list1.get(0);
		list1.remove(0);//O(n)
		List<List<Integer>> res1 = getInterleaving(list1, list2);
		list1.add(0, node11);

		int node12 = list2.get(0);
		list2.remove(0);
		List<List<Integer>> res2 = getInterleaving(list1, list2);
		list2.add(0, node12);

		res.addAll(res1);
		res.addAll(res2);

		return res;
	}

	public static void main(String[] args) {
		TreeNode root = generateTree();
		List<List<Integer>> res = BSTSequences(root);

		System.out.println("----------- 4.9 BST Sequences -----------");
		System.out.println("Result: ");

		for (List<Integer> l : res) {
			for (int i : l) {
				System.out.print(i);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private static TreeNode generateTree() {

		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);

		n2.left = n1;
		n2.right = n3;

		return n2;
	}

}