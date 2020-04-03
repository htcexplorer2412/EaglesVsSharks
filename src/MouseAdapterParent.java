/*
Author - Ayam Ajmera
Date (Last modified) - 04 APR 2020

This class tracks the mouse listeners for every tile on the board. All the tiles have a same mouse listener. This class tracks the turns of each player, uses the dice value to calculate how many steps the player can move in this turn. Also tracks if the move is valid or not by communicating with the Player class. If a move is valid, then the piece is moved from the tile it previously occupied to the newly selected tile. This is done by capturing which panel is clicked by the mouse.

Tracking tiles - 
The object for MouseAdapterParent class is created in the Board class and when the object is created, it passes all the reference to the Tile objects to this class. All the Tile object are stored locally in this class and accessed when mouse click is detected.
*/
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Observer pattern implemented between class Game and class MouseAdapterParent
public class MouseAdapterParent extends MouseAdapter implements Subject {
	
	
	//Change isClicked to boolean after doing final tests
	
	private boolean diceRolled = false, whosTurn;							//whosTurn = true for Eagle team's turn and false for Shark team's turn
	private int diceValue = 0;
	private char isClicked = 'n';											//'n' is for no selection, 'f' is when a piece is clicked
	private JLabel icon;
	private JTextField name;
	private Tile[][] tile;
	private int prevPointX, prevPointY;
	private Player eagle, shark;
	private Observer o;
	
	public MouseAdapterParent(Player eagle, Player shark, Game game)
	{
		this.eagle = eagle;
		this.shark = shark;
		this.addObserver(game);
	}
	
	public void storeTiles(Tile[][] t)
	{
		tile = t;
	}
	
	
	public void mouseClicked(MouseEvent e)
	{
		//Getting the source of the click
		JPanel source = (JPanel) e.getComponent();
		
		//Translating the source's X and Y coordinates to Tile coordinates set by the Board (which is between 0 and 11, each tile is 42x42 pixels)
		int pointY = source.getX()/42;
		int pointX = source.getY()/42;
		
		//If dice is rolled and possible number of steps are not taken
		if(diceRolled && diceValue > 0)
		{
			//n is when there has been no "click"/selection
			if(isClicked == 'n')
			{
				try
				{
					//whosTurn true for eagle, false for shark
					if(this.whosTurn)
					{
						//Player Eagle can select eagle pieces only. If eagle piece selected, then the piece is highlighted and isClicked is changed to 'f'. The JLabel and JTextField component data is stored locally for further use. The coordinates are stored locally as well, to compare and validate the move.
						if(tile[pointX][pointY].whatIsOnTile().toString().charAt(1) == 'E')
						{
							tile[pointX][pointY].highlightPiece(true);
							icon = tile[pointX][pointY].getIcon();
							name = tile[pointX][pointY].getJTextField();
							isClicked = 'f';
							prevPointX = pointX;
							prevPointY = pointY;
						}
					}
					else
					{
						//Player Shark can select shark pieces only. If shark piece selected, then the piece is highlighted and isClicked is changed to 'f'. The JLabel and JTextField component data is stored locally for further use. The coordinates are stored locally as well, to compare and validate the move.
						if(tile[pointX][pointY].whatIsOnTile().toString().charAt(1) == 'S')
						{
							tile[pointX][pointY].highlightPiece(true);
							icon = tile[pointX][pointY].getIcon();
							name = tile[pointX][pointY].getJTextField();
							isClicked = 'f';
							prevPointX = pointX;
							prevPointY = pointY;
						}
					}
				}
				catch(Exception ex)
				{
					
				}
			}
			//f is when a Player has selected their piece
			else if(isClicked == 'f')
			{
				//If they select the same piece again, then the piece is deselected and now player can select some other piece to move. If they select an empty tile, then the move validity is checked first. If the piece is allowed to move in that direction, then number of steps are checked. This is where Player can move multiple pieces in one turn. Once the Player has moved steps equivalent to the value on dice, there move is finished. If the Player lands on opposing piece, then battle condition is implemented (incomplete)
				
				if(tile[pointX][pointY].whatIsOnTile().equals(this.name.getText()))
				{
					tile[pointX][pointY].highlightPiece(false);
					isClicked = 'n';
				}
				else if(tile[pointX][pointY].whatIsOnTile().toString().equals(""))
				{
					//Calculating number of steps here
					int temp = Math.abs(pointX - prevPointX) > 0 ? Math.abs(pointX - prevPointX) : Math.abs(pointY - prevPointY);
					
					//Checking if the move is valid or not and number of steps taken are less than or equal to the value shown on dice. Repaint the old and new positions if true, else print an error message and let the user select another tile which is valid.
					if(this.whosTurn)						//Eagle
					{
						if(eagle.checkValidMove(this.name, prevPointX, prevPointY, pointX, pointY))
						{
							if(temp < this.diceValue)
							{
								tile[pointX][pointY].putPieceOnTile(this.icon, this.name);
								tile[prevPointX][prevPointY].removePieceFromTile();
								tile[pointX][pointY].highlightPiece(false);
								
								this.diceValue = this.diceValue - temp; 
								isClicked = 'n';									// or isClicked = 's'
							}
							else if(temp == this.diceValue)
							{
								tile[pointX][pointY].putPieceOnTile(this.icon, this.name);
								tile[prevPointX][prevPointY].removePieceFromTile();
								tile[pointX][pointY].highlightPiece(false);
								
								this.diceValue = 0; 
								this.diceRolled = !this.diceRolled;
								this.whosTurn = !this.whosTurn;
								notifyObservers(this.diceRolled);
								System.out.println(diceRolled + "," + diceValue);
								isClicked = 'n';									// or isClicked = 's'
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Exceeded the value of dice", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid move for this piece", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else									//Shark
					{
						if(shark.checkValidMove(this.name, prevPointX, prevPointY, pointX, pointY) && temp <= this.diceValue)
						{
							if(temp < this.diceValue)
							{
								tile[pointX][pointY].putPieceOnTile(this.icon, this.name);
								tile[prevPointX][prevPointY].removePieceFromTile();
								tile[pointX][pointY].highlightPiece(false);
								
								this.diceValue = this.diceValue - temp; 
								isClicked = 'n';									// or isClicked = 's'
							}
							else if(temp == this.diceValue)
							{
								tile[pointX][pointY].putPieceOnTile(this.icon, this.name);
								tile[prevPointX][prevPointY].removePieceFromTile();
								tile[pointX][pointY].highlightPiece(false);
								
								this.diceValue = 0; 
								this.diceRolled = !this.diceRolled;
								this.whosTurn = !this.whosTurn;
								notifyObservers(this.diceRolled);
								System.out.println(diceRolled + "," + diceValue); 
								isClicked = 'n';									// or isClicked = 's'
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Exceeded the value of dice", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid move for this piece", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
				//This part is to be done when battle is introduced (for phase 2)
				else if((tile[pointX][pointY].whatIsOnTile().toString().charAt(1) == 'S' && this.whosTurn) || (tile[pointX][pointY].whatIsOnTile().toString().charAt(1) == 'E' && !this.whosTurn))
				{
					//BATTLE CONDITION
				}
			}
		}
	}
	
	public void setDiceRolledValue(boolean value)
	{
		this.diceRolled = value;
	}
	
	public void setRolledValue(int value)
	{
		this.diceValue = value;
	}
	
	public void setTurn(boolean value)
	{
		this.whosTurn = value;
	}
	
	//Add observers - that are observing this class
	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		this.o = o;
	}
	
	//Notify observers when the diceRolled value is changed
	public void notifyObservers(boolean diceRolled)
	{
		this.o.update(diceRolled);
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
