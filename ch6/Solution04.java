/* 
6.4 Ants on a Triangle:
	There are three ants on different vertices of a triangle. What is the 
	probability of collision (between any two or all of them) if they start
	walking on the sides of the triangle? Assume that each ant randomly picks
	a direction, with either direction being equally likely to be chosen, and
	that they walk at the same speed.
	Similaryly, find the probability of collision with n ants on an n-vertex
	polygon.
*/


/* 
Solution 
	Suppose the probility that they won't get collision is P', so 
		P' = (1/2)^(3-1) = 1/4
		(The first ant can choose either side to walk, then the rest of the ants, 
		the rest two ants, should go the same direction with it.)
	So the probility of collision is
		1 - P' = 3/4

	FOLLOW UP
	if for n-vertex polygon, 
		P' = (1/2)^(n-1)
		(The first ant goes either direction, the rest n-1 ants should go the same
		direction.)
	so,
		P = 1 - P' = 1 - (1/2)^(n-1)

*/
	