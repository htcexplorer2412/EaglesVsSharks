package Model.Memento;

import java.util.ArrayDeque;
import Model.SingletonBoard;

public class SingletonCareTaker
{
	private ArrayDeque<Memento> mementos;
	private static SingletonCareTaker single_instance = null;
	
	private SingletonCareTaker()
	{
		mementos = new ArrayDeque<Memento>(7);
	}
	
	public synchronized static SingletonCareTaker getInstance()
	{
		if (single_instance == null) 
            single_instance = new SingletonCareTaker(); 
  
        return single_instance;
	}
	
	public synchronized static void loadState(ArrayDeque<Memento> mementos)
	{
		if(single_instance == null)
		{
			single_instance = new SingletonCareTaker();
			single_instance.mementos = mementos;
		}
	}
	
	public void save(Memento m)
	{
		if(mementos.size() == 7)
			mementos.removeLast();
		
		mementos.push(m);
	}
	
	//Consider the case for less number of states in the deque
	public boolean undo(SingletonBoard board, int numberOfUndoMoves)
	{
		if(mementos.size() > numberOfUndoMoves*2)
		{
			for(int i = 0; i < numberOfUndoMoves*2; i++)
			{
				mementos.pop();
			}
			board.restore(mementos.pop());
			return true;
		}
		else
			return false;
	}
	
	public ArrayDeque<Memento> getMementos()
	{
		return mementos;
	}
}
