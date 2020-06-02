package Model.Iterator;

import com.google.java.contract.Requires;

import Model.Collection.TileCollection;
import Model.PrototypeTileFactory.Tile;

/**
 * <H1>Row Iterator</H1>
 * <p>
 * This class implements the CustomIterator interface. 
 * <p>
 * It iterates through the matrix in a row-wise fashion. It iterates through rows from left to right.
 * It iterates through the first row, then second and goes on till the last row.
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-05-12
 */
public class RowIterator implements CustomIterator<Tile> 
{
	/** Stores the index after each iteration */
	private long index;
	/** Stores the matrix to be iterated through */
	private TileCollection t;
	
	/**
	 * Sets the index to 0 and stores the collection reference locally
	 * 
	 * @param t Matrix of type T
	 */
	@Requires("t != null")
	public RowIterator(TileCollection t) 
	{
		this.t = t;
		index = 0;
	}

	/**
	 * Sets the index to 0.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public void first() 
	{
		index = 0;
	}

	/**
	 * Increments the index by 1
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public void next() 
	{
		index++;
	}

	/**
	 * Returns a boolean value specifying if all the indexes are iterated or not
	 * 
	 * @return TRUE if all the indexes are iterated<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean isDone() 
	{
		//if(index == t.length*t[0].length)
		if(index == t.row_size()*t.column_size())
			return true;
		
		return false;
	}

	/**
	 * Converts the index into x and y axis values and returns the element at that position
	 * 
	 * @return Element at current position
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public Tile currentItem()
	{
		int i = (int) index / t.row_size();
		int j = (int) index % t.column_size();
		return (Tile) t.getTile(i, j);
	}

	/**
	 * Returns the index value
	 * 
	 * @return index value
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public long currentIndex() 
	{
		// TODO Auto-generated method stub
		return index;
	}

	/**
	 * Returns the row length of the matrix
	 * 
	 * @return row length
	 * @version 1.0
	 * @since 1.1
	 */
	public int getRowLength()
	{
		return t.row_size();
	}
	
	/**
	 * Returns the column length of the matrix
	 * 
	 * @return column length
	 * @version 1.0
	 * @since 1.1
	 */
	public int getColumnLength()
	{
		return t.column_size();
	}
}
