import javax.swing.ImageIcon;

public class australianHawk extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public australianHawk()
	{
		super();
		attack = 55;
		defense = 85;
		speed = 50;
		healthPoints = 700;
		maxHealthPoints = 700;
		movement = 'x';
		shortName = "UE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("src/eagle-icons/Sequence 010.png");
	}
}
