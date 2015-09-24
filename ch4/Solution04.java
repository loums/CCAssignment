/* 
4.4 Check Balanced: 
	Implement a function to check if a binary tree is balanced. For the purposes
	of this question, a balanced tree is defined to be a tree such that the 
	heights of the two subtrees of any node never differ by more than one.
*/

import java.io.*;
import java.util.*;

public class Solution04 {
	/*
	Solution 1
		Count left sub tree's height and right sub tree's height, and check if 
		their difference is larger than 1, if not return the (larger height + 1),
		else return -1.
	Time complexity: O(n) for traversal
	Space Complexity: O(n) for storing tree
	*/
	static private boolean checkBalanced(TreeNode root) {
		return helper(root) != -1;
	}

	static private int helper(TreeNode root) {
		if (root == null)
			return 0;

		int left = helper(root.left);
		int right = helper(root.right);
		int height;
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			height = -1;
		} else {
			height = Math.max(left, right) + 1;
		}

		return height;
	}


	static public void main(String[] args) {
		TreeNode root = generateTree();
		boolean res = checkBalanced(root);
		System.out.println(res);
	}

	//output
	static private TreeNode generateTree() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);


		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n1.right = n4;
		n3.left = n5;


		return n0;
	}

}