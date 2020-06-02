package Model.Collection;

import Model.Iterator.CustomIterator;

public interface Collection<T> 
{
	public CustomIterator<T> createIterator(int type); 
}
