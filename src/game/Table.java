package game;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Table extends JPanel implements Score {
	protected static final int amountOfCards = 30;
	protected Card[] cards = new Card[amountOfCards];
	protected Card[] selectedCards = new Card[2];
	protected int selectedCardsCounter = 0;
	
	protected int attempts = 0;
	protected int mistakes = 0;
	protected ScorePanel scorePanel;

	protected int positiveScore = 0;
	protected int negativeScore = 0;
	
	protected int playCounter = 0;
	protected boolean enableVSMode;
	protected VSMode vsmode;
	
	protected boolean firstClick = true, secondClick = true;
	
	/**
	 * Create the panel.
	 */
	public Table(int x, int y, ScorePanel scorePanel, boolean enableVSMode, VSMode vsmode) {
		this.scorePanel = scorePanel;
		this.enableVSMode = enableVSMode;
		if (enableVSMode) {
			this.vsmode = vsmode;
		}
		initializeCards(cards);
		initializeTable(x, y);
	}
	
	public Table(ScorePanel scorePanel, boolean enableVSMode, VSMode vsmode) {
		this(0, 0, scorePanel, enableVSMode, vsmode);
	}
	
	public Table(ScorePanel scorePanel) {
		this(0, 0, scorePanel, false, null);
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
					// Reset preview action
					if (!firstClick) {
						if (!secondClick) {
							if (selectedCardsCounter == 2) {
								if (!selectedCards[0].isPair(selectedCards[1])) {
									selectedCards[0].reset();
									selectedCards[1].reset();
								}
								selectedCardsCounter = 0;
							}
						}
						else {
							secondClick = false;
						}
					}
					else {
						firstClick = false;
					}
					
					// Get selected card
					selectedCards[selectedCardsCounter] = (Card) e.getSource();
					selectedCards[selectedCardsCounter++].setEnabled(false);
					
					// Pair check
					if (selectedCardsCounter == 2) {
						if (selectedCards[0].isPair(selectedCards[1])) {
							selectedCards[0].setEnabled(false);
							selectedCards[0].setLocked(true);
							selectedCards[1].setEnabled(false);
							selectedCards[1].setLocked(true);
							positiveScore += 50;
						}
						else {
							addMistakes();
							negativeScore += 5;
						}
						addAttempts();
						updateScore();
						
						
						// VS Mode enabled
						if (enableVSMode) {
							playCounter++;
							VSMode.compareTables(vsmode.table1, vsmode.table2);
						}
						
						// Game end check
						if (attempts - mistakes == amountOfCards / 2) {
							// VS Mode
							if (enableVSMode) {
								JOptionPane.showMessageDialog(null, "Game ended!\nPlayer1 Score: " + vsmode.table1.calculateScore() + "\nPlayer2 Score: " + vsmode.table2.calculateScore());
								vsmode.table1.setEnabled(false);
								vsmode.table1.setSelected(true);
								vsmode.table2.setEnabled(false);
								vsmode.table2.setSelected(true);
							}
							// Solo Mode
							else {
								JOptionPane.showMessageDialog(null, "Game ended!\nPlayer Score: " + calculateScore());
								setEnabled(false);
								setSelected(true);
							}
						}
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
	 * Set all table cards to enabled, except locked cards.
	 */
	public void setEnabled(boolean enabled) {
		for (int i = 0; i < amountOfCards; i++) {
			if (!cards[i].isLocked()) {
				cards[i].setEnabled(enabled);
			}
		}
	}

	/**
	 * Set all table card to selected, except locked cards.
	 */
	public void setSelected(boolean selected) {
		for (int i = 0; i < amountOfCards; i++) {
			if (!cards[i].isLocked()) {
				cards[i].setSelected(selected);
			}
		}
	}
	
	@Override
	public int calculateScore() {
		int score = positiveScore - negativeScore;
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
	
	/**
	 * Update the score.
	 */
	public void updateScore() {
		scorePanel.score.setText(String.format("%04d", calculateScore()));
		scorePanel.attempts.setText(String.format("Tentativas: %d", attempts));
		scorePanel.mistakes.setText(String.format("Erros: %d", mistakes));
	}
	
}
