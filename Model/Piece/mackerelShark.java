package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Mackerel Shark</H1>
 * <p>
 * This class represents the piece Mackerel Shark for Shark team.
 * <p>
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class mackerelShark extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public mackerelShark()
	{
		super();
		attack = 60;
		defense = 80;
		speed = 65;
		healthPoints = 700;
		maxHealthPoints = 700;
		movement = '+';
		shortName = "KS";
		fullName = "Mackerel Shark";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark3.png");
		team = 's';
	}
}
