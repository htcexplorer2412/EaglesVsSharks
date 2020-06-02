package Model.Iterator;

import com.google.java.contract.Requires;

import Model.Collection.TileCollection;
import Model.PrototypeTileFactory.Tile;

/**
 * <H1>Reverse Column Iterator</H1>
 * <p>
 * This class implements the CustomIterator interface. 
 * <p>
 * It iterates through the matrix in a column-wise fashion but in reverse. It iterates through columns from bottom to top.
 * It iterates through the last column, then second last and goes on till the first column.
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-05-12
 * 
 * @param <T> - The type of elements returned by this iterator
 */
public class ReverseColumnIterator implements CustomIterator<Tile> 
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
	 * Sets the index to last columns's last element (the bottom element in the column) and stores the matrix locally
	 * 
	 * @param t Matrix of type T
	 */
	@Requires("t != null")
	public ReverseColumnIterator(TileCollection t) 
	{
		this.t = t;
		index = t.row_size()*t.column_size() - 1;
	}
	
	/**
	 * Sets the index to last column's last element (i.e. the bottom element in the column).
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public void first() 
	{
		index = t.row_size()*t.column_size() - 1;
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
		if(index == -1)
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
		int i = (int) index % t.row_size();
		int j = (int) index / t.column_size();
		return t.getTile(i, j);
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
