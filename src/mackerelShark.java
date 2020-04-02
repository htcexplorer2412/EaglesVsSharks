import javax.swing.ImageIcon;

public class mackerelShark extends Piece {

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
		pieceAlive = true;
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
