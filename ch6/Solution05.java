/* 
6.5 Jugs of Water:
	You have a five-quart jug, a three-quart jug, and an unlimited supply of water
	(but no measuring cups). How would you come up with exactly for quarts of 
	water? Note that the jugs are oddly shaped, such that filling up exactly "half"
	of the jug would be impossible. 
*/


/* 
Solution 
	Since we only have three quart or five quart jug, so in order to get four quart
	of water, the key point is to get one quart water first. 

	1). Fill up the 3-quart jug, then pour all the water into 5-quart jug. 
	2). Fill up the 3-quart jug again, then pour the water into 5-quart jug 
	until the 5-quart is full.
	3). Then the 3-quart jug left one quart in there. Empty the 5-quart jug and move the 
	one quart into the 5-quart jug.
	4). Fill up the 3-quart jug, and then pour all the water into 5-quart jug.

	So that we got 4 quart water in the 5-quart jug.
*/
	