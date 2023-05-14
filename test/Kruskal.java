package test;

public class Kruskal {
	public int numNode = 50;
	public Edge[] edgeOutput = new Edge[numNode];
	public int[] nodes = new int[numNode];

	public Kruskal(Edge[] graph, int numNode, int numEdge) {

		kruskal(graph, numNode, numEdge, nodes, edgeOutput);
	}

	public void init(int[] t) {
		for (int i = 0; i < t.length; i++) {
			t[i] = -1;
		}
	}

	public void sortWeightGraph(Edge[] t, int numNode) {

		int j;
		int k;
		Edge a;
		for (int i = 0; i < numNode; i++) {
			j = i;
			for (k = j; k < numNode; k++) {
				if (t[k].getWeight() < t[j].getWeight()) {
					j = k;
				}
			}
			a = t[j];
			t[j] = t[i];
			t[i] = a;
		}
		return;
	}

	public boolean isCycle(int[] t, int a, int b) {
		int i = a;
		int j = b;
		while (t[i] > 0) {
			i = t[i];
		}
		while (t[j] > 0) {
			j = t[j];
		}

		if (i != j) {
			t[i] = j;
		}
		return i != j;
	}

	public boolean kruskal(Edge[] g, int numNode, int numEdge, int[] nodes, Edge[] array_edge_output) {
		init(nodes);
		int node = 0;
		int edge = 0;
		sortWeightGraph(g, numEdge);
		while (node < numEdge && edge < numNode) {
			if (isCycle(nodes, g[node].getNode1(), g[node].getNode2())) {
				array_edge_output[edge] = g[node];
				edge++;

			}
			node++;
		}
		return edge == numNode - 1;
	}
	public static void main(String[] args) {
		Edge[] edges = new Edge[10];
		edges[0] = new Edge(0, 1, 16);
		edges[1] = new Edge(0, 2, 13);
		edges[2] = new Edge(1, 3, 12);
		edges[3] = new Edge(1, 2, 10);
		edges[4] = new Edge(2, 1, 4);
		edges[5] = new Edge(2, 4, 14);
		edges[6] = new Edge(3, 2, 9);
		edges[7] = new Edge(3, 5, 20);
		edges[8] = new Edge(4, 3, 7);
		edges[9] = new Edge(4, 5, 4);
		
		Kruskal kruskal = new Kruskal( edges, 5, 10);
		for (int i = 0; i < edges.length; i++) {
			System.out.println(kruskal.edgeOutput[i]);
		}
	}
}