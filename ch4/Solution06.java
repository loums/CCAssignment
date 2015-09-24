/* 
4.6 Successor:
	Write an algorithm to find the "next" node (i.e., in-order successor) of a
	given node in a binary search tree. You may assume that each node has a link
	to its parent.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;

	TreeNode (int value) {
		this.value = value;
	}
}

public class Solution06 {
	/*
	Solution 1
		If the node has right child, then the right subtree's left most child will 
		be the successor;
		if not (this node is the last child in in-order traversal, then need to find
		this subtree's parent node), go along its parents,  
			1). if meet one parent's rihgt child is not current subtree : the parent 
				is the successor
			2). if goes back to root node, then no successor, this node is the last one.

	Time complexity: O(1) if has right child, O(n) to find sub tree's parent node
	Space Complexity: O(n) for stroing tree, and extra pointers to parent
	*/
	static private TreeNode Successor(TreeNode node) {
		if (node == null)
			return node;

		if (node.right != null) {
			//find its right sub tree's left most
			TreeNode cur = node.right;
			while (cur.left != null) {
				cur = cur.left;
			}
			return cur;
		}

		TreeNode subtree = node;
		TreeNode targetParent = node.parent;
		while (targetParent != null && targetParent.right == subtree) {
			//the parent's right children is not the subtree
			//means this sub tree is left part, so that's the 
			//parent we want to find
			subtree = targetParent;
			targetParent = targetParent.parent;
		}

		return targetParent;//if null, the right most node

	}


	static public void main(String[] args) {

		TreeNode target = generateTree();
		TreeNode res = Successor(target);

		System.out.println("----------- 4.6 Successor -----------");
		System.out.println("Result: ");
		if (res == null) {
			System.out.println("null");
		} else {
			System.out.println(res.value);
		}

	}

	static private TreeNode generateTree() {

		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);


		n0.left = n1;
		n0.right = n2;
		n0.parent = null;

		n1.left = n3;
		n1.right = n4;
		n1.parent = n0;

		n2.parent = n0;
		n3.parent = n1;
		n4.parent = n1;

		return n2;
	}
}