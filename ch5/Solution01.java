/* 
5.1 Insertion:
	You are given two 32-bit numbers, N and M, and two bit positions, i and j. 
	Write a method to insert M into N such that M starts at bit j and ends at
	bit i. You can assume that the bits j through i have enough space to all
	of M. That is, if M = 10011, you can assume that there are at least 5 bits
	between j and i. You would not, for example, have j = 3 and i = 2, because 
	M could not fully fit between bit 3 and bit 2.
	EXAMPLE
	Input:  N = 10000000000, M = 10011, i = 2, j = 6
	Output: N = 10001001100
*/


import java.io.*;
import java.util.*;

public class Solution01 {

	/* 
	Solution 
		First clear the bits from i to j. 
		Then merge M and N.
	Assumptions:
		M and N are 32-bit numbers, and int is 4 byte, 32 bits as well. So that
		we can use antoher int mask to help.
	Time complexity: O(b), b is the number of bits
	Space complexity: O(b)
	*/
	private static int Insertion(int m, int n, int i, int j) {
		//create mask
		int ones = ~0;//all ones
		int leftPart = ones << (j+1);//i and j start from 0, and start from right. 11110000000
		int rightPart = (1 << i) - 1;//set the bits after i to 1. 0000000011
		int mask = leftPart | rightPart;

		//clear M
		m &= mask;

		//shift N
		n = n << i;

		//merge M and N
		int res = m | n;
		return res;
	}

	private static String decimalToBinary(int num) {
		//start from the first 1
		StringBuilder sb = new StringBuilder();
		int remainder = num;
		while (num != 0) {
			remainder = num % 2;
			num = num / 2;
			sb.append(remainder);
		}
		sb.reverse();//reverse

		return sb.toString();
	}

	private static int binaryToDecimal(String s) {
		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			int digit = s.charAt(i) - '0';
			num = num * 2 + digit;
		}
		return num;
	}


	public static void main(String[] args) {
		String M = "10000000000";
		String N = "10011";

		int m = binaryToDecimal(M);
		int n = binaryToDecimal(N);

		int num = Insertion(m, n, 2, 6);
		String res = decimalToBinary(num);

		System.out.println("----------- 5.1 Insertion -----------");
		System.out.println("M = " + M);
		System.out.println("N = " + N);
		System.out.println("R = " + res);
	}

}