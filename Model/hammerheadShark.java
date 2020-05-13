package Model;

import javax.swing.ImageIcon;

public class hammerheadShark extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public hammerheadShark()
	{
		super();
		attack = 75;
		defense = 75;
		speed = 75;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = '+';
		shortName = "HS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark1.png");
	}
}
