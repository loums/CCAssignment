/* 
10.8 Find Dupliates
	 You have an array with all the numbers from 1 to N, where N is at most 
	 32,000. The array may have duplicate entries and you do not know what 
	 N is. With only 4 kilobytes of memory available, how would you print 
	 all duplicate elements in the array.
*/

import java.io.*;
import java.util.*;

public class Solution08 {
	/* 
	Solution
		Memory 4 KB = 4 * 2^10 * 8 = 32 * 2^10 bit > 32000.
		So we can use a bit vector to record integers already exists.

	Assumptions:
		The rest of the memmory will be enough for running the program.

	Time complexity: O(n)
	Space complexity: O(n)
	*/
	private static void FindDupliates(String filename) throws FileNotFoundException{

		byte[] bitvector = new byte[32000 / 8];//bit vector of size total number/8
		System.out.println("Duplicate numbers: ");

		Scanner scanner = new Scanner(new FileReader(filename));
		while(scanner.hasNextInt()) {
			int num = scanner.nextInt();
			
			if ( (bitvector[num/8] & (1 << num%8)) == 0) {//didn't appear before
				bitvector[num/8] |= (1 << (num%8)); //set the mapping bit to 1
			} else {
				System.out.print(num);
				System.out.print(" ");
			}
		}

		System.out.println();
	}


	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("----------- 10.8 Find Dupliates -----------");

		String filename = "test.txt";

		//FindDupliates(filename);
	}

}