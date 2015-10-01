/* 
10.10 Rank from Stream
	  Imagine you are reading in a stream of integeers. Periodically, you wish 
	  to be able to lok up the reank of a number x (the number of values less 
	  than or equal to x). Implement the data structures and algorithms to 
	  support these operations. That is, implement the method track(int x) which 
	  is called when each number is generated, and the method getRankOfNumber(
	  int x), which returns the number of values less than or equal to x (not 
	  including x itself).

	  EXAMPLE
	  Stream (in order of apperance): 5, 1, 4, 4, 5, 9, 7, 13, 3
	  getRankOfNumber(1) = 0
	  getRankOfNumber(1) = 1
	  getRankOfNumber(1) = 3
*/


import java.io.*;
import java.util.*;

/* 
Solution 1
	Build binary search tree. And each node records the number of nodes in its
	left sub tree.

	Insert: if new node goes to root's left, then root.leftSize++; if goes to
	its right, root.leftSize keep the same. 

	getRank: cumRank = 0; if target value goes to its left, then cumRank keep the 
	same; if target value goes to its right, then cumRank += root.leftSize + 1 (root);
	when target value = root.value, cumRank += root.leftSize, return cumRank.

Assumptions:
	The tree is not balanced binary search tree. So the worst case is n height.

Time complexity: O(lgn) for inserting, O(lgn) for getting rank, but O(n) for worst vase
Space complexity: O(n)
*/

class TreeNode {
	public int value;
	public int leftSize; //number of noded in its left sub tree
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int value) {
		this.value = value;
		this.leftSize = 0;
	}
}

class BST {
	TreeNode root;

	public BST(TreeNode root) {
		this.root = root;
	}

	public int getRank(int target) {
		int cumRank = 0;

		int res = getRankHelper(this.root, target, cumRank);

		return res;
	}

	public int getRankHelper(TreeNode curRoot, int target, int cumRank) {
		if (curRoot == null) {
			return -1;
		}

		if (curRoot.value == target) {
			cumRank += curRoot.leftSize;
			return cumRank;
		} else if (target < curRoot.value) {//go to left branch
			return getRankHelper(curRoot.left, target, cumRank);
		} else {//go to right branch
			cumRank += curRoot.leftSize + 1;
			return getRankHelper(curRoot.right, target, cumRank);
		}

	}

	public void track(int value) {//insert
		if (root == null) 
			return;

		insertHelper(this.root, value);
	}

	private void insertHelper(TreeNode curRoot, int value) {
		if (curRoot == null) {
			return;
		}

		if (value <= curRoot.value) {//goes to left sub tree
			if (curRoot.left == null) {
				TreeNode node = new TreeNode(value);
				curRoot.left = node;
			} else {
				insertHelper(curRoot.left, value);
			}

			curRoot.leftSize++;//new node inserts into curRoot.left, then left size+1

		} else { //goes to right subtree
			if (curRoot.right == null) {
				TreeNode node = new TreeNode(value);
				curRoot.right = node;
			} else {
				insertHelper(curRoot.right, value);
			}
		}	

	}

}

public class Solution10 {

	/*
					5
				  /   \	
				 2     7
			   /   \	
			  1     3
	*/
	public static void main(String[] args) {
		System.out.println("----------- 10.10 Rank from Stream -----------");
		int[] values = {5, 2, 7, 1, 3};

		TreeNode n5 = new TreeNode(5);
		BST bst = new BST(n5);

		for (int i = 1; i < values.length; i++) {
			bst.track(values[i]);
			printTree(n5);

			int rank = bst.getRank(values[i]);
			System.out.format("Rank of %d: %d", values[i], rank);
			System.out.println();
		}
		
	}


	//for testing purpose
	private static void printTree (TreeNode root) {
		ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);

		System.out.println("------ Print tree -----");
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
				System.out.print(cur.value);
				System.out.print(" ");
			}
			System.out.println();
		}

	}

}