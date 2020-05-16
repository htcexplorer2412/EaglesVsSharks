package Model;

import java.util.ArrayList;

import Controller.MouseAdapterParent;
import Model.Observer.*;
import View.Game;


public class Dice implements Subject{

	private int diceVal;
	private boolean turn;															//turn = true for Player 1's turn and turn = false for Player 2
	private boolean diceRolled;
	final int min = 1, max = 6;
	private ArrayList<Observer> observerList = new ArrayList<Observer>();
	private static Dice single_instance = null;

	/*
	 * Observers added. Player 2 always moves first because Player 1 gets to choose the team.
	 */
	private Dice() 
	{
		addObserver(Game.getInstance());
		addObserver(MouseAdapterParent.getInstance());
		this.turn = false;
	}
	
	public synchronized static Dice getInstance()
	{
		if(single_instance == null)
			single_instance = new Dice();
		
		return single_instance;
	}
	
	public boolean getDiceRolled() {
		return this.diceRolled;
	}
	
	public boolean getTurn()
	{
		return this.turn;
	}
	
	public synchronized void rollDice()
	{    	
		this.diceVal = (int)(Math.random()*((max - min) + 1)) + min;
		this.diceRolled = true;
		notifyObservers();
	}
	
	public int getDiceVal()
	{
		return diceVal;
	}
	
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

	//Add observers - that are observing this class
	@Override
	public void addObserver(Observer o) 
	{
		// TODO Auto-generated method stub
			this.observerList.add(o);
	}
	
	//Notify all the observers
	@Override
	public void notifyObservers() 
	{
		// TODO Auto-generated method stub
		for(int i = 0; i < observerList.size(); i++)
		{
			this.observerList.get(i).update(this);
		}
	}
}
