package Model.Memento;

import java.io.Serializable;
import java.util.ArrayDeque;
import Model.Player;
import Model.SingletonDice;
import Model.Iterator.RowIterator;

public class SaveGameMemento implements Memento, Serializable
{
	private static final long serialVersionUID = 145L;
	private int boardRows;
	private int boardColumns;
	private Player player1;
	private Player player2;
	private int diceVal;
	private boolean turn;
	private boolean diceRolled;
	private UndoMemento currentBoardState;
	private ArrayDeque<Memento> undo_mementos;
	
	//Singletons not serializable
	//public SaveGameMemento(RowIterator iterator, Player player1, Player player2, ArrayList<Island> islands, SingletonDice dice, SingletonCareTaker careTaker)
	public SaveGameMemento(RowIterator iterator, UndoMemento memento, Player player1, Player player2, SingletonDice dice, SingletonCareTaker careTaker)
	{
		this.boardColumns = iterator.getColumnLength();
		this.boardRows = iterator.getRowLength();
		this.currentBoardState = memento;
		this.undo_mementos = careTaker.getMementos();
		this.player1 = player1;
		this.player2 = player2;
		this.diceVal = dice.getDiceVal();
		this.diceRolled = dice.getDiceRolled();
		this.turn = dice.getTurn();
	}

	public Player getPlayer1() 
	{
		return player1;
	}

	public Player getPlayer2() 
	{
		return player2;
	}

	public int getDiceVal() 
	{
		return diceVal;
	}

	public boolean getTurn() 
	{
		return turn;
	}

	public boolean isDiceRolled() 
	{
		return diceRolled;
	}

	public ArrayDeque<Memento> getUndoMementos() 
	{
		return undo_mementos;
	}

	public int getBoardRows() 
	{
		return boardRows;
	}

	public int getBoardColumns() 
	{
		return boardColumns;
	}

	public UndoMemento getCurrentBoardState() 
	{
		return currentBoardState;
	}
}
