package Model.Piece;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.google.java.contract.Requires;

/**
 * <H1>Piece</H1>
 * <p>
 * This class represents a piece in the game - i.e. American eagle, Sea eagle, Mackerel shark, Hammerhead shark, etc.
 * <p>
 * This class is a parent class to all the pieces in the game. 
 * The class stores the attributes for each piece like movement orientation, attack, defense, speed, health points and other attributes.
 * It has setters and getters for all these attributes, so it can be manipulated according to the game.
 * 
 * @author Ayam Ajmera
 * @version 1.3
 * @since 2020-04-21
 */
public abstract class Piece implements Serializable, Cloneable 
{
	private static final long serialVersionUID = 1L;
	protected int attack, defense, speed, healthPoints, maxHealthPoints;
	protected char movement;								//'+' for sideways and forwards, backwards; 'x' for diagonal movement; '*' for all directions
	protected String shortName;
	protected String fullName;
	protected boolean pieceAlive;
	protected ImageIcon pieceIcon;
	//protected boolean isMoved = false;						//true if moved, false if not moved
	protected char team;
	
	/*public void setIsMoved(boolean moved)
	{
		isMoved = moved;
	}
	
	public boolean IsMoved()
	{
		return isMoved;
	}*/
	
	/**
	 * Get the attack attribute of a piece
	 * 
	 * @return A piece's attack points attribute
	 * @version 1.0
	 * @since 1.0
	 */
	public int getAttackPoints()
	{
		return attack;
	}
	
	/**
	 * Get the defense attribute of a piece
	 * 
	 * @return A piece's defense points attribute
	 * @version 1.0
	 * @since 1.0
	 */
	public int getDefensePoints()
	{
		return defense;
	}
	
	/**
	 * Get the speed attribute of a piece
	 * 
	 * @return A piece's speed points attribute
	 * @version 1.0
	 * @since 1.0
	 */
	public int getSpeedPoints()
	{
		return speed;
	}
	
	/**
	 * Get the health attribute (current) of a piece
	 * 
	 * @return A piece's health points
	 * @version 1.0
	 * @since 1.0
	 */
	public int getHealthPoints()
	{
		return healthPoints;
	}
	
	/**
	 * Get the health attribute (maximum) of a piece
	 * 
	 * @return A piece's health points
	 * @version 1.0
	 * @since 1.3
	 */
	public int getMaxHealthPoints()
	{
		return maxHealthPoints;
	}
	
	/**
	 * Get the movement orientation of a piece
	 * 
	 * @return A piece's movement orientation: 
	 * <blockquote>'+' if a piece can move in + directions only.<br>'x' if a piece can move in diagonals only.
	 * <br>'*' if a piece can move in all the directions.</blockquote>
	 * @version 1.0
	 * @since 1.0
	 */
	public char getMovementOrientation()
	{
		return movement;
	}
	
	/**
	 * Get the short name of a piece
	 * 
	 * @return A piece's short name
	 * @version 1.0
	 * @since 1.0
	 */
	public String getShortName()
	{
		return shortName;
	}
	
	/**
	 * Get the full name of a piece
	 * 
	 * @return A piece's full name
	 * @version 1.0
	 * @since 1.3
	 */
	public String getFullName()
	{
		return fullName;
	}
	
	/**
	 * Get the icon designated to a piece
	 * 
	 * @return A piece's icon
	 * @version 1.0
	 * @since 1.0
	 */
	public JLabel getIcon()
	{
		return new JLabel(pieceIcon);
	}
	
	/**
	 * If a piece is alive or not
	 * 
	 * @return TRUE if the piece is alive<br>FALSE if the piece is dead
	 * @version 1.0
	 * @since 1.1
	 */
	public boolean isPieceAlive()
	{
		return pieceAlive;
	}
	
	/**
	 * Get the team which this piece belongs to
	 * 
	 * @return A piece's team ('e' for Eagle, 's' for Shark)
	 * @version 1.0
	 * @since 1.0
	 */
	public char getTeam()
	{
		return team;
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
	
	/**
	 * Increase the health points of the piece
	 * 
	 * @param addition amount of points that should be added
	 * @version 1.0
	 * @since 1.0
	 */
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
	
	/**
	 * Decrease the health points of the piece
	 * 
	 * @param reduction amount of points that should be reduced
	 * @version 1.0
	 * @since 1.0
	 */
	@Requires("pieceAlive")
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
	
	public Object clone()
	{
		Object clone = null;
		
		try 
		{
			clone = super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return clone;
	}
}
