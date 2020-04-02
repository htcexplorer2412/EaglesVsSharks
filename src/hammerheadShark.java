import javax.swing.ImageIcon;

public class hammerheadShark extends Piece {

	public hammerheadShark()
	{
		super();
		attack = 75;
		defense = 75;
		speed = 75;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = '+';
		shortName = "HS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
