//This interface is a part of the 'Observer pattern' implemented in this project. The class which is to be observed will implement this interface. The pattern is used to update the observer whenever diceRolled value is changed by the Subject. This interface can be modified to add other triggers for the Observer to see.

public interface Subject {
	public void addObserver(Observer o);
	public void notifyObservers();
	public boolean getDiceRolled();
	public int getDiceVal();
	public boolean getTurn();
}
