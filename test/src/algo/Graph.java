package algo;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Graph extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Edge[] edges;
	private int numNode = 0;
	public int numEdge = 0;

	Edge[] final_edges = new Edge[2500];
	private int num_edge_found = 0;
	public boolean hasEnd = false;
	public boolean startAgain = false;

	private String choice = "MaxFlow";

	public int source;
	public int sink;
	public int maxflow;

	public ArrayList<Edge> doubleEdgeList = new ArrayList<>();

	public Graph() {
		edges = new Edge[2500];

		setBackground(Color.white);
	}

	public void paint(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int width = getSize().width;
		int height = getSize().height;
		double angle = 0;

		g.setColor(Color.black);
		g.drawString("Source :" + source, 10, 20);
		g.drawString("Sink :" + sink, 10, 40);
		g.drawString("Max Flow :" + maxflow, 10, 60);

//		Vẽ cạnh
		for (int i = 0; i < numEdge; i++) {
			int node1 = edges[i].getNode1();
			int node2 = edges[i].getNode2();

			angle = (node1 - 1) * (2 * Math.PI / numNode);
			double angle1 = (node2 - 1) * (2 * Math.PI / numNode);

			g2.setColor(Color.gray);

			int x = (int) ((width / 2) - ((width / 4) * Math.cos(angle)) + 15);

			int y = (int) (height / 2 - (height / 4) * Math.sin(angle) + 15);

			int x2 = (int) ((width / 2) - ((width / 4) * Math.cos(angle1)) + 15);

			int y2 = (int) (height / 2 - (height / 4) * Math.sin(angle1) + 15);

//			Đường đôi
			boolean doubleEdge = false;
			for (int j = 0; j < i; j++) {
				if (edges[j].getNode2() == node1 && edges[j].getNode1() == node2) {
					doubleEdge = true;
					doubleEdgeList.add(edges[i]);
					break;
				}
			}
			

			if (doubleEdge) {

				g2.drawLine(x - 5, y - 5, x2 - 5, y2 - 5);

				g.setColor(Color.gray);

				g.drawString("     " + (edges[i].getWeight()), (int) ((x + x2) / 2 + 5), (int) ((y + y2) / 2));

				Graphics2D g2d = (Graphics2D) g;
				Polygon arrowHead = new Polygon();
				arrowHead.addPoint(-15, 0);
				arrowHead.addPoint(-25, 5);
				arrowHead.addPoint(-25, -5);
				g2d.translate(x2 - 5, y2 - 5);
				g2d.rotate(Math.atan2(y2 - y, x2 - x));
				g2d.fill(arrowHead);
				g2d.rotate(-Math.atan2(y2 - y, x2 - x));
				g2d.translate(-x2 + 5, -y2 + 5);

			} else {

				g2.drawLine(x, y, x2, y2);

				g.setColor(Color.gray);

				g.drawString("     " + (edges[i].getWeight()), (int) ((x + x2) / 2 + 10), (int) ((y + y2) / 2 + 10));

				Polygon arrowHead = new Polygon();
				arrowHead.addPoint(-15, 0);
				arrowHead.addPoint(-25, 5);
				arrowHead.addPoint(-25, -5);
				g2.translate(x2, y2);
				g2.rotate(Math.atan2(y2 - y, x2 - x));
				g2.fill(arrowHead);
				g2.rotate(-Math.atan2(y2 - y, x2 - x));
				g2.translate(-x2, -y2);

			}
		}

//		Tô màu đường đi
		for (int i = 0; i < getNumEdgeFound(); i++) {
			if (final_edges[i] != null) {
				int node1 = final_edges[i].getNode1();
				int node2 = final_edges[i].getNode2();

				angle = (node1 - 1) * (2 * Math.PI / numNode);
				double angle1 = (node2 - 1) * (2 * Math.PI / numNode);

				g2.setColor(Color.red);

				int x = (int) ((width / 2) - ((width / 4) * Math.cos(angle)) + 15);

				int y = (int) (height / 2 - (height / 4) * Math.sin(angle) + 15);

				int x2 = (int) ((width / 2) - ((width / 4) * Math.cos(angle1)) + 15);
				int y2 = (int) (height / 2 - (height / 4) * Math.sin(angle1) + 15);

//				Đường đôi
				boolean doubleEdge = false;
				if (doubleEdgeList.size() > 0) {
					for (Edge edge : doubleEdgeList) {
						if (edge.getNode1() == node1 && edge.getNode2() == node2) {
							doubleEdge = true;
						}
					}
				}

				if (doubleEdge) {

					g2.drawLine(x - 5, y - 5, x2 - 5, y2 - 5);

					g.setColor(Color.white);
					g.fillRect((int) ((x + x2) / 2 + 2), (int) ((y + y2) / 2 + -9), 20, 10);
					g.setColor(Color.red);
					g.drawString((final_edges[i].getWeight() + "/"), (int) ((x + x2) / 2+2), (int) ((y + y2) / 2));

					Polygon arrowHead = new Polygon();
					arrowHead.addPoint(-15, 0);
					arrowHead.addPoint(-25, 5);
					arrowHead.addPoint(-25, -5);
					g2.translate(x2 - 5, y2 - 5);
					g2.rotate(Math.atan2(y2 - y, x2 - x));
					g2.fill(arrowHead);
					g2.rotate(-Math.atan2(y2 - y, x2 - x));
					g2.translate(-x2 + 5, -y2 + 5);

				} else {

					g2.drawLine(x, y, x2, y2);

					g.setColor(Color.white);
					g.fillRect((int) ((x + x2) / 2 + 7), (int) ((y + y2) / 2 + 1), 20, 10);
					g.setColor(Color.red);
					g.drawString((final_edges[i].getWeight() + "/"), (int) ((x + x2) / 2 + 10),
							(int) ((y + y2) / 2 + 10));

					Polygon arrowHead = new Polygon();
					arrowHead.addPoint(-15, 0);
					arrowHead.addPoint(-25, 5);
					arrowHead.addPoint(-25, -5);
					g2.translate(x2, y2);
					g2.rotate(Math.atan2(y2 - y, x2 - x));
					g2.fill(arrowHead);
					g2.rotate(-Math.atan2(y2 - y, x2 - x));
					g2.translate(-x2, -y2);

				}
			}
		}

//		Vẽ node
		angle = 0;
		for (int i = 0; i < numNode; i++) {
			g2.setColor(Color.darkGray);
			g2.fillOval((int) ((width / 2) - ((width / 4) * Math.cos(angle))),
					(int) (height / 2 - (height / 4) * Math.sin(angle)), 30, 30);

			g.setColor(Color.white);
			g.drawString("" + (i + 1), (int) ((width / 2) - ((width / 4) * Math.cos(angle)) + 10),
					(int) (height / 2 - (height / 4) * Math.sin(angle) + 20));

			angle += 2 * Math.PI / numNode;
		}

	}

	public int getNumNode() {
		return numNode;
	}

	public void setNumNode(int i) {
		this.numNode = i;
	}

	public int getNumEdgeFound() {
		return num_edge_found;
	}

	public void setNumEdgeFound(int i) {
		this.num_edge_found = i;
	}

	public int getNumEdge() {
		return numEdge;
	}

	public void addEdge(Edge a, int index) {
		edges[index] = a;
		numEdge++;
	}

	public void setChoice(String s) {
		this.choice = s;
	}

	public String getChoice() {
		return choice;
	}

	public void modifWeight(int idx, int weight) {
		edges[idx].setWeight(weight);
	}
}
