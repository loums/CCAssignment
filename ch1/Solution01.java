/* 
1.1 Is Unique: 
	Implement an algorithm to determine if a string has all unique characters.
	What if you cannot use additional data structures?

	Here we assume that "no additional data structures" means O(1) space 
	complexity.
*/

import java.io.*;
import java.util.*;

public class Solution01 {
	/*
	Solution 1
		Hashtable, using java hashset
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private boolean isUnique1(String s) {
		if (s == null || s.length() == 0)
			return false;

		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (set.contains(s.charAt(i)))
				return false;
			else
				set.add(s.charAt(i));
		}

		return true;
	}

	/*
	Solution 2
		//Bit vector
	Assumption: 
		only contains characters from 0 to 127 ascii 
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private boolean isUnique2(String s) {
		if (s == null || s.length() == 0)
			return false;

		int[] hash = new int[128];
		for (int i = 0; i < s.length(); i++) {
			if (hash[s.charAt(i)] != 0)
				return false;
			else
				hash[s.charAt(i)] = 1;
		}
		return true;
	}


	/*
	Solution 3
		sort and check if the continue characters are different
	Assumption:
		Assume the time complexity of sorting is O(nlgn)
	Time complexity: O(nlgn)
	Space Complexity: O(1)
	*/
	static private boolean isUnique3(String s) {
		if (s == null || s.length() == 0)
			return false;

		char[] str = s.toCharArray();
		Arrays.sort(str);
		for (int i = 1; i < str.length; i++) {
			if (str[i] == str[i-1]) {
				return false;
			}
		}
		return true;
	}

	static public void main(String[] args) {
		//Input
		String s = input();

		//Solutions
		//boolean res = isUnique1(s);
		//boolean res = isUnique2(s);
		boolean res = isUnique3(s);

		//Output
		output(res);
	}

	//get input
	static private String input() {
		System.out.println("/**** 1.1 Is Unique ****/");
		System.out.println("Please input your a string: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	//output
	static private void output(boolean b) {
		System.out.println(b);
	}

}