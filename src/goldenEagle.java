import javax.swing.ImageIcon;

public class goldenEagle extends Piece {

	public goldenEagle()
	{
		super();
		attack = 75;
		defense = 75;
		speed = 70;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = '+';
		shortName = "GE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
