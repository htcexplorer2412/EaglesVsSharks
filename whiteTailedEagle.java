import javax.swing.ImageIcon;

public class whiteTailedEagle extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public whiteTailedEagle()
	{
		super();
		attack = 70;
		defense = 80;
		speed = 65;
		healthPoints = 500;
		maxHealthPoints = 500;
		movement = '+';
		shortName = "WE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 014.png");
	}
}
