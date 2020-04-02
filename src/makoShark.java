import javax.swing.ImageIcon;

public class makoShark extends Piece {

	public makoShark()
	{
		super();
		attack = 80;
		defense = 50;
		speed = 90;
		healthPoints = 400;
		maxHealthPoints = 400;
		movement = 'x';
		shortName = "MS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
