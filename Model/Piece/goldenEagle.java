package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Golden Eagle</H1>
 * <p>
 * This class represents the piece Golden Eagle for Eagle team.
 * <p>
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class goldenEagle extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public goldenEagle()
	{
		super();
		attack = 75;
		defense = 75;
		speed = 70;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = 'x';
		shortName = "GE";
		fullName = "Golden Eagle";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 015.png");
		team = 'e';
	}
}
