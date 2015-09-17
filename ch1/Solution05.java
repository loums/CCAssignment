/*
1.5 One Away:
	There are three types of edits that can be performed on strings: insert a 
	character, remove a character, or replace a character. Given two strings, 
	write a function to check if they are one edit (or zero edits) away.

	EXAMPLE
	pale,  ple	->	true
	pales, pale	->	true
	pale,  bale	->	true
	pale,  bake	->	false
*/

import java.io.*;
import java.util.*;

public class Solution05 {
	/*
	Solution 1
		consider three conditions seperately:
		1. if they have same length, check if only one character is different so that 
			replace can make them same.
		2. if their length difference is 1, then check if inserting can make them same
	Time complexity: O(n)
	Space Complexity: O(1)
	*/
	static private boolean oneAway1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
			return false;
		
		if (s1.length() == s2.length()) {//if replace work
			int diff = 0;
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) != s2.charAt(i)) {
					diff++;
				}
				if (diff > 1)
					return false;
			}
			return true;
		} else if (Math.abs(s1.length() - s2.length()) == 1) {//if insert or remove work
			
			if (s1.length() > s2.length()) { //s1 always be the shorter one
				String tmp = s1;
				s1 = s2;
				s2 = tmp;
			}

			int i = 0, j = 0;
			while ( i < s1.length() && j < s2.length()) {
				if (s1.charAt(i) == s2.charAt(j)) {
					i++;
					j++;
				} else if (i == j){//the first difference
					j++;
				} else { //i != j, not the first difference
					return false;
				}
			}

			return true;
		}

		return false;
	}


	/*
	Solution 2
		Using Dynamic Programing to get minimal distance to get their minimal distance.
		1. definition:
			dp[i][j] means the minimal edit distance of s1[0:i-1] and s2[0:j-1] 
			since we need dp[0][0] or dp[0][i] or dp[0][j] to represent empty string, then 
			dp[i][j] the i, j is one more than string actual index.
		2. recursion:
			consider change s1[0:i] from s2[0:j], we can:
			1). dp[i-1][j-1] + 1: change s1[i] to s2[j]: 
			2). dp[i-1][j] + 1: so we want s1[0:i-1] = s2[0:j-1], since s1[0:i-2] = s2[0:j-1], 
				then we need to delete s1[i-1] to make them equal. 
			3). dp[i][j-1] + 1: so we want s1[0:i-1] = s2[0:j-1], since s1[0:i-1] = s2[0:j-2], 
				then s2[0:j-1] first has an additional s2[j-1], so that s1 also need to add 
				s2[j-1] to maintain the equal. 
			so dp[i][j] = min(these three posibilites above) + 1;

		3. initialize
			dp[0][j] = j;
			dp[i][0] = i;
		4. calcuate direction:
			in 2D dimension, from left to right, top to bottom, up left to bottom right.

	Time complexity: O(n)
	Space Complexity: O(n^2)
	*/
	static private boolean oneAway2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
			return false;

		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m+1][n+1];
		for (int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int j = 1; j <= n; j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
				}
			}
		}

		return dp[m][n] <= 1;
	}

	static public void main(String[] args) {
		//Input
		List<String> list = input();

		//Solutions
		String s1 = list.get(0);
		String s2 = list.get(1);
		//boolean res = oneAway1(s1, s2);
		boolean res = oneAway2(s1, s2);

		//Output
		output(res);
	}

	//input
	static private List<String> input() {
		List<String> list = new ArrayList<String>();
		System.out.println("/**** 1.5 One Away ****/");
		System.out.println("Please input two words (use space to seperate words): ");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			String[] strings = s.split(" ");
			list.add(strings[0]);
			list.add(strings[1]);
		} catch (IOException e) {
			System.out.println("error: " + e.getMessage());
		}
		return list;
	}

	//output
	static private void output(boolean b) {
		System.out.println(b);
	}
}