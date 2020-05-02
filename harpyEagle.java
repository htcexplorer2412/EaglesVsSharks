import javax.swing.ImageIcon;

public class harpyEagle extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public harpyEagle()
	{
		super();
		attack = 60;
		defense = 80;
		speed = 75;
		healthPoints = 700;
		maxHealthPoints = 700;
		movement = '+';
		shortName = "WE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 013.png");
	}
}
