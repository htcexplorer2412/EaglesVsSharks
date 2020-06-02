package Model;

import java.util.ArrayList;

import com.google.java.contract.Requires;

import Controller.SingletonMouseAdapterParent;
import Model.Observer.*;
import View.SingletonGame;

/**
 * <H1>Dice</H1>
 * <p>
 * This class represents the Dice that is used in the game.
 * <p>
 * This class generates a random number between 1 and 6 and a player can move that number of steps on the board. 
 * This class also acts as a Subject in Observer pattern. 
 * It stores a list of observers and when state of the dice changes, it notifies all the observers about the change in state.
 * It is also implementing Singleton pattern and hence only one instance of Dice can be created for a game.
 * 
 * @author Ayam Ajmera
 * @version 1.5
 * @since 2020-04-21
 */
public class SingletonDice implements Subject
{
	/** Stores the random number that is generated when dice is rolled */
	private int diceVal;
	/** Stores the turn. TRUE when its Player 1's turn, FALSE when its Player 2's turn. */
	private boolean turn;
	/** Stores the state of the dice. TRUE if dice has been rolled, FALSE otherwise */
	private boolean diceRolled;
	final int min = 1, max = 6;
	/** Stores the observers that have subscribed to this Subject */
	private ArrayList<Observer> observerList = new ArrayList<Observer>();
	/** Stores the only instance of this class */
	private static SingletonDice single_instance = null;

	/**
	 * Constructor is private because this class follows Singleton pattern. 
	 * It adds the observers to the list and sets the turn to false, so its Player 2's turn 
	 * 
	 * @version 1.2
	 * @since 1.0
	 */
	private SingletonDice() 
	{
		addObserver(SingletonGame.getInstance());
		addObserver(SingletonMouseAdapterParent.getInstance());
		this.turn = false;
	}
	
	/**
	 * This method should be called to get an instance of this class. 
	 * As the class is a Singleton class, it's constructor is hidden and the class cannot be instantiated by a client using the new operator.
	 * When this method is called, it checks for existing instance of this class. 
	 * If there is, then it returns that instance, else it creates a new instance of this class.
	 * 
	 * 
	 * @return Instance of this class.
	 * @version 1.0
	 * @since 1.2
	 */
	public synchronized static SingletonDice getInstance()
	{
		if(single_instance == null)
			single_instance = new SingletonDice();
		
		return single_instance;
	}
	
	public synchronized static void loadState(int diceVal, boolean turn, boolean diceRolled)
	{
		if(single_instance == null)
		{
			single_instance = new SingletonDice();
			single_instance.diceVal = diceVal;
			single_instance.turn = turn;
			single_instance.diceRolled = diceRolled;
			single_instance.notifyObservers();
		}
	}
	
	/**
	 * Get the state of the dice
	 * 
	 * @return TRUE if dice has been rolled<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	public boolean getDiceRolled() {
		return this.diceRolled;
	}
	
	/**
	 * Get the current turn.
	 * 
	 * @return TRUE when its Player 1's turn<br>FALSE when its Player 2's turn.
	 * @version 1.0
	 * @since 1.2
	 */
	public boolean getTurn()
	{
		return this.turn;
	}
	
	/**
	 * Rolls dice - Generates a random number between 1 and 6 and notifies observers about the new value generated and the state of Dice.
	 * 
	 * @version 1.2
	 * @since 1.0
	 */
	public synchronized void rollDice()
	{    	
		this.diceVal = (int)(Math.random()*((max - min) + 1)) + min;
		this.diceRolled = true;
		notifyObservers();
	}
	
	/**
	 * Get the value rolled
	 * 
	 * @return Integer between 1 and 6.
	 * @version 1.0
	 * @since 1.0
	 */
	public int getDiceVal()
	{
		return diceVal;
	}
	
	/**
	 * Deducts the given amount from the rolled value. 
	 * When the rolled value goes to 0, it changes the state of the dice to false and now the dice can be rolled again. 
	 * It also changes the turn when the value goes to 0.
	 * If after the deduction, rolled value doesn't reduce to 0 then the new value is stored in place of original rolled value and observers are notified about that.
	 * This feature lets multiple pieces to move in a single turn.
	 * 
	 * @param deduction Integer that is to be deducted from the rolled value.
	 * @return TRUE if deduction was valid and the input parameter was less than or equal to the rolled value<br>
	 * FALSE if dice was not rolled or the input exceeded the rolled value.
	 * @version 1.4
	 * @since 1.0
	 */
	public boolean deductDiceVal(int deduction)
	{
		if(this.diceRolled)
		{
			if(this.diceVal == deduction)
			{
				this.diceVal = this.diceVal - deduction;
				this.diceRolled = false;
				this.turn = !this.turn;
				//Change the turn and update observers here
				notifyObservers();
				return true;
			}
			else if(this.diceVal > deduction)
			{
				this.diceVal = this.diceVal - deduction;
				notifyObservers();
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	/**
	 * Reset the rolled value and the state of the dice and notifies the observers.
	 * 
	 * @version 1.0
	 * @since 1.5
	 */
	public void resetDice()
	{
		this.diceRolled = false;
		this.diceVal = 0;
		notifyObservers();
	}
	
	/**
	 * Add the observers that have subscribed to this class 
	 * 
	 * @version 1.1
	 * @since 1.2
	 */
	@Override
	@Requires("o != null")
	public void addObserver(Observer o) 
	{
		this.observerList.add(o);
	}
	
	/**
	 * Notify all the observers that have subscribed to this class
	 * 
	 * @version 1.2
	 * @since 1.2
	 */
	@Override
	public void notifyObservers() 
	{
		for(int i = 0; i < observerList.size(); i++)
		{
			this.observerList.get(i).update(this);
		}
	}
	
	
}
