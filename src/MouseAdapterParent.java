/*
Author - Ayam Ajmera
Date (Last modified) - 04 APR 2020

This class tracks the mouse listeners for every tile on the board. All the tiles have a same mouse listener. This class tracks the turns of each player, uses the dice value to calculate how many steps the player can move in this turn. Also tracks if the move is valid or not by communicating with the Player class. If a move is valid, then the piece is moved from the tile it previously occupied to the newly selected tile. This is done by capturing which panel is clicked by the mouse.

Tracking tiles - 
The object for MouseAdapterParent class is created in the Board class and when the object is created, it passes all the reference to the Tile objects to this class. All the Tile object are stored locally in this class and accessed when mouse click is detected.

Controller in MVC pattern
*/

//Update to observer and remove diceValue from this class and use it from Dice class

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Observer pattern implemented between class Dice and class MouseAdapterParent
public class MouseAdapterParent extends MouseAdapter implements Observer, Serializable {
	
	
	//Change isClicked to boolean after doing final tests
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean turn;								//whosTurn = true for Eagle team's turn and false for Shark team's turn
	private boolean diceRolledLocal;						//Value always changes when an update is received as part of Observer pattern. Value isn't changed internally
	private char isClicked = 'n';							//'n' is for no selection, 'f' is when a piece is clicked
	private JLabel icon;
	private String name;
	private int prevPointX, prevPointY;
	private static MouseAdapterParent single_instance = null;
	
	
	private MouseAdapterParent(){}
	
	public synchronized static MouseAdapterParent getInstance()
	{
		if(single_instance == null)
			single_instance = new MouseAdapterParent();
		
		return single_instance;
	}
	
	//Precondition is that dice is rolled
	//Add a block statement to block clicks for player not on turn - for client server
	public synchronized void mouseClicked(MouseEvent e)
	{
		//Getting the source of the click
		JPanel source = (JPanel) e.getComponent();
		
		//Translating the source's X and Y coordinates to Tile coordinates set by the Board (which is between 0 and 11, each tile is 50x50 pixels)
		int pointY = source.getX()/50;
		int pointX = source.getY()/50;
		
		//If dice is rolled and possible number of steps are not taken
		if(diceRolledLocal)
		{
			//n is when there has been no "click"/selection
			if(isClicked == 'n')
			{
				//turn true for Player 1, false for Player 2
				if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Character.toUpperCase(PlayerRegistry.getPlayerTeam(this.turn)))
				{
					Board.getInstance().getTileViewObj(pointX, pointY).highlightPiece(true);
					this.icon = Board.getInstance().getTileViewObj(pointX, pointY).getIcon();
					this.name = Board.getInstance().getTileObj(pointX, pointY).getOccupierName();
					isClicked = 'f';
					prevPointX = pointX;
					prevPointY = pointY;
				}
			}
			//f is when a Player has selected their piece
			else if(isClicked == 'f')
			{
				//If they select the same piece again, then the piece is deselected and now player can select some other piece to move. If they select an empty tile, then the move validity is checked first. If the piece is allowed to move in that direction, then number of steps are checked. This is where Player can move multiple pieces in one turn. Once the Player has moved steps equivalent to the value on dice, their move is finished. If the Player lands on opposing piece, then battle condition is implemented (incomplete)
				if(prevPointX == pointX && prevPointY == pointY)
				{
					Board.getInstance().getTileViewObj(pointX, pointY).highlightPiece(false);
					isClicked = 'n';
				}
				else
				{
					//Calculating number of steps here
					int temp = Math.abs(pointX - prevPointX) > 0 ? Math.abs(pointX - prevPointX) : Math.abs(pointY - prevPointY);
					
					if(PlayerRegistry.getPlayerObj(this.turn).checkValidMove(this.name, prevPointX, prevPointY, pointX, pointY))
					{
						if(Dice.getInstance().getDiceVal() >= temp)
						{
							int i, j;
							//Check tile and move -- If not successful then send error to view (Game/TileView) class. If opposing piece then ask for battle. If successful, change view/model (TileView/Tile) class
							if((pointX - prevPointX) > 0 && (pointY - prevPointY) == 0)			//South
							{
								
								for(i = prevPointX; i < pointX; i++)							//Checking for any blocked tiles in the way. If yes, break the loop
								{
									if(!Board.getInstance().getTileObj(i, pointY).goSouth())
									{
										Game.getInstance().showError("Passage blocked. Move cannot be completed");
										break;
									}
										
								}
								if(i == pointX)								//If loop not interrupted, then check for occupiers in the way
								{
									for(j = prevPointX + 1; j < pointX; j++)
									{
										if(Board.getInstance().getTileObj(j, pointY).getOccupierName() != "")
										{
											Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
											break;
										}
									}
									if(j == pointX)
									{
										if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
										{
											this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
											Dice.getInstance().deductDiceVal(temp);
										}
										else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
										{
											Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
											isClicked = 'n';
										}
										else
										{
											//BATTLE CONDITION
										}
									}
								}
							}
							else if((pointX - prevPointX) < 0 && (pointY - prevPointY) == 0)	//North
							{
								for(i = prevPointX; i > pointX; i--)							//Checking for any blocked tiles in the way. If yes, break the loop
								{
									if(!Board.getInstance().getTileObj(i, pointY).goNorth())
									{
										Game.getInstance().showError("Passage blocked. Move cannot be completed");
										break;
									}
										
								}
								if(i == pointX)								//If loop not interrupted, then check for occupiers in the way
								{
									for(j = prevPointX - 1; j > pointX; j--)
									{
										if(Board.getInstance().getTileObj(j, pointY).getOccupierName() != "")
										{
											Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
											break;
										}
									}
									if(j == pointX)
									{
										if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
										{
											this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
											Dice.getInstance().deductDiceVal(temp);
										}
										else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
										{
											Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
											isClicked = 'n';
										}
										else
										{
											//BATTLE CONDITION
										}
									}
								}
							}
							else if((pointX - prevPointX) == 0 && (pointY - prevPointY) > 0)	//East
							{
								for(i = prevPointY; i < pointY; i++)							//Checking for any blocked tiles in the way. If yes, break the loop
								{
									if(!Board.getInstance().getTileObj(pointX, i).goEast())
									{
										Game.getInstance().showError("Passage blocked. Move cannot be completed");
										break;
									}
										
								}
								if(i == pointY)								//If loop not interrupted, then check for occupiers in the way
								{
									for(j = prevPointY + 1; j < pointY; j++)
									{
										if(Board.getInstance().getTileObj(pointX, j).getOccupierName() != "")
										{
											Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
											break;
										}
									}
									if(j == pointY)
									{
										if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
										{
											this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
											Dice.getInstance().deductDiceVal(temp);
										}
										else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
										{
											Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
											isClicked = 'n';
										}
										else
										{
											//BATTLE CONDITION
										}
									}
								}
							}
							else if((pointX - prevPointX) == 0 && (pointY - prevPointY) < 0)	//West
							{
								for(i = prevPointY; i > pointY; i--)							//Checking for any blocked tiles in the way. If yes, break the loop
								{
									if(!Board.getInstance().getTileObj(pointX, i).goWest())
									{
										Game.getInstance().showError("Passage blocked. Move cannot be completed");
										break;
									}
										
								}
								if(i == pointY)								//If loop not interrupted, then check for occupiers in the way
								{
									for(j = prevPointY - 1; j > pointY; j--)
									{
										if(Board.getInstance().getTileObj(pointX, j).getOccupierName() != "")
										{
											Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
											break;
										}
									}
									if(j == pointY)
									{
										if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
										{
											this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
											Dice.getInstance().deductDiceVal(temp);
										}
										else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
										{
											Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
											isClicked = 'n';
										}
										else
										{
											//BATTLE CONDITION
										}
									}
								}
							}
							else if(Math.abs(pointX - prevPointX) == Math.abs(pointY - prevPointY))
							{
								int k;
								if((pointX - prevPointX) < 0 && (pointY - prevPointY) > 0)		//North-East
								{
									k = prevPointY;
									for(i = prevPointX; i > pointX; i--)							//Checking for any blocked tiles in the way. If yes, break the loop
									{
										if(!Board.getInstance().getTileObj(i, k).goNorthEast())
										{
											Game.getInstance().showError("Passage blocked. Move cannot be completed");
											break;
										}
										k++;
									}
									if(i == pointX && k == pointY)								//If loop not interrupted, then check for occupiers in the way
									{
										k = prevPointY + 1;
										for(j = prevPointX - 1; j > pointX; j--)
										{
											if(Board.getInstance().getTileObj(j, k).getOccupierName() != "")
											{
												Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
												break;
											}
											k++;
										}
										if(j == pointX && k == pointY)
										{
											if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
											{
												this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
												Dice.getInstance().deductDiceVal(temp);
											}
											else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
											{
												Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
												isClicked = 'n';
											}
											else
											{
												//BATTLE CONDITION
											}
										}
									}
								}
								else if((pointX - prevPointX) < 0 && (pointY - prevPointY) < 0)	//North-West
								{
									k = prevPointY;
									for(i = prevPointX; i > pointX; i--)							//Checking for any blocked tiles in the way. If yes, break the loop
									{
										if(!Board.getInstance().getTileObj(i, k).goNorthWest())
										{
											Game.getInstance().showError("Passage blocked. Move cannot be completed");
											break;
										}
										k--;
									}
									if(i == pointX && k == pointY)								//If loop not interrupted, then check for occupiers in the way
									{
										k = prevPointY - 1;
										for(j = prevPointX - 1; j > pointX; j--)
										{
											if(Board.getInstance().getTileObj(j, k).getOccupierName() != "")
											{
												Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
												break;
											}
											k--;
										}
										if(j == pointX && k == pointY)
										{
											if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
											{
												this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
												Dice.getInstance().deductDiceVal(temp);
											}
											else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
											{
												Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
												isClicked = 'n';
											}
											else
											{
												//BATTLE CONDITION
											}
										}
									}
								}
								else if((pointX - prevPointX) > 0 && (pointY - prevPointY) > 0)	//South-East
								{
									k = prevPointY;
									for(i = prevPointX; i < pointX; i++)							//Checking for any blocked tiles in the way. If yes, break the loop
									{
										if(!Board.getInstance().getTileObj(i, k).goSouthEast())
										{
											Game.getInstance().showError("Passage blocked. Move cannot be completed");
											break;
										}
										k++;
									}
									if(i == pointX && k == pointY)								//If loop not interrupted, then check for occupiers in the way
									{
										k = prevPointY + 1;
										for(j = prevPointX + 1; j < pointX; j++)
										{
											if(Board.getInstance().getTileObj(j, k).getOccupierName() != "")
											{
												Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
												break;
											}
											k++;
										}
										if(j == pointX && k == pointY)
										{
											if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
											{
												this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
												Dice.getInstance().deductDiceVal(temp);
											}
											else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
											{
												Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
												isClicked = 'n';
											}
											else
											{
												//BATTLE CONDITION
											}
										}
									}
								}
								else if((pointX - prevPointX) > 0 && (pointY - prevPointY) < 0)	//South-West
								{
									k = prevPointY;
									for(i = prevPointX; i < pointX; i++)							//Checking for any blocked tiles in the way. If yes, break the loop
									{
										if(!Board.getInstance().getTileObj(i, k).goSouthWest())
										{
											Game.getInstance().showError("Passage blocked. Move cannot be completed");
											break;
										}
										k--;
									}
									if(i == pointX && k == pointY)								//If loop not interrupted, then check for occupiers in the way
									{
										k = prevPointY - 1;
										for(j = prevPointX + 1; j < pointX; j++)
										{
											if(Board.getInstance().getTileObj(j, k).getOccupierName() != "")
											{
												Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
												break;
											}
											k--;
										}
										if(j == pointX && k == pointY)
										{
											if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().equals(""))		//if tile empty
											{
												this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
												Dice.getInstance().deductDiceVal(temp);
											}
											else if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupierName().charAt(1))			//if tile occupied by same team piece
											{
												Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
												isClicked = 'n';
											}
											else
											{
												//BATTLE CONDITION
											}
										}
									}
								}
							}
						}
						else
						{
							Game.getInstance().showError("Exceeded the value of dice!");
						}
					}
					else
					{
						Game.getInstance().showError("Invalid move for this piece!");
					}
				}
			}
		}
	}

	private void relocatePiece(int prevPointX, int pointX, int prevPointY, int pointY)
	{
		Board.getInstance().getTileObj(pointX, pointY).setOccupierName(name);		//Set occupier name at destination (model)
		Board.getInstance().getTileViewObj(pointX, pointY).putPieceOnTile(icon);	//Set occupying icon at destination (view)
		Board.getInstance().getTileObj(prevPointX, prevPointY).setOccupierName("");	//Set occupier name empty at source (model)
		Board.getInstance().getTileViewObj(prevPointX, prevPointY).removePieceFromTile();	//Remove icon from source (view)
		Board.getInstance().getTileViewObj(pointX, pointY).highlightPiece(false);
		
		isClicked = 'n';
	}
	
	@Override
	public void update(Subject s) {
		// TODO Auto-generated method stub
		this.diceRolledLocal = s.getDiceRolled();
		this.turn = s.getTurn();
	}
	
	/*
	 * can use pattern to show piece data here?
	 * public void mouseEntered(MouseEvent e)
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
