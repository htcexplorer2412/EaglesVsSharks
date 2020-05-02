import java.io.Serializable;
import java.util.ArrayList;

/*
 * Send command from client to server and vice versa
 * 
 * Code incomplete
 */
public class Command implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> players;
	private boolean diceRoll;
	private String playerName;
	private Game gameObj;
	private Board boardObj;
	private Tile[][] tileObjMatrix;
	private Tile tileObj;
	private Player playerObj;
	
	
	public Command(int commandNum, int playerNumber)
	{
		
	}
	
	public Command(ArrayList<String> players)
	{
		this.setPlayersList(players);
	}
	
	public Command(boolean diceRollClicked)
	{
		this.setDiceRollClicked(diceRollClicked);
	}
	
	public Command(ArrayList<String> players, String playerName)
	{
		this.setPlayerName(playerName);
	}
	
	public Command(Game game, Board board, Tile[][] tiles, Player player)
	{
		this.setGameObj(game);
		this.setBoardObj(board);
		this.setTileObjMatrix(tiles);
		this.setPlayerObj(player);
	}
	
	public Command(Game game)
	{
		this.setGameObj(game);
	}
	
	public Command(Tile tile)
	{
		this.setTileObj(tile);
	}
	
	public Command(Command command)
	{
		this.setPlayersList(command.getPlayersList());
		this.setDiceRollClicked(command.getDiceRollClicked());
		this.setPlayerName(command.getPlayerName());
		this.setGameObj(command.getGameObj());
		this.setBoardObj(command.getBoardObj());
		this.setTileObjMatrix(command.getTileObjMatrix());
		this.setTileObj(command.getTileObj());
		this.setPlayerObj(command.getPlayerObj());
	}
	
	/*
	 * Setters and getters from here
	 */
	
	public void setPlayersList(ArrayList<String> players)
	{
		this.players = players;
	}
	
	public ArrayList<String> getPlayersList()
	{
		return this.players;
	}
	
	public void setDiceRollClicked(boolean diceRollClicked)
	{
		this.diceRoll = diceRollClicked;
	}
	
	public boolean getDiceRollClicked()
	{
		return this.diceRoll;
	}
	
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public void setGameObj(Game game)
	{
		this.gameObj = game;
	}
	
	public Game getGameObj()
	{
		return this.gameObj;
	}
	
	public void setBoardObj(Board board)
	{
		this.boardObj = board;
	}
	
	public Board getBoardObj()
	{
		return this.boardObj;
	}
	
	public void setTileObjMatrix(Tile[][] tiles)
	{
		this.tileObjMatrix = tiles;
	}
	
	public Tile[][] getTileObjMatrix()
	{
		return this.tileObjMatrix;
	}
	
	public void setTileObj(Tile tile)
	{
		this.tileObj = tile;
	}
	
	public Tile getTileObj()
	{
		return this.tileObj;
	}
	
	public void setPlayerObj(Player player)
	{
		this.playerObj = player;
	}
	
	public Player getPlayerObj()
	{
		return this.playerObj;
	}
}
