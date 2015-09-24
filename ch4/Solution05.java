/* 
4.5 Validate BST:
	Implement a function to check if a binary tree is a binary search tree.
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

public class Solution05 {
	/*
	Solution 1
		Use inorder traversal, the result will be the same sequence as the sorted array. 
		Use a pre pointer record the last element, and each time compare this node and
		its previous, if they follow the pre.value < cur.value .
	Time complexity: O(n) for traversal
	Space Complexity: O(1) for only one pointer
	*/
	private static TreeNode pre = null;

	static private boolean validateBST(TreeNode root) {
		if (root == null)
			return true;

		//left tree is not BST
		if (!validateBST(root.left))
			return false;

		//root
		if (pre == null) { //Initialize pre
			pre = root;
		}

		if (pre.value > root.value) {//handle root with pre node
			return false;
		}

		pre = root;

		//right tree
		if (!validateBST(root.right))
			return false;

		return true;
	}


	static public void main(String[] args) {
		TreeNode root = generateTree();
		boolean res = validateBST(root);
		System.out.println(res);
	}

	static private TreeNode generateTree() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);

		n3.left = n1;
		n3.right = n5;
		n1.left = n6;
		n1.right = n2;
		n5.left = n4;
		n5.right = n0;

		return n3;
	}


}