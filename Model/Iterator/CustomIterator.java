package Model.Iterator;

/**
 * <H1>Iterator</H1>
 * <p>
 * An iterator over a collection.
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-12
 * 
 * @param <T> - The type of elements returned by this iterator
 */
public interface CustomIterator<T> 
{
	/**
	 * Places the index to first element of the collection
	 * 
	 * @since 1.0
	 */
	public void first();
	/**
	 * Changes the index to next element
	 * 
	 * @since 1.0
	 */
	public void next();
	/**
	 * Returns true if there are no more elements in the collection to iterate through. Returns false otherwise
	 * @return TRUE if there are no more elements<br>FALSE otherwise
	 * 
	 * @since 1.0
	 */
	public boolean isDone();
	/**
	 * Returns the element at the current index
	 * 
	 * @return The element at the current position
	 * 
	 * @since 1.0
	 */
	public T currentItem();
	/**
	 * Returns the index value at any iteration.
	 * 
	 * @return index value
	 * 
	 * @since 1.0
	 */
	public long currentIndex();
}
