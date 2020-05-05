import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.io.Serializable;
import java.util.HashMap;


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
	//private Player eagle;
	//private Player shark;
	private Container c;
	private JPanel mainPanel = new JPanel(new GridLayout(12,12));
	private JPanel[][] cellPanel = new JPanel[12][12];
	private JPanel buttonPanel = new JPanel();
	private Button diceButton, newGameButton, loadGameButton, exitButton, select, next, selectTeam;
	private TextField tf1;
	private boolean diceRolledView;							//locally used variable. Stores the incoming update from Dice class
	private Board board;
	//private Dice dice;
	private final Color lightBrown = new Color(153,102,0);
	private JList<String> list, teamList;
	private int listSelectionIndex;
	private String listSelectionValue;
	private int selectionCount = 0;
	private int[] selectionIndex = new int[3];
	private boolean selectionTeam = true;					//true for player 1 and false for player 2
	private HashMap <Integer, Player> playerTeamMapping = new HashMap<Integer, Player>();
	private boolean turn;									//Gets value from the Subject (Dice class) - Remove if not used here
	public boolean check = false;
	
	private Game()
	{
		//this.welcomePage();  
		drawGameContainer();
        setVisible(true);
	}
	
	public void setPlayerTeams(String nameSelected) 
	{
		if(nameSelected.equals("Eagle"))
		{
			System.out.println("Eagle selected");
			playerTeamMapping.put(1, new Player('e'));
			playerTeamMapping.put(2, new Player('s'));
		}
		else
		{
			System.out.println("Shark selected");
			playerTeamMapping.put(1, new Player('s'));
			playerTeamMapping.put(2, new Player('e'));
		}
	}
	
	public char getPlayerTeam(boolean player)
	{
		if(player)
		{
			return playerTeamMapping.get(1).getTeam();
		}
		else
		{
			return playerTeamMapping.get(2).getTeam();
		}
	}
	
	public Player getPlayerObj(boolean player)
	{
		if(player)
		{
			return playerTeamMapping.get(1);
		}
		else
		{
			return playerTeamMapping.get(2);
		}
	}
	
	public void welcomePage()
	{
		System.out.println("Enter");
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
	
	//Draws game container which contains Board panel and button panel
	protected void drawGameContainer()
	{
		c = getContentPane();
		//Setting the size of the frame and background of the frame
		setBounds(0, 0, 1200, 800);
		c.setBackground(this.lightBrown);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Game board");
        setResizable(false);
        setLayout(null); 
        c.setVisible(true);
        
        this.drawBoardPanel();
        this.drawButtonPanel();
        //this.drawSidePanel();
        this.selectTeam();
        //this.selectPiecesPanel();
	}
	
	//Remove eagle and shark declaration from here and place it in setPlayerTeams 
	protected void drawBoardPanel()
	{
		//Setting the size of the Main panel and background of the panel
        mainPanel.setBounds(10, 10, 600, 600);
        mainPanel.setBackground(new Color(255, 255, 255));
        //Adding main panel to the frame
        c.add(mainPanel);
        
        //eagle = new Player('e');
        //shark = new Player('s');
        
        //board = new Board(this.cellPanel, this.mainPanel);
        this.board = Board.getInstance();
        this.board.setBoardPanels(this.cellPanel, this.mainPanel);
        this.board.drawBoard();
        //this.board.arrangeIslands();
	}
	
	public void display()
	{
		System.out.println("Displaying");
		c.setVisible(true);
	}
	
	protected void drawButtonPanel()
	{
		//Button panel currently contains button and textfield for dice. It can include other buttons.
		BoxLayout bl = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.setLayout(bl);
		
		buttonPanel.setBounds(615, 10, 500, 600);
		buttonPanel.setBackground(lightBrown);
        
        c.add(buttonPanel);
	}
	
	private void addDiceButtonToPanel()
	{
		diceButton = new Button("Roll dice");
        tf1 = new TextField();
        tf1.setEditable(false);
        
        diceButton.setMaximumSize(new Dimension(55, 30));
        tf1.setMaximumSize(new Dimension(20, 30));
        
        buttonPanel.add(diceButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
        buttonPanel.add(tf1);
        buttonPanel.add(Box.createRigidArea(new Dimension(8,0)));
        
        buttonPanel.repaint();
		buttonPanel.revalidate();

        this.addDiceButtonListener();
	}
	private void addDiceButtonListener()
	{
		diceButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!diceRolledView)
        		{
        			Dice.getInstance().rollDice();
            		//System.out.println(dice.getDiceVal());
            		tf1.setText(Dice.getInstance().getDiceVal() + "");
        		}
        		else
        		{
        			System.out.println("Move not complete yet!");
        		}
        	}
        });
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
		
		teamList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent le) {
				listSelectionValue = teamList.getSelectedValue();
		      }
		    });
		
		buttonPanel.add(jsp);
		buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));		//Filler between components
		buttonPanel.add(selectTeam);
		buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
		this.teamPanelButtonListener();
	}
	
	//Variable piece selection changes here
	private void selectPiecesPanel()
	{
		select = new Button("Select");
		next = new Button("Next");
		
		select.setMaximumSize(new Dimension(55, 30));
		next.setMaximumSize(new Dimension(55, 30));
		
		if(selectionTeam)											//Show list for player 1 to select pieces
		{
			String[] pieceList = this.getPlayerObj(selectionTeam).getPieceNames();
			list = new JList<String>(pieceList);
			
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane jsp = new JScrollPane(list);
			jsp.setMaximumSize(new Dimension(230, 120));
			jsp.setRowHeaderView(new JLabel("Select 3 pieces"));
			
			list.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent le) {
			        listSelectionIndex = list.getSelectedIndex();
			      }
			    });
			
			buttonPanel.add(jsp);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));		//Filler between components
			buttonPanel.add(select);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
			buttonPanel.add(next);
			
			buttonPanel.repaint();
			buttonPanel.revalidate();
			
			next.setEnabled(false);
			select.setEnabled(true);
			
			this.piecePanelButtonListeners();
		}
		else if(!selectionTeam)
		{
			String[] pieceList = this.getPlayerObj(selectionTeam).getPieceNames();
			list = new JList<String>(pieceList);
			
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane jsp = new JScrollPane(list);
			jsp.setMaximumSize(new Dimension(240, 120));
			jsp.setRowHeaderView(new JLabel("Select 3 pieces"));
			
			list.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent le) {
			        listSelectionIndex = list.getSelectedIndex();
			      }
			    });
			
			buttonPanel.add(jsp);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));		//Filler between components
			buttonPanel.add(select);
			buttonPanel.add(Box.createRigidArea(new Dimension(3,0)));
			buttonPanel.add(next);
			
			buttonPanel.repaint();
			buttonPanel.revalidate();
			
			next.setEnabled(false);
			select.setEnabled(true);
			
			this.piecePanelButtonListeners();
		}
	}
	
	protected void teamPanelButtonListener()
	{
		this.selectTeam.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setPlayerTeams(listSelectionValue);
        		buttonPanel.removeAll();
        		selectPiecesPanel();
        	}
        });
	}
	
	private void piecePanelButtonListeners()
	{	
		select.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(selectionCount < 3)
        		{
        			if(listSelectionIndex != -1)
            		{
        				selectionIndex[selectionCount] = listSelectionIndex;
            			selectionCount++;
            		}
        			
        			if(selectionCount == 3)
        			{
        				next.setEnabled(true);
        				select.setEnabled(false);
        			}
        		}
        	}
        });
		
		next.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(selectionTeam)
        		{
        			getPlayerObj(selectionTeam).selectPieces(selectionIndex);
            		
            		Board.getInstance().arrangePieceInitial(getPlayerTeam(selectionTeam), getPlayerObj(selectionTeam).getIcons(), getPlayerObj(selectionTeam).getAllNames());
            		
            		buttonPanel.removeAll();
            		selectionTeam = !selectionTeam;
            		selectPiecesPanel();
            		selectionCount = 0;
        		}
        		else if(!selectionTeam)
        		{
        			getPlayerObj(selectionTeam).selectPieces(selectionIndex);
        			
        			Board.getInstance().arrangePieceInitial(getPlayerTeam(selectionTeam), getPlayerObj(selectionTeam).getIcons(), getPlayerObj(selectionTeam).getAllNames());
        			
        			selectionCount = 0;
        			buttonPanel.removeAll();
        			addDiceButtonToPanel(); 
        		}
        	}
        });
	}

	public void showError(String s)
	{
		JOptionPane.showMessageDialog(null, s, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void update(boolean diceRolled) {
		// TODO Auto-generated method stub
		this.diceRolledView = diceRolled;
		
		/*
		 * To update roll dice button
		 * if(diceRolled)
			diceButton.setEnabled(false);
		else
			diceButton.setEnabled(true);*/
	}

	@Override
	public void update(boolean diceRolled, boolean turn) {
		// TODO Auto-generated method stub
		this.diceRolledView = diceRolled;
		this.turn = turn;
	} 
	
	public synchronized static Game getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Game(); 
  
        return single_instance; 
    }
	
}
