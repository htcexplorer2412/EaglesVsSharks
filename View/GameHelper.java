package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Controller.ButtonController;
import Controller.TableButtonController;

/**
 * <H1>Game Helper</H1>
 * <p>
 * Helper class to SingletonGame class. 
 * <p>
 * This class helps the SingletonGame class in drawing and managing the side panel (The panel with all the components except the game board).
 * All the subpanels which show the game messages, errors, selection lists, data tables are drawn from this class. 
 * The SingletonGame class delegates these tasks to this class.
 * 
 * @author Ayam Ajmera
 * @version 1.2
 * @since 2020-06-01
 */
class GameHelper 
{
	/**
	 * Reference to the Container created in SingletonGame class
	 */
	private Container c;
	/**
	 * The panel which contains other panels to show messages, data and other components.
	 */
	private JPanel sidePanel; 
	private JPanel buttonPanel, messageBoxPanel, dataBoxPanel, saveButtonPanel;
	private final Color lightBrown;
	private int sidePanelX, sidePanelY, sidePanelWidth, sidePanelHeight;
	private ButtonController bc;
	private TableButtonController tbc;
	private MessageBoxPanelHelper mbh;
	private ButtonPanelHelper bph;
	private DataBoxPanelHelper dbh;
	private SaveButtonPanelHelper sbh;
	
	/**
	 * 
	 * @param c Container in which the panels are placed
	 * @param sidePanel The side panel
	 * @param messageBoxPanel Panel that will display message box
	 * @param buttonPanel Panel that will display selection lists and dice button
	 * @param dataBoxPanel Panel that will display the tables (regarding Piece information)
	 * @param bc Controller to handle components in button panel.
	 * @param tbc Controller to handle components in data box panel
	 * @version 1.0
	 * @since 1.0
	 */
	public GameHelper(Container c, JPanel sidePanel, JPanel messageBoxPanel, JPanel buttonPanel, JPanel dataBoxPanel, JPanel saveButtonPanel, ButtonController bc, 
			TableButtonController tbc)
	{
		this.c = c;
		this.sidePanel = sidePanel;
		this.messageBoxPanel = messageBoxPanel;
		this.buttonPanel = buttonPanel;
		this.dataBoxPanel = dataBoxPanel;
		this.saveButtonPanel = saveButtonPanel;
		this.bc = bc;
		this.tbc = tbc;
		this.lightBrown = new Color(153,102,0);
	}
	
	/**
	 * Set the bounds for the side panel
	 * 
	 * @param mainPanelWidth Width of the board panel
	 * @param containerWidth Width of the container
	 * @param containerHeight Height of the container
	 * @version 1.1
	 * @since 1.0
	 */
	protected void setSidePanelBounds(int mainPanelWidth, int containerWidth, int containerHeight)
	{
		this.sidePanelX = mainPanelWidth + 15;
		this.sidePanelY = 10;
		this.sidePanelWidth = containerWidth - mainPanelWidth - 50;
		this.sidePanelHeight = containerHeight - 50;
	}
	
	/**
	 * Draws the side panel and sets the layout for the entire panel.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void drawSidePanel()
	{
		BoxLayout bl = new BoxLayout(sidePanel, BoxLayout.Y_AXIS);
		sidePanel.setLayout(bl);
		
		sidePanel.setBounds(sidePanelX, sidePanelY, sidePanelWidth, sidePanelHeight);
		sidePanel.setBackground(lightBrown);
		mbh = new MessageBoxPanelHelper(sidePanel, messageBoxPanel);
		bph = new ButtonPanelHelper(sidePanel, buttonPanel);
		dbh = new DataBoxPanelHelper(sidePanel, dataBoxPanel);
		sbh = new SaveButtonPanelHelper(sidePanel, saveButtonPanel);
        
		c.add(sidePanel);
        sidePanel.add(messageBoxPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0,20)));
        sidePanel.add(buttonPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0,20)));
        sidePanel.add(dataBoxPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0,20)));
        sidePanel.add(saveButtonPanel);
        sidePanel.repaint();
        sidePanel.revalidate();
	}
	
	/**
	 * Creates a text area and adds it to the side panel.
	 * 
	 * @version 1.1
	 * @since 1.0
	 */
	protected void createMessageBox()
	{
		mbh.createMessageBox();
	}
	
	/**
	 * Shows a message in the text area.
	 * 
	 * @param s Message to be displayed
	 * @since 1.0
	 * @version 1.1
	 */
	protected void showMessage(String s)
	{
		mbh.showMessage(s);
	}
	
	/**
	 * Creates and displays a JList for Player 1 to select a team - Eagle or Shark.
	 * The list and the corresponding button are handled in Button Controller class
	 * 
	 * @version 1.1
	 * @since 1.0
	 */
	protected void selectTeam()
	{
		bph.selectTeam(bc);
		this.showMessage("Player 1 - Please select a team!");
	}
	
	/*
	 * Variable piece selection changes here 
	 * AI or real life user check here
	 */
	/**
	 * Create 'Select' and 'Next' buttons and delegate the setup and display of list and buttons to an internal (private) method.
	 * 
	 * @version 1.1
	 * @since 1.0
	 */
	protected void selectPiecesPanel(boolean selectionTeam)
	{
		bph.selectPiecesPanel(selectionTeam, bc);
		if(selectionTeam)
			this.showMessage("Player 1 - Please select the pieces");
		else
			this.showMessage("Player 2 - Please select the pieces");
	}
	
	/**
	 * Disable and enable a pair of buttons during piece selection.
	 * 
	 * @version 1.1
	 * @since 1.0
	 */
	protected void disableSelectEnableNext()
	{
		bph.disableSelectEnableNext();
	}
	
	/**
	 * Remove the name of a piece from the piece selection list.
	 * 
	 * @param index - Index of the name on the list that should be removed.
	 * @version 1.1
	 * @since 1.0
	 */
	protected void removeFromList(int index)
	{
		bph.removeFromList(index);
	}
	
	/**
	 * Add a button and text field for dice. 
	 * This button when clicked, calls for rollDice method of the Dice class through the Button Controller class.
	 * The text field shows the value that is generated by the Dice class.
	 * 
	 * @version 1.1
	 * @since 1.0
	 */
	protected void addDiceButtonToPanel()
	{
		bph.addDiceButtonToPanel(bc);
		this.showMessage("Player 2 - Roll dice");
	}
	
	/**
	 * Shows the value in the text field that is shown with the dice button.
	 * 
	 * @param value - integer
	 * @version 1.1
	 * @since 1.0
	 */
	protected void setDiceFieldValue(int value)
	{
		bph.setDiceFieldValue(value);
	}
	
	/**
	 * Enable or disable the dice button. 
	 * 
	 * @param value - TRUE if button should be enabled. FALSE if it should be disabled.
	 * @version 1.1
	 * @since 1.0
	 */
	protected void changeStateOfDiceButton(boolean value, boolean turn)
	{
		bph.changeStateOfDiceButton(value);
		if(turn)
		{
			if(value)
				this.showMessage("Player 1 - Roll dice");
			else
				this.showMessage("Player 1 - Move a piece");
		}
		else
		{
			if(value)
				this.showMessage("Player 2 - Roll dice");
			else
				this.showMessage("Player 2 - Move a piece");
		}
	}
	
	/**
	 * Creates panels which shows a table for all the selected piece's health points.
	 * 
	 * @param team TRUE for Player 1, FALSE for Player 2
	 * @version 1.2
	 * @since 1.0
	 */
	protected void createDataBox(boolean team)
	{
		dbh.createDataBox(team, tbc);
	}
	
	/**
	 * Update the panels that contain data about health points of all the pieces.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void updateDataBoxes()
	{
		dbh.updateDataBoxes();
	}
	
	/**
	 * Highlight a row in a JTable
	 * 
	 * @param team TRUE if Player 1, FALSE if Player 2
	 * @param pieceName Full name of the Piece
	 * @param highlight TRUE if row should be highlighted, FALSE if row should be unhighlighted.
	 * @version 1.0
	 * @since 1.1
	 */
	protected void highlightRow(boolean team, String pieceName, boolean highlight)
	{
		dbh.highlightRow(team, pieceName, highlight);
	}
	
	/**
	 * Enable undo buttons for the player in turn
	 * 
	 * @param player1UndoEnable TRUE if undo buttons should be enabled for Player 1 FALSE otherwise
	 * @param player2UndoEnable TRUE if undo buttons should be enabled for Player 2 FALSE otherwise
	 * @version 1.0
	 * @since 1.1
	 */
	protected void enableUndoButtonForPlayer(boolean player1UndoEnable, boolean player2UndoEnable)
	{
		dbh.enableUndoButtonForPlayer(player1UndoEnable, player2UndoEnable);
	}
	
	protected void placeSaveButtons()
	{
		sbh.placeButtons();
	}
}
