package game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.Table;

public class VSMode {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		play();
	}
	
	/**
	 * Play the application.
	 */
	public static void play() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VSMode window = new VSMode();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VSMode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1166, 611);
		frame.getContentPane().setLayout(null);
		
		// Table 1
		Table table1 = new Table();;
		frame.getContentPane().add(table1);
		// table1.setEnabled(false);
		
		// Score
		JPanel panel = new JPanel();
		panel.setBounds(500, 0, 150, 572);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Temporizador");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(21, 255, 108, 61);
		panel.add(lblNewLabel);
		
		// Table 2
		Table table2 = new Table(660, 0);
		frame.getContentPane().add(table2);
			
	
		
	}
}
