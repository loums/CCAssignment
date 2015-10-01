/* 
10.9 Sorted Matrix Search
	 Given an M x N matrix in which each row and each column is sorted in 
	 ascending order, write a method to find an element.
*/

import java.io.*;
import java.util.*;

public class Solution09 {
	/* 
	Solution 1
		Start from the right corner, if the target is less than the corner value,
		then the target won't be in this column, skip this column; if the target 
		is larger than the corner value, then the target won't be in this row, skip
		this row. 

		Each time reduce the at least one row or one column's searching space.

	Assumptions:
		if find the element, return its indexs. If not, return (-1, -1).
		matrix may contain duplicates, return any of the positions.

	Time complexity: O(m+n). worst case: the target is at the left down corner,
		then need to delete all the rows and columns. 

	Space complexity: O(1)
	*/


	private static List<Integer> SortedMatrixSearch(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}

		List<Integer> res = new ArrayList<Integer>();
		int m = matrix.length;
		int n = matrix[0].length;
		int row = 0;
		int col = n-1;
		while(row < m && col >= 0) {
			if (matrix[row][col] == target) {
				res.add(row);
				res.add(col);
				return res;
			} else if (target < matrix[row][col]) { //not in this column
				col--;
			} else { //target > matrix[row][col], not in this row
				row++;
			}
		}

		return null;
	}

	

	public static void main(String[] args) {
		System.out.println("----------- 10.9 Sorted Matrix Search -----------");

		int[][] matrix = {{15, 20, 40, 58}, {20, 35, 80, 95}, {30, 55, 95, 105}, {40, 80, 100, 120}};

		List<Integer> indexs = SortedMatrixSearch(matrix, 40);
		
		if (indexs == null) {
			System.out.println("No target value");
		} else {
			System.out.println(indexs.get(0) + " " + indexs.get(1));
		}
		
	}

}