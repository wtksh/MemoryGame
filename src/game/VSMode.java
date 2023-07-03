package game;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.Table;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VSMode {
	protected JFrame frmVSMode;
	protected Table table1, table2;
	protected ScorePanel scorePanel1, scorePanel2;
	protected int playCounter = 0;
	

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
					window.frmVSMode.setVisible(true);
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
	 * Initialize the contents of the frmVSMode.
	 */
	private void initialize() {
		frmVSMode = new JFrame();
		frmVSMode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVSMode.setResizable(false);
		frmVSMode.setBounds(100, 100, 1433, 611);
		frmVSMode.getContentPane().setLayout(null);
		
		// Score 1
		scorePanel1 = new ScorePanel();
		scorePanel1.setLocation(0, 176);
		frmVSMode.getContentPane().add(scorePanel1);
		
		// Table 1
		table1 = new Table(scorePanel1, true, this);
		table1.setLocation(170, 0);
		frmVSMode.getContentPane().add(table1);
		table1.setEnabled(false);
		table1.addComponentListener(null);
		
		// Score 2
		scorePanel2 = new ScorePanel();
		scorePanel2.setLocation(1256, 176);
		frmVSMode.getContentPane().add(scorePanel2);
		
		// Table 2
		table2 = new Table(660, 0, scorePanel2, true, this);
		table2.setLocation(756, 0);;
		frmVSMode.getContentPane().add(table2);
		table2.setEnabled(false);
		
		// VS
		JLabel labelVS = new JLabel("VS");
		labelVS.setBounds(670, 260, 76, 51);
		frmVSMode.getContentPane().add(labelVS);
		labelVS.setFont(new Font("Tahoma", Font.PLAIN, 32));
		labelVS.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton startButton = new JButton("INICIAR");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 36));
		startButton.setBounds(610, 241, 197, 89);
		frmVSMode.getContentPane().add(startButton);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButton.setVisible(false);
				table1.setEnabled(true);
				table2.setEnabled(true);
			}
		});
	}
	
	protected static void compareTables(Table table1, Table table2) {
		if (table1.playCounter == 3) {
			table1.playCounter = 0;
			table1.setEnabled(false);
			table2.setEnabled(true);
		}
		else if (table2.playCounter == 3) {
			table2.playCounter = 0;
			table2.setEnabled(false);
			table1.setEnabled(true);
		}
	}
}
