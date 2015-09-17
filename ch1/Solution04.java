/*
1.4 Palindrome Permutation:
	Given a string, write a function to check if it is a permutation of a 
	palindrome. A palindrome is a word or phrase that is the same forwards 
	and backwards. A permutation is a rearrangement of letters. The palindrome 
	does not need to be limited to just dictionary words.

	EXAMPLE
	Input:	Tact Coa
	Output:	True (permutations: "taco cat", "atco cta", etc)
*/

import java.io.*;
import java.util.*;

public class Solution04 {
	/*
	Solution 1:
		Count characters numbers, at most one character's amount can be odd.
		Using boolean array as hashtable, true means character's amount is odd.
	Assumption:
		the spaces are ignored;
		input only contains a/A to z/Z letters and spaces.
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private boolean PalindromePermutation1(String s) {
		if (s == null || s.length() == 0)
			return false;

		boolean[] hash = new boolean[128];
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				continue;
			} else {
				hash[s.charAt(i)] = !hash[s.charAt(i)];
			}
		}

		int oddNum = 0;
		for (int i = 0; i < 128; i++) {
			if (hash[i] == true)
				oddNum++;

			if (oddNum > 1)
				return false;
		}
		return true;
	}

	static public void main(String[] args) {
		//Input
		String s = input();

		//Solutions
		boolean res = PalindromePermutation1(s);
		//boolean res = PalindromePermutation2(s);

		//Output
		output(res);
	}

	//input
	static private String input() {
		String s = null;
		try {
			System.out.println("/**** 1.4 Palindrome Permutation ****/");
			System.out.println("Please input a string (can include spaces): ");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			s = br.readLine();
		} catch (IOException e) {
			System.out.println("input error" + e.getMessage());
		}
		return s.trim();
	}

	//output
	static private void output(boolean b) {
		System.out.println(b);
	}
}