package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import Model.Piece.*;


/*
 * Create a list of available and unavailable pieces. Read from available piece list always (For selection purpose).
 */

/**
 * <H1>Player</H1>
 * <p>
 * This class represents a player in the game. 
 * The class stores the team (Eagle or Shark) and pieces from that team.
 * 
 * @author Ayam Ajmera
 * @version 1.5
 * @since 2020-04-21
 */
public class Player implements Serializable 
{
	private static final long serialVersionUID = 178L;
	/** Stores team info. 'e'for Eagle and 's' for Shark */
	private char team;						//'e' for eagle, 's' for shark
	private String[] pieceNames;
	/** Stores Piece objects */
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	/** Stores icons related to each Piece in the ArrayList above (for Piece objects) */
	private ArrayList<JLabel> icons = new ArrayList<JLabel>();
	/** Stores short names related to each Piece in the ArrayList above (for Piece objects) */
	private ArrayList<String> sNames = new ArrayList<String>();
	/** TRUE if Player has used the undo feature once FALSE otherwise */
	private boolean undo_used;
	
	/**
	 * Initialize a Player with team they have selected. 
	 * 
	 * @param team 'e' for Eagle, 's' for Shark
	 */
	public Player(char team)
	{
		this.team = team;
		this.undo_used = false;
	}
	
	/**
	 * Creates an object for the piece selected and stores the object, its icon and short name in the ArrayLists respectively
	 * 
	 * @param pieceNameSelected Name of the piece that is selected. These names are available in the pieceNames array
	 * @version 1.1
	 * @since 1.0
	 */
	@Requires("pieceNameSelected != null")
	@Ensures("pieces.get(pieces.size() - 1).getFullName().equals(pieceNameSelected)")
	public void selectPieces(String pieceNameSelected)
	{
		if(this.team == 'e')
		{
			if(pieceNameSelected.equals("Sea Eagle"))
			{
				pieces.add(new seaEagle());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("American Eagle"))
			{
				pieces.add(new americanEagle());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Australian Hawk"))
			{
				pieces.add(new australianHawk());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("White Tailed Eagle"))
			{
				pieces.add(new whiteTailedEagle());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Golden Eagle"))
			{
				pieces.add(new goldenEagle());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Harpy Eagle"))
			{
				pieces.add(new harpyEagle());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
		}
		else
		{
			if(pieceNameSelected.equals("Mako Shark"))
			{
				pieces.add(new makoShark());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Grey Reef Shark"))
			{
				pieces.add(new greyReefShark());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Megatooth Shark"))
			{
				pieces.add(new megatoothShark());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Bull Shark"))
			{
				pieces.add(new bullShark());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Hammerhead Shark"))
			{
				pieces.add(new hammerheadShark());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Mackerel Shark"))
			{
				pieces.add(new mackerelShark());
				icons.add(pieces.get(pieces.size() - 1).getIcon());
				sNames.add(pieces.get(pieces.size() - 1).getShortName());
			}
		}
	}
	
	/**
	 * Returns the list of pieces (names) that are available.
	 * 
	 * @return Array of Strings with the piece names that are currently available for selection
	 * @since 1.0
	 * @version 1.0
	 */
	public String[] getPieceNames()
	{
		if(this.team == 'e')
			pieceNames = new String[] {"Sea Eagle", "American Eagle", "Australian Hawk", "White Tailed Eagle", "Golden Eagle", "Harpy Eagle"};
		else
			pieceNames = new String[] {"Mako Shark", "Grey Reef Shark", "Megatooth Shark", "Bull Shark", "Hammerhead Shark", "Mackerel Shark"};
		
		return pieceNames;
	}
	
	/**
	 * Get the team this Player has selected
	 * 
	 * @return character with 'e' or 's' (Eagle or Shark respectively)
	 * @version 1.0
	 * @since 1.0
	 */
	public char getTeam()
	{
		return this.team;
	}
	
	/**
	 * Returns icons for each piece selected
	 * 
	 * @return List of icons
	 * @version 1.1
	 * @since 1.0
	 */
	public ArrayList<JLabel> getIcons()
	{
		return this.icons;
	}
	
	/**
	 * Returns short names for each piece selected
	 * 
	 * @return List of short names
	 * @version 1.1
	 * @since 1.0
	 */	
	public ArrayList<String> getAllNames()
	{
		return this.sNames;
	}
	
	/**
	 * Returns Piece objects for each piece selected
	 * 
	 * @return List of Piece objects
	 * @version 1.1
	 * @since 1.0
	 */
	public ArrayList<Piece> getAllSelectedPieces()
	{
		return this.pieces;
	}
	
	/**
	 * Restores the previous state of all the pieces of this Player.
	 * 
	 * @param piece List of pieces that replaces the current list of pieces.
	 * @version 1.0
	 * @since 1.5
	 */
	public void restorePieces(ArrayList<Piece> piece)
	{
		this.pieces.clear();
		for(int i = 0; i < piece.size(); i++)
		{
			this.pieces.add((Piece) piece.get(i).clone());
		}
	}
	
	/**
	 * Get the Piece object that matches the full name passed in input parameter.
	 * 
	 * @param fullName Full name of the piece that should be returned
	 * @return Piece object that matches the full name passed in input parameter.
	 * @version 1.0
	 * @since 1.5
	 */
	public Piece getPiece(String fullName)
	{
		for(int i = 0; i < this.pieces.size(); i++)
		{
			if(this.pieces.get(i).getFullName().equals(fullName))
				return this.pieces.get(i);
		}
		return null;
	}
	
	/**
	 * Return information if the Player has used the undo option once or not.
	 * 
	 * @return TRUE if undo option is used by the Player<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.5
	 */
	public boolean undoUsed()
	{
		return this.undo_used;
	}
	
	public void setUndoUsed(boolean used)
	{
		this.undo_used = used;
	}
	
	/*Precondition - X-Y coordinates should be between 0 and board's max size. Piece array should not be empty
	At the end of execution, supplier guarantees that the result will sent out which will be either true or false*/
	/**
	 * Checks if a move for a certain piece is valid or not.
	 * 
	 * @param name		 Short name of the piece
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if move is valid and according to the movement orientation of the piece<br>FALSE otherwise
	 * @version 1.2
	 * @since 1.1
	 */
	@Requires({"prevPointX >= 0", "prevPointY >= 0", "pointX >= 0", "pointY >= 0", "!name.trim().isEmpty()"})
	public boolean checkValidMove(String name, int prevPointX, int prevPointY, int pointX, int pointY)
	{
		int index = this.pieces.size() - 1;
		
		for(int i = 0; i < this.pieces.size(); i++)
		{
			if(pieces.get(i).getShortName().equals(name))
			{
				index = i;
				break;
			}
		}
		
		char orientation = pieces.get(index).getMovementOrientation();
		
		if(orientation == '+')
		{
			//Sideways
			if((Math.abs(pointX - prevPointX) > 0 && Math.abs(pointY - prevPointY) == 0) || (Math.abs(pointX - prevPointX) == 0 && Math.abs(pointY - prevPointY) > 0))
				return true;
			else
				return false;
		}
		else if(orientation == 'x')
		{
			//Diagonal
			if(Math.abs(pointX - prevPointX) == Math.abs(pointY - prevPointY))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
}
