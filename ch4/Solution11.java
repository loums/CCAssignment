 /* 
4.11 Random Node:
	You are implementing a binary tree class from scratch which, in addition to
	insert, find, and delete, has a method getRandomNode() which returns a random
	node from the tree. All nodes should be equally likely to be chosen. Design
	and implement an algorithm for getRandomNode, and explain how you would implement
	the rest of the methods.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	public int size = 1;//the number of nodes in this subtree

	TreeNode (int value, int size) {
		this.value = value;
		this.size = size;
	}
}

class BST {
	public TreeNode root;
	
	public TreeNode getRandomNode() {
		int n = root.size;
		Random random = new Random();
		int num = random.nextInt(n) + 1;//random returns [0, n), change to [1, n]
 		System.out.println(num);

		return getithNode(root, num);
	}

	private TreeNode getithNode(TreeNode root, int num) {
		int leftSize = root.left == null ? 0 : root.left.size;

		if (leftSize == num) {
			return root;
		} else if (leftSize > num) { //go to left sub tree
			return getithNode(root.left, num);
		} else { //go to right sub tree
			return getithNode(root.right, (num - leftSize - 1));//ignore the left part size and the root node
		}
	}

	public void insert (TreeNode a) {
		if (root == null) return;

		insertHelper(this.root, a);
	}

	public void insertHelper (TreeNode curRoot, TreeNode a) {
		if (a.value <= curRoot.value) {
			if (curRoot.left == null) {
				curRoot.left = a;
			} else {
				insertHelper(curRoot.left, a);
			}
		} else {
			if (curRoot.right == null) {
				curRoot.right = a;
			} else {
				insertHelper(curRoot.right, a);
			}
		}

		curRoot.size++;
	}

	public TreeNode find (int num) {
		TreeNode cur = root;

		while (cur != null) {
			if (num == cur.value) {
				return cur;
			} else if (num < cur.value){
				if (cur.left == null) {
					return null;
				} else {
					cur = cur.left;
				}
			} else {
				if (cur.right == null) {
					return null;
				} else {
					cur = cur.right;
				}
			}
		}

		return null;
	}


}

public class Solution11 {

	static public void main(String[] args) {
		BST bst = generateTree();

		testCase(bst);
	}

	private static void testCase (BST bst) {

		System.out.println("Insert node 6: ");
		TreeNode n6 = new TreeNode(6, 1);
		bst.insert(n6);
		printTree(bst.root);

		System.out.println("Get ramdon node: ");
		TreeNode random = bst.getRandomNode();
		System.out.println(random.value);

		System.out.println("Find target node 3: ");
		TreeNode target = bst.find(3);
		System.out.println(target.value);
	}

	/*
					5
				  /   \	
				 2     7
			   /   \	
			  1     3
	*/

	private static BST generateTree() {
		BST bst = new BST();

		TreeNode n1 = new TreeNode(1, 1);
		TreeNode n2 = new TreeNode(2, 3);
		TreeNode n3 = new TreeNode(3, 1);
		TreeNode n5 = new TreeNode(5, 5);
		TreeNode n7 = new TreeNode(7, 1);

		n5.left = n2;
		n5.right = n7;
		n2.left = n1;
		n2.right = n3;

		bst.root = n5;

		return bst;
	}

	private static void printTree(TreeNode root) {
		ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				System.out.print(cur.value);
				System.out.print(" ");
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			System.out.println();
		}
	}

}