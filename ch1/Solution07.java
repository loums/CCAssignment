/*
1.7 Rotate Matrix:
	Given an image represented by an N*N matrix, where each pixel in the image 
	is 4 bytes, write a method to rotate the image by 90 degrees. Can you do 
	this in place?
*/


import java.io.*;
import java.util.*;

public class Solution07 {
	/*
	Solution 1
		each time rotate mapping elements in four quadrants.
		Only need to loop thourgh 1/4 of the up left part of the matrix
	Assumption:
		rotate the image clockwise by 90 degree
	Time complexity: O(n^2)
	Space Complexity: O(1)
	*/
	static private int[][] RotateMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return matrix;

		int len = matrix.length;
		int m, n;
		if (len % 2 == 0) {//if even, just loop through 1/4 of the up left part
			m = len / 2;
			n = len / 2;
		} else {//if odd, then rotate one more col but one less row of the up left part
			m = (len + 1) / 2;
			n = m - 1;
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[len-j-1][i];
				matrix[len-j-1][i] = matrix[len-i-1][len-j-1];
				matrix[len-i-1][len-j-1] = matrix[j][len-i-1];
				matrix[j][len-i-1] = tmp;
			}
		}

		return matrix;
	}
	
	static public void main(String[] args) {
		//Input
		int[][] m = input();

		//Solutions
		int[][] res = RotateMatrix(m);

		//Output
		output(res);
	}

	//input
	static private int[][] input(){
		int[][] m = null;
		System.out.println("/**** 1.7 Rotate Matrix ****/");
		System.out.println("Please input N value and a N*N matrix: ");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String num = br.readLine();
			int n = Integer.parseInt(num);
			m = new int[n][n];

			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				String[] nums = s.split(" ");
				for (int j = 0; j < n; j++) {
					m[i][j] = Integer.parseInt(nums[j]);
				}
			}

		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
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