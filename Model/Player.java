package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.google.java.contract.Requires;


/*
 * Create a list of available and unavailable pieces. Read from available piece list always (For selection purpose).
 */
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char team;						//'e' for eagle, 's' for shark
	private String[] pieceNames;
	private ArrayList<Piece> piece = new ArrayList<Piece>();
	private ArrayList<JLabel> icons = new ArrayList<JLabel>();
	private ArrayList<String> sNames = new ArrayList<String>();
	
	
	public Player(char team)
	{
		this.team = team;
	}
	
	//Take in 3 attacking piece from players. Let them select.
	/*Add precondition that pieceIndex is not empty. 
	At the end of execution, supplier guarantees that there will be an object generated for the piece selected, with icon and name related to that piece (Post condition)*/
	
	@Requires("pieceNameSelected != null")
	public void selectPieces(String pieceNameSelected)
	{
		if(this.team == 'e')
		{
			if(pieceNameSelected.equals("Sea Eagle"))
			{
				piece.add(new seaEagle());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("American Eagle"))
			{
				piece.add(new americanEagle());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Australian Hawk"))
			{
				piece.add(new australianHawk());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("White Tailed Eagle"))
			{
				piece.add(new whiteTailedEagle());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Golden Eagle"))
			{
				piece.add(new goldenEagle());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Harpy Eagle"))
			{
				piece.add(new harpyEagle());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
		}
		else
		{
			if(pieceNameSelected.equals("Mako Shark"))
			{
				piece.add(new makoShark());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Grey Reef Shark"))
			{
				piece.add(new greyReefShark());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Megatooth Shark"))
			{
				piece.add(new megatoothShark());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Bull Shark"))
			{
				piece.add(new bullShark());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Hammerhead Shark"))
			{
				piece.add(new hammerheadShark());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
			else if(pieceNameSelected.equals("Mackerel Shark"))
			{
				piece.add(new mackerelShark());
				icons.add(piece.get(piece.size() - 1).getIcon());
				sNames.add(piece.get(piece.size() - 1).getShortName());
			}
		}
	}
	
	public String[] getPieceNames()
	{
		if(this.team == 'e')
			pieceNames = new String[] {"Sea Eagle", "American Eagle", "Australian Hawk", "White Tailed Eagle", "Golden Eagle", "Harpy Eagle"};
		else
			pieceNames = new String[] {"Mako Shark", "Grey Reef Shark", "Megatooth Shark", "Bull Shark", "Hammerhead Shark", "Mackerel Shark"};
		
		return pieceNames;
	}
	
	public char getTeam()
	{
		return this.team;
	}
	
	public ArrayList<JLabel> getIcons()
	{
		return icons;
	}
	
	public ArrayList<String> getAllNames()
	{
		return sNames;
	}
	
	public ArrayList<Piece> getAllSelectedPieces()
	{
		return piece;
	}
	
	/*Precondition - X-Y coordinates should be between 0 and board's max size. Piece array should not be empty
	At the end of execution, supplier guarantees that the result will sent out which will be either true or false*/
	
	@Requires({"prevPointX >= 0", "prevPointY >= 0", "pointX >= 0", "pointY >= 0", "!name.trim().isEmpty()"})
	public boolean checkValidMove(String name, int prevPointX, int prevPointY, int pointX, int pointY)
	{
		int index = 3;
		
		for(int i = 0; i < this.piece.size(); i++)
		{
			if(sNames.get(i).equals(name))
			{
				index = i;
				break;
			}
		}
		
		char orientation = piece.get(index).getMovementOrientation();
		
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
