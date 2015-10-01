/* 
10.6 Sort Big File
	 Imagine you have a 20 GB file with one string per line. Explain how you 
	 would sort the file.
*/

/*Solution: Merge sort
	Assume we have 1 GB memory available. 

	First, divid the file into 40 chunks, each 500 MB. For each chunk, doing the in memory mearge sort 
	and write the result into 40 unit files. 

	Then start external merge sort for the 40 chunks. Merge two unit file each time and write their result
	into one bigger unit file. When doing merging, you may read a bunch of strings into buffer, for 
	example, 200 MB for each. Once one buffer is empty, read another 200 MB or the size of rest strings.
	Then we have 20 sorted unit files. 
	
	Doing it itertively for the 20 sorted unit files, finally get the result sorted file.
*/



