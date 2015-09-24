/* 
4.3 List of Depths:
	Given a binary tree, design an algorithm which creates a linked list of all
	the nodes at each depth (e.g., if you have a tree with depth D, you'll have
	D linked lists).
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

public class Solution03 {
	/*
	Solution 1
		Use BFS to traversal tree. Record each level's nodes and link the nodes in
		the same level.
	Time complexity: O(n)
	Space Complexity: O(n) for storing tree
	*/
	private static List<List<TreeNode>> listofDepths(TreeNode root) {
		List<List<TreeNode>> res =  new ArrayList<List<TreeNode>>();
		if (root == null)
			return res;

		ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {

			int size = queue.size();//record the amount of nodes in this level
			List<TreeNode> list = new ArrayList<TreeNode>();

			for (int i = 0; i < size; i++) {
				//make linked list
				TreeNode cur = queue.poll();
				list.add(cur);

				//queue next level
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}

			res.add(list);
		}

		return res;
	}


	public static void main(String[] args) {
		TreeNode root = generateTree();
		List<List<TreeNode>> res = listofDepths(root);

		output(res);
	}

	private static TreeNode generateTree() {
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

		return n0;
	}

	private static void output(List<List<TreeNode>> res) {
		System.out.println("----------- 4.3 List of Depths -----------");
		System.out.println("Result: ");

		for (int i = 0; i < res.size(); i++) {
			List<TreeNode> list = res.get(i);
			for (int j = 0; j < list.size(); j++) {
				System.out.print(list.get(j).value);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}