package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.MouseAdapterParent;
import Model.Iterator.*;
import Model.PrototypeTileFactory.Tile;
import Model.PrototypeTileFactory.TileFactory;
import View.TileView;

/*
 * Add a buffer place on board where pieces will be initialized and after dice roll they will be moved to the board.
 * Create iterator to iterate through tiles on board.
 */

public class Board implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Island island1, island2, island3, island4, island5, island6;
	private Tile[][] t;
	private TileView[][] tView;
	private static Board single_instance = null;
	
	//private String[][] boardState = new String[][] {{"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", "", "", ""}};
	
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
	
	/*
	 * Island location are fixed on the board. We won't be giving the selection of placing islands to user
	 * Each TileView class is linked with a Tile class here. View and model are changed simultaneously.
	 */
	public void drawBoard(JPanel[][] cellPanel, JPanel mainPanel)
	{
		this.t = new Tile[cellPanel.length][cellPanel[0].length];
		this.tView = new TileView[cellPanel.length][cellPanel[0].length];
		
		//Setting up Tile (Model) class
		for(int i = 0; i < t.length; i++)
		{
			for(int j = 0; j < t[i].length; j++)
			{
				if((i <= 1 && j == 1) || (i >= t.length - 2 && j == t[i].length - 1))
				{
					t[i][j] = TileFactory.getTile(4);					//EBordered
				}
				else if(i == (t.length/2) - 4 && (j == (t[i].length/2) - 2 || j == (t[i].length/2) + 3))
				{
					t[i][j] = TileFactory.getTile(7);					//NWBordered
				}
				else if((i == (t.length/2) - 4 && j == (t[i].length/2) - 1) || (i == (t.length/2) + 2 && j == (t[i].length/2) - 4))
				{
					t[i][j] = TileFactory.getTile(6);					//NEBordered
				}
				else if((i >= t.length - 2 && j == t[i].length - 2) || (i <= 1 && j == 0))
				{
					t[i][j] = TileFactory.getTile(5);					//WBordered
				}
				else if(i == (t.length/2) + 3 && (j == (t[i].length/2) - 4 || j == (t[i].length/2) + 1))
				{
					t[i][j] = TileFactory.getTile(8);					//SEBordered
				}
				else if((i == (t.length/2) - 3 && j == (t[i].length/2) + 3) || (i == (t.length/2) + 3 && j == t[i].length/2))
				{
					t[i][j] = TileFactory.getTile(9);					//SWBordered
				}
				else
				{
					t[i][j] = TileFactory.getTile(1);					//Unbordered tile
				}
			}
		}
		
		//Setting neighbors for each tile cell
		for(int i = 0; i < t.length; i++)
		{
			for(int j = 0; j < t[i].length; j++)
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
				if(j == t[i].length - 1)
				{
					t[i][j].setEastNeighbour(null);
					t[i][j].setWestNeighbour(t[i][j - 1]);
				}
				if(j > 0 && j < t[i].length - 1)
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
		
		//Setting up TileView class
		for(int i = 0; i < t.length; i++)
		{
			for(int j = 0; j < t[i].length; j++)
			{
				tView[i][j] = new TileView(cellPanel[i][j], mainPanel);
				tView[i][j].setTileVisible(MouseAdapterParent.getInstance(), j, t[i][j]);
			}
		}
	}
	
	public Tile getTileObj(int x, int y)
	{
		return t[x][y];
	}
	
	public TileView getTileViewObj(int x, int y)
	{
		return tView[x][y];
	}
	
	public RowIterator<Tile> getRowIterator()
	{
		return new RowIterator<Tile>(t);
	}
	
	public ReverseRowIterator<Tile> getReverseRowIterator()
	{
		return new ReverseRowIterator<Tile>(t);
	}
	
	public ColumnIterator<Tile> getColumnIterator()
	{
		return new ColumnIterator<Tile>(t);
	}
	
	public ReverseColumnIterator<Tile> getReverseColumnIterator()
	{
		return new ReverseColumnIterator<Tile>(t);
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
	
	public void arrangePieceInitial(char team, ArrayList<JLabel> icon, ArrayList<Piece> selectedPieces)
	{		
		if(team == 'e')
		{
			for(int i = 0; i < icon.size(); i++)
			{
				tView[i + (tView.length/2) - 1][tView.length - 1].putPieceOnTile(icon.get(i));
				t[i + (t.length/2) - 1][t.length - 1].setOccupier(selectedPieces.get(i));
				//System.out.println("Putting " + name.get(i) + " on tile " + (i + (tView.length/2) - 1) + "," + (tView.length - 1));
			}
		}
		else if(team == 's')
		{
			for(int i = 0; i < icon.size(); i++)
			{
				tView[i + (tView.length/2) - 1][0].putPieceOnTile(icon.get(i));
				t[i + (t.length/2) - 1][0].setOccupier(selectedPieces.get(i));
				//System.out.println("Putting " + name.get(i) + " on tile " + (i + (tView.length/2) - 1) + ",0");
			}
		}
	}
	
	
}
