import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import TileFactory.*;


/*
 * Add a buffer place on board where pieces will be initialized and after dice roll they will be moved to the board.
 */

public class Board implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel[][] cellPanel;
	private JPanel mainPanel;
	private Island island1, island2, island3, island4, island5, island6;
	private Tile[][] t;
	private TileView[][] tView;
	//private MouseAdapterParent map;
	//private Player eagle, shark;
	private static Board single_instance = null;
	
	//private String[][] boardState = new String[][] {{"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}};
	
	//private Board(JPanel[][] cellPanel, JPanel mainPanel)
	private Board()
	{
		/**/
	}
	
	public synchronized static Board getInstance() 
    { 
        if (single_instance  == null) 
            single_instance = new Board(); 
  
        return single_instance; 
    }
	
	public void setBoardPanels(JPanel[][] cellPanel, JPanel mainPanel)
	{
		this.cellPanel = cellPanel;
		this.mainPanel = mainPanel;
		this.t = new Tile[this.cellPanel.length][this.cellPanel.length];
		this.tView = new TileView[this.cellPanel.length][this.cellPanel.length];
	}
	
	/*
	 * Island location are fixed on the board. We won't be giving the selection of placing islands to user. Hence, hard-coded.
	 */
	public void drawBoard()
	{
		//Putting unbordered and bordered tiles
		for(int i = 0; i < t.length; i++)
		{
			for(int j = 0; j < t.length; j++)
			{
				if((i <= 1 && j == 1) || (i >= t.length - 2 && j == t.length - 1))
				{
					t[i][j] = (EBorderedTile) TileFactory.getTile(4);					//EBordered
				}
				else if(i == (t.length/2) - 4 && (j == (t.length/2) - 2 || j == (t.length/2) + 3))
				{
					t[i][j] = (NWBorderedTile) TileFactory.getTile(7);					//NWBordered
				}
				else if((i == (t.length/2) - 4 && j == (t.length/2) - 1) || (i == (t.length/2) + 2 && j == (t.length/2) - 4))
				{
					t[i][j] = (NEBorderedTile) TileFactory.getTile(6);					//NEBordered
				}
				else if((i >= t.length - 2 && j == t.length - 2) || (i <= 1 && j == 0))
				{
					t[i][j] = (WBorderedTile) TileFactory.getTile(5);					//WBordered
				}
				else if(i == (t.length/2) + 3 && (j == (t.length/2) - 4 || j == (t.length/2) + 1))
				{
					t[i][j] = (SEBorderedTile) TileFactory.getTile(8);					//SEBordered
				}
				else if((i == (t.length/2) - 3 && j == (t.length/2) + 3) || (i == (t.length/2) + 3 && j == t.length/2))
				{
					t[i][j] = (SWBorderedTile) TileFactory.getTile(9);					//SWBordered
				}
				else
				{
					t[i][j] = (unborderedTile) TileFactory.getTile(1);					//Unbordered tile
				}
			}
		}
		
		//Setting neighbors for each tile cell
		for(int i = 0; i < t.length; i++)
		{
			for(int j = 0; j < t.length; j++)
			{
				if(i == 0)
				{
					t[i][j].setNorthNeighbour(null);
					t[i][j].setSouthNeighbour(t[i + 1][j]);
				}
				if(j == 0)
				{
					t[i][j].setWestNeighbour(null);
					t[i][j].setEastNeighbour(t[i][j + 1]);
				}
				if(i == t.length - 1)
				{
					t[i][j].setNorthNeighbour(t[i - 1][j]);
					t[i][j].setSouthNeighbour(null);
				}
				if(j == t.length - 1)
				{
					t[i][j].setEastNeighbour(null);
					t[i][j].setWestNeighbour(t[i][j - 1]);
				}
				if(j > 0 && j < t.length - 1)
				{
					t[i][j].setEastNeighbour(t[i][j + 1]);
					t[i][j].setWestNeighbour(t[i][j - 1]);
				}
				if(i > 0 && i < t.length - 1)
				{
					t[i][j].setNorthNeighbour(t[i - 1][j]);
					t[i][j].setSouthNeighbour(t[i + 1][j]);
				}
			}
		}
		
		//System.out.println("Length of tile array - " + t.length);
		for(int i = 0; i < t.length; i++)
		{
			for(int j = 0; j < t.length; j++)
			{
				tView[i][j] = new TileView(this.cellPanel[i][j], this.mainPanel);
				tView[i][j].setTileVisible(MouseAdapterParent.getInstance(), j, t[i][j]);
			}
		}
		//Create static method to access TileView class instances from MouseAdapterParent
	}
	
	public Tile getTileObj(int x, int y)
	{
		return t[x][y];
	}
	
	public TileView getTileViewObj(int x, int y)
	{
		return tView[x][y];
	}
	
	/*public void arrangeIslands()
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
	}*/
	
	public void arrangePieceInitial(char team, JLabel[] icon, String[] name)
	{		
		if(team == 'e')
		{
			for(int i = 0; i < icon.length; i++)
			{
				tView[i + 5][11].putPieceOnTile(icon[i]);
				t[i + 5][11].setOccupierName(name[i]);
				System.out.println("Putting " + name[i] + " on tile " + (i+5) + ",11");
			}
		}
		else if(team == 's')
		{
			for(int i = 0; i < icon.length; i++)
			{
				tView[i + 5][0].putPieceOnTile(icon[i]);
				t[i + 5][0].setOccupierName(name[i]);
				System.out.println("Putting " + name[i] + " on tile " + (i+5) + ",0");
			}
		}
	}
	
	
}
