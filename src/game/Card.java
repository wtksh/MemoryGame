package game;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Card extends JToggleButton {
	protected int iconCode;

	public Card(int iconCode) {
		super("", false);
		this.iconCode = iconCode;
		setCardIcon();
	}
	
	public Card() {
		this(1);
	}
	
	private void setCardIcon() {
		setIcon(new ImageIcon(Table.class.getResource("/img/0.png")));
		setPressedIcon(new ImageIcon(Table.class.getResource("/img/0.png")));
		setDisabledIcon(new ImageIcon(Table.class.getResource("/img/" + iconCode + ".png")));
		setSelectedIcon(new ImageIcon(Table.class.getResource("/img/" + iconCode + ".png")));
		setDisabledSelectedIcon(new ImageIcon(Table.class.getResource("/img/" + iconCode + ".png")));
	}
	
	public boolean isPair(Card card) {
		return this.iconCode == card.iconCode;
	}
	
	public void reset() {
		setSelected(false);
		setEnabled(true);
		// setCardIcon();
	}
	
}
