/* 
5.6 Conversion: 
	Write a function to determine the number of bits you would need to flip to
	convert integer A to integer B.
	EXAMPLE
	Input: 29 (or: 11101), 15 (or: 01111)
	Output: 2 
*/


import java.io.*;
import java.util.*;

public class Solution06 {

	/* 
	Solution 
		Count how many bits are different between two numbers.
		a ^ b, then count the number of bit 1.
	Assumptions:
		These two numbers have same length of bits
	Time complexity: O(b), b is the length of bit sequence
	Space complexity: O(1)
	*/
	private static int Conversion(int a, int b) {
		int count = 0;
		int merge = a ^ b;
		for (int i = 0; i < 32; i++) {
			if ((merge & (1 << i)) != 0) {
				count++;
			}
		}
		return count;
	}


	public static void main(String[] args) {
		System.out.println("----------- 5.6 Conversion -----------");
		int a = 29;
		int b = 15;
		int res = Conversion(a, b);
		System.out.println(res);
	}



}