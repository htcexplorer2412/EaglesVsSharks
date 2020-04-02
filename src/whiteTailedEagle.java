import javax.swing.ImageIcon;

public class whiteTailedEagle extends Piece {

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
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
