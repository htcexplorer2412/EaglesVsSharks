package Model.Memento;

import Model.PlayerRegistry;
import Model.SingletonBoard;
import Model.SingletonDice;
import Model.Memento.Originator;
import View.SingletonGame;

public class SaveGameOriginator implements Originator 
{
	@Override
	public Memento createMemento() 
	{
		UndoMemento undoMem = (UndoMemento) SingletonBoard.getInstance().createMemento();
		SaveGameMemento m = new SaveGameMemento(SingletonBoard.getInstance().getRowIterator(), undoMem, PlayerRegistry.getPlayerObj(true), PlayerRegistry.getPlayerObj(false), SingletonDice.getInstance(), SingletonCareTaker.getInstance());
		return m;
	}

	@Override
	public void restore(Memento m) 
	{
		SaveGameMemento memento = (SaveGameMemento) m;
		//Loading players from the memento
		PlayerRegistry.loadPlayers(memento.getPlayer1(), memento.getPlayer2());
		//Loading board size and board related contents. If game class is already initialized, this will fail. This design is to restrict loading state during game.
		SingletonGame.loadState(memento.getBoardRows(), memento.getBoardColumns(), memento.getCurrentBoardState());
		//Loading state of the dice. If dice is already initialized, this will fail. This design is to restrict loading state during game.
		SingletonDice.loadState(memento.getDiceVal(), memento.getTurn(), memento.isDiceRolled());
		//Loading state of caretaker. This will provide undo option even after loading the game. If caretaker is already initialized, this will fail.
		SingletonCareTaker.loadState(memento.getUndoMementos());
	}

}
