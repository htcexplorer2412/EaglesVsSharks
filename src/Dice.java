import java.util.ArrayList;

//Update to subject

public class Dice implements Subject{

	private int diceVal;
	private boolean turn;															//turn = true for Player 1's turn and turn = false for Player 2
	private boolean diceRolled;
	final int min = 1, max = 6;
	private ArrayList<Observer> observerList = new ArrayList<Observer>();
	private static Dice single_instance = null;
	
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
	
	//Change visibility of the method and consider the possibility of limiting the number of times this method can be accessed
	/*protected void setTurn(boolean value)
	{
		this.turn = value;
	}*/
	
	public boolean getTurn()
	{
		return this.turn;
	}
	
	public synchronized void rollDice()
	{    	
		this.diceVal = (int)(Math.random()*((max - min) + 1)) + min;
		this.diceRolled = true;
		notifyObservers(this.diceRolled);
	}
	
	public int getDiceVal()
	{
		return diceVal;
	}
	
	public boolean deductDiceVal(int deduction)
	{
		if(this.diceRolled)
		{
			//this.diceVal = this.diceVal - deduction;
			if(this.diceVal == deduction)
			{
				this.diceVal = this.diceVal - deduction;
				this.diceRolled = false;
				this.turn = !this.turn;
				//Change the turn and update observers here
				notifyObservers(this.diceRolled, this.turn);
				return true;
			}
			else if(this.diceVal > deduction)
			{
				this.diceVal = this.diceVal - deduction;
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
	
	//Notify observers when the diceRolled value is changed
		//Notify about turn as well
	public void notifyObservers(boolean diceRolled)
	{
		for(int i = 0; i < observerList.size(); i++)
		{
			this.observerList.get(i).update(diceRolled);
		}
	}
	
	public void notifyObservers(boolean diceRolled, boolean turn)
	{
		for(int i = 0; i < observerList.size(); i++)
		{
			this.observerList.get(i).update(diceRolled, turn);
		}
	}
}
