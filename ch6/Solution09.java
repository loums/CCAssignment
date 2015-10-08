/* 
6.9 100 Lockers:
	There are 100 closed lockers in a hallway. A man begins by opening all 100 
	lockers. Next, he closes every second lovker. Then on his third pass, he 
	toggles every third locker (closes it if it is open or opens it if it is 
	closed). This process continues for 100 passes, such that on each pass i,
	the man toggles every ith lovker. After 100th pass in the hallway, in which
	he toggles only locker #100, how many lockers are open?
*/


/*Solution:
	From observation, for kth locker, it will be toggled x times, x is the number
	of its factors. If the door left open that means it has odd numbers of factors.

	Normally, the numbers have even factors. like 10, factors will be pairs since 
	each pair need to multiply together to be the number, like 2 and 5. For those 
	who has odd number of factors will have pairs composed of the same number, like
	4 and 4. So that the numbers composed of squares are the doors left open. There 
	are 1^2, 2^2, ..., 10^2, 10 doors left open at the end.
*/