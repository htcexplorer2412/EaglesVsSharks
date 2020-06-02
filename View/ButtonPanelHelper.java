package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Controller.ButtonController;
import Model.PlayerRegistry;

class ButtonPanelHelper 
{
	private JPanel buttonPanel;
	private Color lightBrown = new Color(153,102,0);
	private JButton select;
	private JButton next;
	/** List model for the lists shown during selection of pieces. */
	private DefaultListModel<String> listModel;
	private JList<String> piecesList;
	private JButton diceButton;	
	private JTextField tf1;
	
	/**
	 * Setup the panel for different lists and buttons
	 * 
	 * @param sidePanel The side panel
	 * @param buttonPanel Panel that will display selection lists and dice button
	 * @param sidePanelWidth Width of side panel
	 * @param sidePanelHeight Height of side panel
	 */
	public ButtonPanelHelper(JPanel sidePanel, JPanel buttonPanel)
	{
		this.buttonPanel = buttonPanel;
		BoxLayout bl = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.setLayout(bl);
		buttonPanel.setBackground(lightBrown);
		buttonPanel.setMinimumSize(new Dimension(sidePanel.getWidth()/2 + 50, sidePanel.getHeight()/4));
		buttonPanel.setMaximumSize(new Dimension(sidePanel.getWidth()/2 + 50, sidePanel.getHeight()/4));
	}
	
	/**
	 * Creates and displays a JList for Player 1 to select a team - Eagle or Shark.
	 * The list and the corresponding button are handled in Button Controller class
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void selectTeam(ButtonController bc)
	{
		JButton selectTeam = new JButton("Select");
		selectTeam.setMaximumSize(new Dimension(70, 30));
		
		String[] teamListNames = {"Eagle", "Shark"};
		JList<String> teamList = new JList<String>(teamListNames);
		
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane jsp = new JScrollPane(teamList);
		jsp.setMaximumSize(new Dimension(230, 100));
		jsp.setRowHeaderView(new JLabel("Select a team"));
		
		teamList.addListSelectionListener(bc);
		
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(bl);
		
		panel.add(jsp);
		panel.add(Box.createRigidArea(new Dimension(5,0)));		//Filler between components
		panel.add(selectTeam);
		panel.add(Box.createRigidArea(new Dimension(8,0)));
		panel.setBackground(lightBrown);
		
		//buttonPanel.add(Box.createRigidArea(new Dimension(0,20)));		//Filler between components
		buttonPanel.add(panel);
		selectTeam.setActionCommand("Team Select");
		selectTeam.addActionListener(bc);
	}
	
	/*
	 * Variable piece selection changes here 
	 * AI or real life user check here
	 */
	/**
	 * Create 'Select' and 'Next' buttons and delegate the setup and display of list and buttons to an internal (private) method.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void selectPiecesPanel(boolean selectionTeam, ButtonController bc)
	{
		select = new JButton("Select");
		next = new JButton("Next");
		
		select.setMaximumSize(new Dimension(70, 30));
		next.setMaximumSize(new Dimension(70, 30));
		
		listAndButtonSetup(selectionTeam, bc);				//Show list according to the player (1 or 2)
	}
	
	/**
	 * Setup and display the list of pieces according to the player and their selected team.
	 * If Player 1 has selected Shark team then it will display the pieces from the Shark team for Player 1 to select.
	 * After Player 1 is done selecting, Player 2 gets to select pieces from the other team.
	 * 
	 * @param team - TRUE if Player 1 should select from the list, FALSE if Player 2 should select from the list.
	 * @param bc Controller to handle the components of this panel
	 * @version 1.0
	 * @since 1.0
	 */
	private void listAndButtonSetup(boolean team, ButtonController bc)
	{
		String[] pieceList = PlayerRegistry.getPlayerObj(team).getPieceNames();
		
        listModel = new DefaultListModel<String>();
        listModel.removeAllElements();
        for(int i = 0; i < pieceList.length; i++)
        {
        	listModel.addElement(pieceList[i]);
        }
        piecesList = new JList<String>(listModel);
        piecesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane jsp = new JScrollPane(piecesList);
		jsp.setMaximumSize(new Dimension(240, 120));
		jsp.setRowHeaderView(new JLabel("Select pieces"));
		
		piecesList.addListSelectionListener(bc);
		
		JPanel panel = new JPanel();
        BoxLayout bl = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(bl);
		
		panel.add(jsp);
		panel.add(Box.createRigidArea(new Dimension(3,0)));		//Filler between components
		panel.add(select);
		panel.add(Box.createRigidArea(new Dimension(3,0)));
		panel.add(next);
		panel.add(Box.createRigidArea(new Dimension(8,0)));
		panel.setBackground(lightBrown);
		
		buttonPanel.add(panel);
		
		next.setEnabled(false);
		select.setEnabled(true);
		
		select.setActionCommand("Piece Select");
		next.setActionCommand("Next");
		
		select.addActionListener(bc);
		next.addActionListener(bc);
		
		buttonPanel.repaint();
		buttonPanel.revalidate();
	}
	
	/**
	 * Disable and enable a pair of buttons during piece selection.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void disableSelectEnableNext()
	{
		next.setEnabled(true);
		select.setEnabled(false);
	}
	
	/**
	 * Remove the name of a piece from the piece selection list.
	 * 
	 * @param index - Index of the name on the list that should be removed.
	 * @version 1.0
	 * @since 1.0
	 */
	protected void removeFromList(int index)
	{
		listModel.remove(index);
		piecesList.repaint();
		piecesList.revalidate();
	}
	
	/**
	 * Add a button and text field for dice. 
	 * This button when clicked, calls for rollDice method of the Dice class through the Button Controller class.
	 * The text field shows the value that is generated by the Dice class.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void addDiceButtonToPanel(ButtonController bc)
	{
		diceButton = new JButton("Roll dice");
        tf1 = new JTextField();
        tf1.setEditable(false);
        tf1.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        tf1.setHorizontalAlignment(JTextField.CENTER);
        
        diceButton.setMaximumSize(new Dimension(100, 30));
        tf1.setMaximumSize(new Dimension(30, 30));
        
        JPanel panel = new JPanel();
        BoxLayout bl = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(bl);
        
        panel.add(diceButton);
        panel.add(Box.createRigidArea(new Dimension(10,0)));
        panel.add(tf1);
        panel.add(Box.createRigidArea(new Dimension(20,0)));
        panel.setBackground(lightBrown);
        
        buttonPanel.add(panel);
        
        buttonPanel.repaint();
		buttonPanel.revalidate();
		
		diceButton.setActionCommand("Dice");
		diceButton.addActionListener(bc);
	}
	
	/**
	 * Shows the value in the text field that is shown with the dice button.
	 * 
	 * @param value - integer
	 * @version 1.0
	 * @since 1.0
	 */
	protected void setDiceFieldValue(int value)
	{
		tf1.setText(Integer.toString(value));
	}
	
	/**
	 * Enable or disable the dice button. 
	 * 
	 * @param value - TRUE if button should be enabled. FALSE if it should be disabled.
	 * @version 1.0
	 * @since 1.0
	 */
	protected void changeStateOfDiceButton(boolean value)
	{
		diceButton.setEnabled(value);
	}
}
