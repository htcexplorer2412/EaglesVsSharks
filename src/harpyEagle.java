import javax.swing.ImageIcon;

public class harpyEagle extends Piece {

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
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
