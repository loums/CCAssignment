/* 
6.7 The Apocalypse:
	In the new post-apocalyptic world, the world queen is desperately concerned
	about the birth rate. Therefore, she decrees that all families should ensure
	that they have one girl or else they face massive fines. If all families abide
	by this policy - that is, they have continue to have children until they have
	one girl, at which point they immediately stop - what will the gender ratio of
	the new generation be? (Assume that the odds of someone having a boy or a girl
	on any given pregnancy is equal.) Solve this out logically and then write a 
	computer simulation of it.
*/


/* 
Solution 
	Consider the expected number of boys for one family. 
	The probility of 0 boy p0 = 1/2; 
	the probility of 1 boy p1 = 1/2 * 1/2 = (1/2)^2
	the probility of 2 boys p2 = (1/2)^3
	..
	the probility of k boys pk = (1/2)^(k+1)

	So the expected number of boys:
	E(x) = 0*p0 + 1*p1 + ... + n*pn
		 = Sigma(i = 0 to n) {i * (1/2)^(i+1)}
		 = (1/2) * Sigma(i = 0 to n) {i * (1/2)^i}

	when i tends to infinite,
		Sigma(i = 0 to n) {i * x^i} = x / (1-x)^2, where 0 < x < 1.
	Here x = 1/2, so 
		Sigma(i = 0 to n) {i * (1/2)^i} = x / (1-x)^2 = 2
	So,
		E(x) = 1, when n is infinite large.

	And each family will have exactly one girl. So the ratio of boys and
	girls is 1 : 1. 

Another way to explain:
	Even though it seems girls have priority, but the probility of having girls
	and boys stays the same. 

	Consider having a boy is adding letter 'B', and having a girl is adding
	letter 'G'. So for each family is a string composed of 'B' and 'G'. Append all
	the families' strings together, that is a huge string, then having a baby is 
	like adding a new letter. It's either 'B' or 'G', both has 1/2 probility. So
	the total probility is still 1/2 for boys. 
*/

//Simulation
import java.io.*;
import java.util.*;

public class Solution07 {
	public static void main(String[] args) {
		System.out.println("----------- 6.7 The Apocalypse -----------");
		testCase();
	}

	private static void testCase () {
		double girls = 0;
		double boys = 0;

		for (; girls < 500; girls++) {
			boys += getOneFamily();
		}

		double boysPercent = boys / (boys + girls);
		System.out.format("Generated %d families.\n", (int)girls);
		System.out.print("The boys percentage is ");
		System.out.print(boysPercent);
		System.out.println();
	}

	//generate one family's amount of boys and girls.
	//Since must have one girl, just need to return boys' number
	private static int getOneFamily() {
		Random random = new Random();
		int boyAmount = 0;

		while(true) {
			int girl = random.nextInt(2);//[0,2) = [0,1]
			if (girl == 1) {
				break;
			}
			boyAmount++;
		}

		return boyAmount;
	}
}
