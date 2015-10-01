/* 
10.4 Sorted Search, No Size
	 You are given an array-like data structure Listy which lacks a size method. 
	 It does, however, have an elementAt(i) method that returns the element at 
	 index i in O(1) time. If i is beyond the bounds of the data structure, it 
	 returns -1. (For this reason, the data structure only supports positive 
	 integers.) Given a Listy which contains sorted, positive integers, find the 
	 index at which an element x occurs. If x occurs multiple time, you may 
	 return any index.
*/


import java.io.*;
import java.util.*;

class Listy {
	private int[] ary;

	public Listy (int[] b) {
		this.ary = b;
	}

	public int elementAt(int index) {
		if (ary == null || index >= ary.length) {
			return -1;
		} else {
			return ary[index];
		}
	}
}

public class Solution04 {
	/* 
	Solution 1
		First need to find the array's length. start from i = 1 and increase the length
		i expnentionally. Once the elementAt(i) returns -1, that means the length is 
		between i/2 to i. 

		We don't even need to find this exact length. Instead, we use this approximate length i, 
		to start binary search for target x. Additionally, when elementAt(mid) == -1, we'll
		need go to left part. 

	Assumptions:
		Array only contains non negative integers.

	Time complexity: O(lgn)
	Space complexity: O(1)
	*/


	private static int SortedSearchNoSize(Listy listy, int target) {
		if (listy == null || listy.elementAt(0) == -1)
			return -1;

		int end = getApproximateLength(listy);
		int start = 0;

		while (start <= end) {
			int mid = start + (end - start) / 2;
			int midValue = listy.elementAt(mid);

			if (midValue == target) {
				return mid;
			} else if (target < midValue || midValue == -1) {//beyond the array bound, target value must be in the left part
				end = mid - 1;
			} else { //target > midValue, must be in the right part
				start = mid + 1;
			}
		}

		return -1;
	}

	private static int getApproximateLength(Listy listy) {
		if (listy == null || listy.elementAt(0) == -1) { //corner cases: empty array
			return -1;
		}

		int i = 1;//index 0 must exist, start from i = 1
		while (true) {
			if (listy.elementAt(i) == -1) {
				break;
			}
			i *= 2;
		}

		return i;
	}


	public static void main(String[] args) {
		System.out.println("----------- 10.4 Sorted Search, No Size -----------");

		int[] ary = {1, 2, 3, 4, 5};
		Listy listy = new Listy(ary);

		int index = SortedSearchNoSize(listy, 4);
		
		System.out.println(index);
	}

}