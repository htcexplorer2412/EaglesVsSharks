package Model.Iterator;

public class ColumnIterator<T> implements CustomIterator<T> {

	private long index;
	private T[][] t;
	
	public ColumnIterator(T[][] t) {
		this.t = t;
		index = 0;
	}

	@Override
	public void first() {
		// TODO Auto-generated method stub
		index = 0;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		index++;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		if(currentIndex() == t.length*t[0].length)
			return true;
		
		return false;
	}

	@Override
	public T currentItem()
	{
		int i = (int) index % t.length;
		int j = (int) index / t[0].length;
		return t[i][j];
	}

	@Override
	public long currentIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	public int getRowLength()
	{
		return t.length;
	}
	
	public int getColumnLength()
	{
		return t[0].length;
	}
}
