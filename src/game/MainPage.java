package game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.SoloMode;
import game.VSMode;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage {

	private JFrame frmJogoDaMemoria;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frmJogoDaMemoria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogoDaMemoria = new JFrame();
		frmJogoDaMemoria.setResizable(false);
		frmJogoDaMemoria.setTitle("Jogo da Memória");
		frmJogoDaMemoria.setBounds(100, 100, 496, 566);
		frmJogoDaMemoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogoDaMemoria.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 460, 500);
		frmJogoDaMemoria.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("JOGO DA MEMÓRIA");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Bookman Old Style", Font.PLAIN, 32));
		lblTitle.setBounds(38, 47, 384, 81);
		panel.add(lblTitle);
		
		// Solo Mode
		JButton btnSoloMode = new JButton("MODO SOLO");
		btnSoloMode.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		btnSoloMode.setBounds(124, 188, 212, 48);
		panel.add(btnSoloMode);
		btnSoloMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoloMode.play();
			}
		});
		
		// VS Mode
		JButton btnVSMode = new JButton("MODO VS");
		btnVSMode.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		btnVSMode.setBounds(124, 247, 212, 48);
		panel.add(btnVSMode);
		btnVSMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VSMode.play();
			}
		});
		
		// Exit
		JButton btnExit = new JButton("SAIR");
		btnExit.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		btnExit.setBounds(124, 347, 212, 30);
		panel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		

	}
}
