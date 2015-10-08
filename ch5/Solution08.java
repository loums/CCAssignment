/* 
5.8 Draw Line:
	A monochrome screen is stored as a single array of bytes, allowing eight
	consecutive pixels to be stored in one byte. The screen has width W, where
	w is divisible by 8 (that is, no byte will be split across rows). The height
	of the screen, of course, can be derived from the length of the array and the
	width. Implement a function that draws a horizontal line from (x1, y) to (x2, y).
	The method signature should look something like:
	drawLine(byte[] screen, int width, int x1, int x2, int y)
*/


import java.io.*;
import java.util.*;

public class Solution08 {

	/* 
	Solution 
		Set the bits by byte, if that full byte is within [x1, x2].
	Assumptions:
		x1 and x2 is in the same row,
	Time complexity:
	Space complexity:
	*/
	private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int mainOffSet = width / 8 * y;

		//get full bytes index
		int firstFullByte = x1 / 8;
		int lastFullByte = x2 / 8;
		int remainder1 = x1 % 8;
		int remainder2 = x2 % 8;
		if (remainder1 != 0) {//the first byte is not full byte
			firstFullByte++;
		}
		if (remainder2 != 7) {//the last byte is not full byte
			lastFullByte--;
		}

		//first byte and last byte that are non full ones
		byte firstByteMask = (byte) (0xFF >>> remainder1);
		byte lastByteMask = (byte) (0xFF << (8 - remainder2 - 1));
		
		if ((x1 / 8) == (x2 / 8)) { //within the same byte
			byte mask = (byte) (firstByteMask & lastByteMask);
			screen[mainOffSet + (x1 / 8)] |= mask;
		} else {
			if (remainder1 != 0) {
				screen[mainOffSet + firstFullByte-1] |= firstByteMask;
			}
			if (remainder2 != 7) {
				screen[mainOffSet + lastFullByte+1] |= lastByteMask;
			}
		}

		//set full bytes
		for (int i = firstFullByte; i <= lastFullByte; i++) {
			screen[mainOffSet + i] |= (byte) 0xFF;
		}

	}

	private static void printScreen(byte[] screen, int width) {
		int rows = screen.length * 8 / width;
		int cols = width / 8;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(byteToBinary(screen[i*rows + j]));
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private static String byteToBinary(byte b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 7; i >= 0; i--) {
			int bit = b & (1 << i);
			if (bit == 0) {
				sb.append(0);
			} else {
				sb.append(1);
			}
			
		}
		return sb.toString();
	}


	public static void main(String[] args) {
		
		byte[] screen = new byte[16];//4 * 4
		int width = 32;

		System.out.println("----------- 5.8 Draw Line -----------");
		System.out.println("Before drawing line");
		printScreen(screen, width);
		
		int x1 = 3; //[0, 31]
		int x2 = 16;//[0, 31]
		int y = 1;  //[0, 3]
		drawLine(screen, width, x1, x2, y);

		System.out.println("After drawing line");
		printScreen(screen, width);
	}





}