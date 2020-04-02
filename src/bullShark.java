import javax.swing.ImageIcon;

public class bullShark extends Piece {

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
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
