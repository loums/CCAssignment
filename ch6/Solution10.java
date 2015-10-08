/* 
6.10 Poison:
	You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test
	strips which can be used to detect poison. A single drop of poison will turn
	the test strip positive permanently. You can put any number of drops on a 
	test strip at once and you can reuse a test strip as many times as you'd like
	(as long as the results are negative). However, you can only run tests once per
	day and it takes seven days to return a result. How would you figure out the 
	poisoned bottle in as few days as possible?

	FOLLOW UP
	Write code to simulate your approach. 
*/

/* 
Optimal Solution: Binary 
	In order to find the target bottle number, consider the number to be represented
	as binary bits. Since 2^10 = 1024, so 10 digits can represent 1 to 1000. 

	Assume the number is composed of 10 bits: a9, a8, ..., a0
	Getting the first group, set a0 to be 1, all the other bits to be either 0 or 1. 
	Getting all these numbers bottles' sample, put them together and use one strip
	to test as the first group. Doing the same for the rest of the bits. Then after 
	seven days, if the ith group is positive, then set ith bit to 1. Combine all the 
	10 groups, we get the target bottle number.
*/

//FOLLOW UP

import java.io.*;
import java.util.*;

class Bottle {
	private boolean poison = false;
	private int id;

	public Bottle (int id) {
		this.id = id;
	}

	public int getID () {
		return id;
	}

	public boolean isPoison () {
		return poison;
	}

	public void setPoison () {
		poison = true;
	}
}

class Strip {
	private final int resultWaitDay = 7;

	private List<Bottle> dropSamples = new ArrayList<Bottle>();

	public void addDrop (Bottle b) {
		dropSamples.add(b);
	}

	public boolean getResultOnDay (int day) {
		if (day < resultWaitDay) {//at least wait for 7 days, by default start from day 0
			return false;
		}

		for (Bottle b : dropSamples) {
			if (b.isPoison()) {
				return true;
			}
		}

		return false;
	}
}

class Input {
	List<Bottle> bottles = new ArrayList<Bottle>();
	List<Strip> strips = new ArrayList<Strip>();
}


public class Solution10 {
	private static int Poison(List<Bottle> bottles, List<Strip> strips) {

		//set tests for each strip
		for (int i = 0; i < strips.size(); i++) {
			int mask = 1 << i;
			Strip sp = strips.get(i);
			//add drops
			for (Bottle b : bottles) {
				if ((b.getID() & mask) != 0) { //the bottle id, the mapping bit is 1, add into test
					sp.addDrop(b);
				}
			}
		}

		//wait for 7 days
		int waitDays = 7;
		int targetNum = 0;

		for (int i = 0; i < strips.size(); i++) {
			if (strips.get(i).getResultOnDay(waitDays)) {
				targetNum |= (1 << i);
			}
		}

		return targetNum;
	}


	public static void main(String[] args) {
		System.out.println("----------- 6.10 Poison -----------");
		Input input = genInput();

		int res = Poison(input.bottles, input.strips);
		System.out.print("Target bottle number: ");
		System.out.println(res);
	}

	private static Input genInput() {
		Input input = new Input();
		for (int i = 1; i <= 1000; i++) {//bottle ID from 1 to 1000
			Bottle b = new Bottle(i);
			input.bottles.add(b);
		}

		//set one bottle poison :index 99, ID 100.
		input.bottles.get(99).setPoison();

		for (int i = 0; i < 10; i++) {
			Strip p = new Strip();
			input.strips.add(p);
		}

		return input;
	}
}