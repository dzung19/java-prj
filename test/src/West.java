package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class West extends JPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lableSorce = new JLabel("Source");
	private JLabel lableSink = new JLabel("Sink");
	private JTextField source = new JTextField();
	private JTextField sink = new JTextField();
	private JButton buttonSend = new JButton("send");

	Graph graph;
	private String ExpressionReguliere = "([0-9]*)";

	public West(Graph graph) {
		this.graph = graph;
		setBackground(Color.black);

		JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
		buttonPanel.setBackground(Color.black);

		buttonPanel.add(lableSorce);
		lableSorce.setForeground(Color.white);
		buttonPanel.add(source);
		source.setColumns(4);
		
		buttonPanel.add(lableSink);
		lableSink.setForeground(Color.white);
		buttonPanel.add(sink);
		sink.setColumns(4);

		JLabel empty = new JLabel();
		buttonPanel.add(empty);
		
		buttonSend.addActionListener(this);
		buttonPanel.add(buttonSend);

        add(buttonPanel);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonSend) {
			String so = source.getText();
			String si = sink.getText();
			if ((so.matches(ExpressionReguliere) && so.length() > 0)&&(si.matches(ExpressionReguliere) && si.length() > 0)) {
				graph.source = Integer.parseInt(so);
				graph.sink = Integer.parseInt(si);
				graph.repaint();
			}
		}
	}
}
