package Model.Strategy;

import com.google.java.contract.Requires;

import Model.Iterator.ReverseColumnIterator;
import Model.PrototypeTileFactory.Tile;

public class MoveNorthStrategy implements Strategy 
{
	private ReverseColumnIterator<Tile> iterator;
	
	public MoveNorthStrategy(ReverseColumnIterator<Tile> iter)
	{
		this.iterator = iter;
	}
	
	@Override
	@Requires({"(pointX - prevPointX) < 0", "(pointY - prevPointY) == 0"})
	public boolean checkPassageForObstacles(int prevPointX, int prevPointY, int pointX, int pointY) 
	{
		for(this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
		{
			if((iterator.currentIndex() <= (iterator.getColumnLength()*prevPointY + prevPointX)) && (iterator.currentIndex() > (iterator.getColumnLength()*pointY + pointX)))
			{
				if(!iterator.currentItem().goNorth())
				{
					return false;
				}
			}
			if((iterator.currentIndex() == (iterator.getColumnLength()*pointY + pointX)))
			{
				break;
			}
		}
		return true;
	}

	@Override
	@Requires({"(pointX - prevPointX) < 0", "(pointY - prevPointY) == 0"})
	public boolean checkPassageForPiece(int prevPointX, int prevPointY, int pointX, int pointY) 
	{
		for(this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
		{
			if((iterator.currentIndex() < (iterator.getColumnLength()*prevPointY + prevPointX)) && (iterator.currentIndex() > (iterator.getColumnLength()*pointY + pointX)))
			{
				if(iterator.currentItem().getOccupier() != null)
				{
					return false;
				}
			}
			if((iterator.currentIndex() == (iterator.getColumnLength()*pointY + pointX)))
			{
				break;
			}
		}
		return true;
	}

}
