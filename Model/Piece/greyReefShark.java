package Model.Piece;

import javax.swing.ImageIcon;

/**
 * <H1>Grey Reef Shark</H1>
 * <p>
 * This class represents the piece Grey Reef Shark for Shark team.
 * <p>
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-04-21
 */
public class greyReefShark extends Piece 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Sets the attributes for this piece like attack, defense, speed, health points, movement orientation and other attributes
	 */
	public greyReefShark()
	{
		super();
		attack = 85;
		defense = 70;
		speed = 75;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = '+';
		shortName = "GS";
		fullName = "Grey Reef Shark";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark2.png");
		team = 's';
	}	
}
