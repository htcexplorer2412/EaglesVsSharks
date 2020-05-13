package Model.Iterator;

public interface CustomIterator<T> {
	public void first();
	public void next();
	public boolean isDone();
	public T currentItem();
	public long currentIndex();
}
