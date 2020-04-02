
public class Dice {

	private int diceVal;
	final int min = 1, max = 6;
	
	public void rollDice()
	{    	
		diceVal = (int)(Math.random()*((max - min) + 1)) + min;
	}
	
	public int getDiceVal()
	{
		return diceVal;
	}
	
	public void deductDiceVal(int deduction)
	{
		this.diceVal = this.diceVal - deduction;
	}
}
