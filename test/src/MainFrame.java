package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Graph graph = new Graph();
	private North north = new North(graph);
	private West west = new West(graph);
	private South south = new South(graph);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super("Graph Agorithm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 25, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());

		this.add(north, BorderLayout.NORTH);
		this.add(graph, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
		this.add(west, BorderLayout.WEST);

		this.setSize(800, 600);
	}
}
