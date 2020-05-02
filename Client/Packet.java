import java.io.Serializable;

public class Packet implements Serializable {

	private static final long serialVersionUID = 1L;
	private Command command;
	private boolean restart;
	private boolean restartConfirm;
	private boolean forfeit;
	private boolean undo;
	private boolean exit;
	private boolean connectedPlayers;
	
	public Packet()
	{
		this.setCommand(null);
		this.setRestart(false);
		this.setRestartConfirm(false);
		this.setForfeit(false);
		this.setUndo(false);
		this.setExit(false);
		this.setConnectedPlayers(false);
	}
	
	public Packet(Command command, boolean restart, boolean restartConfirm, boolean forfeit, boolean undo, boolean exit, boolean connectedPlayers)
	{
		this.setCommand(command);
		this.setRestart(restart);
		this.setRestartConfirm(restartConfirm);
		this.setForfeit(forfeit);
		this.setUndo(undo);
		this.setExit(exit);
		this.setConnectedPlayers(connectedPlayers);
	}

	/*
	 * Setters and getters from here on
	 */
	public void setCommand(Command command)
	{
		this.command = command;
	}
	
	public void setRestart(boolean value)
	{
		this.restart = value;
	}
	
	public void setRestartConfirm(boolean value)
	{
		this.restartConfirm = value;
	}
	
	public void setForfeit(boolean value)
	{
		this.forfeit = value;
	}
	
	public void setUndo(boolean value)
	{
		this.undo = value;
	}
	
	public void setExit(boolean value)
	{
		this.exit = value;
	}
	
	public void setConnectedPlayers(boolean value)
	{
		this.connectedPlayers = value;
	}
	
	public Command getCommand()
	{
		return this.command;
	}
	
	public boolean isExit()
	{
		return this.exit;
	}
	
	public boolean isUndo()
	{
		return this.undo;
	}
	
	public boolean isRestart()
	{
		return this.restart;
	}
	
	public boolean isRestartConfirm()
	{
		return this.restartConfirm;
	}
	
	public boolean isForfeit()
	{
		return this.forfeit;
	}
	
	public boolean getConnectedPlayers()
	{
		return this.connectedPlayers;
	}
}
