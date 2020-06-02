package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.SingletonBoard;
import Model.SingletonDice;
import Model.PlayerRegistry;
import Model.Memento.SingletonCareTaker;
import View.SingletonGame;

public class TableButtonController implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 1; i < 4; i++)
		{
			if(e.getActionCommand().equals("Undo"+ i + " P1"))
			{
				if(!PlayerRegistry.getPlayerObj(true).undoUsed())
				{
					boolean ans = SingletonCareTaker.getInstance().undo(SingletonBoard.getInstance(), i);
					if(ans)
					{
						SingletonGame.getInstance().showMessage("Player 1 - Undo was successful. Number of moves backtracked - " + i);
						PlayerRegistry.getPlayerObj(true).setUndoUsed(true);
						SingletonGame.getInstance().enableUndoForPlayers(false, false);
						SingletonDice.getInstance().resetDice();
					}
					else
						SingletonGame.getInstance().showMessage("Player 1 - Undo was unsuccessful. Game doesn't have enough saved states");
				}
				else
					SingletonGame.getInstance().showMessage("Player 1 - Undo was unsuccessful. You have used up the chance to undo.");
				
			}
			else if(e.getActionCommand().equals("Undo"+ i + " P2"))
			{
				if(!PlayerRegistry.getPlayerObj(true).undoUsed())
				{
					boolean ans = SingletonCareTaker.getInstance().undo(SingletonBoard.getInstance(), i);
					if(ans)
					{
						SingletonGame.getInstance().showMessage("Player 2 - Undo was successful. Number of moves backtracked - " + i);
						PlayerRegistry.getPlayerObj(false).setUndoUsed(true);
						SingletonGame.getInstance().enableUndoForPlayers(false, false);
						SingletonDice.getInstance().resetDice();
					}
					else
						SingletonGame.getInstance().showMessage("Player 2 - Undo was unsuccessful. Game doesn't have enough saved states");	
				}
				else
					SingletonGame.getInstance().showMessage("Player 2 - Undo was unsuccessful. You have used up the chance to undo.");
			}
		}
	}
	
	public void enableUndoButtonForPlayer(boolean turn)
	{
		if(turn)
		{
			if(PlayerRegistry.getPlayerObj(turn).undoUsed())
				SingletonGame.getInstance().enableUndoForPlayers(false, false);
			else
				SingletonGame.getInstance().enableUndoForPlayers(true, false);
		}
		else
		{
			if(PlayerRegistry.getPlayerObj(turn).undoUsed())
				SingletonGame.getInstance().enableUndoForPlayers(false, false);
			else
				SingletonGame.getInstance().enableUndoForPlayers(false, true);
		}
		
	}
}
