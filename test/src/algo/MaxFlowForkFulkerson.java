package algo;

import java.util.ArrayList;
import java.util.LinkedList;

public class MaxFlowForkFulkerson {
	public int V; // Số đỉnh
	public int E; // Số cạnh
	public Edge[] graph;
	public int max_flow;

	public ArrayList<Edge> paths = new ArrayList<>();
	public ArrayList<Integer> idxList = new ArrayList<>();

	public MaxFlowForkFulkerson(Edge[] g, int V, int E, int s, int t) {
		Edge[] inputGraph = inputEdge(g, E);
		this.graph = inputGraph;
		this.V = V;
		this.E = E;
		fordFulkerson(inputGraph, s - 1, t - 1);
	}

	public Edge[] inputEdge(Edge[] g, int E) {
		Edge[] result = new Edge[g.length];
		for (int i = 0; i < E; i++) {
			result[i] = new Edge(g[i].getNode1() - 1, g[i].getNode2() - 1, g[i].getWeight());
		}
		return result;
	}

	boolean bfs(Edge[] rGraph, int s, int t, int parent[]) {

		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; ++i)
			visited[i] = false;

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s] = -1;

		// Standard BFS Loop
		while (queue.size() != 0) {
			int u = queue.poll();
			for (int e = 0; e < E; e++) {
				if (rGraph[e].getNode1() == u && rGraph[e].getWeight() > 0 && !visited[rGraph[e].getNode2()]) {
					if (rGraph[e].getNode2() == t) {
						parent[rGraph[e].getNode2()] = u;
						return true;
					}
					queue.add(rGraph[e].getNode2());
					parent[rGraph[e].getNode2()] = u;
					visited[rGraph[e].getNode2()] = true;
				} else
					continue;

			}
		}

		return false;
	}

	public Edge getEdgeWeight(int node1, int node2) {
		for (int i = 0; i < graph.length; i++) {
			if (graph[i].getNode1() == node1 && graph[i].getNode2() == node2) {
				return new Edge(graph[i].getNode1(), graph[i].getNode2(), graph[i].getWeight());
			}
		}
		return null;
	}

	public int fordFulkerson(Edge[] graph, int s, int t) {
		int u, v;

		Edge[] rGraph = new Edge[E];
		Edge[] temp = new Edge[50];
		idxList.add(0);

		for (u = 0; u < E; u++) {
			rGraph[u] = new Edge(graph[u].getNode1(), graph[u].getNode2(), graph[u].getWeight());
			rGraph[u].setWeight(graph[u].getWeight());

		}

		int parent[] = new int[V];

		int max_flow = 0;

		int count = 0;
		int idx = 0;
		while (bfs(rGraph, s, t, parent)) {

			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
//				System.out.println(u);
				for (int e = 0; e < E; e++) {
					if (rGraph[e].getNode1() == u && rGraph[e].getNode2() == v) {
						path_flow = Math.min(path_flow, rGraph[e].getWeight());
						temp[count] = new Edge(rGraph[e].getNode1(), rGraph[e].getNode2(), rGraph[e].getWeight());
						count++;
						break;
					}
				}

			}
			idxList.add(count);
//			System.out.println("Path flow: " + path_flow);

			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				for (int e = 0; e < E; e++) {
					if (rGraph[e].getNode1() == u && rGraph[e].getNode2() == v) {
						rGraph[e].setWeight(rGraph[e].getWeight() - path_flow);
						break;
					}
				}
				for (int e = 0; e < E; e++) {
					if (rGraph[e].getNode1() == v && rGraph[e].getNode2() == u) {
						rGraph[e].setWeight(rGraph[e].getWeight() + path_flow);
						break;
					}
				}
			}

			for (int i = count - 1; i >= idxList.get(idx); i--) {
//				System.out.println(temp[i]);
				paths.add(new Edge(temp[i].getNode1() + 1, temp[i].getNode2() + 1,
						getEdgeWeight(temp[i].getNode1(), temp[i].getNode2()).getWeight() - temp[i].getWeight()
								+ path_flow));
			}
//			System.out.println("---------" + count);

			max_flow += path_flow;
			idx++;
		}
		this.max_flow = max_flow;
		return max_flow;
	}

	public static void main(String[] args) {

		Edge[] graph = new Edge[9];
//		graph[0] = new Edge(0, 1, 16);
//		graph[1] = new Edge(0, 2, 13);
//		graph[2] = new Edge(1, 2, 10);
//		graph[3] = new Edge(1, 3, 12);
//		graph[4] = new Edge(2, 1, 4);
//		graph[5] = new Edge(2, 4, 14);
//		graph[6] = new Edge(3, 2, 9);
//		graph[7] = new Edge(3, 5, 20);
//		graph[8] = new Edge(4, 3, 7);
//		graph[9] = new Edge(4, 5, 4);

//		graph[0] = new Edge(0, 1, 10);
//		graph[1] = new Edge(0, 5, 10);
//		graph[2] = new Edge(1, 5, 2);
//		graph[3] = new Edge(1, 2, 4);
//		graph[4] = new Edge(1, 4, 8);
//		graph[5] = new Edge(2, 3, 10);
//		graph[6] = new Edge(4, 2, 6);
//		graph[7] = new Edge(4, 3, 10);
//		graph[8] = new Edge(5, 4, 9);

		graph[0] = new Edge(1, 2, 10);
		graph[1] = new Edge(1, 6, 10);
		graph[2] = new Edge(2, 3, 4);
		graph[3] = new Edge(2, 5, 8);
		graph[4] = new Edge(2, 6, 2);
		graph[5] = new Edge(3, 4, 10);
		graph[6] = new Edge(5, 3, 6);
		graph[7] = new Edge(5, 4, 10);
		graph[8] = new Edge(6, 5, 9);

		MaxFlowForkFulkerson m = new MaxFlowForkFulkerson(graph, 6, graph.length, 1, 4);
		Edge[] arr = m.paths.toArray(new Edge[0]);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println(m.max_flow);

//		Edge[] arr = m.inputEdge(graph, graph.length);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//		}
	}
}
