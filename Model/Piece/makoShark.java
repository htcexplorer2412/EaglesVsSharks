package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Mako Shark</H1>
 * <p>
 * This class represents the piece Mako Shark for Shark team.
 * <p>
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class makoShark extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
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
		fullName = "Mako Shark";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark4.png");
		team = 's';
	}
}
