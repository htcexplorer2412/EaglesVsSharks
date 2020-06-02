package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Sea Eagle</H1>
 * <p>
 * This class represents the piece Sea Eagle for Eagle team.
 * <p>
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class seaEagle extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public seaEagle()
	{
		super();
		attack = 80;
		defense = 50;
		speed = 90;
		healthPoints = 400;
		maxHealthPoints = 400;
		movement = 'x';
		shortName = "SE";
		fullName = "Sea Eagle";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 016.png");
		team = 'e';
	}
}
