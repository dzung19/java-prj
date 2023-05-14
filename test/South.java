package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class South extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField text_field_1st_node = new JTextField("");
	private JTextField text_field_2nd_node = new JTextField("");
	private JTextField text_field_weight = new JTextField("");

	private JButton buttonSend = new JButton("send");
	private JButton buttonStep = new JButton("step");

	private JLabel lableNode1 = new JLabel("1st node");
	private JLabel lableNode2 = new JLabel("2nd node");
	private JLabel weight = new JLabel("weight");

	private String ExpressionReguliere = "([1-9][0-9]*)";
	Graph graph;
	public int numNode = 50;
	Edge[] edges = new Edge[numNode * numNode];
	MaxFlow m;
	MaxFlowForkFulkerson mf;
	Kruskal k;

	public South(Graph graph) {
		this.graph = graph;
		initArbre(edges);
		setBackground(Color.black);
		buttonSend.addActionListener(this);
		buttonStep.addActionListener(this);

		lableNode1.setForeground(Color.white);
		this.add(lableNode1);
		text_field_1st_node.setColumns(4);
		this.add(text_field_1st_node);

		lableNode2.setForeground(Color.white);
		this.add(lableNode2);
		text_field_2nd_node.setColumns(4);
		this.add(text_field_2nd_node);

		weight.setForeground(Color.white);
		this.add(weight);
		text_field_weight.setColumns(4);

		this.add(text_field_weight);
		this.add(buttonSend);
		this.add(buttonStep);
	}

	public void initArbre(Edge[] edges) {
		for (int i = 0; i < edges.length; i++) {
			edges[i] = null;
		}
	}

	public void actionPerformed(ActionEvent event) {
		if (graph.startAgain) {
			initArbre(edges);
			graph.startAgain = false;
		}
		if (event.getSource() == buttonSend) {
			String c1 = text_field_1st_node.getText();
			String c2 = text_field_2nd_node.getText();
			String c3 = text_field_weight.getText();

			if (c1.matches(ExpressionReguliere) && c2.matches(ExpressionReguliere) && c3.matches(ExpressionReguliere)) {

				int node1 = Integer.parseInt(c1);
				int node2 = Integer.parseInt(c2);
				int weight = Integer.parseInt(c3);

				if (node1 <= graph.getNumNode() && node2 <= graph.getNumNode() && node1 != node2) {
					Edge a = new Edge(node1, node2, weight);

					boolean already = false;
					int location = 0;
					int i = 0;

					while (edges[i] != null && i < edges.length) {
						if ((edges[i].getNode1() == a.getNode1()) && (edges[i].getNode2() == a.getNode2())) {
							location = i;
							already = true;
						}
						i++;
					}
					if (!already) {
						edges[graph.getNumEdge()] = a;
						graph.addEdge(a, graph.getNumEdge());

					} else {
						edges[location].setWeight(weight);
						graph.modifWeight(location, weight);
					}

					graph.repaint();
				}
			}

		} else if (event.getSource() == buttonStep) {
			if (edges[0] != null) {
				if (graph.getNumEdgeFound() == 0) {
					if (graph.getChoice().equals("MaxFlow")) {
						m = new MaxFlow(edges, graph.getNumNode(), graph.getNumEdge(), graph.source, graph.sink);
						ArrayList<ArrayList<Integer>> allPaths = m.findAllPaths();
						graph.maxflow = m.findMaxFLow();
						graph.final_edges = m.removeDuplicates(m.paths);
					} else if (graph.getChoice().equals("MaxFlowFulkerson")) {
						mf = new MaxFlowForkFulkerson(edges, graph.getNumNode(), graph.getNumEdge(), graph.source, graph.sink);
						graph.maxflow = mf.max_flow;
						graph.final_edges = mf.paths.toArray(new Edge[0]);
					} else if (graph.getChoice().equals("Kruskal")) {
						k = new Kruskal(edges, graph.getNumNode(), graph.getNumEdge());
						graph.final_edges = k.edgeOutput;
					}

					graph.hasEnd = true;
				}

				if (graph.final_edges[graph.getNumEdgeFound()] != null) {
					graph.setNumEdgeFound(graph.getNumEdgeFound() + 1);
				}
				graph.repaint();
			}
		}
	}
}