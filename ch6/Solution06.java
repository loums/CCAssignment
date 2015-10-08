/* 
6.6 Blue-Eyed Island:
	A bunch of people are living on an island, when a visitor comes with a strange
	order: all blue-eyed people must leave the island as soon as possible. There 
	will be a flight out at 8:00 pm every evening. Each person can see everyone
	else's eye color, but they do not know their own (not is anyone allowed to tell
	then). Additionally, they do not know how many people have blue eyes, although 
	they do know that at least one person does. How many days will it take the
	blue-eyed people to leave? 
*/


/* 
Solution 
	1). Assume only one person is blue eyes:
		Then the first day he will find none of the other people is blue eyes, and 
		at least one person has blue eyes. So he knows that he is the blue eyes guy.
		Then the second day he will leave the island. 
		So that takes one day, if only one person has blue eyes.

	2). Assume two persons (suppose A and B) are blue eyes:
		The first day A (or B) will find one of the other people is blue eyes, but he 
		cannot sure if himself is blue eyes or not. So there are only two kinds of 
		possibilties. The first one is only one person ,the person he saw B, is blue eyes, 
		then it's the same as the first case, B will leave on the second day.

		But if not, that means himself is also blue eyes, since B also saw A's blue eyes 
		and cannot about B himself as well. So A and B should leave together on the third day.

	3). For more general cases, if there are k persons are blue eyes:
		Continue the induction, when k = 3, all the three person (A, B, C) will need to wait until the 
		third day, for A, if B and C remain stay on the third, then he knows himself is blue eyes and
		will leave on the fourth day. Ohterwise, B and C will leave as case 2, if A is not blue eyes.
		So that for k person, each of them will need to stay on the kth day, if the k-1 blue eyes
		person he saw still stays on that day, he knows himself is blue eyes. Then he will leave on
		the (k+1)th day. The same for the rest of blue eyes person.

	Conclusion: If k persons are blue eyes, it will cost k days for them to leave.
*/
