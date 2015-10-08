/* 
6.2 Basketball
	You have a basketball hoop and someone says that you can play one of two games.
	Game 1: You get one shot to make the hoop.
	Game 2: You get three shots and you have to make two of three shots,
	If p is the probility of making a particular shot, for which values of p should
	you pick one game or the other? 
*/


/* 
Assumptions:
	p < 1, but cannot be 1. That means a person cannot a hundred percent sure he will 
	make the shot.

Solution: 	
	Assume the probilities of winning game 1 and game 2 are P1 and P2.
	Note the combination formula here is C(n, k)

	P1 = p
	P2 = C(3, 2) * p^2 * (1-p) + C(3, 3) * p^3 (the probility to get two
	shots plus that of geting three shots)

	Then suppose P1 >= P2,
	we get (2p - 1)(p - 1) >= 0
	that is , p <= 0.5 or p >= 1 (but p < 1, so this is ignored)

	That means if p is larger than 0.5, then choose game 1 will have a larger probility to
	win, if p equals 0.5 choose either of the two games, if p is smaller than 0.5, choose
	game 2. 
*/
	