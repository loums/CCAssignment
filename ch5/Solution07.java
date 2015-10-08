/* 
5.7 Pairwise Swap: 
	Wtire a program to swap odd and even bits in an integer with as few instructions
	as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and
	so on). 
*/


import java.io.*;
import java.util.*;

public class Solution07 {

	/* 
	Solution 
		Use two masks to seperate odd bits and even bits. Then shift them serperatly
		and merge together.
		
	Time complexity: O(1), if shift time complexity is O(1)
	Space complexity: O(1)
	*/
	private static int pairwiseSwap(int num) {
		int oddBits = num & 0x55555555;//0x5 = 0101
		int evenBits = num & 0xaaaaaaaa;//0xa = 1010

		return ((oddBits << 1) | (evenBits >>> 1));
	}

	private static String decimalToBinary(int num) {
		StringBuilder sb = new StringBuilder();
		int remainder = num;
		while(num != 0) {
			remainder = num % 2;
			num /= 2;
			sb.append(remainder);
		}

		return sb.reverse().toString();
	}

	private static int binaryToDecimal(String s) {
		int num = 0;

		for (int i = 0; i < s.length() ;i++) {
			int digit = s.charAt(i) - '0';
			num *= 2;
			num += digit;
		}

		return num;
	}

	public static void main(String[] args) {
		System.out.println("----------- 5.7 Pairwise Swap -----------");
		int num = 52;

		int res = pairwiseSwap(num);
		System.out.println(decimalToBinary(num));
		System.out.println(decimalToBinary(res));
	}



}