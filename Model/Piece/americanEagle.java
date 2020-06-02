package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>American Eagle</H1>
 * <p>
 * This class represents the piece American Eagle for Eagle team.
 * <p>
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class americanEagle extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
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
		fullName = "American Eagle";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 011.png");
		team = 'e';
	}
}