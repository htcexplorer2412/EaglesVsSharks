package Model.Observer;

/**
 * <H1>Observer</H1>
 * <p>
 * This interface is a part of the 'Observer pattern'.
 * <p>
 * A class can implement the Observer interface when it wants to be informed of changes in 'Subject' objects. 
 * 
 * @author Ayam Ajmera
 * @version 1.3
 * @since 2020-04-21
 */
public interface Observer 
{
	/**
	 * This method is called whenever the Subject is changed. 
	 * An application calls a Subject's notifyObservers method to have all the observers notified of the change.
	 * 
	 * @param s - The Subject
	 * @version 1.2
	 * @since 1.0
	 */
	public void update(Subject s);
}
