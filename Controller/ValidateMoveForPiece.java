package Controller;

import Model.Board;
import Model.Dice;
import Model.PlayerRegistry;
import Model.Strategy.*;
import View.Game;

public class ValidateMoveForPiece 
{
	private Strategy strategy;
	
	public boolean validateMove(int steps, boolean turn, String name, int prevPointX, int prevPointY, int pointX, int pointY)
	{	
		if(PlayerRegistry.getPlayerObj(turn).checkValidMove(name, prevPointX, prevPointY, pointX, pointY))
		{
			if(diceValueCheckAndValidate(steps, prevPointX, prevPointY, pointX, pointY))
				return true;
			else
				return false;
		}
		else
		{
			Game.getInstance().showError("Invalid move for this piece!");
			return false;
		}
	}
	
	private boolean diceValueCheckAndValidate(int steps, int prevPointX, int prevPointY, int pointX, int pointY)
	{
		if(Dice.getInstance().getDiceVal() >= steps)
		{
			if(validate(prevPointX, prevPointY, pointX, pointY))
				return true;
			else
				return false;
		}
		else
		{
			Game.getInstance().showError("Exceeded the value of dice!");
			return false;
		}
	}
	
	private boolean validate(int prevPointX, int prevPointY, int pointX, int pointY)
	{	
		if((pointX - prevPointX) > 0 && (pointY - prevPointY) == 0)						//South
		{
			strategy = new MoveSouthStrategy(Board.getInstance().getColumnIterator());
		}
		else if((pointX - prevPointX) < 0 && (pointY - prevPointY) == 0)				//North
		{
			strategy = new MoveNorthStrategy(Board.getInstance().getReverseColumnIterator());
		}
		else if((pointX - prevPointX) == 0 && (pointY - prevPointY) > 0)				//East
		{
			strategy = new MoveEastStrategy(Board.getInstance().getRowIterator());
		}
		else if((pointX - prevPointX) == 0 && (pointY - prevPointY) < 0)				//West
		{
			strategy = new MoveWestStrategy(Board.getInstance().getReverseRowIterator());
		}
		else if((pointX - prevPointX) < 0 && (pointY - prevPointY) < 0)					//North-West
		{
			strategy = new MoveNorthWestStrategy(Board.getInstance().getReverseColumnIterator());
		}
		else if((pointX - prevPointX) < 0 && (pointY - prevPointY) > 0)					//North-East
		{
			strategy = new MoveNorthEastStrategy(Board.getInstance().getColumnIterator());
		}
		else if((pointX - prevPointX) > 0 && (pointY - prevPointY) < 0)					//South-West
		{
			strategy = new MoveSouthWestStrategy(Board.getInstance().getRowIterator());
		}
		else if((pointX - prevPointX) > 0 && (pointY - prevPointY) > 0)					//South-East
		{
			strategy = new MoveSouthEastStrategy(Board.getInstance().getRowIterator());
		}
		else
		{
			return false;
		}
		
		if(strategyCheck(strategy, prevPointX, prevPointY, pointX, pointY))
			return true;
		else
			return false;
	}
	
	private boolean strategyCheck(Strategy s, int prevPointX, int prevPointY, int pointX, int pointY)
	{
		boolean strategyComp1 = s.checkPassageForObstacles(prevPointX, prevPointY, pointX, pointY);
		boolean strategyComp2 = s.checkPassageForPiece(prevPointX, prevPointY, pointX, pointY);
		
		if(strategyComp1 && strategyComp2)
		{
			return true;
		}
		else if(!strategyComp1 && (strategyComp2 || !strategyComp2))
		{
			Game.getInstance().showError("Passage blocked. Move cannot be completed");
			return false;
		}
		else
		{
			Game.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
			return false;
		}
	}
}
