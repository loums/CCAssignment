/*
1.3 URLify: 
	Write a method to replace all spaces in a string with '%20'. You may assume 
	that the string has sufficient space at the end to hold the additional 
	characters, and that you are given the "true" length of the string. 
	(Note: If implementing in Java, please use a character array so that you can
	perform this operation in place.)

	EXAMPLE
	Input: "Mr John Smith    ", 13
	Ouput: "Mr%20John%20Smith"
*/



import java.io.*;
import java.util.*;

public class Solution03 {
	/*
	Solution 1: 
		calcuate expected result length, then start moving from the last word
	Assumption: 
		Each space will be converted to '%20', even for continue spaces;
		the spaces in the header or tail will be trimed and ignored.
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	
	static private void URLify(char[] str, int trueLen) {
		if (str == null || str.length == 0 || trueLen == 0) {
			System.out.println("invalid input");
			return;
		}

		//get expected length
		int length = 0;
		for (int i = 0; i < trueLen; i++) {
			if (str[i] == ' ') {//space
				length += 3;//%20
			} else {	//normal characters
				length++;
			}
		}

		//moving words backwards 
		int j = length - 1;//expected string index
		for (int i = trueLen - 1; i >= 0 && j >= 0; i--) {
			if (str[i] == ' ') {
				replace(str, j);
				j -= 3;//update index j
			} else {
				str[j--] = str[i];
			}
		}
	}

	static private void replace(char[] str, int i) {
		if (i < str.length && i-2 >= 0) {
			str[i] = '0';
			str[i-1] = '2';
			str[i-2] = '%';
		}
	}

	static public void main(String[] args) {
		//Input
		char[] str = input();
		int trueLen = str.length;
		char[] strExpand = Arrays.copyOf(str, str.length * 3);//give enough space to expand
		
		//Solutions
		URLify(strExpand, trueLen);

		//Output
		output(strExpand);
	}

	//input
	static private char[] input(){
		char[] str = null;
		try {
			System.out.println("/**** 1.3 URLify ****/");
			System.out.println("Please input your texts (including blanks): ");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			str = s.trim().toCharArray();
		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return str;
	}

	//output
	static private void output(char[] str) {
		System.out.println(new String(str));
	}
}