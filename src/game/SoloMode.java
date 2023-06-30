package game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class SoloMode {
	protected JFrame frmSoloMode;
	protected Table table;
	protected ScorePanel scorePanel;
	

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
		frmSoloMode.setBounds(100, 100, 686, 611);
		frmSoloMode.getContentPane().setLayout(null);
		
		scorePanel = new ScorePanel();
		scorePanel.setBounds(500, 176, 160, 220);
		frmSoloMode.getContentPane().add(scorePanel);
		
		// Table
		table = new Table(scorePanel);
		table.setLocation(0, 0);
		frmSoloMode.getContentPane().add(table);
		
		
		
	
		
	}
}
