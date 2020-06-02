package Model.Iterator;

import com.google.java.contract.Requires;

import Model.Collection.TileCollection;
import Model.PrototypeTileFactory.Tile;

/**
 * <H1>Reverse Row Iterator</H1>
 * <p>
 * This class implements the CustomIterator interface. 
 * <p>
 * It iterates through the matrix in a row-wise fashion but in reverse. It iterates through rows from right to left.
 * It iterates through the last row, then second last and goes on till the first row.
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-05-12
 */
public class ReverseRowIterator implements CustomIterator<Tile> 
{
	/**
	 * Stores the index after each iteration
	 */
	private long index;
	/**
	 * Stores the matrix to be iterated through
	 */
	private TileCollection t;
	
	/**
	 * Sets the index to last row's last element (the rightmost element in the row) and stores the matrix locally
	 * 
	 * @param t Matrix of type T
	 */
	@Requires("t != null")
	public ReverseRowIterator(TileCollection t) 
	{
		this.t = t;
		index = t.row_size()*t.column_size() - 1;
	}

	/**
	 * Sets the index to last row's last element (i.e. the rightmost element in the row).
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public void first() 
	{
		index = t.row_size()*t.column_size() - 1;;
	}

	/**
	 * Decrements the index by 1 (because moving in reverse)
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public void next() 
	{
		index--;
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
		if(currentIndex() == -1)
			return true;
		
		return false;
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
		return index;
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
		return t.getTile(i, j);
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
