package Model;

import javax.swing.ImageIcon;

public class makoShark extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public makoShark()
	{
		super();
		attack = 80;
		defense = 50;
		speed = 90;
		healthPoints = 400;
		maxHealthPoints = 400;
		movement = 'x';
		shortName = "MS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark4.png");
	}
}
