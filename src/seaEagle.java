import javax.swing.ImageIcon;

public class seaEagle extends Piece {

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
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
