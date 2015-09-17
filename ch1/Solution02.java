/* 
1.2 Check Permutation: 
	Given two strings, write a method to decide if one is a permutation of the 
	other.
*/

import java.io.*;
import java.util.*;

public class Solution02 {
	/*
	Solution 1
		Sort two strings and compare if they are the same
	Assumption: if either of the two strings is null or empty then return false
	Time complexity: O(n)
	Space Complexity: O(n)
	*/
	static private boolean checkPermutation1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
			return false;

		if (s1.length() != s2.length())
			return false;

		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();

		Arrays.sort(str1);
		Arrays.sort(str2);

		String newstr1 = new String(str1);
		String newstr2 = new String(str2);

		return newstr1.equals(newstr2);
	}

	/*
	Solution 2
		Hashtable, using array to record character numbers, compare if they have same amount
	Assumption: 
		input only contains characters from 0 to 127 ascii 
	Time complexity: O(n)
	Space Complexity: O(n)
	*/
	static private boolean checkPermutation2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
			return false;

		if (s1.length() != s2.length())
			return false;

		int[] hash = new int[128];
		for (int i = 0; i < s1.length(); i++) {
			hash[s1.charAt(i)]++;
			hash[s2.charAt(i)]--;//if s1 = s2 then the array should be all zeros
		}

		for (int i = 0; i < 128; i++) {
			if (hash[i] != 0)
				return false;
		}
		return true;
	}

	static public void main(String[] args) {
		//Input
		List<String> str = input(); 

		//Solutions
		String s1 = str.get(0);
		String s2 = str.get(1);
		//boolean res = checkPermutation1(s1, s2);
		boolean res = checkPermutation2(s1, s2);

		//Output
		output(res);
	}

	//input
	static private List<String> input() {
		System.out.println("/**** 1.2 Check Permutation ****/");
		System.out.println("Please input two strings: (enter space between each input string)");
		Scanner scanner = new Scanner(System.in);
		List<String> str = new ArrayList<String>();
		for (int i = 0; i < 2; i++) {
			str.add(scanner.next());
		}

		return str;
	}

	//output
	static private void output(boolean b) {
		System.out.println(b);
	}
}