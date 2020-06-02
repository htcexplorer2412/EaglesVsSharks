package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Harpy Eagle</H1>
 * <p>
 * This class represents the piece Harpy Eagle for Eagle team.
 * <p>
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class harpyEagle extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public harpyEagle()
	{
		super();
		attack = 60;
		defense = 80;
		speed = 75;
		healthPoints = 700;
		maxHealthPoints = 700;
		movement = '+';
		shortName = "HE";
		fullName = "Harpy Eagle";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 013.png");
		team = 'e';
	}
}
