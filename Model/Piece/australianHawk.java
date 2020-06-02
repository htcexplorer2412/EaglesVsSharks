package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Australian Hawk</H1>
 * <p>
 * This class represents the piece Australian Hawk for Eagle team.
 * <p>
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class australianHawk extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public australianHawk()
	{
		super();
		attack = 55;
		defense = 85;
		speed = 50;
		healthPoints = 700;
		maxHealthPoints = 700;
		movement = 'x';
		shortName = "UE";
		fullName = "Australian Hawk";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 010.png");
		team = 'e';
	}
}
