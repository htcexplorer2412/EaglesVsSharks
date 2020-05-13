package Model.Observer;
//This interface is a part of the Observer pattern implemented in this project. The class who acts as observer will implement this interface. The pattern is used to update the observer whenever diceRolled value is changed by the Subject. This interface can be modified to add other triggers for the Observer to see.

public interface Observer {
	
	public void update(Subject s);
}
