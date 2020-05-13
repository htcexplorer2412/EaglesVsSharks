package Model;

import javax.swing.ImageIcon;

public class americanEagle extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public americanEagle()
	{
		super();
		attack = 85;
		defense = 70;
		speed = 75;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = '+';
		shortName = "AE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 011.png");
	}
}