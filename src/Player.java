import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char team;						//'e' for eagle, 's' for shark
	private String[] pieceNames;
	//Piece[] piece = new Piece[3];					//Change to arraylist
	private ArrayList<Piece> piece = new ArrayList<Piece>();
	JLabel[] icons;
	String[] sNames;
	//private ArrayList<JLabel> icons = new ArrayList<JLabel>();
	//private ArrayList<String> sNames = new ArrayList<String>();
	
	
	public Player(char team)
	{
		this.team = team;
	}
	
	//Take in 3 attacking piece from players. Let them select.
	/*Add precondition that pieceIndex is not empty. 
	At the end of execution, supplier guarantees that there will be an object generated for the piece selected, with icon and name related to that piece (Post condition)*/
	public void selectPieces(ArrayList<Integer> pieceIndex)
	{
		icons = new JLabel[pieceIndex.size()];
		sNames = new String[pieceIndex.size()];
		
		if(this.team == 'e')
		{
			for(int i = 0; i < pieceIndex.size(); i++)
			{
				if(pieceNames[pieceIndex.get(i)].equals("Sea Eagle"))
				{
					piece.add(new seaEagle());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("American Eagle"))
				{
					piece.add(new americanEagle());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Australian Hawk"))
				{
					piece.add(new australianHawk());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("White Tailed Eagle"))
				{
					piece.add(new whiteTailedEagle());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Golden Eagle"))
				{
					piece.add(new goldenEagle());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Harpy Eagle"))
				{
					piece.add(new harpyEagle());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
			}
		}
		else
		{
			for(int i = 0; i < pieceIndex.size(); i++)
			{
				if(pieceNames[pieceIndex.get(i)].equals("Mako Shark"))
				{
					piece.add(new makoShark());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Grey Reef Shark"))
				{
					piece.add(new greyReefShark());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Megatooth Shark"))
				{
					piece.add(new megatoothShark());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Bull Shark"))
				{
					piece.add(new bullShark());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Hammerhead Shark"))
				{
					piece.add(new hammerheadShark());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
				else if(pieceNames[pieceIndex.get(i)].equals("Mackerel Shark"))
				{
					piece.add(new mackerelShark());
					icons[i] = piece.get(i).getIcon();
					sNames[i] = piece.get(i).getShortName();
				}
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
	
	public JLabel[] getIcons()
	{
		return icons;
	}
	
	public String[] getAllNames()
	{
		return sNames;
	}
	
	/*Precondition - X-Y coordinates should be between 0 and board's max size. Piece array should not be empty
	At the end of execution, supplier guarantees that the result will sent out which will be either true or false (Post condition)*/
	public boolean checkValidMove(String name, int prevPointX, int prevPointY, int pointX, int pointY)
	{
		int index = 3;
		
		for(int i = 0; i < this.piece.size(); i++)
		{
			if(sNames[i].equals(name))
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
