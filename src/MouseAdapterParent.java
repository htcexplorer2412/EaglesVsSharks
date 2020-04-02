import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MouseAdapterParent extends MouseAdapter {
	
	private boolean diceRolled = false;
	private char isClicked = 'n';
	private JLabel icon;
	private JTextField name;
	private Tile[][] tile;
	private int prevPointX, prevPointY;
	
	public void storeTiles(Tile[][] t)
	{
		tile = t;
	}
	
	
	//Involve diceRolled calculation
	public void mouseClicked(MouseEvent e)
	{
		JPanel source = (JPanel) e.getComponent();
		
		int pointY = source.getX()/42;
		int pointX = source.getY()/42;
		
		System.out.println(pointX + "," + pointY);
		
		if(diceRolled)
		{
			if(isClicked == 'n')
			{
				try
				{
					if(tile[pointX][pointY].whatIsOnTile().toString().charAt(1) == 'E' || tile[pointX][pointY].whatIsOnTile().toString().charAt(1) == 'S')
					{
						tile[pointX][pointY].highlightPiece(true);
						icon = tile[pointX][pointY].getIcon();
						name = tile[pointX][pointY].getJTextField();
						isClicked = 'f';
						prevPointX = pointX;
						prevPointY = pointY;
					}
				}
				catch(Exception ex)
				{
					
				}
			}
			else if(isClicked == 'f')
			{
				if(tile[pointX][pointY].whatIsOnTile().equals(this.name.getText()))
				{
					tile[pointX][pointY].highlightPiece(false);
					isClicked = 'n';
				}
				else if(tile[pointX][pointY].whatIsOnTile().toString().equals(""))
				{
					tile[pointX][pointY].putPieceOnTile(this.icon, this.name);
					tile[prevPointX][prevPointY].removePieceFromTile();
					tile[pointX][pointY].highlightPiece(false);
					//isClicked = 'n' or isClicked = 's'
				}
			}
		}
	}
	
	public void setDiceRolledValue(boolean value)
	{
		this.diceRolled = value;
	}
	
	
	/*public void mouseEntered(MouseEvent e)
	{
		//Object source = e.getSource();
		Tile tile = (Tile) e.getSource();
		
		tile.highlightPiece(true);
	}
	
	public void mouseExited(MouseEvent e)
	{
		//Object source = e.getSource();
		Tile tile = (Tile) e.getSource();
		
		tile.highlightPiece(false);
	}*/
}
