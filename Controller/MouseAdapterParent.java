/*
Author - Ayam Ajmera
Date (Last modified) - 04 APR 2020

This class tracks the mouse listeners for every tile on the board. All the tiles have a same mouse listener. This class tracks the turns of each player, uses the dice value to calculate how many steps the player can move in this turn. Also tracks if the move is valid or not by communicating with the Player class. If a move is valid, then the piece is moved from the tile it previously occupied to the newly selected tile. This is done by capturing which panel is clicked by the mouse.

Tracking tiles - 
The object for MouseAdapterParent class is created in the Board class and when the object is created, it passes all the reference to the Tile objects to this class. All the Tile object are stored locally in this class and accessed when mouse click is detected.

Controller in MVC pattern
*/

//Update to observer and remove diceValue from this class and use it from Dice class

package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Board;
import Model.Dice;
import Model.PlayerRegistry;
import Model.Iterator.RowIterator;
import Model.Observer.Observer;
import Model.Observer.Subject;
import Model.PrototypeTileFactory.Tile;
import View.Game;


//Observer pattern implemented between class Dice and class MouseAdapterParent
public class MouseAdapterParent extends MouseAdapter implements Observer, Serializable {
	
	
	//Change isClicked to boolean after doing final tests
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean turnLocal;								//whosTurn = true for Eagle team's turn and false for Shark team's turn
	private boolean diceRolledLocal;						//Value always changes when an update is received as part of Observer pattern. Value isn't changed internally
	private char isClicked = 'n';							//'n' is for no selection, 'f' is when a piece is clicked
	private JLabel icon;
	private String name;
	private int prevPointX, prevPointY;
	private static MouseAdapterParent single_instance = null;
	private ValidateMoveForPiece validateMoveLocalObj;
	
	private MouseAdapterParent()
	{
		validateMoveLocalObj = new ValidateMoveForPiece();
	}
	
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
				if(Board.getInstance().getTileObj(pointX, pointY).getOccupier() != null)
				{
					//if(Board.getInstance().getTileObj(pointX, pointY).getOccupierName().charAt(1) == Character.toUpperCase(PlayerRegistry.getPlayerTeam(this.turnLocal)))
					if(Board.getInstance().getTileObj(pointX, pointY).getOccupier().getShortName().charAt(1) == Character.toUpperCase(PlayerRegistry.getPlayerTeam(this.turnLocal)) && !Board.getInstance().getTileObj(pointX, pointY).getOccupier().IsMoved())	
					{
						Board.getInstance().getTileViewObj(pointX, pointY).highlightPiece(true);
						//Board.getInstance().getTileViewObj(pointX, pointY).highlightTile(true);
						this.icon = Board.getInstance().getTileViewObj(pointX, pointY).getIcon();
						this.name = Board.getInstance().getTileObj(pointX, pointY).getOccupier().getShortName();
						isClicked = 'f';
						prevPointX = pointX;
						prevPointY = pointY;
					}
					else if(Board.getInstance().getTileObj(pointX, pointY).getOccupier().IsMoved())
					{
						Game.getInstance().showError("This piece has already been moved");
					}
				}
			}
			//f is when a Player has selected their piece
			else if(isClicked == 'f')
			{
				//If they select the same piece again, then the piece is deselected and now player can select some other piece to move. If they select an empty tile, then the move validity is checked first. If the piece is allowed to move in that direction, then number of steps are checked. This is where Player can move multiple pieces in one turn. Once the Player has moved steps equivalent to the value on dice, their move is finished. If the Player lands on opposing piece, then battle condition is implemented (incomplete)
				this.computeValidity(pointX, pointY);
			}
		}
	}

	private void relocatePiece(int prevPointX, int pointX, int prevPointY, int pointY)
	{
		Board.getInstance().getTileObj(pointX, pointY).setOccupier(Board.getInstance().getTileObj(prevPointX, prevPointY).getOccupier());		//Set occupying piece at destination (model)
		Board.getInstance().getTileViewObj(pointX, pointY).putPieceOnTile(icon);	//Set occupying icon at destination (view)
		Board.getInstance().getTileObj(prevPointX, prevPointY).setOccupier(null);	//Set occupier piece empty at source (model)
		Board.getInstance().getTileViewObj(prevPointX, prevPointY).removePieceFromTile();	//Remove icon from source (view)
		Board.getInstance().getTileViewObj(pointX, pointY).highlightPiece(false);
		Board.getInstance().getTileObj(pointX, pointY).getOccupier().setIsMoved(true);
		//Board.getInstance().getTileViewObj(pointX, pointY).highlightTile(false);
		
		this.isClicked = 'n';
	}
	
	@Override
	public void update(Subject s) {
		// TODO Auto-generated method stub
		this.diceRolledLocal = ((Dice) s).getDiceRolled();
		if(this.turnLocal != ((Dice) s).getTurn())
		{	
			this.resetMovedVariable();
			//reset moved variable for each piece
		}
		this.turnLocal = ((Dice) s).getTurn();
		
	}
	
	private void computeValidity(int pointX, int pointY)
	{
		if(prevPointX == pointX && prevPointY == pointY)
		{
			Board.getInstance().getTileViewObj(pointX, pointY).highlightPiece(false);
			//Board.getInstance().getTileViewObj(pointX, pointY).highlightTile(false);
			this.isClicked = 'n';
		}
		else
		{
			//Calculating number of steps here
			int steps = Math.abs(pointX - prevPointX) > 0 ? Math.abs(pointX - prevPointX) : Math.abs(pointY - prevPointY);
			
			if(validateMoveLocalObj.validateMove(steps, this.turnLocal, this.name, prevPointX, prevPointY, pointX, pointY))
			{
				if(Board.getInstance().getTileObj(pointX, pointY).getOccupier() == null)
				{
					this.relocatePiece(prevPointX, pointX, prevPointY, pointY);
					Dice.getInstance().deductDiceVal(steps);
				}
				else if(Board.getInstance().getTileObj(pointX, pointY).getOccupier().getShortName().charAt(1) == this.name.charAt(1))
				{
					Game.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
					Board.getInstance().getTileViewObj(prevPointX, prevPointY).highlightPiece(false);
					//Board.getInstance().getTileViewObj(prevPointX, prevPointY).highlightTile(false);
					this.isClicked = 'n';
				}
				else
				{
					//BATTLE CONDITION
				}
			}
			else
			{
				Board.getInstance().getTileViewObj(prevPointX, prevPointY).highlightPiece(false);
				//Board.getInstance().getTileViewObj(prevPointX, prevPointY).highlightTile(false);
				isClicked = 'n';
			}
		}
	}
	
	private void resetMovedVariable()
	{
		RowIterator<Tile> iter = Board.getInstance().getRowIterator();
		
		for(iter.first(); !iter.isDone(); iter.next())
		{
			if(iter.currentItem().getOccupier() != null)
			{
				iter.currentItem().getOccupier().setIsMoved(false);
			}
		}
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
