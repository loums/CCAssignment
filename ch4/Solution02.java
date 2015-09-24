/* 
4.2 Minimal Tree:
	Given a sorted (increasing order) array with unique integer elements, write
	an algorithm to create a binary search tree with minimal height.
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

public class Solution02 {
	/*
	Solution 1
		Get the middle element as root, and its left elements will be left sub tree, its
		right elements will be right sub tree. Do it recursively. 
	Time complexity: O(n) for traversal each node
	Space Complexity: O(n) for storing tree nodes
	*/
	private static TreeNode getMinTree(int[] ary, int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = start + (end - start + 1) / 2;
		//round up: can also be start + (end - start - 1) / 2 + 1, but when 
		//end = start like 0, we want to get mid = 0, but the second way 
		//will return 1. Since -1 / 2 = 0, Java's way for negative divide,
		//round to the larger value. 

		TreeNode node = new TreeNode(ary[mid]);

		node.left = getMinTree(ary, start, mid - 1);
		node.right = getMinTree(ary, mid + 1, end);

		return node;
	}


	public static void main(String[] args) {
		System.out.println((-100)%3);

		int[] ary = {1, 2, 3, 4, 5, 6};
		TreeNode root = getMinTree(ary, 0, 5);

		System.out.println("----------- 4.2 Minimal Tree -----------");
		System.out.println("Result: ");
		printTree(root);
	}

	public static void printTree(TreeNode root) {
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