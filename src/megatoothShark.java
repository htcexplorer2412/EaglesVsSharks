import javax.swing.ImageIcon;

public class megatoothShark extends Piece {

	public megatoothShark()
	{
		super();
		attack = 85;
		defense = 60;
		speed = 70;
		healthPoints = 500;
		maxHealthPoints = 500;
		movement = 'x';
		shortName = "TS";
		pieceAlive = true;
		pieceIcon = new ImageIcon("C:/Users/Ayam/Documents/RMIT/Sem 3/OOSD/Assignment/eagleLogo.png");
	}
}
