/* 
4.1 Route Between Nodes:
	Given a directed graph, design an algorithm to find out whether there is a 
	route between two nodes.
*/

import java.io.*;
import java.util.*;

//Graph
class GraphNode {
	public int id;
	private List<Integer> list = new ArrayList<Integer>();
	public boolean visited = false;

	public GraphNode (int id) {
		this.id = id;
	}

	public void addAdjacentNode (int id) {
		list.add(id);
	}

	public List<Integer> getAdjacentNode() {
		return list;
	}

}

class Graph {
	private Map<Integer, GraphNode> nodesMap = new HashMap<Integer, GraphNode>();

	public void addNode (GraphNode node) {
		nodesMap.put(node.id, node);
	}

	public Map<Integer, GraphNode> getNodesMap() {
		return nodesMap;
	}

	public GraphNode getNode(int id) {
		if (nodesMap.containsKey(id)) {
			return nodesMap.get(id);
		}
		return null;
	}
}

public class Solution01 {
	/*
	Solution 1
		Use BFS to traversal the tree, check if can reach the end node
		Pros: fast to reach the shortest path.
		Cons: need a queue to record each level's nodes

	Time complexity: O(n)
	Space Complexity: O(n^2) for storing graph, but no extra space needed for algorithm.
	*/
	static private boolean Solution(Graph g, GraphNode start, GraphNode end) {
		Map<Integer, GraphNode> nodesMap = g.getNodesMap();//nodes
		if (nodesMap.isEmpty()) return false;

		ArrayDeque<GraphNode> queue = new ArrayDeque<GraphNode>();//queue for BFS
		queue.offer(start);

		while(!queue.isEmpty()) {
			GraphNode node = queue.poll();
			if (node == end) {//reach the end node
				return true;
			}
			node.visited = true;//set visited to avoid cycle

			List<Integer> list = node.getAdjacentNode();
			for (Integer i : list) {
				GraphNode cur = nodesMap.get(i);
				if (cur.visited == false) {
					queue.offer(cur);//enqueue its children
				}
			}
		}

		return false;
	}


	static public void main(String[] args) {
		//Input
		Graph g = generateGraph();
		//boolean res1 = Solution(g, g.getNode(0), g.getNode(5));
		boolean res2 = Solution(g, g.getNode(4), g.getNode(3));
		//boolean res3 = Solution(g, g.getNode(4), g.getNode(2));

		System.out.println("----------- 4.1 Route Between Nodes -----------");
		System.out.println("See the test graph in code comments");
		//System.out.println("There is a route from Node 0 to Node 5 (should be true): " + String.valueOf(res1));
		System.out.println("There is a route from Node 4 to Node 3 (should be true): " + String.valueOf(res2));
		//System.out.println("There is a route from Node 4 to Node 2 (should be false): " + String.valueOf(res3));

	}

	//generate graph
	/*
			 -----------
			|			|
			1 --> 3 --> 4
		  /	    /   \
		0      /     \
		  \   /       \
		  	2		   5
		 Note: 0 -> 1; 0 -> 2; 2 -> 3; 3 -> 5; 4 -> 1
	*/
	static private Graph generateGraph() {
		Graph g = new Graph();

		GraphNode n0 = new GraphNode(0);
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		GraphNode n4 = new GraphNode(4);
		GraphNode n5 = new GraphNode(5);

		n0.addAdjacentNode(1);
		n0.addAdjacentNode(2);
		n1.addAdjacentNode(3);
		n2.addAdjacentNode(3);
		n3.addAdjacentNode(4);
		n3.addAdjacentNode(5);
		n4.addAdjacentNode(1);

		g.addNode(n0);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);

		return g;
	}

}