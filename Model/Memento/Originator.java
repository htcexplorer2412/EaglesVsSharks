package Model.Memento;

public interface Originator 
{
	public Memento createMemento();
	public void restore(Memento m);
}
