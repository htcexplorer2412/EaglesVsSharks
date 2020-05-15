package Model.Strategy;

import com.google.java.contract.Requires;

import Model.Iterator.RowIterator;
import Model.PrototypeTileFactory.Tile;

public class MoveSouthEastStrategy implements Strategy 
{
	private RowIterator<Tile> iterator;
	
	public MoveSouthEastStrategy(RowIterator<Tile> iter)
	{
		this.iterator = iter;
	}
	
	@Override
	@Requires({"(pointX - prevPointX) > 0", "(pointY - prevPointY) > 0"})
	public boolean checkPassageForObstacles(int prevPointX, int prevPointY, int pointX, int pointY) 
	{
		for(this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
		{
			if((iterator.currentIndex() >= (iterator.getRowLength()*prevPointX + prevPointY)) && (prevPointX < pointX && prevPointY < pointY))
			{
				if(!iterator.currentItem().goSouthEast())
				{
					return false;
				}
				
				prevPointX++;
				prevPointY++;
			}
			if((iterator.currentIndex() == (iterator.getRowLength()*pointX + pointY)))
			{
				break;
			}
		}
		return true;
	}

	@Override
	@Requires({"(pointX - prevPointX) > 0", "(pointY - prevPointY) > 0"})
	public boolean checkPassageForPiece(int prevPointX, int prevPointY, int pointX, int pointY) 
	{
		prevPointX++;
		prevPointY++;
		
		for(this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
		{
			if((iterator.currentIndex() >= (iterator.getRowLength()*prevPointX + prevPointY)) && (prevPointX < pointX && prevPointY < pointY))
			{
				if(iterator.currentItem().getOccupier() != null)
				{
					return false;
				}
				
				prevPointX++;
				prevPointY++;
			}
			if((iterator.currentIndex() == (iterator.getRowLength()*pointX + pointY)))
			{
				break;
			}
		}
		return true;
	}

}
