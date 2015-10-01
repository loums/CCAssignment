/* 
10.2 Group Anagrams
	 Write a method to sort an array of strings so that all the anagrams are 
	 next to each other.
*/


import java.io.*;
import java.util.*;

public class Solution02 {
	/* 
	Solution 1
		Implement a comparator that compares the sorted strings. If two strings are anagrams,
		then their sorted version should be equal, if not compare two strings in alphabetic order.
	Assumptions:
		Allowed to use arrays.sort(). 
	Time complexity: O(nlgn * klgk) if nlgn for sorting the whole array, klgk for sorting each string.
	Space complexity: O(1) 
	*/
	static private String[] groupAnagrams1(String[] strs) {//‘length’ is the number of elements in A
		
		class AnagramComparator implements Comparator<String>{
			public String convertToSortedChars(String s) {
				char[] tmp = s.toCharArray();
				Arrays.sort(tmp);
				return new String(tmp);
			}

			public int compare(String s1, String s2) {
				String a = convertToSortedChars(s1);
				String b = convertToSortedChars(s2);
				return a.compareTo(b);
			}
		}

		Arrays.sort(strs, new AnagramComparator());

		return strs;

	}

	/* 
	Solution 2
		Use hashmap to record each group. Take the sorted string as key and all the anagrams as value.
		Then make the result array accroding to hashmap. 

	Assumptions:
		hashmap get(String key) is O(1)

	Time complexity: O(n)
	Space complexity: O(n) for hashmap and new result array
	*/
	static private String[] groupAnagrams2(String[] strs) {//‘length’ is the number of elements in A
		
		Map<String, List<Integer>> hash = new HashMap<String, List<Integer>>();

		//record in hash map
		for (int i = 0; i < strs.length; i++) {
			String key = convertToSortedChars(strs[i]);//O(n)
			if (!hash.containsKey(key)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				hash.put(key, list);
			} else {
				List<Integer> list = hash.get(key);
				list.add(i);
			}
		}

		//convert result into array
		int j = 0;
		String[] res = new String[strs.length];
		for (List<Integer> list : hash.values()) {
			 for (Integer i : list) {
			 	res[j++] = strs[i];
			 }
		}

		return res;

	}

	public static String convertToSortedChars(String s) {
		char[] tmp = s.toCharArray();
		Arrays.sort(tmp);
		return new String(tmp);
	}


	public static void main(String[] args) {
		System.out.println("----------- 10.2 Group Anagrams -----------");

		String[] strs = {"abc", "bcd", "acb", "dcb", "egf", "efg"};

		String[] res = groupAnagrams1(strs);
		//String[] res = groupAnagrams2(strs);

		for (int i = 0; i < 6; i++) {
			System.out.print(res[i]);
			System.out.print(" ");
		}
		System.out.println();



	}

}