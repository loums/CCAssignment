/*
1.6 String Compression:
	Implement a method to perform basic string compression using the counts of 
	repeated chracters. For example, the string aabcccccaaa would become 
	a2b1c5a3. If the "compressed" string would not bcome smaller than the original
	string, your method should return the original string. You can assume the 
	string has only uppercase and lowercase letters (a-z).
*/

import java.io.*;
import java.util.*;

public class Solution06 {
	/*
	Solution 1
	Assumption:
		input will only contains a/A to z/Z
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private String StringCompression(String s) {
		if (s == null || s.length() == 0)
			return s;

		StringBuilder sb = new StringBuilder();
		char pre = s.charAt(0);
		int count = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == pre) {
				count++;
			} else {
				sb.append(pre);
				sb.append(count);
				count = 1;
				pre = s.charAt(i);
			}
		}
		sb.append(pre);
		sb.append(count);

		return sb.length() < s.length() ? sb.toString() : s;
	}
	
	static public void main(String[] args) {
		//Input
		String s = input();

		//Solutions
		String res = StringCompression(s);

		//Output
		output(res);
	}

	//input
	static private String input() {
		System.out.println("/**** 1.6 String Compression ****/");
		System.out.println("Please input a string to compress: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	//output
	static private void output(String s) {
		System.out.println(s);
	}
}