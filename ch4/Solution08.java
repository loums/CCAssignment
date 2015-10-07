/* 
4.8 First Common Ancestor:
	Design an algorithm and write code to find the first common ancestor of two 
	nodes in a binary tree. Avoid storing additional nodes in a data structure. 
	NOTE: This is not necessarily a binary search tree.
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

class CInput {
	TreeNode root;
	TreeNode target1;
	TreeNode target2;
}

public class Solution08 {
	/*
	Assumption:
		Input contains the tree's root and the two nodes. Nodes don't have access
		back to their parents.

	Solution: 
		Obervation: If a is the common ancestor of b and c, then b and c must be 
		on a's different sub branch. 

		If one node is in that branch, then return true to its parent. Their common ancestor
		will collect more than 1 true returns (e.g., from both left and right children). 
		This is the first ancestor. 

		This method also works for general trees, not only binary tree.

	Special cases:
		Target nodes don't exist, return null;
		Two target nodes in the same branch, one is the ancestor of the other.

	Time complexity: O(n) for traversal
	Space Complexity: O(n) for storing tree, no extra space
	*/
	private static TreeNode firstCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null)
			return null;

		if (root == a || root == b) {
			return root;
		}

		TreeNode left = firstCommonAncestor(root.left, a, b);
		TreeNode right = firstCommonAncestor(root.right, a, b);
		if (left != null && right != null){
			return root;
		}

		return left != null ? left : right;
	}


	public static void main(String[] args) {
		CInput input = generateTree();
		TreeNode root = input.root;
		TreeNode a = input.target1;
		TreeNode b = input.target2;

		TreeNode res = firstCommonAncestor(root, a, b);
		System.out.println("----------- 4.8 First Common Ancestor -----------");
		if (res == null) {
			System.out.println("null");
		} else {
			System.out.println(res.value);
		}
	}

	static private CInput generateTree() {
		CInput input = new CInput();

		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);

		n0.left = n1;
		n0.right = n2;

		n1.left = n3;
		n1.right = n4;

		n2.left = n5;
		n2.right = n6;

		input.root = n0;
		input.target1 = n2;
		input.target2 = n6;

		return input;
	}

}