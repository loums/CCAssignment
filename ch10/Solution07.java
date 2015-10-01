/* 
10.7 Missing Int
	 Given an input file with four billion non-negative integers, provide an 
	 algorithm to generate an integer that is not contained in the file. Assume 
	 you have 1 GB of memory available for this task.

	 FOLLOW UP
	 What if you have only 10 MB of memory? Assume that all the values are 
	 distinct and we now have no more than one billion non-negative integers.
*/

import java.io.*;
import java.util.*;

public class Solution07 {
	/* 
	Solution
		There are total 2^31 unique non-negative integers, that is 2 billion. So
		4 billion contains duplicates. And 1 GB = 2^30 * 8 bit = 8 billion memory. 
		2 billion < 8 billion. Use a 2 billion bit vector, record each integer.
		Then scan through the bit vector to find the missing integer. 

	Assumptions:
		The rest of the memmory, 6 billion bit = 0.75 MB, will be enough for running
		the program.

	Time complexity: O(n)
	Space complexity: O(n/8) = O(n)
	*/
	private static int MissingInt(String filename) throws FileNotFoundException{

		byte[] bitvector = new byte[2^31];//size of 2 billion = 2^31
		int missing = -1;

		Scanner scanner = new Scanner(new FileReader(filename));
		while(scanner.hasNextInt()) {
			int num = scanner.nextInt();
			//find the place in bit vector, first which byte then which bit
			bitvector[num / 8] |= (1 << (num%8)); //set the mapping bit to 1
		}

		for (int i = 0; i < bitvector.length; i++) {
			for (int j = 0; j < 8; j++) {
				if ( (bitvector[i] & (1 << j)) == 0) {
					missing = i * 8 + j;
					break;
				}
			}
		}

		return missing;
	}


	/* 
	Followup
		Divide integers into group, count each group's numbers, use a int to 
		record that count. Since the values are distinct, then the missing int's
		group must be one less than other group. Then doing the same bitvector
		for this group to find the missing int.

		Assume the group size is x, each group need one int, that is 4 bytes. 
		There are at most one billion non-negative integers = 2^30.
		Memory is 10 MB = 10 * 2^20, we use the nearest 8 * 2^20 = 2^23 bytes.
		So the group size should satisfy storing all the int count, and also be able
		to store the following in group bit vector.

		So, a). 2^30 / x * 4 <= 2^23
			b). x <= 2^23 * 8 = 2^26(bit)

		we get, 2^11  <=  x  <= 2^26

		Here we use x = 2048.


	Assumptions:
		The rest of the memmory will be enough for running the program.

	Time complexity: O(2n) = O(n)
	Space complexity: O(groupsize + n/groupsize)
	*/
	private static int MissingInt2(String filename) throws FileNotFoundException{
		//find the missing int group
		int targetGroup = -1;
		int groupSize = 2048;
		int groupNum = 2^30 / groupSize;
		{
			int[] groups = new int [groupNum];

			Scanner scanner = new Scanner(new FileReader(filename));
			while(scanner.hasNextInt()) {
				int num = scanner.nextInt();
				groups[num / groupNum]++; 
			}

			for (int i = 0 ; i < groupNum; i++) {
				if (groups[groupNum] < groupSize) {
					targetGroup = i;
					break;
				}
			}
		}
		
		//find the missing int
		{
			byte[] bitvector = new byte[groupSize];

			Scanner scanner = new Scanner(new FileReader(filename));
			while(scanner.hasNextInt()) {
				int num = scanner.nextInt();
				if (num / groupSize == targetGroup) {
					num %= groupSize;
					bitvector[num / 8] |= (1 << (num % 8));
				}
			}

			//get the int value
			int missing = -1;
			for (int i = 0; i < groupSize; i++) {
				for (int j = 0; j < 8; j++) {
					if ( (bitvector[i] & (1 << j)) == 0) {
						missing = targetGroup * groupSize + i * 8 + j;
						break;
					}
				}
			}
				
			return missing;
		}

	}


	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("----------- 10.7 Missing Int -----------");

		String filename = "test.txt";

		//int missing = MissingInt2(filename);
		
		//System.out.println(missing);
	}

}