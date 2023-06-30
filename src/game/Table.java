package game;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Table extends JPanel implements Score {
	protected static final int amountOfCards = 30;
	protected Card[] cards = new Card[amountOfCards];
	protected Card[] selectedCards = new Card[2];
	protected int selectedCardsCounter = 0;
	protected int attempts = 0;
	protected int mistakes = 0;
	protected ScorePanel scorePanel;
	
	/**
	 * Create the panel.
	 */
	public Table(int x, int y, ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		initializeCards(cards);
		initializeTable(x, y);
	}
	
	public Table(ScorePanel scorePanel) {
		this(0, 0, scorePanel);
	}
	
	/**
	 * Initialize the table cards.
	 */
	private void initializeCards(Card[] cards) {
		int[] pairs = generateRandomPairs();
		shuffle(pairs); //
		
		for (int i = 0; i < amountOfCards; i++) {
			cards[i] = new Card(pairs[i]);
			
			cards[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedCards[selectedCardsCounter] = (Card) e.getSource();
					selectedCards[selectedCardsCounter++].setEnabled(false);
					System.out.println(e.getSource());
										
					if (selectedCardsCounter == 2) {
						if (selectedCards[0].isPair(selectedCards[1])) {
							selectedCards[0].setEnabled(false);
							selectedCards[1].setEnabled(false);
						}
						else {
							// selectedCards[0].reset();
							// selectedCards[1].reset();
							addMistakes();
						}
						addAttempts();
						selectedCardsCounter = 0;
						updateScore();
					}
				}
			});
		}
	}
	
	
	/**
	 * Initialize the game table.
	 */
	private void initializeTable(int x, int y) {
		setLayout(null);
		setBounds(x, y, 490, 572);
		
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 6; column++) {
				int pos = 6 * row + column;
				cards[pos].setBounds(10 + 80 * column, 11 + 113 * row, 70, 102);
				add(cards[pos]);
			}
		}
	}
	
	/**
	 * Generate 15 random pairs.
	 */
	private int[] generateRandomPairs() {
		int numOfPairs = amountOfCards / 2;
		int[] list = new int[numOfPairs];
		Random rand = new Random();
		
		// Generates 15 non-duplicate random numbers
		int i = 0;
		while (i < numOfPairs) {
			int randomNum = rand.nextInt(1, 53);
			if (!isDuplicate(list, randomNum)) {
				list[i++]  = randomNum;
			}
		}
		
		int[] pairs = new int[amountOfCards];
		for (int j = 0; j < numOfPairs; j ++) {
			pairs[j] = pairs[j + numOfPairs] = list[j];
			// System.out.println(j + ", " + pairs[j] + ", " + pairs[j + numOfPairs]);
		}
		return pairs;
	}
	
	/**
	 * Checks for duplicate numbers in an array.
	 */
	private boolean isDuplicate(int[] array, int number) {
		for (int element : array) {
			if (number == element)
				return true;
		}
		return false;
	}
	
	/**
	 * Shuffles the positions of an array.
	 */
	private void shuffle(int[] array) {
		Random rand = new Random();
				
		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			int temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
	}
	

	/**
	 * Set table to enabled.
	 */
	public void setEnabled(boolean enabled) {
		for (int i = 0; i < amountOfCards; i++) {
			cards[i].setEnabled(enabled);
		}
	}

	@Override
	public int calculateScore() {
		int score = 20 * (attempts - mistakes) - 5 * mistakes;
		return Math.max(score, 0);
	}

	@Override
	public void addAttempts() {
		attempts++; 
	}

	@Override
	public void addMistakes() {
		mistakes++;
	}	
	
	public void updateScore() {
		scorePanel.score.setText(String.format("%04d", calculateScore()));
		scorePanel.attempts.setText(String.format("Tentativas: %d", attempts));
		scorePanel.mistakes.setText(String.format("Erros: %d", mistakes));
	}

}
