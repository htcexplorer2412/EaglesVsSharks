import javax.swing.ImageIcon;

public class seaEagle extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public seaEagle()
	{
		super();
		attack = 80;
		defense = 50;
		speed = 90;
		healthPoints = 400;
		maxHealthPoints = 400;
		movement = 'x';
		shortName = "SE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 016.png");
	}
}
