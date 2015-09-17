/*
1.9 String Rotation:
	Assume you have a method isSubstring which checks if one word is a substring 
	of another. Given two strings, s1 and s2, write code to check if s2 is a 
	rotation of s1 using only one call to isSubstring (e.g., "waterbottle" is a 
	rotation of "erbottlewa").
*/

import java.io.*;
import java.util.*;

public class Solution09 {

	/*
	Solution 1
		if two strings have rotation relation, suppose s1 composed of "xy", then 
		s2 must be "yx". (x and y is common substring of s1 and s2). Then s2 must
		be a substring of "s1s1" that is "xyxy";
	Assumption:
		the complexity of isSubstring() is O(1)
	Time complexity: O(1)
	Space Complexity: O(n)
	*/
	static private boolean StringRotation(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;

		StringBuilder sb = new StringBuilder();
		sb.append(s1);
		sb.append(s1);

		return isSubstring(sb.toString(), s2);
	}

	static public void main(String[] args) {
		//Input
		List<String> list = input();
		String s1 = list.get(0);
		String s2 = list.get(1);

		//Solutions
		boolean res = StringRotation(s1, s2);

		//Output
		output(res);
	}

	static private boolean isSubstring(String s1, String s2) {
		int index = s1.indexOf(s2);
		return index == -1 ? false : true;
	}

	//input
	static private List<String> input(){
		List<String> list = new ArrayList<String>();
		System.out.println("/**** 1.9 String Rotation ****/");
		System.out.println("Please input two words (use space to seperate): ");

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			String[] words = s.split(" ");
			list.add(words[0]);
			list.add(words[1]);

		} catch (IOException e) {
			System.out.println("input error, " + e.getMessage());
		}

		return list;
	}


	//output
	static private void output(boolean b) {
		System.out.println(b);
	}
}