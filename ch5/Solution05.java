/* 
5.5 Debugger:
	Explain what the following code does: ((n & (n-1)) == 0) 
*/


/*
Solution:
	It judges if n has only one bit of 1. 

	Since n-1 will turn the first 1 bit (from right to left) into zero and the 
	bits in the right of it to all ones, like 1000100 - 1 = 1000011. Here the 
	third bit (from left to right), turn to 0, and the two bits in the right 
	it (the first and second bit, from left to right) turn to 1s. 
	
	Since n-1 only toggled the first 1 (index i) and the bits in the right
	it, and didn't chnage the bits in the left of i. So if there is only one 1, 
	then n-1 is totally toggled with n, so that n & (n-1) will be 0. But if not,
	the bits in the left of the first 1, will still be the same. Then the ones
	in the right of i, will make the n & (n-1) != 0.

	Corner case: when n == 0, it also equals to 0.
*/