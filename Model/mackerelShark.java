package Model;

import javax.swing.ImageIcon;

public class mackerelShark extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public mackerelShark()
	{
		super();
		attack = 60;
		defense = 80;
		speed = 65;
		healthPoints = 700;
		maxHealthPoints = 700;
		movement = '+';
		shortName = "KS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark3.png");
	}
}
