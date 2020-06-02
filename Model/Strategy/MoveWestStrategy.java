package Model.Strategy;

import com.google.java.contract.Requires;

import Model.Iterator.ReverseRowIterator;

/**
 * <H1>Move West side</H1>
 * <p>
 * This class gives a concrete implementation of the Strategy interface. 
 * <p>
 * This class implements a way to move from one point to other in a 2-dimensional structure. 
 * The class always performs all the checks while iterating through the tiles in west direction.
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-12
 */
public class MoveWestStrategy implements Strategy 
{
	/**
	 * Stores the reference to iterate through the tiles.
	 */
	private ReverseRowIterator iterator;
	
	/**
	 * 
	 * @param iter - ReverseRowIterator object for Tile collection
	 * @version 1.0
	 * @since 1.0
	 */
	public MoveWestStrategy(ReverseRowIterator iter)
	{
		this.iterator = iter;
	}
	
	/**
	 * Check between source and destination for blocked pathways using the ReverseRowIterator.
	 * 
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX 	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if there are no blocked pathways between source and destination (including source and destination).
	 * <br>FALSE otherwise.
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	@Requires({"(pointX - prevPointX) == 0", "(pointY - prevPointY) < 0"})
	public boolean checkPassageForObstacles(int prevPointX, int prevPointY, int pointX, int pointY) 
	{
		for(this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
		{
			if((iterator.currentIndex() <= (iterator.getRowLength()*prevPointX + prevPointY)) && (iterator.currentIndex() > (iterator.getRowLength()*pointX + pointY)))
			{
				if(!iterator.currentItem().goWest())
				{
					return false;
				}
			}
			if((iterator.currentIndex() == (iterator.getRowLength()*pointX + pointY)))
			{
				break;
			}
		}
		return true;
	}

	/**
	 * Check between source and destination for a piece from any team using the ReverseRowIterator.
	 * 
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX 	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if there is no piece occupying a Tile between source and destination (excluding source and destination).
	 * <br>FALSE if there is atleast 1 piece occupying a Tile between source and destination (excluding source and destination).
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	@Requires({"(pointX - prevPointX) == 0", "(pointY - prevPointY) < 0"})
	public boolean checkPassageForPiece(int prevPointX, int prevPointY, int pointX, int pointY) 
	{
		for(this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
		{
			if((iterator.currentIndex() < (iterator.getRowLength()*prevPointX + prevPointY)) && (iterator.currentIndex() > (iterator.getRowLength()*pointX + pointY)))
			{
				if(iterator.currentItem().getOccupier() != null)
				{
					return false;
				}
			}
			if((iterator.currentIndex() == (iterator.getRowLength()*pointX + pointY)))
			{
				break;
			}
		}
		return true;
	}
}
