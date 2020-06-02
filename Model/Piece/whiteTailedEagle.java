package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>White Tailed Eagle</H1>
 * <p>
 * This class represents the piece White Tailed Eagle for Eagle team.
 * <p>
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class whiteTailedEagle extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public whiteTailedEagle()
	{
		super();
		attack = 70;
		defense = 80;
		speed = 65;
		healthPoints = 500;
		maxHealthPoints = 500;
		movement = '+';
		shortName = "WE";
		fullName = "White Tailed Eagle";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 014.png");
		team = 'e';
	}
}
