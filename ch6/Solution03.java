/* 
6.3 Domainos:
	There is an 8*8 chessboard in which two diagonally opposite corners have been
	cut off. you are given 31 dominos, and a single domino can cover exactly two
	squares. Can you use the 31 dominos to cover the entire board? Prove your 
	answer (by providing an example or showing why it's impossible).
*/


/* 
Solution 
	31 dominos cannot cover the entire board.
Proof:
	Before cutting off the two corners, we mark the left up corner grid to white, and 
	then its neighbour to black, doing it recurisively until the whole chessboard has
	been colored.

	Since a domino can only cover two adjacent grids. So it must cover one black
	and one white grid. Then remove the two diagonally opposite corners. Since these two
	grids must be in the same color, in this case, both of them are black. So we left
	30 black grids and 32 white grids. According to the conclusion beofre, one domino
	must cover one white and one black grid. But the chessborad does't have same number of
	black and white grids, so that we won't be able to cover the chessboard using 31 dominos.

*/
