package Model.Iterator;

public class ReverseRowIterator<T> implements CustomIterator<T> {

	private long index;
	private T[][] t;
	
	public ReverseRowIterator(T[][] t) {
		this.t = t;
		index = t.length*t[0].length - 1;
	}

	@Override
	public void first() {
		// TODO Auto-generated method stub
		index = t.length*t[0].length - 1;;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		index--;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		if(currentIndex() == -1)
			return true;
		
		return false;
	}

	@Override
	public long currentIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public T currentItem() {
		// TODO Auto-generated method stub
		int i = (int) index / t.length;
		int j = (int) index % t[0].length;
		return t[i][j];
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
