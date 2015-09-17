/*
1.8 Zero Matrix:
	Write an algorithm such that if an element in an M*N matrix is 0, its entire
	row and column are set to 0.
*/


import java.io.*;
import java.util.*;

public class Solution08 {
	/*
	Solution 1
		use first row and colmun to record if this row or colmun need to be all zeros,
		use two boolean values to record if the first row and first col need to be all zeros.
	Assumption:
		None.
	Time complexity: O(n^2)
	Space Complexity: O(1)
	*/
	static private int[][] ZeroMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return matrix;

		int m = matrix.length;
		int n = matrix[0].length;

		//to record if the first row and first col need to be all zeros.
		boolean row1 = false;
		boolean col1 = false; 
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0)
				col1 = true;
		}
		for (int j = 0; j < n; j++) {
			if (matrix[0][j] == 0)
				row1 = true;
		}

		//to record each row and col if need to be all zeros.
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		//set rows to zeros
		for (int i = 1; i < m; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		//set cols to zeros
		for (int j = 1; j < n; j++) {
			if (matrix[0][j] == 0) {
				for (int i = 0; i < m; i++) {
					matrix[i][j] = 0;
				}
			}
		}
		//set first row and col
		if (row1 == true) {
			for (int j = 0; j < n; j++) {
				matrix[0][j] = 0;
			}
		}
		if (col1 == true) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}

		return matrix;
	}


	static public void main(String[] args) {
		//Input
		int[][] m = input();

		//Solutions
		int[][] res = ZeroMatrix(m);

		//Output
		output(res);
	}

	//input
	static private int[][] input(){
		int[][] m = null;
		System.out.println("/**** 1.8 Zero Matrix ****/");
		System.out.println("Please input M and N value and a M*N matrix (include some values be 0): ");

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String num = br.readLine();
			String[] rowcol = num.split(" ");
			int row = Integer.parseInt(rowcol[0]);
			int col = Integer.parseInt(rowcol[1]);
			m = new int[row][col];

			for (int i = 0; i < row; i++) {
				String s = br.readLine();
				String[] nums = s.split(" ");
				for (int j = 0; j < col; j++) {
					m[i][j] = Integer.parseInt(nums[j]);
				}
			}

		} catch (IOException e) {
			System.out.println("input error: " + e.getMessage());
		}

		return m;
	}


	//output
	static private void output(int[][] m) {
		int n = m.length;

		System.out.println("Result: ");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(m[i][j]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
}