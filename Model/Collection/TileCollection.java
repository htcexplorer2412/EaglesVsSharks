package Model.Collection;

import Model.Iterator.CustomIterator;
import Model.Iterator.*;
import Model.PrototypeTileFactory.Tile;

public class TileCollection implements Collection<Tile>, Cloneable
{
	Tile tiles[][];
	
	public TileCollection(int rowSize, int columnSize)
	{
		this.tiles = new Tile[rowSize][columnSize];
	}
	
	public TileCollection(TileCollection t)
	{
		this.tiles = new Tile[t.row_size()][t.column_size()];
		
		for(int i = 0; i < this.tiles.length; i++)
		{
			for(int j = 0; j < this.tiles[0].length; j++)
			{
				this.tiles[i][j] = t.getTile(i, j);
			}
		}
	}
	
	public void add(int rowIndex, int columnIndex, Tile tile)
	{
		this.tiles[rowIndex][columnIndex] = tile;
	}
	
	public Tile getTile(int rowIndex, int columnIndex)
	{
		return this.tiles[rowIndex][columnIndex];
	}
	
	public int row_size()
	{
		return this.tiles.length;
	}
	
	public int column_size()
	{
		return this.tiles[0].length;
	}
	
	public void removeAt(int rowIndex, int columnIndex)
	{
		this.tiles[rowIndex][columnIndex] = null;
	}
	
	public void clear()
	{
		for(int i = 0; i < this.tiles.length; i++)
		{
			for(int j = 0; j < this.tiles[0].length; j++)
			{
				this.tiles[i][j] = null;
			}
		}
	}
	
	@Override
	public CustomIterator<Tile> createIterator(int type) 
	{
		if(type == 1)
			return new RowIterator(this);
		else if(type == 2)
			return new ReverseRowIterator(this);
		else if(type == 3)
			return new ColumnIterator(this);
		else if(type == 4)
			return new ReverseColumnIterator(this);
		else
			return null;
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

	public void removeAllOccupiers()
	{
		RowIterator iter = new RowIterator(this);
		for(iter.first(); !iter.isDone(); iter.next())
		{
			if(iter.currentItem().getOccupier() != null)
			{
				iter.currentItem().setOccupier(null);
			}
		}
	}
}
