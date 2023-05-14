package algo;

import java.util.*;

public class MaxFlow {
	private int numNode;
	private int numEdge;
	private LinkedList<Edge>[] adj;
	public LinkedList<Edge>[] flow;
	private boolean visited[];
	private ArrayList<ArrayList<Integer>> allPaths;

	public int maxflow = 0;
	private int source;
	private int sink;

	public Edge[] paths = new Edge[2500];
	public int idx = 0;

	public MaxFlow(Edge[] graph, int numNode, int numEdge, int source, int sink) {
		this.numNode = numNode;
		this.numEdge = numEdge;
		this.source = source - 1;
		this.sink = sink - 1;

		adj = new LinkedList[numNode];
		flow = new LinkedList[numNode];
		for (int i = 0; i < numNode; ++i) {
			adj[i] = new LinkedList<Edge>();
			flow[i] = new LinkedList<Edge>();
		}

		visited = new boolean[numNode];
		allPaths = new ArrayList<ArrayList<Integer>>();

		inputGraph(graph);
	}

	public void inputGraph(Edge[] graph) {
		for (int i = 0; i < numEdge; i++) {
			addEdge(graph[i].getNode1() - 1, graph[i].getNode2() - 1, graph[i].getWeight());
		}
	}

	// Thêm cạnh vào đồ thị
	public void addEdge(int node1, int node2, int weight) {
		adj[node1].add(new Edge(node1, node2, weight));
		flow[node1].add(new Edge(node1, node2, 0));
	}

	// tăng flow
	public void addFlow(int node1, int node2, int weight) {
		for (int i = 0; i < flow[node1].size(); i++) {
			if (flow[node1].get(i).getNode2() == node2) {
				Edge edge = new Edge(node1, node2, flow[node1].get(i).getWeight() + weight);
				flow[node1].set(i, edge);
			}
		}
	}

	public Edge getEdge(int node1, int node2) {
		for (int i = 0; i < adj[node1].size(); i++) {
			if (adj[node1].get(i).getNode2() == node2) {
				return adj[node1].get(i);
			}
		}
		return null;
	}

	public Edge getEdgeFlow(int node1, int node2) {
		for (int i = 0; i < flow[node1].size(); i++) {
			if (flow[node1].get(i).getNode2() == node2) {
				return flow[node1].get(i);
			}
		}
		return null;
	}

	// Tìm tất cả các đường đi từ start đến end sử dụng DFS
	public ArrayList<ArrayList<Integer>> findAllPaths() {
//		Đường đi
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		pathList.add(source);
		dfs(source, sink, pathList);
		return allPaths;
	}

	// Tìm các đường đi từ đỉnh cur đến đỉnh end
	void dfs(int cur, int end, ArrayList<Integer> pathList) {
		visited[cur] = true;
		if (cur == end) {
			allPaths.add(new ArrayList<Integer>(pathList));
		} else {
			Iterator<Edge> i = adj[cur].listIterator();
			while (i.hasNext()) {
				Edge e = i.next();
				int n = e.getNode2();
				int w = e.getWeight();

				if (!visited[n]) {
					pathList.add(n);
					dfs(n, end, pathList);
					pathList.remove(pathList.size() - 1);
				}
			}
		}
		visited[cur] = false;
	}

	public int findMaxFlowWeight(ArrayList<Integer> list) {
		int maxFlowWeight = Integer.MAX_VALUE;
		for (int i = 0; i < list.size() - 1; i++) {
			if (getEdge(list.get(i), list.get(i + 1)).getWeight()
					- getEdgeFlow(list.get(i), list.get(i + 1)).getWeight() < maxFlowWeight)
				maxFlowWeight = getEdge(list.get(i), list.get(i + 1)).getWeight()
						- getEdgeFlow(list.get(i), list.get(i + 1)).getWeight();
		}
		return maxFlowWeight;
	}

	public int findMaxFLow() {
		for (int i = 0; i < allPaths.size(); i++) {
			int maxFlowWeight = findMaxFlowWeight(allPaths.get(i));
			for (int j = 0; j < allPaths.get(i).size() - 1; j++) {
				Edge e = getEdge(allPaths.get(i).get(j), allPaths.get(i).get(j + 1));
				addFlow(e.getNode1(), e.getNode2(), maxFlowWeight);
				Edge pathFlow = getEdgeFlow(allPaths.get(i).get(j), allPaths.get(i).get(j + 1));
				paths[idx] = new Edge(pathFlow.getNode1() + 1, pathFlow.getNode2() + 1, pathFlow.getWeight());
				idx++;

			}
			maxflow += maxFlowWeight;
			
		}
		return maxflow;
	}

	public Edge[] removeDuplicates(Edge[] edges) {
		Edge[] uniqueEdges = new Edge[edges.length];
		int count = 0;
		for (int i = 0; i < idx; i++) {
			boolean isDuplicate = false;
			for (int j = 0; j < count; j++) {
				if (edges[i].getNode1() == uniqueEdges[j].getNode1() && edges[i].getNode2() == uniqueEdges[j].getNode2()
						&& edges[i].getWeight() == uniqueEdges[j].getWeight()) {
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate && edges[i].getWeight() != 0) {
				uniqueEdges[count++] = edges[i];
			}
		}
		return Arrays.copyOf(uniqueEdges, count);
	}

	public static void main(String args[]) {
		Edge[] edges = new Edge[10];
		edges[0] = new Edge(1, 2, 16);
		edges[1] = new Edge(1, 6, 13);
		edges[2] = new Edge(2, 3, 12);
		edges[3] = new Edge(2, 6, 10);
		edges[4] = new Edge(3, 4, 20);
		edges[5] = new Edge(3, 6, 9);
		edges[6] = new Edge(5, 3, 7);
		edges[7] = new Edge(5, 4, 4);
		edges[8] = new Edge(6, 2, 4);
		edges[9] = new Edge(6, 5, 14);

		MaxFlow graph = new MaxFlow(edges, 6, 10, 1, 4);

		ArrayList<ArrayList<Integer>> allPaths = graph.findAllPaths();
		for (ArrayList<Integer> path : allPaths) {
			System.out.println(path);
		}

		int maxflow = graph.findMaxFLow();
		Edge[] list = graph.removeDuplicates(graph.paths);
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}
}