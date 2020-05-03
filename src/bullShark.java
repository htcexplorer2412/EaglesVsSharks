import javax.swing.ImageIcon;

public class bullShark extends Piece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/shark-icons/shark0.png");
	}
}
