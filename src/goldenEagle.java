import javax.swing.ImageIcon;

public class goldenEagle extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public goldenEagle()
	{
		super();
		attack = 75;
		defense = 75;
		speed = 70;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = 'x';
		shortName = "GE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 015.png");
	}
}
