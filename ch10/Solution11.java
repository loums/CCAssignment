/* 
10.11 Peaks and Valleys
	  In an array of integers, a "peak" is an element which is greater than or 
	  equal to the adjacent integers and a "valley" is an element which is less
	  than or equal to the adjacent integers. For example, in the array {5, 8, 
	  6, 2, 3, 4, 6}, {8, 6} are peaks and {5, 2} are valleys. Given an array 
	  of integers, sort the array into an alternating sequence of peaks and 
	  valleys.

	  EXAMPLE
	  Input: {5, 3, 1, 2, 3}
	  Output: {5, 1, 3, 2, 3}
*/


import java.io.*;
import java.util.*;

public class Solution11 {
	/* 
	Solution 1
		Make sure peeks at the right place, then valleys will be at the right place.

		Sort the array, then consider each pair, swap the greater one at the first
		place. 

	Assumptions:
		No duplicates. 

	Time complexity: O(nlgn) for sorting and O(n) for swapping

	Space complexity: O(1)
	*/
	private static void PeaksAndValleys1(int[] ary) {
		if (ary == null || ary.length <= 1) {
			return;
		}

		Arrays.sort(ary);

		for (int i = 1 ; i < ary.length; i += 2) {
			if (ary[i] > ary[i-1]) {
				int tmp = ary[i];
				ary[i] = ary[i-1];
				ary[i-1] = tmp;
			}
		}
	}


	/* 
	Solution 2
		Don't need to sort array. 
		Start from the second element, compare the two adjacent values together, find the biggest
		value among the three of them. Make the biggest one in the middle. Then jump index two steps,
		do the same thing iteratively.
		
		So each time, swapping the larger one in the middle, make it as peek. Appently, it won't affect
		the elements on its right side. Also, since the swapping will only swap a smaller to the left,
		so it won't affect the left part that already sorted. 

	Assumptions:
		No duplicates. 

	Time complexity: O(n) for swapping

	Space complexity: O(1)
	*/
	private static void PeaksAndValleys2(int[] ary) {
		if (ary == null || ary.length <= 1) {
			return;
		}

		for (int i = 1 ; i < ary.length; i += 2) {
			int maxIndex = getMax(ary, i-1, i, i+1);
			if (maxIndex == -1)
				return;

			if (i != maxIndex) {
				int tmp = ary[i];
				ary[i] = ary[maxIndex];
				ary[maxIndex] = tmp;
			}
		}
	}

	private static int getMax(int[] ary, int i1, int i2, int i3) {
		if (i1 > ary.length && i2 > ary.length && i3 > ary.length)
			return -1;

		int a = i1 > ary.length ? Integer.MIN_VALUE : ary[i1];
		int b = i2 > ary.length ? Integer.MIN_VALUE : ary[i2];
		int c = i3 > ary.length ? Integer.MIN_VALUE : ary[i3];

		int max = Math.max(Math.max(a, b), c);

		if (max == a) {
			return i1;
		} else if (max == b){
			return i2;
		} else {
			return i3;
		}
	}

	

	public static void main(String[] args) {
		System.out.println("----------- 10.11 Peaks and Valleys -----------");

		int[] ary = {4, 5, 2, 1, 3};

		System.out.println("Before sorting: ");
		printArray(ary);

		PeaksAndValleys2(ary);
		System.out.println("After sorting: ");
		printArray(ary);
		
	}

	public static void printArray(int[] ary) {
		for (int i = 0; i < ary.length; i++) {
			System.out.print(ary[i]);
			System.out.print(" ");
		}
		System.out.println();
	}

}