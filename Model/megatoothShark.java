package Model;

import javax.swing.ImageIcon;

public class megatoothShark extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public megatoothShark()
	{
		super();
		attack = 85;
		defense = 60;
		speed = 70;
		healthPoints = 500;
		maxHealthPoints = 500;
		movement = 'x';
		shortName = "TS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark6.png");
	}
}
