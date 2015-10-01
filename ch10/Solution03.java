/* 
10.3 Search in Rotated Array
	 Given a sorted array of n integers that has been rotated an unknown number 
	 of times, write code to find an element in the array. You may assume that
	 the array was originally sorted in increasing order.

	 EXAMPLE
	 Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
	 Output: 8 (the index of 5 in the array)
*/

import java.io.*;
import java.util.*;

public class Solution03 {
	/* 
	Solution 1
		Use binary search. At least one half of the array is in order. For example, We need to compare 
		the middle value and the start value, if start < middle, then the first half is in order. And if
		the target value is in the this ordered part, then end = mid-1. If not, the target must be in the
		second half.
	Assumptions:
		There are no duplicate elements.
		If the target element exists, return its index; if not, return -1;
	Time complexity: O(lgn)
	Space complexity: O(1) 
	*/
	static private int searchinRotatedArray1(int[] ary, int start, int end, int target) {//‘length’ is the number of elements in A
		if (start > end) //cannot find
			return -1;
		
		int mid = start + (end - start) / 2;

		//find target
		if (ary[mid] == target) {
			return mid;
		}

		if (ary[start] < ary[mid]) { //left part is in order
			if (target >= ary[start] && target < ary[mid]) {//target in left ordered half
				return searchinRotatedArray1(ary, start, mid-1, target);
			} else { //in right unordered part
				return searchinRotatedArray1(ary, mid+1, end, target);
			}
		} else { //right part is in order
			if (target > ary[mid] && target <= ary[end]) {//target in right ordered half
				return searchinRotatedArray1(ary, mid+1, end, target);
			} else {	//target in the left unordered half
				return searchinRotatedArray1(ary, start, mid-1, target);
			}
		}
	}


	/* 
	Solution 2 (Has duplicate elements)
		Use binary search as well. But need to check more about duplicates. If mid == ary, 
		then cannot decide which part should 
	Assumptions:
		There are duplicate elements.
		If the target element exists, return its index; if not, return -1;
	Time complexity: O(lgn) if almost no duplicates; O(n) if nearly all are duplicates
	Space complexity: O(1) 
	*/
	static private int searchinRotatedArray2(int[] ary, int start, int end, int target) {//‘length’ is the number of elements in A
		if (start > end) //cannot find
			return -1;
		
		int mid = start + (end - start) / 2;

		//find target
		if (ary[mid] == target) {
			return mid;
		}

		if (ary[start] < ary[mid]) { //left part is in order
			if (target >= ary[start] && target < ary[mid]) {//target in left ordered half
				return searchinRotatedArray2(ary, start, mid-1, target);
			} else { //in right unordered part
				return searchinRotatedArray2(ary, mid+1, end, target);
			}
		} else if (ary[start] > ary[mid]){ //right part is in order
			if (target > ary[mid] && target <= ary[end]) {//target in right ordered half
				return searchinRotatedArray2(ary, mid+1, end, target);
			} else {	//target in the left unordered half
				return searchinRotatedArray2(ary, start, mid-1, target);
			}
		} else { //ary[strat] == ary[mid], cannot decide which part is ordered, need to try both parts
			int res = searchinRotatedArray2(ary, start, mid-1, target);
			if (res == -1) {
				res = searchinRotatedArray2(ary, mid+1, end, target);
			}
			return res;
		}
	}


	public static void main(String[] args) {
		System.out.println("----------- 10.3 Search in Rotated Array -----------");

		int[] ary1 = {2, 3, 4, 5, 6, 7, 8, 9, 0, 1};
		int[] ary2 = {2, 2, 2, 2, 5, 2, 2};

		int res1 = searchinRotatedArray1(ary1, 0, ary1.length-1, 5);
		int res2 = searchinRotatedArray2(ary2, 0, ary2.length-1, 5);

		System.out.println(res1);
		System.out.println(res2);
	}

}