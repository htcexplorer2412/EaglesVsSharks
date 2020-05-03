import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char team;						//'e' for eagle, 's' for shark
	private String[] pieceNames;
	Piece[] piece = new Piece[3];
	JLabel[] icons = new JLabel[piece.length];
	JTextField[] sNames = new JTextField[piece.length];
	
	public Player(char team)
	{
		this.team = team;
	}
	
	//Take in 3 attacking piece from players. Let them select.
	/*Add precondition that pieceIndex is not empty. 
	At the end of execution, supplier guarantees that there will be an object generated for the piece selected, with icon and name related to that piece (Post condition)*/
	public void selectPieces(int[] pieceIndex)
	{
		if(this.team == 'e')
		{
			for(int i = 0; i < pieceIndex.length; i++)
			{
				if(pieceNames[pieceIndex[i]].equals("Sea Eagle"))
				{
					piece[i] = new seaEagle();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("American Eagle"))
				{
					piece[i] = new americanEagle();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Australian Hawk"))
				{
					piece[i] = new australianHawk();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("White Tailed Eagle"))
				{
					piece[i] = new whiteTailedEagle();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Golden Eagle"))
				{
					piece[i] = new goldenEagle();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Harpy Eagle"))
				{
					piece[i] = new harpyEagle();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
			}
		}
		else
		{
			for(int i = 0; i < pieceIndex.length; i++)
			{
				if(pieceNames[pieceIndex[i]].equals("Mako Shark"))
				{
					piece[i] = new makoShark();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Grey Reef Shark"))
				{
					piece[i] = new greyReefShark();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Megatooth Shark"))
				{
					piece[i] = new megatoothShark();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Bull Shark"))
				{
					piece[i] = new bullShark();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Hammerhead Shark"))
				{
					piece[i] = new hammerheadShark();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
				}
				else if(pieceNames[pieceIndex[i]].equals("Mackerel Shark"))
				{
					piece[i] = new mackerelShark();
					icons[i] = piece[i].getIcon();
					sNames[i] = new JTextField(piece[i].getShortName());
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
	
	public JTextField[] getAllNames()
	{
		return sNames;
	}
	
	/*Precondition - X-Y coordinates should be between 0 and board's max size. Piece array should not be empty
	At the end of execution, supplier guarantees that the result will sent out which will be either true or false (Post condition)*/
	public boolean checkValidMove(JTextField name, int prevPointX, int prevPointY, int pointX, int pointY)
	{
		int index = 3;
		
		for(int i = 0; i < this.sNames.length; i++)
		{
			if(sNames[i].getText().toString().equals(name.getText().toString()))
			{
				index = i;
				break;
			}
		}
		
		char orientation = piece[index].getMovementOrientation();
		
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
