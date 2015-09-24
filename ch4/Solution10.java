 /* 
4.10 Check Subtree:
	T1 and T2 are two very large binary trees, with T1 much bigger than T2. 
	Create an algorithm to determine if T2 is a subtree of T1.
	A tree T2 is a subtree of T1 if there exists a node n in T1 such that
	the subtree of n is identical to T2. That is, if you cut off at node n,
	the two trees would be identical.
*/

import java.io.*;
import java.util.*;

class CInput {
	TreeNode t1;
	TreeNode t2;
}

public class Solution10 {
	/*
	Solution 1
		First, inorder and preorder (or inorder and postorder) can identify a tree
		uniquely.

		Second, a sub tree's inorder or preorder sequence will be a continue sub string, 
		it won't be seperate. Since either for inorder or post order, the subtree will 
		be traversed as a unit, then root or right subtree. So they will be continue.

		So if a tree is another tree's subtree. Its inorder sequence will be the larger
		tree's sub string. The same for the preorder sequence. 

	Time complexity: O(n) for traversal 4 times, O(n) for substring (KMP)
	Space Complexity: O(n) for storing traverse sequence.
	*/

	private static boolean checkSubtree(TreeNode t1, TreeNode t2) {
		StringBuilder sb = new StringBuilder();

		//inorder
		inorder(t1, sb);
		String inorder1 = sb.toString();
		sb.setLength(0);

		inorder(t2, sb);
		String inorder2 = sb.toString();
		sb.setLength(0);

		if ( inorder1.indexOf(inorder2) == -1 && inorder2.indexOf(inorder1) == -1) {
			return false;
		}

		//preorder
		preorder(t1, sb);
		String preorder1 = sb.toString();
		sb.setLength(0);

		preorder(t2, sb);
		String preorder2 = sb.toString();

		if ( preorder1.indexOf(preorder2) == -1 && preorder2.indexOf(preorder1) == -1) {
			return false;
		}

		return true;
	}

	private static void inorder (TreeNode root, StringBuilder sb){
		if (root == null)
			return;

		inorder(root.left, sb);
		sb.append(root.value);
		inorder(root.right, sb);
	}

	private static void preorder (TreeNode root, StringBuilder sb){
		if (root == null)
			return;

		sb.append(root.value);
		preorder(root.left, sb);
		preorder(root.right, sb);
	}


	public static void main(String[] args) {
		CInput input = generateTree();
		TreeNode t1 = input.t1;
		TreeNode t2 = input.t2;

		boolean res = checkSubtree(t1, t2);
		System.out.println("----------- 4.10 Check Subtree -----------");
		System.out.println("Result: ");
		System.out.println(res);
	}

	private static CInput generateTree() {
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

		input.t1 = n0;
		input.t2 = n1;

		return input;
	}

}