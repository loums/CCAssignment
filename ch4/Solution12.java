 /* 
4.12 Paths with Sum:
	You are given a binary tree in which each node contains an integer value
	(which might be positive or negative). Design an glgorithm to count the
	number of paths that sum to a given value. The path does not need to start 
	or end at the root or a leaf, but it must go downwards (traveling only from
	parent nodes to child nodes)
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

public class Solution12 {
	/*
	Solution
		DFS traverse the tree. Record the cumulative sum on the way, and store
		it in hashmap. At each new node, if (cum sum - target sum) exist in the
		hashmap, then the according number will be the newly added paths number. 

	Time complexity: O(n)
	Space Complexity: O(n)
	*/

	private static int pathswithSum (TreeNode root, int target) {
		if (root == null) return 0;
		Map<Integer, Integer> hash = new HashMap<Integer, Integer>();

		updateHashMap(hash, 0, 1);//Note here

		return pathswithSumHelper(root, target, 0, hash);
	}

	private static int pathswithSumHelper (TreeNode root, int target, int cum, Map<Integer, Integer> hash) {
		if (root == null) {
			return 0;
		}

		cum += root.value;
		updateHashMap(hash, cum, 1);//increment

		//find the number of target paths ends at this node
		int diff = cum - target;
		int paths = hash.containsKey(diff) ? hash.get(diff) : 0;

		paths += pathswithSumHelper(root.left, target, cum, hash);
		paths += pathswithSumHelper(root.right, target, cum, hash);

		updateHashMap(hash, cum, -1);
		return paths;
	}

	private static void updateHashMap(Map<Integer, Integer> hash, int key, int value) {
		if (hash.containsKey(key)) {
			hash.put(key, hash.get(key) + value);
		} else {
			hash.put(key, value);
		}
	}


	public static void main(String[] args) {
		TreeNode root = generateTree();

		int res = pathswithSum(root, 7);

		System.out.println("----------- 4.12 Paths with Sum -----------");
		System.out.println("Result (should be 4): ");
		System.out.println(res);
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
		n1.right = n6;
		n2.left = n5;
		n2.right = n4;

		return n0;
	}

}