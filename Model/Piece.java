package Model;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Piece implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int attack, defense, speed, healthPoints, maxHealthPoints;
	protected char movement;		//'+' for sideways and forwards, backwards; 'x' for diagonal movement; '*' for all directions
	protected String shortName;
	protected boolean pieceAlive;
	protected ImageIcon pieceIcon;
	protected boolean isMoved = false;						//true if moved, false if not moved
	
	public void setIsMoved(boolean moved)
	{
		this.isMoved = moved;
	}
	
	public boolean IsMoved()
	{
		return isMoved;
	}
	
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
	
	/*Given that piece is in island (Pre condition)
	Post condition - Defense attribute is updated*/
	public void addToDefenseAttribute(int def)
	{
		defense = defense + def;
	}
	
	/*Given that piece is in island (Pre condition)
	Post condition - Defense attribute is updated*/
	public void removeDefenseAdditions(int def)
	{
		defense = defense - def;
	}
	
	/*Pre condition - Given that piece is not dead (Healthpoints > 0)
	Post condition - Healthpoint attribute is updated and not exceed max healthpoints*/
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
	
	/*Pre condition - Given that piece is not dead (Healthpoints > 0)
	Post condition - Piece health is updated and/or piece is confirmed dead*/
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
