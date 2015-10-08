/* 
6.8 The Egg Drop Problem:
	There is a building of 100 floors. If an egg drops from the Nth floor or above,
	it will break. If it's dropped from any floor below, it will not break. You're
	given two eggs. Find N, while minimizing the number of drops for the worst case. 
*/


/* 
Solution 	
	
	The key point is worst case balancing. 
	
	A non optimal solution is Egg 1 jump 10 floors each time. If Egg 1 broke at 10th
	floor, then Egg 2 should start from 1 to find the N between [1, 9]. So the worst 
	case for Egg 2 is 9. The total drops is 1 + 9 = 10. If Egg 1 broke at 20th floor,
	Egg 2 should try from 11 to 19. The total will be 2 + 9 = 11. So if Egg 1 broke
	at 100th floor, Egg2 should try from 91 to 99. The total drops are 10 + 9 = 19.

	From observation, Egg1's worst case increase from 1 to 10 with Egg2's worst remain
	at 9. And also Egg2's worst case depends on how big Egg2's step is. So in order to 
	avoid either of them to vary a lot about worst case, we want the sum of them to be
	stable. 

	Assume the stable sum is x, then for Egg 1 it need to reach 99 or 100, suppose 100,
		x + (x-1) + (x-2) + ... + 1 = 100
	we get x = 13.65

	Now if we set x = 14, then Egg1's jumps are
		14, 27, 39, 50, 60, 69, 77, 84, 90, 95, 99
	Egg1 goes 11 drops to reach 99, the drops of Egg1 and Egg2 is 14.
	For the last one, if Egg1 still does not break, the N will be 100, and the total 
	steps are 12. So the worst case remains 14. 

	If we set x = 13, then Egg1's jumps are
		13, 25, 36, 46, 55, 63, 70, 76, 81, 85, 88, 90, 91
	Until 91, the Egg1's jump reach 1. Egg1 goes 13 drops to reach 91. Although the sum
	of drops is 13 before 91, but it cannot stay 13 after that. Apprently, if we want 
	the worst case to be better than or equal to x = 14, we only left one drop, which 
	is impossible. 

	So in the perspective of minimize the worst case, x = 14 is the optimal solution.

FOLLOW UP ANALYSIZE

	But if in the persective of expected drops of worst case, x = 13 may not be that bad.

	From 92 to 100, we still left 8 floors. If we apply the same approach to them, we get
	the stable drops is 4, so that Egg1's jumps can be 4, 3, 2 starting from 91. That is,
		 91, 95, 98, 100. 
	So beofre 91 the sum of drops are 13, after 91 we need four more drops that is 17 drops.
	The expected drops are
		13 * 0.91 + 17 * 0.09 = 13.36
	But the expected drops of x = 14 is 14, which is larger than 13.36.

	So that x = 13 have a better expected drops.
*/

//Simulation
import java.io.*;
import java.util.*;

public class Solution08 {
	public static void main(String[] args) {
		System.out.println("----------- 6.8 The Egg Drop Problem -----------");
		testCase();
	}

	private static int totalFloors = 100;
	private static int breakPoint = 100;//[1, 100]
	private static int numOfdrops = 0;

	private static void testCase () {
		int jump = 14;

		//drop egg1 
		int preFloor = 0;
		int curFloor = jump;
		while(curFloor <= totalFloors) {
			if (isBroken(1, curFloor)) {// egg1 broke
				break;
			}
			jump--;
			preFloor = curFloor;
			curFloor += jump;
		}

		//drop egg2

		//if break point in [pre+1, cur-1]
		for (int cur = preFloor + 1; cur < curFloor && cur <= totalFloors; cur++) {//cur <= totalFloors is for special case 100
			if (isBroken(2, cur)) {
				System.out.format("The break point is %d\n", cur);
				System.out.format("The total drops are %d\n", numOfdrops);
				return;
			}
		}

		//if break point is cur
		System.out.format("The break point is %d\n", curFloor);
		System.out.format("The total drops are %d\n", numOfdrops);
	}

	private static boolean isBroken(int id, int floor) {
		System.out.format("Drop at %d floor\n", floor);

		numOfdrops++;
		if (floor >= breakPoint) {
			if (id == 1) {//egg 1 broke
				System.out.println("Egg1 broke.");
			}
			return true;
		}
		return false;
	}
}



