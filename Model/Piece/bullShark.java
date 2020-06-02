package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Bull Shark</H1>
 * <p>
 * This class represents the piece Bull Shark for Shark team.
 * <p>
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class bullShark extends Piece
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public bullShark()
	{
		super();
		attack = 55;
		defense = 85;
		speed = 50;
		healthPoints = 700;
		maxHealthPoints = 700;
		movement = 'x';
		shortName = "BS";
		fullName = "Bull Shark";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark0.png");
		team = 's';
	}
}
