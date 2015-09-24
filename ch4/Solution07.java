/* 
4.7 Build Order:
	You are given a list of projects and a list of dependencies (which is a list
	of projects, where the first project is dependent on the second project). All
	of a project's dependencies must be built before the project is. Find a build
	order that will allow the projects to be built. If there is no valid build
	order, return an error.
	EXAMPLE
	Input:
		projects: a, b, c, d, e, f
		dependencies: (d, a), (b, f), (d, b), (a, f), (c, d)
	Output:
		f, e, a, b, d, c
*/

import java.io.*;
import java.util.*;

//Graph
class GraphNode {
	private List<GraphNode> list = new ArrayList<GraphNode>();//adjacent nodes
	public String name;
	public boolean visited = false;
	public int indegree = 0;

	public GraphNode (String name) {
		this.name = name;
	}

	public void addAdjacentNode (GraphNode b) {
		list.add(b);
	}

	public List<GraphNode> getAdjacentNode() {
		return list;
	}

	public void removeAdjacentNode (String name) { //O(n)
		for (int i = 0; i < list.size(); i++) {
			if (name.equals(list.get(i)) ) {
				list.remove(i);
				break;
			}
		}
	}
}

class Graph {
	private List<GraphNode> nodes = new ArrayList<GraphNode>();

	public void addNode (GraphNode node) {
		nodes.add(node);
	}

	public List<GraphNode> getNodes() {
		return nodes;
	}

	public void addEdge (GraphNode a, GraphNode b) {
		a.addAdjacentNode(b);
		b.indegree++;
	}

	public void removeNode (GraphNode a) {

		//remove edges O(n)
		List<GraphNode> list = a.getAdjacentNode();
		for (GraphNode b : list) {
			b.indegree--;
		}

		//remove node: O(n)
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i) == a) {
				nodes.remove(i);
			}
		}
	}

	public GraphNode getZeroIndegreeNode () {
		for (GraphNode a : nodes) {
			if (a.indegree == 0)
				return a;
		}
		return null;
	}

	public boolean isGraphEmpty() {
		return nodes.size() == 0;
	}

}


public class Solution07 {
	/*
	Solution: Topological sorting
		Find a node that in degree is 0, and remove all the edges related to it. 
		update the indegreee to adjacent nodes. Doing it iteratively.
	Time complexity: 
	Space Complexity: O(n^2) for storing graph
	*/
	static private List<String> buildOrder(Graph g) {
		List<String> res = new ArrayList<String>();
		if (g == null) return res;

		while (!g.isGraphEmpty()) {
			GraphNode a = g.getZeroIndegreeNode();
			if (a != null) { //avoid cycle
				res.add(a.name);
				g.removeNode(a);
			}
		}

		return res;
	}


	static public void main(String[] args) {
		Graph g = generateGraph();
		List<String> res = buildOrder(g);

		System.out.println("----------- 4.7 Build Order -----------");
		for (String s : res) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	//generate graph
	/*
	Input:
		projects: a, b, c, d, e, f
		dependencies: (d, a), (b, f), (d, b), (a, f), (c, d)
	Output:
		f, e, a, b, d, c
	*/
	static private Graph generateGraph() {
		Graph g = new Graph();

		GraphNode a = new GraphNode("a");
		GraphNode b = new GraphNode("b");
		GraphNode c = new GraphNode("c");
		GraphNode d = new GraphNode("d");
		GraphNode e = new GraphNode("e");
		GraphNode f = new GraphNode("f");

		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);
		g.addNode(f);
		
		g.addEdge(a, d); //(d, a)
		g.addEdge(f, b); //(b, f)
		g.addEdge(b, d); //(d, b)
		g.addEdge(f, a); //(a, f)
		g.addEdge(d, c); //(c, d)

		return g;
	}

}