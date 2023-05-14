package algo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class North extends JPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_field_numNode = new JTextField();
	private JButton buttonSend = new JButton("send");
	private JLabel numNode = new JLabel(" number of Node");
	private JComboBox<String> boxAlgoritthm = new JComboBox<String>();

	Graph d;
	private String ExpressionReguliere = "([0-9]*)";

	public North(Graph d) {
		this.d = d;
		setBackground(Color.black);
		boxAlgoritthm.addItem(("MaxFlow"));
		boxAlgoritthm.addItem(("MaxFlowFulkerson"));
		boxAlgoritthm.addItem(("Kruskal"));

		boxAlgoritthm.setBackground(Color.WHITE);

		boxAlgoritthm.addActionListener(this);
		buttonSend.addActionListener(this);

		this.add(boxAlgoritthm);
		numNode.setForeground(Color.white);
		this.add(numNode);

		text_field_numNode.setColumns(4);
		this.add(text_field_numNode);
		this.add(buttonSend);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boxAlgoritthm) {
			d.setChoice((String) boxAlgoritthm.getSelectedItem());
		} else if (e.getSource() == buttonSend) {
			String s = text_field_numNode.getText();
			if (s.matches(ExpressionReguliere) && s.length() > 0) {
				d.setNumNode(Integer.parseInt(s));
				d.edges = new Edge[2500];
				d.setNumEdgeFound(0);
				d.repaint();
			}
		}
	}
}
