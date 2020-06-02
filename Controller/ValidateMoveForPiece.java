package Controller;

import Model.SingletonBoard;
import Model.SingletonDice;
import Model.PlayerRegistry;
import Model.Strategy.*;
import View.SingletonGame;

/**
 * <H1>Move validation</H1>
 * This class checks for different parameters to validate move of a piece from source to destination.
 * 
 * @author	Ayam Ajmera
 * @version	1.0
 * @since	2020-05-13
 *
 */
public class ValidateMoveForPiece 
{
	/**
	 * Stores reference to the Strategy class instantiated
	 */
	private Strategy strategy;
	
	/**
	 * Checks with the Piece if the selected source and destination are valid for their movement orientation. If true, calls for another method to check for rolled value. If false, calls for an error message
	 * 
	 * @param steps		 Distance between source and destination tiles
	 * @param turn		 Which team's turn it is - True for Eagle team, False for Shark team
	 * @param name		 Short name of the piece
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if all the checks are valid<br>FALSE otherwise
	 */
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
			SingletonGame.getInstance().showError("Invalid move for this piece!");
			return false;
		}
	}
	
	/**
	 * Checks with the Dice class if the distance between source and destination doesn't exceed the value shown on Dice. If true, then calls for another method to determine the strategy to be used, to move from source to destination. Otherwise, calls for an error message
	 * 
	 * @param steps		 Distance between source and destination tiles
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if underlying checks pass<br>FALSE otherwise
	 */
	private boolean diceValueCheckAndValidate(int steps, int prevPointX, int prevPointY, int pointX, int pointY)
	{
		if(SingletonDice.getInstance().getDiceVal() >= steps)
		{
			if(validate(prevPointX, prevPointY, pointX, pointY))
				return true;
			else
				return false;
		}
		else
		{
			SingletonGame.getInstance().showError("Exceeded the value of dice!");
			return false;
		}
	}
	
	/**
	 * Determines the strategy to be used to move from source to destination and calls another method to check the path between source and destination for obstacles and other pieces.
	 * 
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if the underlying method returns true<br>FALSE, otherwise
	 */
	private boolean validate(int prevPointX, int prevPointY, int pointX, int pointY)
	{	
		if((pointX - prevPointX) > 0 && (pointY - prevPointY) == 0)						//South
		{
			strategy = new MoveSouthStrategy(SingletonBoard.getInstance().getColumnIterator());
		}
		else if((pointX - prevPointX) < 0 && (pointY - prevPointY) == 0)				//North
		{
			strategy = new MoveNorthStrategy(SingletonBoard.getInstance().getReverseColumnIterator());
		}
		else if((pointX - prevPointX) == 0 && (pointY - prevPointY) > 0)				//East
		{
			strategy = new MoveEastStrategy(SingletonBoard.getInstance().getRowIterator());
		}
		else if((pointX - prevPointX) == 0 && (pointY - prevPointY) < 0)				//West
		{
			strategy = new MoveWestStrategy(SingletonBoard.getInstance().getReverseRowIterator());
		}
		else if((pointX - prevPointX) < 0 && (pointY - prevPointY) < 0)					//North-West
		{
			strategy = new MoveNorthWestStrategy(SingletonBoard.getInstance().getReverseColumnIterator());
		}
		else if((pointX - prevPointX) < 0 && (pointY - prevPointY) > 0)					//North-East
		{
			strategy = new MoveNorthEastStrategy(SingletonBoard.getInstance().getColumnIterator());
		}
		else if((pointX - prevPointX) > 0 && (pointY - prevPointY) < 0)					//South-West
		{
			strategy = new MoveSouthWestStrategy(SingletonBoard.getInstance().getRowIterator());
		}
		else if((pointX - prevPointX) > 0 && (pointY - prevPointY) > 0)					//South-East
		{
			strategy = new MoveSouthEastStrategy(SingletonBoard.getInstance().getRowIterator());
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
	
	/**
	 * Checks for the path between source and destination for any obstacles or pieces using the selected strategy.
	 * 
	 * @param s			 Selected strategy for this move
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if there are no obstacles or pieces in the path<br>FALSE otherwise
	 */
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
			SingletonGame.getInstance().showError("Passage blocked. Move cannot be completed");
			return false;
		}
		else
		{
			SingletonGame.getInstance().showError("A piece is occupying a tile between the source and destination. Move cannot be completed");
			return false;
		}
	}
}
