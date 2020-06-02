package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Megatooth Shark</H1>
 * <p>
 * This class represents the piece Megatooth Shark for Shark team.
 * <p>
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class megatoothShark extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
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
		fullName = "Megatooth Shark";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark6.png");
		team = 's';
	}
}
