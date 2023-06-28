package game;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

// import game.SoloMode;

import java.util.Random;

public class Table extends JPanel {
	protected static final int amountOfCards = 30;
	protected JToggleButton[] cards = new JToggleButton[amountOfCards];

	/**
	 * Create the panel.
	 */
	public Table(int x, int y) {
		initializeCards(cards);
		initializeTable(x, y);
	}
	
	public Table() {
		this(0, 0);
	}
	
	private void initializeCards(JToggleButton[] cards) {
		for (int i = 0; i < amountOfCards; i++) {
			cards[i] = new JToggleButton("", true);
		}
	}
	
	private void initializeTable(int x, int y) {
		setLayout(null);
		setBounds(x, y, 490, 572);
		
		int[] pairs = generateRandomPairs();
		shuffle(pairs);

		int i = 0;
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 6; column++) {
				int pos = 6 * row + column;
				cards[pos].setBounds(10 + 80 * column, 11 + 113 * row, 70, 102);
				add(cards[pos]);
				cards[pos].setSelectedIcon(new ImageIcon(SoloMode.class.getResource("/img/0.png")));
				cards[pos].setIcon(new ImageIcon(SoloMode.class.getResource("/img/" + pairs[i++] + ".png")));
			}
		}
	}
	
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
	
	private boolean isDuplicate(int[] array, int number) {
		for (int element : array) {
			if (number == element)
				return true;
		}
		return false;
	}
	
	private void shuffle(int[] cards) {
		Random rand = new Random();
				
		for (int i = 0; i < cards.length; i++) {
			int randomIndexToSwap = rand.nextInt(cards.length);
			int temp = cards[randomIndexToSwap];
			cards[randomIndexToSwap] = cards[i];
			cards[i] = temp;
		}
	}
	
	public void setEnabled(boolean enabled) {
		for (int i = 0; i < amountOfCards; i++) {
			cards[i].setEnabled(enabled);
		}
	}
	
	
}
