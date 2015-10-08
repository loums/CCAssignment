/* 
5.2  Binary to String:
	Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a 
	double, print the binary representation. If the number cannot be represented 
	accurately in binary with at most 32 characters, print "ERROR".
*/


import java.io.*;
import java.util.*;

public class Solution02 {

	/* 
	Solution 
		Convert decimal float number to binary format.
		At ith step, multiply the number by 2, get the integer part as ith bit 
		(starts from point and from left to right);
		Then set integer part to 0, leaving the decimal part to do the same 
		procedure again until the decimal part to be 0.
	Assumption:
		The given number is in the range (0, 1).
	Time complexity: O(b), b is the number of bits

	*/
	private static String BinarytoString(double num) {
		StringBuilder sb = new StringBuilder("0.");

		for (int i = 0; i < 32 && num != 0; i++) {
			num *= 2;
			if (num >= 1) {
				sb.append(1);
				num -= 1;
			} else {
				sb.append(0);
			}
		}

		return sb.toString();
	}



	public static void main(String[] args) {
		double num = 0.72;
		String res = BinarytoString(num);

		System.out.println("----------- 5.2  Binary to String -----------");
		System.out.println(res);
	}



}