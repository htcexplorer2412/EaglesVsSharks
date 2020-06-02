package View;

import java.awt.*;
import javax.swing.*;
import Controller.ButtonController;
import Controller.TableButtonController;
import Model.SingletonBoard;
import Model.SingletonDice;
import Model.Memento.UndoMemento;
import Model.Observer.*;

/**
 * <H1>Game</H1>
 * <p>
 * This class is responsible to start the game and display the GUI to the user.
 * <p>
 * The class is responsible to ask the user about the size of board, number of pieces per side, show the board once its drawn, 
 * show different buttons and components during the course of the game.
 * This class implements 'Singleton pattern', so only one instance can be created for a session.
 * It also implements 'Observer pattern' to observe the state of the Dice class and show the view accordingly.
 * This class represents the View in Model-View-Controller paradigm.
 * 
 * @author Ayam Ajmera
 * @version 4.0
 * @since 2020-04-21
 */
public class SingletonGame extends JFrame implements Observer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Stores the only instance of this class
	 */
	private static SingletonGame single_instance = null;
	/**
	 * Container to display the board and components related to the game
	 */
	private Container c;
	/**
	 * Panel on which board is designed
	 */
	private JPanel mainPanel;
	/**
	 * Collection of panels on which all the tiles are designed
	 */
	private JPanel[][] cellPanel;
	/**
	 * Panel which shows different information, buttons and other components during the course of the game.
	 */
	private JPanel sidePanel, buttonPanel, messageBoxPanel, dataBoxPanel, saveButtonPanel;
	/**
	 * Background color of the container
	 */
	private final Color lightBrown = new Color(153,102,0);
	private boolean selectionTeam = true;					//true for player 1 and false for player 2
	private int containerWidth, containerHeight, mainPanelWidth, mainPanelHeight;
	/**
	 * Controller class to handle the listeners for components in the side panel
	 */
	private ButtonController bc;
	/** Helper class to draw some parts of the game. */
	private GameHelper helper;
	private TableButtonController tbc;
	
	/**
	 * Constructor is private because this class follows Singleton pattern. 
	 * Sets default look and feel for JFrame, calls an internal method and sets the container visible.
	 * 
	 * @version 3.2
	 * @since 1.0
	 */
	private SingletonGame()
	{
		this.sidePanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.messageBoxPanel = new JPanel();
		this.dataBoxPanel = new JPanel();
		this.saveButtonPanel = new JPanel();
		this.bc = new ButtonController();
		this.tbc = new TableButtonController();
		setDefaultLookAndFeelDecorated(true);
		//Remove these lines
		//boardTypesAndPieceSelections();
        //setVisible(true);
	}
	
	/**
	 * Shows the welcome page with options to select a board size (12x12, 14x14, 16x16)
	 * 
	 * @version 1.0
	 * @since 3.4
	 */
	public void boardTypesAndPieceSelections()
	{
		c = getContentPane();
		//Add a window closing listener and use it to close the program gracefully
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Eagles vs Sharks - Select board size");
        
        //Adding all buttons in grid layout
        JPanel Panel = new JPanel();
        //BoxLayout vb = new BoxLayout(vPanel, BoxLayout.Y_AXIS);
        Panel.setLayout(new GridLayout(3,3));
        Panel.setBounds(0, 0, 483, 210);
        
        JButton[] buttons = new JButton[9];
        
        int temp = 4;
        for(int i = 0; i < buttons.length/3; i++)
        {
        	buttons[i] = new JButton("<html><center>Board Size - 12x12<br>Pieces - " + temp + "</center></html>");
        	buttons[i].setActionCommand("12x12-" + temp);
        	buttons[i + 3] = new JButton("<html><center>Board Size - 14x14<br>Pieces - " + temp + "</center></html>");
        	buttons[i + 3].setActionCommand("14x14-" + temp);
        	buttons[i + 6] = new JButton("<html><center>Board Size - 16x16<br>Pieces - " + temp + "</center></html>");
        	buttons[i + 6].setActionCommand("16x16-" + temp);
        	temp++;
        }
        
        for(int i = 0; i < buttons.length; i++)
        {
        	buttons[i].addActionListener(bc);
        	Panel.add(buttons[i]);
        }
        
        c.add(Panel);
        c.setVisible(true);
        setResizable(false);
        setLayout(null);
        //Setting the size of the frame and background of the frame
        setBounds(250, 250, 500, 250);
		c.setBackground(Color.WHITE);
		setVisible(true);
	}
	
	/**
	 * Sets the size of the board according to the selection done by the user. 
	 * Also sets the absolute size of all the panels according to board size selected by the user.
	 * 
	 * @param i - Size of the board in X-axis
	 * @param j - Size of the board in Y-axis
	 * @version 1.0
	 * @since 3.4
	 */
	public void setBoardSize(int i, int j)
	{
		c.removeAll();
		mainPanel = new JPanel(new GridLayout(i,j));
		cellPanel = new JPanel[i][j];
		
		containerWidth = 1400;
		containerHeight = 800;
		mainPanelWidth = 45*i;
		mainPanelHeight = 45*i;
	}
	
	/**
	 * Draws the container which contains the panels to show board and other components. 
	 * Sets the bounds, background color and other parameters for the container. 
	 * 
	 * @version 1.5
	 * @since 1.0
	 */
	public void drawGameContainer()
	{
		c = getContentPane();
		//Setting the size of the frame and background of the frame
		setBounds(-10, 0, containerWidth, containerHeight);
		c.setBackground(this.lightBrown);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Eagles vs Sharks");
        setResizable(false);
        setLayout(null); 
        c.setVisible(true);
        
        helper = new GameHelper(c, this.sidePanel, this.messageBoxPanel, this.buttonPanel, this.dataBoxPanel, this.saveButtonPanel, bc, tbc);
        helper.setSidePanelBounds(mainPanelWidth, containerWidth, containerHeight);
        
        this.drawBoardPanel();
        helper.drawSidePanel();
        helper.createMessageBox();
	}
	
	//Remove board call from here and place it in controller. 
	/**
	 * Draws the skeleton/outer boundaries of the board and delegates the internal design of each tile to the TileView class(es).
	 * Sets the bounds and background color of the outer panel and adds it to the container.
	 * 
	 * @version 1.4
	 * @since 1.0
	 */
	protected void drawBoardPanel()
	{
		//Setting the size of the Main panel and background of the panel
		mainPanel.setBounds(5, 5, mainPanelWidth, mainPanelHeight);
        mainPanel.setBackground(new Color(255, 255, 255));
        //Adding main panel to the frame
        c.add(mainPanel);
        
        SingletonBoard.getInstance().drawBoard(this.cellPanel, this.mainPanel);
	}
	
	/**
	 * Removes all the components (except JTextArea for messages) from the side panel.
	 * 
	 * @version 1.3
	 * @since 3.4
	 */
	public void clearButtonPanel()
	{
		buttonPanel.removeAll();
		buttonPanel.repaint();
		buttonPanel.revalidate();
	}
	
	/**
	 * Add a button and text field for dice. 
	 * This button when clicked, calls for rollDice method of the Dice class through the Button Controller class.
	 * The text field shows the value that is generated by the Dice class.
	 * 
	 * @version 1.4
	 * @since 1.0
	 */
	public void addDiceButtonToPanel()
	{
		helper.addDiceButtonToPanel();
		helper.placeSaveButtons();
	}
	
	/**
	 * Delegate the disabling and enabling a pair of buttons during piece selection to GameHelper class.
	 * 
	 * @version 1.1
	 * @since 3.3
	 */
	public void disableSelectEnableNext()
	{
		helper.disableSelectEnableNext();
	}
	
	/**
	 * Get whose turn it is to select the pieces.
	 * 
	 * @return TRUE if its Player 1's turn to select pieces.<br>FALSE if its Player 2's turn to select pieces.
	 * @version 1.0 
	 * @since 3.4
	 */
	public boolean getWhichPlayerSelecting()
	{
		return selectionTeam;
	}
	
	/**
	 * Set turn to select pieces
	 * 
	 * @param value - TRUE if it should be Player 1's turn, FALSE if it should be Player 2's turn.
	 * @version 1.0
	 * @since 3.4
	 */
	public void setWhichPlayerSelecting(boolean value)
	{
		selectionTeam = value;
	}
	
	/**
	 * Delegate removing the name of a piece from the piece selection list to GameHelper.
	 * 
	 * @param index - Index of the name on the list that should be removed.
	 * @version 1.1
	 * @since 3.5
	 */
	public void removeFromList(int index)
	{
		helper.removeFromList(index);
	}
	
	/**
	 * Creates and displays a JList for Player 1 to select a team - Eagle or Shark.
	 * The list and the corresponding button are handled in Button Controller class
	 * 
	 * @version 1.0
	 * @since 4.0
	 */
	public void selectTeam()
	{
		helper.selectTeam();
	}
	
	/**
	 * Delegate the setup and display of pieces and buttons to GameHelper class.
	 * 
	 * @version 3.0
	 * @since 1.0
	 */
	public void selectPiecesPanel()
	{
		helper.selectPiecesPanel(selectionTeam);
	}
	
	/**
	 * This method is used to show any error for invalid steps taken by a player.
	 * 
	 * @param s - error message
	 * @version 1.2
	 * @since 3.3
	 */
	public void showError(String s)
	{
		helper.showMessage("Error - " + s);
	}
	
	/**
	 * Delegates the task to display the message to GameHelper class.
	 * 
	 * @param s Message to be displayed
	 * @since 3.5
	 * @version 1.1
	 */
	public void showMessage(String s)
	{
		helper.showMessage(s);
	}
	
	/**
	 * Delegates the task of creating panels which show a table for all the selected piece's health points to GameHelper class.
	 * 
	 * @param team TRUE for Player 1, FALSE for Player 2
	 * @version 1.1
	 * @since 3.6
	 */
	public void createDataBox(boolean team)
	{
		helper.createDataBox(team);
	}
	
	/**
	 * Delegates the task of updating the data table to GameHelper
	 * 
	 * @version 1.0
	 * @since 4.0
	 */
	public void updateDataBoxes()
	{
		helper.updateDataBoxes();
	}
	
	/**
	 * Delegates the task of highlighting a row in the data table to GameHelper class
	 * 
	 * @param turn TRUE if Player 1, FALSE if Player 2
	 * @param highlight TRUE if row should be highlighted, FALSE if row should be unhighlighted
	 * @param pieceName Full name of the Piece
	 * @version 1.0
	 * @since 4.0
	 */
	public void highlightTableRow(boolean turn, boolean highlight, String pieceName)
	{
		helper.highlightRow(turn, pieceName, highlight);
	}
	
	public void enableUndoForPlayers(boolean player1UndoEnable, boolean player2UndoEnable)
	{
		helper.enableUndoButtonForPlayer(player1UndoEnable, player2UndoEnable);
	}
	
	/**
	 * This method is called whenever the Subject is changed. 
	 * Incoming object is used to update the text field that shows the rolled value and also used to change the state of the dice button.
	 * Dice button remains disabled until the rolled value is reduced to 0 and state of the dice changes. 
	 * Once a player has moved their piece(s) for the number of steps shown on dice, the button is enabled.
	 * Updates the data table every time.
	 * 
	 * @version 2.4
	 * @since 1.4
	 */
	@Override
	public void update(Subject s) 
	{
		helper.setDiceFieldValue(((SingletonDice) s).getDiceVal());
		helper.changeStateOfDiceButton(!((SingletonDice) s).getDiceRolled(), ((SingletonDice) s).getTurn());
		this.updateDataBoxes();
		tbc.enableUndoButtonForPlayer(((SingletonDice) s).getTurn());
	} 
	
	/**
	 * This method should be called to get an instance of this class. 
	 * As the class is a Singleton class, it's constructor is hidden and the class cannot be instantiated by a client using the new operator.
	 * When this method is called, it checks for existing instance of this class. 
	 * If there is, then it returns that instance, else it creates a new instance of this class.
	 * 
	 * 
	 * @return Instance of this class.
	 * @version 1.0
	 * @since 1.1
	 */
	public synchronized static SingletonGame getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new SingletonGame(); 
  
        return single_instance; 
    }
	
	private Object readResolve()
	{
		return single_instance;
	}
	
	public synchronized static void loadState(int boardRows, int boardColumns, UndoMemento umem)
	{
		if(single_instance == null)
		{
			single_instance = new SingletonGame();
			single_instance.c = single_instance.getContentPane();
			single_instance.setBoardSize(boardRows, boardColumns);
			SingletonBoard.getInstance().setBoardColumnsSize(boardColumns);
			single_instance.drawGameContainer();
			single_instance.addDiceButtonToPanel();
			SingletonBoard.getInstance().restore(umem);
			single_instance.createDataBox(true);
			single_instance.createDataBox(false);
			single_instance.setVisible(true);
		}
	}
	
	public void closeGracefully()
	{
		dispose();
		System.exit(0);
	}
}