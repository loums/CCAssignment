/* 
10.1 Sorted Merge
	 You are given two sorted arrays, A and B, where A has a large enough buffer 
	 at the end to hold B. Write a method to merge B into A in sorted order.
*/


import java.io.*;
import java.util.*;

public class Solution01 {

	/* 
	Solution 
		Calcuate the total length of the result. And start from two array's tail
		each time move the larger one into the target place.
	Assumptions:
		A is larger than B. And have enough space to expend array.
	Time complexity: O(n)
	Space complexity: O(n) for two arrays
	*/
	static private int[] sortedMerge(int[] A, int length, int[] B) {//'length' is the number of elements in A
		if (A == null || B == null || A.length == 0) { //assume a is longer than b
			return null;
		}
		
		int total = length + B.length - 1;
		int len1 = length - 1;
		int len2 = B.length - 1;
		int mainLen = total;
		for (; total >= 0 && len1 >= 0 && len2 >= 0; total--) {
			A[total] = A[len1] >= B[len2] ? A[len1--] : B[len2--];
		}

		//the smallest values remains in b array
		//if the smallest values is in a (the larger target array), then keep them in place
		for ( ; len2 >= 0 && total >= 0; len2--, total--) {
			A[total] = B[len2];
		}

		return A;
	}




	public static void main(String[] args) {
		System.out.println("----------- 10.1 Sorted Merge -----------");

		int[] A = new int[20]; 
		int[] B = new int[5];

		for (int i = 0; i < 10; i++) {
			A[i] = i * 2;
		}
		for (int i = 1; i < 5; i++) {
			B[i] = i + 1;
		}

		sortedMerge(A, 10, B);

		for (int i = 0; i < 15; i++) {
			System.out.print(A[i]);
			System.out.print(" ");
		}
		System.out.println();
	}



}