import javax.swing.ImageIcon;

public class greyReefShark extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark2.png");
	}	
}
