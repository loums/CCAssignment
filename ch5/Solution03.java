/* 
5.3 Flip Bit to Win:
	You have an integer and you can flip exactly one bit from a 0 to a 1. Write 
	code to find the length of the longest sequence of 1s you could create.
	EXAMPLE
	Input: 1775 (or: 11011101111)  
	Output: 8
*/


import java.io.*;
import java.util.*;

class Result {
	public boolean moreThanOneZeros = false;
	public int continueOnes = 0;
	public int nextIndex = -1;
}

public class Solution03 {

	/* 
	Solution 1
		Brute force way is to flip each zero bit, and find current longest sequence of ones.
		Don't need to flip zero bits and flip back, when doing the counting just need to 
		consider it as value one and just skip this index. 
	Assumptions:
		If no zeros, then return the initial longest sequence of ones.
	Time complexity: O(b^2), b is the length of integer
	Space complexity: O(1)
	*/

	private static int integerLength = 32;

	private static int flipBitToWin1(int num) {
		if (num == -1) //all ones, no zeros
			return -1;

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < integerLength; i++) {
			if ((num & (1 << i)) == 0) {//flip zeros
				max = Math.max(max, getLongestSequence(num, i));
			}
		}
		
		return max;
	}

	private static int getLongestSequence(int num, int ignoreIndex) {
		int max = Integer.MIN_VALUE;
		int count = 0;

		for (int i = 0; i < integerLength; i++) {
			if (i == ignoreIndex) {
				count++;
			} else if ((num & (1 << i)) != 0) { //index is the bit from left to right
				count++;
			} else {
				max = Math.max(max, count);
				count = 0;
			}
		}
		max = Math.max(max, count);

		return max;
	}

	/* 
	Solution 2
		Use two variables pre and cur to record the previous and current continue 
		sequence of ones. There will be only one zero between pre and cur, if meet 
		more than one zero, pre will be 0. So each time update the max with (pre +
		1 + cur).
	Assumptions:
		If no zeros, then return the initial longest sequence of ones.
	Corner cases:
		all zeros or all ones.
	Time complexity: O(b), b is the length of integer
	Space complexity: O(1)
	*/
	private static int flipBitToWin2(int num) {
		int max = Integer.MIN_VALUE;
		int pre = 0;
		int cur = 0;

		int index = 0;
		while (index < integerLength) {
			//count the continues length
			Result res = getContinueOnes(num, index);
			cur = res.continueOnes;

			//if encounter more than two zeros, set pre to zero
			if (res.moreThanOneZeros) {
				pre = 0;
			}

			//update max
			max = Math.max(max, (pre + 1 + cur));

			//update index and pre
			index = res.nextIndex;
			pre = cur;
		}

		return max;
	}

	private static Result getContinueOnes(int num, int index) {
		Result res = new Result();
		int oneCount = 0;
		int zeroCount = 0;

		//count head zeros
		while (index < integerLength && (num & (1 << index)) == 0) {
			zeroCount++;
			index++;
		}
		if (zeroCount > 1) {
			res.moreThanOneZeros = true;
		}

		//count ones
		for (; index < integerLength; index++) {
			if ((num & (1 << index)) == 0) {
				break;
			}
			oneCount++;
		}

		//result
		res.nextIndex = index;
		res.continueOnes = oneCount;

		return res;
	}



	private static String decimalToBinary(int num) {
		StringBuilder sb = new StringBuilder();
		int remainder = num;//incase of 0
		while (num != 0) {
			remainder = num % 2;
			num = num / 2;
			sb.append(remainder);
		}

		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		
		int num = 1775;
		int res1 = flipBitToWin1(num);
		int res2 = flipBitToWin2(num);

		System.out.println("----------- 5.3 Flip Bit to Win -----------");
		System.out.println(res1);
		System.out.println(res2);
	}



}