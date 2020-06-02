package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Model.SingletonBoard;
import Model.SingletonDice;
import Model.PlayerRegistry;
import Model.Memento.Memento;
import Model.Memento.SingletonCareTaker;
import Model.Observer.Observer;
import Model.Observer.Subject;
import View.SingletonGame;

/**
 * <h1>Board Controller</h1>
 * This class handles the mouse clicks on the board and handles all the validation checks for a piece to move from one tile to another.
 * <p>
 * This class handles the mouse clicks for movement of pieces on the board. All the tiles on the board point to this listener. The class receives data about dice being rolled, which team is currently on turn, how may steps a team can take and how many are left, Players and their pieces from different classes. All the validation checks are done through this class.
 * This is a Singleton class - i.e. Only one instance of this class can be created. This class is an Observer of Dice class.
 * 
 * @author	Ayam Ajmera
 * @version	3.4
 * @since	2020-04-21
 * 
 */
public class SingletonMouseAdapterParent extends MouseAdapter implements Observer
{
	//Change isClicked to boolean after doing final tests

	/**
	 * Stores whose turn it is for local use. Receives this data from Dice class
	 */
	private boolean turnLocal;								//whosTurn = true for Eagle team's turn and false for Shark team's turn
	/**
	 * Stores if dice is rolled or not for local use. Receives this data from Dice class
	 */
	private boolean diceRolledLocal;						//Value always changes when an update is received as part of Observer pattern. Value isn't changed internally
	private char isClicked = 'n';							//'n' is for no selection, 'f' is when a piece is clicked
	private JLabel icon;
	private String name;
	private char teamLocal;
	private int prevPointX, prevPointY;
	private static SingletonMouseAdapterParent single_instance = null;
	private ValidateMoveForPiece validateMoveLocalObj;
	
	private SingletonMouseAdapterParent()
	{
		validateMoveLocalObj = new ValidateMoveForPiece();
	}
	
	/**
	 * This method should be called to get an instance of this class. 
	 * As the class is a Singleton class, it's constructor is hidden and the class cannot be instantiated by a client using the new operator.
	 * When this method is called, it checks for existing instance of this class. 
	 * If there is, then it returns that instance, else it creates a new instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public synchronized static SingletonMouseAdapterParent getInstance()
	{
		if(single_instance == null)
			single_instance = new SingletonMouseAdapterParent();
		
		return single_instance;
	}
	
	//Precondition is that dice is rolled
	//Add a block statement to block clicks for player not on turn - for client server
	/**
	 * Overrides the mouseClicked method of MouseAdapter class.
	 * The method records the mouse click and finds the source of the click to determine which tile is clicked from the board. Depending on the state of click (isClicked), it determines the process. If the click was to determine source (i.e. select a piece) then it will highlight the piece. If it was to determine a destination, then it will validate the move and place the piece at destination if the move was valid.
	 * 
	 * @param e The mouse clicked event
	 * @version 3.4
	 * @since 1.0
	 */
	@Override
	public synchronized void mouseClicked(MouseEvent e)
	{
		//Getting the source of the click
		JPanel source = (JPanel) e.getComponent();
		
		//Translating the source's X and Y coordinates to Tile coordinates set by the Board (which is between 0 and 11, each tile is 50x50 pixels)
		int pointY = source.getX()/45;
		int pointX = source.getY()/45;
		
		//If dice is rolled and possible number of steps are not taken
		if(diceRolledLocal)
		{
			//n is when there has been no "click"/selection
			if(isClicked == 'n')
			{
				//turn true for Player 1, false for Player 2
				if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier() != null)
				{
					// && !Board.getInstance().getTileObj(pointX, pointY).getOccupier().IsMoved()
					if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier().getTeam() == PlayerRegistry.getPlayerTeam(this.turnLocal))	
					{
						SingletonBoard.getInstance().getTileViewObj(pointX, pointY).highlightPiece(true);
						this.icon = SingletonBoard.getInstance().getTileViewObj(pointX, pointY).getIcon();
						this.name = SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier().getShortName();
						this.teamLocal = SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier().getTeam();
						SingletonGame.getInstance().highlightTableRow(turnLocal, true, SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier().getFullName());
						isClicked = 'f';
						prevPointX = pointX;
						prevPointY = pointY;
					}
					/*else if(Board.getInstance().getTileObj(pointX, pointY).getOccupier().IsMoved())
					{
						Game.getInstance().showError("This piece has already been moved");
					}*/
				}
			}
			//f is when a Player has selected their piece
			else if(isClicked == 'f')
			{
				/*
				 * If they select the same piece again, then the piece is deselected and now player can select some other piece to move. 
				 * If they select an empty tile, then the move validity is checked first. 
				 * If the piece is allowed to move in that direction, then number of steps are checked. 
				 * This is where Player can move multiple pieces in one turn. 
				 * Once the Player has moved steps equivalent to the value on dice, their move is finished. 
				 * If the Player lands on opposing piece, then battle condition is implemented (incomplete)
				 */
				this.computeValidity(pointX, pointY);
			}
		}
	}

	/**
	 * Relocates the piece from source to destination.
	 * 
	 * @param prevPointX	Source point on X-axis
	 * @param prevPointY	Source point on Y-axis
	 * @param pointX		Destination point on X-axis
	 * @param pointY		Destination point on Y-axis
	 * @since 3.1
	 */
	private void relocatePiece(int prevPointX, int prevPointY, int pointX, int pointY)
	{
		SingletonBoard.getInstance().getTileObj(pointX, pointY).setOccupier(SingletonBoard.getInstance().getTileObj(prevPointX, prevPointY).getOccupier());		//Set occupying piece at destination (model)
		SingletonBoard.getInstance().getTileViewObj(pointX, pointY).putPieceOnTile(icon);	//Set occupying icon at destination (view)
		SingletonBoard.getInstance().getTileObj(prevPointX, prevPointY).setOccupier(null);	//Set occupier piece empty at source (model)
		SingletonBoard.getInstance().getTileViewObj(prevPointX, prevPointY).removePieceFromTile();	//Remove icon from source (view)
		SingletonBoard.getInstance().getTileViewObj(pointX, pointY).highlightPiece(false);
		SingletonGame.getInstance().highlightTableRow(this.turnLocal, false, SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier().getFullName());
		//Board.getInstance().getTileObj(pointX, pointY).getOccupier().setIsMoved(true);
		//Board.getInstance().getTileViewObj(pointX, pointY).highlightTile(false);
		
		this.isClicked = 'n';
	}
	
	/**
	 * Receive updates from a Subject.
	 * 
	 * @param s Object of the 'Subject' class
	 * @since 1.3
	 */
	@Override
	public void update(Subject s) 
	{
		this.diceRolledLocal = ((SingletonDice) s).getDiceRolled();
		this.turnLocal = ((SingletonDice) s).getTurn();
		if(!this.diceRolledLocal)
		{
			Memento m = SingletonBoard.getInstance().createMemento();
			SingletonCareTaker.getInstance().save(m);
		}
	}
	
	/**
	 * Computes the validity of move after destination tile is clicked.
	 * 
	 * @param pointX Destination point on X-axis
	 * @param pointY Destination point on Y-axis
	 * @since 3.3
	 */
	private void computeValidity(int pointX, int pointY)
	{
		//If source and destination are same, then cancel the move
		if(prevPointX == pointX && prevPointY == pointY)
		{
			SingletonBoard.getInstance().getTileViewObj(pointX, pointY).highlightPiece(false);
			SingletonGame.getInstance().highlightTableRow(this.turnLocal, false, SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier().getFullName());
			this.isClicked = 'n';
		}
		/*
		 * Else, send the coordinates to validate the move according to the piece selected. If the move is valid then check if the destination is an island or not.
		 * If it is an island, then check who owns it. If not an island then place the piece on the destination tile.
		 */
		else
		{
			//Calculating number of steps here
			int steps = Math.abs(pointX - prevPointX) > 0 ? Math.abs(pointX - prevPointX) : Math.abs(pointY - prevPointY);
			
			if(validateMoveLocalObj.validateMove(steps, this.turnLocal, this.name, prevPointX, prevPointY, pointX, pointY))
			{
				//No piece occupying the tile
				if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier() == null)
				{
					checkIslandAndOccupiers(steps, pointX, pointY);
				}
				//Piece from same team occupying the destination tile
				else if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getOccupier().getTeam() == Character.toLowerCase(this.name.charAt(1)))
				{
					SingletonGame.getInstance().showError("A piece from your team is occupying the destination tile. Move cannot be completed");
					SingletonBoard.getInstance().getTileViewObj(prevPointX, prevPointY).highlightPiece(false);
					SingletonGame.getInstance().highlightTableRow(this.turnLocal, false, SingletonBoard.getInstance().getTileObj(prevPointX, prevPointY).getOccupier().getFullName());
					this.isClicked = 'n';
				}
				//Piece from opposing team occupying the tile
				else
				{
					//BATTLE CONDITION
				}
			}
			else
			{
				SingletonBoard.getInstance().getTileViewObj(prevPointX, prevPointY).highlightPiece(false);
				SingletonGame.getInstance().highlightTableRow(this.turnLocal, false, SingletonBoard.getInstance().getTileObj(prevPointX, prevPointY).getOccupier().getFullName());
				isClicked = 'n';
			}
		}
	}
	
	/**
	 * Checks if the destination tile is an island or not.
	 * 
	 * @param steps	 Distance betwwen source and destination tile
	 * @param pointX Destination point on X-axis
	 * @param pointY Destination point on Y-axis
	 * @since 3.4
	 */
	private void checkIslandAndOccupiers(int steps, int pointX, int pointY)
	{
		if(!SingletonBoard.getInstance().getTileObj(pointX, pointY).isIsland())
		{
			this.relocatePiece(prevPointX, prevPointY, pointX, pointY);
			SingletonDice.getInstance().deductDiceVal(steps);
		}
		else
		{
			if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getIslandObj().checkIslandOccupier(this.teamLocal).equals("STCE"))
			{
				//Show message that the island is already owned by entering team
				if(this.turnLocal)
					SingletonGame.getInstance().showMessage("This island is already owned by Player 1");
				else
					SingletonGame.getInstance().showMessage("This island is already owned by Player 2");
				
				this.relocatePiece(prevPointX, prevPointY, pointX, pointY);
				SingletonDice.getInstance().deductDiceVal(steps);
			}
			else if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getIslandObj().checkIslandOccupier(this.teamLocal).equals("STCN"))
			{
				SingletonGame.getInstance().showError("A piece from your team is occupying the Island. You can't enter");
				SingletonBoard.getInstance().getTileViewObj(prevPointX, prevPointY).highlightPiece(false);
				SingletonGame.getInstance().highlightTableRow(this.turnLocal, false, SingletonBoard.getInstance().getTileObj(prevPointX, prevPointY).getOccupier().getFullName());
				this.isClicked = 'n';
			}
			else if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getIslandObj().checkIslandOccupier(this.teamLocal).equals("CO"))
			{
				//Show message that the island is now owned by the entering team
				if(this.turnLocal)
					SingletonGame.getInstance().showMessage("This island is now owned by Player 1!");
				else
					SingletonGame.getInstance().showMessage("This island is now owned by Player 2!");
				
				this.relocatePiece(prevPointX, prevPointY, pointX, pointY);
				SingletonBoard.getInstance().getTileObj(pointX, pointY).getIslandObj().changeOwner(this.teamLocal);
				SingletonDice.getInstance().deductDiceVal(steps);
			}
			else if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getIslandObj().checkIslandOccupier(this.teamLocal).equals("COO"))
			{
				//Show message that the island is now owned by the entering team but it was previously owned by opposing team
				if(this.turnLocal)
					SingletonGame.getInstance().showMessage("This island was owned by Player 2. Player 1 now owns this island");
				else
					SingletonGame.getInstance().showMessage("This island was owned by Player 1. Player 2 now owns this island");
				
				this.relocatePiece(prevPointX, prevPointY, pointX, pointY);
				SingletonBoard.getInstance().getTileObj(pointX, pointY).getIslandObj().changeOwner(this.teamLocal);
				SingletonDice.getInstance().deductDiceVal(steps);
			}
			else if(SingletonBoard.getInstance().getTileObj(pointX, pointY).getIslandObj().checkIslandOccupier(this.teamLocal).equals("IB"))
			{
				//BATTLE CONDITION
			}
		}
	}
	/**
	 * Resets the isMoved variable of a piece.
	 * 
	 * @since 3.3
	 */
	/*private void resetMovedVariable()
	{
		RowIterator<Tile> iter = Board.getInstance().getRowIterator();
		
		for(iter.first(); !iter.isDone(); iter.next())
		{
			if(iter.currentItem().getOccupier() != null)
			{
				iter.currentItem().getOccupier().setIsMoved(false);
			}
		}
	}*/
}