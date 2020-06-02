package Model.Memento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Island;
import Model.Player;
import Model.Piece.Piece;
import Model.Iterator.RowIterator;

public final class UndoMemento implements Memento, Serializable
{
	private static final long serialVersionUID = 188L;
	private HashMap<Integer, String> pieceLocationOnBoard;
	private HashMap<Boolean, ArrayList<Piece>> player_pieces;
	private ArrayList<Island> islands;
	
	public UndoMemento(RowIterator iterator, Player player1, Player player2, ArrayList<Island> islandsList)
	{
		pieceLocationOnBoard = new HashMap<Integer, String>();
		this.islands = new ArrayList<Island>();
		player_pieces = new HashMap<Boolean, ArrayList<Piece>>();
		
		//Cloning piece list from both players
		ArrayList<Piece> tempList = new ArrayList<Piece>();
		for(int i = 0; i < player1.getAllSelectedPieces().size(); i++)
		{
			tempList.add((Piece) player1.getAllSelectedPieces().get(i).clone());
		}
		player_pieces.put(Boolean.TRUE, tempList);
		
		ArrayList<Piece> tempList2 = new ArrayList<Piece>();
		for(int i = 0; i < player2.getAllSelectedPieces().size(); i++)
		{
			tempList2.add((Piece) player2.getAllSelectedPieces().get(i).clone());
		}
		player_pieces.put(Boolean.FALSE, tempList2);
		
		/*
		 * Storing location of a piece on Tile. If a piece is found on a Tile, its full name is stored with the index in a hashmap.
		 */
		for(iterator.first(); !iterator.isDone(); iterator.next())
		{
			if(iterator.currentItem().getOccupier() != null)
			{
				pieceLocationOnBoard.put((int) iterator.currentIndex(), iterator.currentItem().getOccupier().getFullName());
			}
		}
		//Cloning and storing islands
		for(int i = 0; i < islandsList.size(); i++)
		{
			this.islands.add((Island) islandsList.get(i).clone());
		}
	}

	public HashMap<Integer, String> getLocationOfAllPieces()
	{
		return pieceLocationOnBoard;
	}
	
	public ArrayList<Island> getIslands()
	{
		return islands;
	}
	
	public HashMap<Boolean, ArrayList<Piece>> getPlayersPieces()
	{
		return player_pieces;
	}
}
