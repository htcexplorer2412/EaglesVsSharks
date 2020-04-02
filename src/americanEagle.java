import javax.swing.ImageIcon;

public class americanEagle extends Piece {

	//private ImageIcon americanEagle = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	
	public americanEagle()
	{
		super();
		attack = 85;
		defense = 70;
		speed = 75;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = '+';
		shortName = "AE";
		pieceAlive = true;
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
