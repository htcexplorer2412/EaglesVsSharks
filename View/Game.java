package View;

import java.awt.*;
import javax.swing.*;

import Controller.ButtonController;
import Model.Board;
import Model.PlayerRegistry;
import Model.Observer.*;

import java.awt.event.*;
import java.io.Serializable;


/*
 * Class that contains all the functions to show the GUI (View in MVC pattern) 
 * Class implements Singleton pattern - Only one instance of this class can be created. This instance can be used by any class.
 * 
 * Separate welcome page from this class
 * Implement deep cloning and send clone objects to clients
 * 
 */

//Implement variable board size and variable number of piece selection

public class Game extends JFrame implements Observer, Serializable {

	/**
	 * 
	 */
	private static Game single_instance = null;
	private static final long serialVersionUID = 1L;
	private Container c;
	//private JPanel mainPanel = new JPanel(new GridLayout(12,12));
	//private JPanel[][] cellPanel = new JPanel[12][12];
	private JPanel mainPanel;
	private JPanel[][] cellPanel;
	private JPanel buttonPanel = new JPanel();
	private Button diceButton, newGameButton, loadGameButton, exitButton, select, next, selectTeam;
	private TextField tf1;
	private boolean diceRolledView;							//locally used variable. Stores the incoming update from Dice class
	private Board board;
	//private Dice dice;
	private final Color lightBrown = new Color(153,102,0);
	private JList<String> list, teamList;
	private boolean selectionTeam = true;					//true for player 1 and false for player 2
	private boolean turn;									//Gets value from the Subject (Dice class) - Remove if not used here
	//public boolean check = false;
	int containerWidth, containerHeight, mainPanelWidth, mainPanelHeight, buttonPanelWidth, buttonPanelHeight, buttonPanelX, buttonPanelY;
	ButtonController bc = new ButtonController();
	DefaultListModel<String> listModel;
	
	private Game()
	{
		//this.welcomePage();  
		//drawGameContainer();
		setDefaultLookAndFeelDecorated(true);
		boardTypesAndPieceSelections();
        setVisible(true);
	}
	
	public void welcomePage()
	{
		//System.out.println("Enter");
		c = getContentPane();
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome page");
        
        
        newGameButton = new Button("New Game");
        loadGameButton = new Button("Load Game");
        exitButton = new Button("Exit");
        
        //Adding all buttons in vertical layout
        JPanel vPanel = new JPanel();
        BoxLayout vb = new BoxLayout(vPanel, BoxLayout.Y_AXIS);
        vPanel.setLayout(vb);
        
        vPanel.setBounds(50, 50, 100, 100);
        
        vPanel.add(newGameButton);
        vPanel.add(loadGameButton);
        vPanel.add(exitButton);
        
        c.add(vPanel);
        c.setVisible(true);
        setResizable(false);
        setLayout(null);
        //Setting the size of the frame and background of the frame
        setBounds(100, 100, 200, 200);
		c.setBackground(Color.WHITE);
		
		//Listeners for all the buttons
        this.addButtonListeners();
        //display();
	}
	
	private void addButtonListeners()
	{
		//When new game is clicked, all the components (buttons) will be removed from the container and the same container will be resized to fit the board and other components
		newGameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		c.removeAll();
        		drawGameContainer();
        		
        	}
        });
		
		//Actions for load game should be defined here
		/*loadGameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });*/
		
		//When exit is clicked, window will be disposed and program will terminate gracefully
		exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		System.exit(0);
        	}
        });
	}
	
	//Board size selection
	protected void boardTypesAndPieceSelections()
	{
		c = getContentPane();
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Select board size");
        
        Button button1 = new Button("12x12");
        Button button2 = new Button("14x14");
        Button button3 = new Button("16x16");
        
        //Adding all buttons in vertical layout
        JPanel Panel = new JPanel();
        //BoxLayout vb = new BoxLayout(vPanel, BoxLayout.Y_AXIS);
        Panel.setLayout(new GridLayout(3,0));

        Panel.setBounds(0, 0, 283, 210);
        
        //Panel.add(new JLabel("Select Board size"));
        Panel.add(button1);
        Panel.add(button2);
        Panel.add(button3);
        
        c.add(Panel);
        c.setVisible(true);
        setResizable(false);
        setLayout(null);
        //Setting the size of the frame and background of the frame
        setBounds(250, 250, 300, 250);
		c.setBackground(Color.WHITE);
		
		button1.setActionCommand("12x12");
		button2.setActionCommand("14x14");
		button3.setActionCommand("16x16");
		button1.addActionListener(bc);
		button2.addActionListener(bc);
		button3.addActionListener(bc);
		
	}
	
	public void setBoardSize(int i, int j)
	{
		c.removeAll();
		//System.out.println("i - " + i + ", j - " + j);
		mainPanel = new JPanel(new GridLayout(i,j));
		cellPanel = new JPanel[i][j];
		
		containerWidth = 1500;
		containerHeight = 800;
		mainPanelWidth = 50*i;
		mainPanelHeight = 50*i;
		buttonPanelX = mainPanelWidth + 15;
		buttonPanelY = 10;
		buttonPanelWidth = containerWidth - mainPanelWidth - 15;
		buttonPanelHeight = containerHeight;
		
		drawGameContainer();
	}
	
	//Draws game container which contains Board panel and button panel
	protected void drawGameContainer()
	{
		c = getContentPane();
		//Setting the size of the frame and background of the frame
		//containerWidth, containerHeight
		setBounds(-10, 0, containerWidth, containerHeight);
		c.setBackground(this.lightBrown);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Game board");
        setResizable(false);
        setLayout(null); 
        c.setVisible(true);
        
        this.drawBoardPanel();
        this.drawButtonPanel();
        this.selectTeam();
	}
	
	//Remove eagle and shark declaration from here and place it in setPlayerTeams 
	protected void drawBoardPanel()
	{
		//Setting the size of the Main panel and background of the panel
		//mainPanelWidth, mainPanelHeight
        mainPanel.setBounds(10, 10, mainPanelWidth, mainPanelHeight);
        mainPanel.setBackground(new Color(255, 255, 255));
        //Adding main panel to the frame
        c.add(mainPanel);
        
        this.board = Board.getInstance();
        this.board.setBoardPanels(this.cellPanel, this.mainPanel);
        this.board.drawBoard();
	}
	
	public void clearButtonPanel()
	{
		buttonPanel.removeAll();
		buttonPanel.revalidate();
		buttonPanel.repaint();
	}
	
	protected void drawButtonPanel()
	{
		//Button panel currently contains button and textfield for dice. It can include other buttons.
		BoxLayout bl = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.setLayout(bl);
		
		//buttonPanelStartX, buttonPanelStartY, buttonPanelWidth, buttonPanelHeight
		buttonPanel.setBounds(buttonPanelX, buttonPanelY, buttonPanelWidth, buttonPanelHeight);
		buttonPanel.setBackground(lightBrown);
        
        c.add(buttonPanel);
	}
	
	public void addDiceButtonToPanel()
	{
		diceButton = new Button("Roll dice");
        tf1 = new TextField();
        tf1.setEditable(false);
        
        diceButton.setMaximumSize(new Dimension(55, 30));
        tf1.setMaximumSize(new Dimension(30, 30));
        
        buttonPanel.add(diceButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
        buttonPanel.add(tf1);
        buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
        
        buttonPanel.repaint();
		buttonPanel.revalidate();
		
		diceButton.setActionCommand("Dice");
		diceButton.addActionListener(bc);
	}
	
	protected void setDiceFieldValue(int value)
	{
		tf1.setText(Integer.toString(value));
		tf1.setEditable(false);
	}
	
	protected void changeStateOfDiceButton(boolean value)
	{
		diceButton.setEnabled(value);
	}
	
	private void selectTeam()
	{
		selectTeam = new Button("Select");
		selectTeam.setMaximumSize(new Dimension(55, 30));
		
		String[] teamListNames = {"Eagle", "Shark"};
		teamList = new JList<String>(teamListNames);
		
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane jsp = new JScrollPane(teamList);
		jsp.setMaximumSize(new Dimension(230, 100));
		jsp.setRowHeaderView(new JLabel("Select a team"));
		
		teamList.addListSelectionListener(bc);
		
		buttonPanel.add(jsp);
		buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));		//Filler between components
		buttonPanel.add(selectTeam);
		buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
		selectTeam.setActionCommand("Team Select");
		selectTeam.addActionListener(bc);
		//this.teamPanelButtonListener();
	}
	
	public void disableSelectEnableNext()
	{
		next.setEnabled(true);
		select.setEnabled(false);
	}
	
	public boolean getWhichPlayerSelecting()
	{
		return selectionTeam;
	}
	
	public void setWhichPlayerSelecting(boolean value)
	{
		selectionTeam = value;
	}
	
	protected void removeFromList(int index)
	{
		listModel.remove(index);
		list.repaint();
		list.revalidate();
	}
	
	/*
	 * Variable piece selection changes here 
	 * AI or real life user check here
	 * Setup listModel properly before using this
	 */
	public void selectPiecesPanel()
	{
		select = new Button("Select");
		next = new Button("Next");
		
		select.setMaximumSize(new Dimension(55, 30));
		next.setMaximumSize(new Dimension(55, 30));
		
		if(selectionTeam)											//Show list for player 1 to select pieces
		{
			String[] pieceList = PlayerRegistry.getPlayerObj(selectionTeam).getPieceNames();
			list = new JList<String>(pieceList);

	        /*listModel = new DefaultListModel<String>();
	        for(int i = 0; i < pieceList.length; i++)
	        {
	        	listModel.addElement(pieceList[i]);
	        }
	        
	        list = new JList<String>(listModel);*/
	        
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane jsp = new JScrollPane(list);
			jsp.setMaximumSize(new Dimension(230, 120));
			jsp.setRowHeaderView(new JLabel("Select 3 pieces"));
			
			list.addListSelectionListener(bc);
			
			buttonPanel.add(jsp);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));		//Filler between components
			buttonPanel.add(select);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
			buttonPanel.add(next);
			
			buttonPanel.repaint();
			buttonPanel.revalidate();
			
			next.setEnabled(false);
			select.setEnabled(true);
			
			select.setActionCommand("Piece Select");
			next.setActionCommand("Next");
			
			select.addActionListener(bc);
			next.addActionListener(bc);
		}
		else if(!selectionTeam)
		{
			String[] pieceList = PlayerRegistry.getPlayerObj(selectionTeam).getPieceNames();
			list = new JList<String>(pieceList);
			
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane jsp = new JScrollPane(list);
			jsp.setMaximumSize(new Dimension(240, 120));
			jsp.setRowHeaderView(new JLabel("Select 3 pieces"));
			
			list.addListSelectionListener(bc);
			
			buttonPanel.add(jsp);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));		//Filler between components
			buttonPanel.add(select);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
			buttonPanel.add(next);
			
			buttonPanel.repaint();
			buttonPanel.revalidate();
			
			next.setEnabled(false);
			select.setEnabled(true);
			
			select.setActionCommand("Piece Select");
			next.setActionCommand("Next");
			
			select.addActionListener(bc);
			next.addActionListener(bc);
		}
	}

	public void showError(String s)
	{
		JOptionPane.showMessageDialog(null, s, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void update(Subject s) {
		// TODO Auto-generated method stub
		this.diceRolledView = s.getDiceRolled();
		this.turn = s.getTurn();
		this.setDiceFieldValue(s.getDiceVal());
		this.changeStateOfDiceButton(!this.diceRolledView);
		//update textfield from here and diceRoll button
	} 
	
	public synchronized static Game getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Game(); 
  
        return single_instance; 
    }
	
}
