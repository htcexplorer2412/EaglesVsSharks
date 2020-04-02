import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece {
	protected int attack, defense, speed, healthPoints, maxHealthPoints;
	protected char movement;		//'+' for sideways and forwards, backwards; 'x' for diagonal movement; '*' for all directions
	protected String shortName;
	protected boolean pieceAlive;
	protected ImageIcon pieceIcon;
	
	public int getAttackPoints()
	{
		return attack;
	}
	
	public int getDefensePoints()
	{
		return defense;
	}
	
	public int getSpeedPoints()
	{
		return speed;
	}
	
	public int getHealthPoints()
	{
		return healthPoints;
	}
	
	public char getMovementOrientation()
	{
		return movement;
	}
	
	public String getShortName()
	{
		return shortName;
	}
	
	public JLabel getIcon()
	{
		return new JLabel(pieceIcon);
	}
	
	public boolean isPieceAlive()
	{
		return pieceAlive;
	}
	
	public void addToDefenseAttribute(int def)
	{
		defense = defense + def;
	}
	
	public void removeDefenseAdditions(int def)
	{
		defense = defense - def;
	}
	
	public void addToHealthPoints(int addition)
	{
		int temp = healthPoints + addition;
		
		if(temp > maxHealthPoints)
		{
			healthPoints = maxHealthPoints;
		}
		else
		{
			healthPoints = temp;
		}
	}
	
	public void reduceHealthPoints(int reduction)
	{
		int temp = healthPoints - reduction;
		
		if(temp <= 0)
		{
			pieceAlive = false;
			healthPoints = 0;
		}
		else
		{
			healthPoints = temp;
		}
	}
}
