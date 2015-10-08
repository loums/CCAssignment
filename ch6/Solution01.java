/* 
6.1 The Heavy Pill:
	You have 20 bottles of pills. 19 bottles have 1.0 gram pills, but one has 
	pills of weight 1.1 gram. Given a scale that provides an exact measurement,
	how would you find the heavy bottle? you can only use the scale once.
*/


/* 
Assumptions:
	Each bottle has more than 19 pills. 

Solution: 
	Since we can only use the scale once. And only one of the bottles have heavier
	pills, so we can take different amount of pills from each bottle, and according
	to the total weight to find the target bottle. 

	Mark the bottles from 1 to 20. Take 1 pill from bottle, take 2 pill from bottle
	2, .. , take 19 pills from bottle 19. Then weight all the pills together. 
	So the total weight will be at least:
		1 + 2 + 3 + ...+ 18 + 19 = (1+19)*19/2 = 190.
	If the total weight is 190, then the number 20 bottle is the heavier bottle. If not
	suppose the total weight is w. Then the target bottle number is 
		(w - 190) / (1.1 - 1.0)

	Thus we can use scale once to get the heavier bottle.
*/
	



}