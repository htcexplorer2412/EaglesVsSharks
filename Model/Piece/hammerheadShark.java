package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Hammerhead Shark</H1>
 * <p>
 * This class represents the piece Hammerhead Shark for Shark team.
 * <p>
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class hammerheadShark extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
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
		fullName = "Hammerhead Shark";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark1.png");
		team = 's';
	}
}
