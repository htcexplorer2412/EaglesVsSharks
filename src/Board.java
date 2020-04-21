import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * Add a buffer place on board where pieces will be initialized and after dice roll they will be moved to the board.
 */

public class Board {
	
	private JPanel[][] cellPanel;
	private JPanel mainPanel;
	private Island island1, island2, island3, island4, island5, island6;
	private Tile[][] t;
	private MouseAdapterParent map;
	private boolean whosTurn;							//true for eagle, false for shark
	//private Player eagle, shark;
	
	//private String[][] boardState = new String[][] {{"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}};
	
	public Board(JPanel[][] cellPanel, JPanel mainPanel, Player eagle, Player shark)
	{
		this.cellPanel = cellPanel;
		this.mainPanel = mainPanel;
		t = new Tile[cellPanel.length][cellPanel.length];
		this.whosTurn = true;
		this.map = new MouseAdapterParent(eagle, shark);
		//this.diceRolled = false;
	}
	
	public void setDiceAndTurn(boolean value)
	{
		map.setDiceRolledValue(value);
		//map.setTurn(this.whosTurnItIs());
	}
	
	public void setDiceAndTurn(boolean value, int dValue)
	{
		/*for(int i = 0 ; i < t.length; i++)
		{
			for(int j = 0; j < t.length; j++)
			{
				t[i][j].setDiceRolledValue(value);
			}
		}*/
		
		map.setDiceRolledValue(value);
		//map.setTurn(this.whosTurnItIs());
		map.setRolledValue(dValue);
	}
	
	public boolean whosTurnItIs()
	{
		return whosTurn;
	}
	
	public void changeTurn(boolean value)
	{
		this.whosTurn = value;
	}
	
	/*
	 * Island location are fixed on the board. We won't be giving the selection of placing islands to user. Hence, hard-coded.
	 */
	public void drawBoard()
	{
		for(int i = 0; i < cellPanel.length; i++)
		{
			for(int j = 0; j < cellPanel.length; j++)
			{
				t[i][j] = new Tile(i, j, cellPanel[i][j], mainPanel);
				t[i][j].setTileVisible(map);
				
				if((i == 0 && j == 0))
					t[i][j].setAsPartOfIsland("2x2", "TLS");
				else if((i == 0 && j == 1))
					t[i][j].setAsPartOfIsland("2x2", "TRS");
				else if((i == 1 && j == 0))
					t[i][j].setAsPartOfIsland("2x2", "BLS");
				else if((i == 1 && j == 1))
					t[i][j].setAsPartOfIsland("2x2", "BRS");
				else if((i == 10 && j == 10))
					t[i][j].setAsPartOfIsland("2x2", "TLE");
				else if((i == 10 && j == 11))
					t[i][j].setAsPartOfIsland("2x2", "TRE");
				else if((i == 11 && j == 10))
					t[i][j].setAsPartOfIsland("2x2", "BLE");
				else if((i == 11 && j == 11))
					t[i][j].setAsPartOfIsland("2x2", "BRE");
				else if((i == 2 && j == 4))
					t[i][j].setAsPartOfIsland("2x1", "LHS");
				else if((i == 2 && j == 5))
					t[i][j].setAsPartOfIsland("2x1", "RHS");
				else if((i == 8 && j == 2))
					t[i][j].setAsPartOfIsland("2x1", "TVS");
				else if((i == 9 && j == 2))
					t[i][j].setAsPartOfIsland("2x1", "BVS");
				else if((i == 9 && j == 6))
					t[i][j].setAsPartOfIsland("2x1", "LHE");
				else if((i == 9 && j == 7))
					t[i][j].setAsPartOfIsland("2x1", "RHE");
				else if((i == 2 && j == 9))
					t[i][j].setAsPartOfIsland("2x1", "TVE");
				else if((i == 3 && j == 9))
					t[i][j].setAsPartOfIsland("2x1", "BVE");
			}
		}
		this.map.storeTiles(t);
	}
	
	public void arrangeIslands()
	{
		island1 = new Island("2x2", 's', 20, 0, 0, 0, 1, 1, 0, 1, 1);
		island1.setEntryPoints(2, 0, 2, 1);
		
		island2 = new Island("2x1", 's', 10, 2, 4, 2, 5);
		island2.setEntryPoints(3, 4, 3, 5);
		
		island3 = new Island("2x1", 's', 10, 8, 2, 9, 2);
		island3.setEntryPoints(8, 1, 9, 1);
		
		island4 = new Island("2x1", 'e', 20, 9, 6, 9, 7);
		island4.setEntryPoints(8, 6, 8, 7);
		
		island5 = new Island("2x1", 'e', 10, 2, 9, 3, 9);
		island5.setEntryPoints(2, 10, 3, 10);
		
		island6 = new Island("2x2", 'e', 10, 10, 10, 10, 11, 11, 10, 11, 11);
		island6.setEntryPoints(9, 10, 9, 11);
	}
	
	public void arrangePieceInitial(char team, JLabel[] icon, JTextField[] name)
	{		
		if(team == 'e')
		{
			for(int i = 0; i < icon.length; i++)
			{
				t[i + 5][11].putPieceOnTile(icon[i], name[i]);
				System.out.println("Putting " + name[i].getText() + " on tile " + (i+5) + ",11");
			}
		}
		else if(team == 's')
		{
			for(int i = 0; i < icon.length; i++)
			{
				t[i + 5][0].putPieceOnTile(icon[i], name[i]);
				System.out.println("Putting " + name[i].getText() + " on tile " + (i+5) + ",0");
			}
		}
	}
}
