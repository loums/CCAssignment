/* 
5.4 Next Number:
	Given a positive integer, print the next smallest and the next largest number
	that have the same number of 1 bits in their binary representation. 
*/


import java.io.*;
import java.util.*;

class CResult {
	public int pre;
	public int next;
}

class CIndex {
	int left = -1;
	int right = -1;
}

public class Solution04 {

	/* 
	Solution 1
	
	For getting the next number:
		Find the first block (from right to left) of the continue ones.
		a). if the first block size is 1, just need to move this bit
		one step left.
			0001111100100
			0001111101000 (next) 
		b). if the first block size is larger than 1. Move its leftmost 
		bit one of this block step left. Then move the rest of ones in 
		this block to the rightmost positions.
			0001110011100
			0001110100011 (next)
		

	For getting previous number:
		Find the first and second block of ones. 
		a). If the first block is not at the rightmost. Then move the rightmost bit
		of one in the first block, one step right.
			0001110011100
			0001110011010 (pre)
		b). If the first block is at the rightmost, then it's the the reverse step 
		of getting next. Move the rightmost one in second block one step right 
		and merge the first block of ones to exactly rightof it. 
			0001110000111
			0001101111000 (pre)	

	Assumptions:
		All zeros and all ones return -1;
	
	Corner cases:
		the first block of ones is size one. For pre, same steps; for next, just need 
		to move that bit one step left. 
			0001110010
			0001100001 (pre, same procedure)
			0001110100 (next)

		if all the ones are already in the leftmost, then it's the largest, it 
		doesn't have next one. Similarly, of all the ones are already in the leftmost, 
		it's the smallest, it doesn't have previous one.
			1110000 (no next)
			0001111 (no pre)

		if there is only one block of ones, then for getting the next one, same steps;
		for getting the previous one, just need to move the rightmost one, one step right.
			0011100 
		  	0011010 (pre)
		  	0100011 (next)

	Time complexity: 
	Space complexity:
	*/

	private static int integerLength = 32;

	private static CResult nextNumber(int num) {
		CResult res = new CResult();

		if (num == -1 || num == 0) {
			return res;
		}

		res.pre = getPreNum(num);
		res.next = getNextNum(num);

		return res;
	}

	private static int getPreNum(int num) {

		//find the first block and sencond block
		CIndex firstBlock = getContinueOnes(num, 0);
		CIndex secondBlock = getContinueOnes(num, firstBlock.left + 1);

		//corner case: only one block of ones and already the smallest, no pre
		if (secondBlock.right == -1 && firstBlock.right == 0) {
			return -1;
		}

		//case a). first block not at the rightmost
		//just need to move the right most bit of 1, one step right
		if (firstBlock.right != 0) {
			int mask = ~(1 << firstBlock.right);
			num &= mask;

			int mask2 = 1 << (firstBlock.right-1);
			num |= mask2;

			return num;
		}

		//case b). first block is at the rightmost
		//Move the rightmost one in second block one step right and merge 
		//the first block of ones to exactly right of it. 

		//clear the right of pos to all zeros
		int pos = secondBlock.right;
		int mask3 = ~((1 << (pos + 1)) - 1);
		num &= mask3;

		//set the bits next to pos to all ones
		int size = firstBlock.left - firstBlock.right + 1;
		int tmp = (1 << (size+1)) - 1;
		int mask4 = tmp << (secondBlock.right - size - 1); //note the errors here
		num |= mask4;

		return num;
	}

	private static int getNextNum(int num) {
		//find the first block of ones
		CIndex firstBlock = getContinueOnes(num, 0);

		//corner case: already the largest, doesn't have next
		if (firstBlock.left == integerLength-1) {
			return -1;
		}

		int size = firstBlock.left - firstBlock.right + 1;
		
		//move the leftmost bit one step left
		int pos = firstBlock.left + 1;
		num |= (1 << pos);

		//case a). only contains one '1'
		//just need to move this '1' one step left
		if (size == 1) {
			return num;
		}

		//clear the right of pos
		int mask = ~((1 << pos) - 1);
		num &= mask;


		//case b). more than one ones
		//move the rest of ones to the rightmost of num
		//set the size-1 rightmost bits to ones.
		int mask2 = (1 << (size - 1)) - 1;
		num |= mask2;

		return num;
	}

	//get conintue ones, starting from index
	private static CIndex getContinueOnes(int num, int index) {
		CIndex res = new CIndex();

		for (; index < integerLength ; index++) {
			if ((num & (1 << index)) != 0) {
				if (res.right == -1) {
					res.right = index;
				}
			} else {//this bit is zero, record the index before it
				if (res.right != -1 && res.left == -1) {
					res.left = index - 1;
				}
			}
		}

		return res;
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
		System.out.println("----------- 5.4 Next Number -----------");

		String s = "110011100";
		int num = binaryToDecimal(s);
		System.out.println("Original: " + s);

		CResult res = nextNumber(num);
		String preStr = decimalToBinary(res.pre);
		String nextStr = decimalToBinary(res.next);
		System.out.println("Previous: " + preStr);
		System.out.println("Next:     " + nextStr);
	}

}