package game;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ScorePanel extends JPanel {
	protected JLabel title;
	protected JLabel score;
	protected JLabel attempts;
	protected JLabel mistakes;
	/**
	 * Create the panel.
	 */
	public ScorePanel() {
		setLayout(null);
		setBounds(0, 0, 160, 220);
		
		title = new JLabel("SCORE");
		title.setFont(new Font("Tahoma", Font.PLAIN, 28));
		title.setBounds(22, 0, 115, 53);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
		
		score = new JLabel("0000");
		score.setFont(new Font("Tahoma", Font.PLAIN, 28));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(33, 53, 93, 53);
		add(score);
		
		attempts = new JLabel("Tentativas: 0");
		attempts.setFont(new Font("Tahoma", Font.PLAIN, 18));
		attempts.setBounds(22, 117, 115, 39);
		add(attempts);
		
		mistakes = new JLabel("Erros: 0");
		mistakes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mistakes.setBounds(39, 167, 81, 39);
		add(mistakes);
	}
	
	
}
