package game;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Card extends JToggleButton {
	protected int iconCode;
	protected boolean locked = false;

	public Card(int iconCode, boolean locked) {
		super("", false);
		this.iconCode = iconCode;
		this.locked = locked;
		setCardIcon();
	}
	
	public Card(int iconCode) {
		this(iconCode, false);
	}
	
	public Card() {
		this(1);
	}
	
	private void setCardIcon() {
		setIcon(new ImageIcon(Table.class.getResource("/img/0.png")));
		setPressedIcon(new ImageIcon(Table.class.getResource("/img/0.png")));
		// setDisabledIcon(new ImageIcon(Table.class.getResource("/img/0.png")));
		// setDisabledIcon(new ImageIcon(Table.class.getResource("/img/" + iconCode + ".png")));
		setSelectedIcon(new ImageIcon(Table.class.getResource("/img/" + iconCode + ".png")));
		setDisabledSelectedIcon(new ImageIcon(Table.class.getResource("/img/" + iconCode + ".png")));
	}
	
	public boolean isPair(Card card) {
		return this.iconCode == card.iconCode;
	}
	
	public void setLocked(boolean lock) {
		locked = lock;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void reset() {
		setSelected(false);
		setEnabled(true);
		// setCardIcon();
	}
	
}
