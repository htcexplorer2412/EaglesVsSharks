import javax.swing.ImageIcon;

public class greyReefShark extends Piece {

	public greyReefShark()
	{
		super();
		attack = 85;
		defense = 70;
		speed = 75;
		healthPoints = 600;
		maxHealthPoints = 600;
		movement = '+';
		shortName = "GS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}	
}
