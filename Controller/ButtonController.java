package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.PlayerRegistry;
import View.Game;
import Model.Board;
import Model.Dice;

public class ButtonController implements ActionListener, ListSelectionListener 
{

	private String listSelectionValue;
	private int listSelectionIndex;
	private int selectionCount = 0;
	private ArrayList<Integer> selectionIndex = new ArrayList<Integer>();
	//private int[] selectionIndex = new int[10];

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Team Select"))
		{
			PlayerRegistry.setPlayerTeams(listSelectionValue);
    		Game.getInstance().clearButtonPanel();
    		Game.getInstance().selectPiecesPanel();
			//buttonPanel.removeAll();	//Clear side panel - create a method in Game class to do that 
    		//selectPiecesPanel();
		}
		
		if(e.getActionCommand().equals("Piece Select"))
		{
			if(selectionCount < 3)
    		{
    			if(listSelectionIndex != -1)
        		{
    				selectionIndex.add(selectionCount, listSelectionIndex);
    				//Game.getInstance().removeFromList(listSelectionIndex);
    				selectionCount++;
        		}
    			
    			if(selectionCount == 3)
    			{
    				Game.getInstance().disableSelectEnableNext();
    			}
    		}
		}
		
		if(e.getActionCommand().equals("Next"))
		{
			if(Game.getInstance().getWhichPlayerSelecting())					//True for player 1, false for player 2
    		{
    			PlayerRegistry.getPlayerObj(Game.getInstance().getWhichPlayerSelecting()).selectPieces(selectionIndex);
        		
        		Board.getInstance().arrangePieceInitial(PlayerRegistry.getPlayerTeam(Game.getInstance().getWhichPlayerSelecting()), PlayerRegistry.getPlayerObj(Game.getInstance().getWhichPlayerSelecting()).getIcons(), PlayerRegistry.getPlayerObj(Game.getInstance().getWhichPlayerSelecting()).getAllNames());
        		
        		Game.getInstance().clearButtonPanel();
        		Game.getInstance().setWhichPlayerSelecting(!Game.getInstance().getWhichPlayerSelecting());
        		//selectionTeam = !selectionTeam;
        		Game.getInstance().selectPiecesPanel();
        		selectionCount = 0;
        		System.out.println(selectionIndex);
        		selectionIndex.removeAll(selectionIndex);
    		}
    		else if(!Game.getInstance().getWhichPlayerSelecting())
    		{
    			PlayerRegistry.getPlayerObj(Game.getInstance().getWhichPlayerSelecting()).selectPieces(selectionIndex);
    			
    			Board.getInstance().arrangePieceInitial(PlayerRegistry.getPlayerTeam(Game.getInstance().getWhichPlayerSelecting()), PlayerRegistry.getPlayerObj(Game.getInstance().getWhichPlayerSelecting()).getIcons(), PlayerRegistry.getPlayerObj(Game.getInstance().getWhichPlayerSelecting()).getAllNames());
    			
    			selectionCount = 0;
    			Game.getInstance().clearButtonPanel();
    			//buttonPanel.removeAll();
    			Game.getInstance().addDiceButtonToPanel(); 
    			System.out.println(selectionIndex);
    		}
		}
		
		if(e.getActionCommand().equals("Dice"))
		{
			Dice.getInstance().rollDice();
        	//System.out.println(dice.getDiceVal());
        	//Game.getInstance().setDiceFieldValue();
		}
		
		if(e.getActionCommand().equals("12x12"))
		{
			Game.getInstance().setBoardSize(12, 12);
		}
		
		if(e.getActionCommand().equals("14x14"))
		{
			Game.getInstance().setBoardSize(14, 14);
		}
		
		if(e.getActionCommand().equals("16x16"))
		{
			Game.getInstance().setBoardSize(16, 16);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		// TODO Auto-generated method stub
		if (!e.getValueIsAdjusting()) 
		{
	          @SuppressWarnings("unchecked")
	          JList<String> list = (JList<String>) e.getSource();
	          System.out.println(list.getSelectionModel());
	          listSelectionIndex = list.getSelectedIndex();
	          //int selections[] = list.getSelectedIndices();
	          listSelectionValue = list.getSelectedValue();
	          //list.remove(listSelectionIndex);
		}
	}
	
	
}
