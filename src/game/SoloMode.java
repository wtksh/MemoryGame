package game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SoloMode {
	private static int cardCounter = 0;
	protected JFrame frmSoloMode;

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
					SoloMode window = new SoloMode();
					window.frmSoloMode.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	/**
	 * Create the application.
	 */
	public SoloMode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSoloMode = new JFrame();
		frmSoloMode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSoloMode.setResizable(false);
		frmSoloMode.setBounds(100, 100, 683, 611);
		frmSoloMode.getContentPane().setLayout(null);
		
		// Table
		Table table = new Table();
		frmSoloMode.getContentPane().add(table);
		
		// Score
		JPanel panel = new JPanel();
		panel.setBounds(500, 0, 167, 572);
		frmSoloMode.getContentPane().add(panel);
		panel.setLayout(null);
		
		// Titles
		JLabel lblNewLabel = new JLabel("Temporizador");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(29, 11, 108, 61);
		panel.add(lblNewLabel);
		
		// 
		for (int i = 0; i < Table.amountOfCards; i++) {
			table.cards[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					System.out.println((e.getSource()));
					cardCounter++;
					if (cardCounter == 2) {
						System.out.println("reset");
						cardCounter = 0;
					}
				}
			});

					
				
		}
		
	}

}
