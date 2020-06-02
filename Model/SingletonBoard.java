package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.SingletonMouseAdapterParent;
import Model.Collection.TileCollection;
import Model.Iterator.*;
import Model.Memento.Memento;
import Model.Memento.Originator;
import Model.Memento.UndoMemento;
import Model.Piece.Piece;
import Model.PrototypeTileFactory.Tile;
import Model.PrototypeTileFactory.TileFactory;
import View.SingletonGame;
import View.TileView;

/*
 * Add a buffer place on board where pieces will be initialized and after dice roll they will be moved to the board.
 * Can't serialize singleton classes due to its constraints.
 */
/**
 * <H1>Board</H1>
 * <p>
 * This class creates the board for the game.
 * <p>
 * This class generates a board where players can place their pieces, move these pieces, battle, attack in LOS and capture islands.
 * The board has 3 main functions - create a square board, create islands and place the selected pieces.
 * It is implementing Singleton pattern and hence only one instance of Board can be created for a game.
 * 
 * @author Ayam Ajmera
 * @version 3.0
 * @since 2020-04-21
 */
public class SingletonBoard implements Originator
{	
	private ArrayList<Island> islandList;
	/** Storing TileView objects in form of a matrix. */
	private TileView[][] tView;
	/** Store the only instance of the class */
	private static SingletonBoard single_instance = null;
	/** Storing Tile objects in form of a matrix in a collection. */
	private TileCollection collection;
	private int boardColumnSize;
	
	private SingletonBoard()
	{
		this.islandList = new ArrayList<Island>();
		this.islandList.add(new Island());
		this.islandList.add(new Island());
		this.islandList.add(new Island());
		this.islandList.add(new Island());
		this.islandList.add(new Island());
		this.islandList.add(new Island());
	}
	
	/**
	 * This method should be called to get an instance of this class. 
	 * As the class is a Singleton class, it's constructor is hidden and the class cannot be instantiated by a client using the new operator.
	 * When this method is called, it checks for existing instance of this class. 
	 * If there is, then it returns that instance, else it creates a new instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public synchronized static SingletonBoard getInstance() 
    { 
        if (single_instance  == null) 
            single_instance = new SingletonBoard(); 
  
        return single_instance; 
    }
	
	/**
	 * Creates a board of size of cellPanel. The size of cellPanel matrix is set by user, when they select the size of the board.
	 * The Tile and TileView matrix are passed the same length. 
	 * This method creates a board of a fixed design where each side has 3 islands - one big island and 2 small islands.
	 * All the attributes of the Tile class are set from this method. 
	 * <br>The two matrices are used for different purposes - 
	 * Matrix with Tile objects is used to take care of moving a piece from one point to another and check for all the validations.
	 * On the other hand, the matrix with TileView objects is just to show the user visually, how the current state of board is.
	 * 
	 * @param cellPanel JPanel matrix that is used to display each Tile
	 * @param mainPanel JPanel which displays the board (The board is made up of cellPanel's)
	 * @version 2.4
	 * @since 1.0
	 */
	public void drawBoard(JPanel[][] cellPanel, JPanel mainPanel)
	{
		collection = new TileCollection(cellPanel.length, cellPanel[0].length);
		this.tView = new TileView[cellPanel.length][cellPanel[0].length];
		
		for(int i = 0; i < collection.row_size(); i++)
		{
			for(int j = 0; j < collection.column_size(); j++)
			{
				if((i <= 1 && j == 1) || (i >= collection.row_size() - 2 && j == collection.column_size() - 1))
				{
					collection.add(i, j, TileFactory.getTile(4));		//East bordered
					collection.getTile(i, j).setAsIsland(true);
					this.setIslandTileLink(i, j);
				}
				else if(i == (collection.row_size()/2) - 4 && (j == (collection.column_size()/2) - 2 || j == (collection.column_size()/2) + 3))
				{
					collection.add(i, j, TileFactory.getTile(7));		//North West Bordered
					collection.getTile(i, j).setAsIsland(true);
					this.setIslandTileLink(i, j);
				}
				else if((i == (collection.row_size()/2) - 4 && j == (collection.column_size()/2) - 1) || (i == (collection.row_size()/2) + 2 && j == (collection.column_size()/2) - 4))
				{
					collection.add(i, j, TileFactory.getTile(6));		//North East Bordered
					collection.getTile(i, j).setAsIsland(true);
					this.setIslandTileLink(i, j);
				}
				else if((i >= collection.row_size() - 2 && j == collection.column_size() - 2) || (i <= 1 && j == 0))
				{
					collection.add(i, j, TileFactory.getTile(5));		//West Bordered
					collection.getTile(i, j).setAsIsland(true);
					this.setIslandTileLink(i, j);
				}
				else if(i == (collection.row_size()/2) + 3 && (j == (collection.column_size()/2) - 4 || j == (collection.column_size()/2) + 1))
				{
					collection.add(i, j, TileFactory.getTile(8));		//South East Bordered
					collection.getTile(i, j).setAsIsland(true);
					this.setIslandTileLink(i, j);
				}
				else if((i == (collection.row_size()/2) - 3 && j == (collection.column_size()/2) + 3) || (i == (collection.row_size()/2) + 3 && j == collection.column_size()/2))
				{
					collection.add(i, j, TileFactory.getTile(9));		//South West Bordered
					collection.getTile(i, j).setAsIsland(true);
					this.setIslandTileLink(i, j);
				}
				else
				{
					collection.add(i, j, TileFactory.getTile(1));		//Unbordered tile
				}
			}
		}
		
		//Setting neighbors for each tile cell
		for(int i = 0; i < collection.row_size(); i++)
		{
			for(int j = 0; j < collection.column_size(); j++)
			{
				if(i == 0)
				{
					collection.getTile(i, j).setNorthNeighbour(null);
					collection.getTile(i, j).setSouthNeighbour(collection.getTile(i + 1, j));
				}
				if(j == 0)
				{
					collection.getTile(i, j).setWestNeighbour(null);
					collection.getTile(i, j).setEastNeighbour(collection.getTile(i, j + 1));
				}
				if(i == collection.row_size() - 1)
				{
					collection.getTile(i, j).setNorthNeighbour(collection.getTile(i - 1, j));
					collection.getTile(i, j).setSouthNeighbour(null);
				}
				if(j == collection.column_size() - 1)
				{
					collection.getTile(i, j).setEastNeighbour(null);
					collection.getTile(i, j).setWestNeighbour(collection.getTile(i, j - 1));
				}
				if(j > 0 && j < collection.column_size() - 1)
				{
					collection.getTile(i, j).setEastNeighbour(collection.getTile(i, j + 1));
					collection.getTile(i, j).setWestNeighbour(collection.getTile(i, j - 1));
				}
				if(i > 0 && i < collection.row_size() - 1)
				{
					collection.getTile(i, j).setNorthNeighbour(collection.getTile(i - 1, j));
					collection.getTile(i, j).setSouthNeighbour(collection.getTile(i + 1, j));
				}
			}
		}
		
		//Setting up TileView class
		for(int i = 0; i < collection.row_size(); i++)
		{
			for(int j = 0; j < collection.column_size(); j++)
			{
				tView[i][j] = new TileView(cellPanel[i][j], mainPanel, this.boardColumnSize);
				tView[i][j].setTileVisible(SingletonMouseAdapterParent.getInstance(), j, collection.getTile(i, j));
			}
		}
		
		//Setting defense upgrade for each island
		this.islandList.stream().forEach(i -> i.setDefenseUpgradeAttribute());
	}
	
	private void setIslandTileLink(int row, int column)
	{
		if(row <= 1 && column == 1)
		{
			//collection.getTile(i, j).setIslandObj(island1);
			collection.getTile(row, column).setIslandObj(islandList.get(0));
			islandList.get(0).addTile(collection.getTile(row, column));
		}
		if(row <= 1 && column == 0)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(0));
			islandList.get(0).addTile(collection.getTile(row, column));
		}
		if(row >= collection.row_size() - 2 && column == collection.column_size() - 1)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(5));
			islandList.get(5).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) - 4 && column == (collection.column_size()/2) - 2)
		{	
			collection.getTile(row, column).setIslandObj(islandList.get(1));
			islandList.get(1).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) - 4 && column == (collection.column_size()/2) + 3)
		{	
			collection.getTile(row, column).setIslandObj(islandList.get(3));
			islandList.get(3).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) - 4 && column == (collection.column_size()/2) - 1)
		{	
			collection.getTile(row, column).setIslandObj(islandList.get(1));
			islandList.get(1).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) + 2 && column == (collection.column_size()/2) - 4)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(2));
			islandList.get(2).addTile(collection.getTile(row, column));
		}
		if(row >= collection.row_size() - 2 && column == collection.column_size() - 2)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(5));
			islandList.get(5).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) + 3 && column == (collection.column_size()/2) - 4)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(2));
			islandList.get(2).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) + 3 && column == (collection.column_size()/2) + 1)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(4));
			islandList.get(4).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) - 3 && column == (collection.column_size()/2) + 3)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(3));
			islandList.get(3).addTile(collection.getTile(row, column));
		}
		if(row == (collection.row_size()/2) + 3 && column == collection.column_size()/2)
		{
			collection.getTile(row, column).setIslandObj(islandList.get(4));
			islandList.get(4).addTile(collection.getTile(row, column));
		}
	}
	
	/**
	 * Get Tile object at given position
	 * 
	 * @param x Point on X-axis
	 * @param y Point on Y-axis
	 * @return Tile object at specified x and y values
	 * @version 1.1
	 * @since 2.0
	 */
	public Tile getTileObj(int x, int y)
	{
		return collection.getTile(x, y);
	}
	
	/**
	 * Get TileView object at given position
	 * 
	 * @param x Point on X-axis
	 * @param y Point on Y-axis
	 * @return TileView object at specified x and y values
	 * @version 1.0
	 * @since 2.0
	 */
	public TileView getTileViewObj(int x, int y)
	{
		return tView[x][y];
	}
	
	/**
	 * Returns the RowIterator on Tile Matrix
	 * 
	 * @return RowIterator of type Tile
	 * @version 1.1
	 * @since 2.3
	 */
	public RowIterator getRowIterator()
	{
		return (RowIterator) collection.createIterator(1);
	}
	
	/**
	 * Returns the ReverseRowIterator on Tile Matrix
	 * 
	 * @return ReverseRowIterator of type Tile
	 * @version 1.1
	 * @since 2.3
	 */
	public ReverseRowIterator getReverseRowIterator()
	{
		return (ReverseRowIterator) collection.createIterator(2);
	}
	
	/**
	 * Returns the ColumnIterator on Tile Matrix
	 * 
	 * @return ColumnIterator of type Tile
	 * @version 1.1
	 * @since 2.3
	 */
	public ColumnIterator getColumnIterator()
	{
		return (ColumnIterator) collection.createIterator(3);
	}
	
	/**
	 * Returns the ReverseColumnIterator on Tile Matrix
	 * 
	 * @return ReverseColumnIterator of type Tile
	 * @version 1.1
	 * @since 2.3
	 */
	public ReverseColumnIterator getReverseColumnIterator()
	{
		return (ReverseColumnIterator) collection.createIterator(4);
	}
	
	//Improve this for higher number of pieces
	/**
	 * Arranges initial location of the selected pieces on the Board
	 * 
	 * @param team 'e' or 's' - Eagle or Shark
	 * @param icon List of icons of selected pieces
	 * @param selectedPieces List of Piece objects that are selected
	 * @version 1.3
	 * @since 1.3
	 */
	public void arrangePieceInitial(char team, ArrayList<JLabel> icon, ArrayList<Piece> selectedPieces)
	{		
		if(team == 'e')
		{
			for(int i = 0; i < icon.size(); i++)
			{
				tView[i + (tView.length/2) - 1][tView.length - 1].putPieceOnTile(icon.get(i));
				collection.getTile(i + (collection.row_size()/2) - 1, collection.column_size() - 1).setOccupier(selectedPieces.get(i));
				//System.out.println("Putting " + name.get(i) + " on tile " + (i + (tView.length/2) - 1) + "," + (tView.length - 1));
			}
		}
		else if(team == 's')
		{
			for(int i = 0; i < icon.size(); i++)
			{
				tView[i + (tView.length/2) - 1][0].putPieceOnTile(icon.get(i));
				collection.getTile(i + (collection.row_size()/2) - 1, 0).setOccupier(selectedPieces.get(i));
				//System.out.println("Putting " + name.get(i) + " on tile " + (i + (tView.length/2) - 1) + ",0");
			}
		}
	}
	
	public void setBoardColumnsSize(int size)
	{
		this.boardColumnSize = size;
	}

	public ArrayList<Island> getIslandsList()
	{
		return islandList;
	}
	
	//Directly send piece list from each player to memento class.
	/**
	 * The method creates a memento that will be stored by the CareTaker class.
	 * 
	 * @return Memento with the current state stored in it.
	 * @version 1.0
	 * @since 3.0
	 */
	@Override
	public Memento createMemento() 
	{
		Memento memento = new UndoMemento(this.getRowIterator(), PlayerRegistry.getPlayerObj(true), PlayerRegistry.getPlayerObj(false), this.islandList);
		return memento;
	}

	/**
	 * Restores the old state by reading the state data back from the Memento.
	 * 
	 * @param m Memento object that should be replace the current state.
	 * @version 1.0
	 * @since 3.0
	 */
	@Override
	public void restore(Memento m) 
	{
		this.collection.removeAllOccupiers();
		HashMap<Integer, String> localPieceMap;
		ArrayList<Island> localIslandList;
		HashMap<Boolean, ArrayList<Piece>> player_pieces = ((UndoMemento) m).getPlayersPieces();
		
		//Restoring pieces and their state for both players
		for(Map.Entry<Boolean, ArrayList<Piece>> entry : player_pieces.entrySet())
		{
			PlayerRegistry.getPlayerObj(entry.getKey()).restorePieces(entry.getValue());
		}
		localPieceMap = ((UndoMemento) m).getLocationOfAllPieces();
		localIslandList = ((UndoMemento) m).getIslands();
		this.islandList.clear();
		for(int i = 0; i < localIslandList.size(); i++)
		{
			this.islandList.add(localIslandList.get(i));
		}
		this.setTilesToThisState(localPieceMap, player_pieces);
		this.setViewToThisState();
	}
	
	/**
	 * Updates the Collection of Tiles to the previous (passed) state. 
	 * Place the pieces according to the state and replace current island objects with older island objects
	 * 
	 * @param localPieceMap Map containing location of each piece. Index of the tile is the key and full name of the piece is the value
	 * @param localIslandMap Map containing state of each island. Index of the tile is the key and Island object is the value
	 * @param player_pieces Map containing all the Pieces (of both the players).
	 * @version 1.0
	 * @since 3.0
	 */
	private void setTilesToThisState(HashMap<Integer, String> localPieceMap, HashMap<Boolean, ArrayList<Piece>> player_pieces)
	{
		//Iterate through all the tiles
		RowIterator iter = this.getRowIterator();
		for(iter.first(); !iter.isDone(); iter.next())
		{
			//If currentindex is found in the map, then search for that piece.
			if(localPieceMap.containsKey((int) iter.currentIndex()))
			{
				String name = localPieceMap.get((int) iter.currentIndex());
				//Check for both the players. Get the object for that piece and send it to the current tile (as its occupier)
				loop1: for(Map.Entry<Boolean, ArrayList<Piece>> entry : player_pieces.entrySet())
				{
					for(int i = 0; i < entry.getValue().size(); i++)
					{
						if(entry.getValue().get(i).getFullName().equals(name))
						{
							iter.currentItem().setOccupier(PlayerRegistry.getPlayerObj(entry.getKey()).getPiece(name));
							break loop1;
						}
					}
				}
			}
			//Replace current island with the older state
			this.setIslandTileLink((int) iter.currentIndex() / collection.row_size(), (int) iter.currentIndex() % collection.column_size());
		}
	}
	
	/**
	 * Updates the View to the previous (passed) state.
	 * 
	 * @version 1.0
	 * @since 3.0
	 */
	private void setViewToThisState()
	{
		for(int i = 0; i < tView.length; i++)
		{
			for(int j = 0; j < tView[0].length; j++)
			{
				tView[i][j].removePieceFromTile();
			}
		}
		
		//Iterate through TileCollection and place the icon of the concerned piece on the panel.
		RowIterator iter = this.getRowIterator();
		for(iter.first(); !iter.isDone(); iter.next())
		{
			if(iter.currentItem().getOccupier() != null)
			{
				int row = (int) iter.currentIndex() / iter.getRowLength();
				int column = (int) iter.currentIndex() % iter.getColumnLength();
				JLabel icon = iter.currentItem().getOccupier().getIcon();
				tView[row][column].putPieceOnTile(icon);
			}
		}
		//Update the data table to the older state
		SingletonGame.getInstance().updateDataBoxes();
	}
	
}