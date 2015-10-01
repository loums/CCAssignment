/* 
10.5 Sparse Search
	 Given a sorted array of strings that is interspersed with empty strings, 
	 write a method to find the location of a given string.

	 EXAMPLE
	 Input: ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "",
	 		""}
	 Output: 4
*/

import java.io.*;
import java.util.*;

public class Solution05 {
	/* 
	Solution 1
		Use binary search. If mid is not empty, doing the normal binary search;
		if mid is empty, then set mid to the nearest non-empty place, then doing
		the normal binary search.

	Assumptions:
		
	Time complexity: O((m+n) * lgn)// lgn is normal binary search, m is the length of string, n is for seraching
					the nearest non-empty string.
	Space complexity: O(1)
	*/


	private static int SparseSearch(String[] ary, String target) {
		if (ary == null || ary.length == 0 || target.isEmpty())
			return -1;

		int start = 0;
		int end = ary.length;
		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (ary[mid].isEmpty()) {//if empty, find the nearest non-empty string
				int left = mid - 1;
				int right = mid + 1;

				while (true) {
					if (left < start && right > end) { //cannot find non-empty
						return -1;
					}
					if (left >= start && !ary[left].isEmpty()) {
						mid = left;
						break;
					}
					if (right <= end && !ary[right].isEmpty()) {
						mid = right;
						break;
					}
					left--;
					right++;
				}
			}

			int compare = target.compareTo(ary[mid]);
			if (compare == 0) {
				return mid;
			} else if (compare > 0) {//target > mid, right part
				start = mid + 1;
			} else {//compare < 0, target < mid, left part
				end = mid - 1;
			}	
		}

		return -1;
	}


	public static void main(String[] args) {
		System.out.println("----------- 10.5 Sparse Search -----------");

		String[] ary = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};

		int index = SparseSearch(ary, "ball");
		
		System.out.println(index);
	}

}