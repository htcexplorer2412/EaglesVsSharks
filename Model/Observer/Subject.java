package Model.Observer;

/**
 * <H1>Subject</H1>
 * <p>
 * This interface is a part of the 'Observer pattern'.
 * <p>
 * A Subject can have one or more observers. 
 * An observer may be any object that implements interface Observer. 
 * After a Subject instance changes, an application calling the Subject's notifyObservers method causes all of its observers to be notified of the change by a call to their update method.
 * The order in which notifications will be delivered can be specified in the class implementing this interface.
 * When a class implementing Subject interface is newly created, its set of observers is empty. 
 * 
 * @author Ayam Ajmera
 * @version 1.3
 * @since 2020-04-21
 */
public interface Subject 
{
	/**
	 * Add an observer to the list of Observers. 
	 * 
	 * @param o - An object of the class implementing Observer interface
	 * @version 1.0
	 * @since 1.0
	 */
	public void addObserver(Observer o);
	/**
	 * Notify all the observers that have subscribed to the Subject.
	 * 
	 * @version 1.0
	 * @since 1.3
	 */
	public void notifyObservers();
}
