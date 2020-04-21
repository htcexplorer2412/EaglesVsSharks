import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;


/*
 * Class that contains all the functions to show the GUI (View in MVC pattern) 
 * Class implements Singleton pattern - Only one instance of this class can be created. This instance can be used by any class.
 */
public class Game extends JFrame implements Observer {

	/**
	 * 
	 */
	private static Game single_instance = null;
	private static final long serialVersionUID = 1L;
	private Player eagle;
	private Player shark;
	private Container c;
	private JPanel mainPanel = new JPanel(new GridLayout(12,12));
	private JPanel[][] cellPanel = new JPanel[12][12];
	private JPanel buttonPanel = new JPanel();
	private Button diceButton, newGameButton, loadGameButton, exitButton, select, next;
	private TextField tf1;
	private boolean diceRolled = false;
	private Board board;
	private Dice dice;
	private final Color lightBrown = new Color(153,102,0);
	private JList<String> list;
	private int listSelectionIndex;
	private int selectionCount = 0;
	private int[] selectionIndex = new int[3];
	private char selectionTeam = 'e';
	
	
	private Game()
	{
		this.welcomePage();        
        setVisible(true);
	}
	
	private void welcomePage()
	{
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
	private void drawGameContainer()
	{
		c = getContentPane();
		//Setting the size of the frame and background of the frame
		setBounds(0, 0, 1200, 800);
		c.setBackground(this.lightBrown);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Game board");
        setResizable(false);
        setLayout(null); 
        
        this.drawBoardPanel();
        this.drawButtonPanel();
        this.selectPiecesPanel();
        
	}
	
	private void drawBoardPanel()
	{
		//Setting the size of the Main panel and background of the panel
        mainPanel.setBounds(10, 10, 600, 600);
        mainPanel.setBackground(new Color(255, 255, 255));
        //Adding main panel to the frame
        c.add(mainPanel);
        
        eagle = new Player('e');
        shark = new Player('s');
        
        board = new Board(this.cellPanel, this.mainPanel, this.eagle, this.shark);
        board.drawBoard();
        board.arrangeIslands();
	}
	
	private void drawButtonPanel()
	{
		//Button panel currently contains button and textfield for dice. It can include other buttons.
		BoxLayout bl = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.setLayout(bl);
		
		buttonPanel.setBounds(615, 10, 500, 100);
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
		dice = new Dice();
		
		diceButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!diceRolled)
        		{
        			dice.rollDice();
            		System.out.println(dice.getDiceVal());
            		tf1.setText(dice.getDiceVal() + "");
            		diceRolled = true;
            		board.setDiceAndTurn(true, dice.getDiceVal());
        		}
        		else
        		{
        			System.out.println("Move not complete yet!");
        		}
        	}
        });
	}
	
	private void selectPiecesPanel()
	{
		select = new Button("Select");
		next = new Button("Next");
		
		select.setMaximumSize(new Dimension(55, 30));
		next.setMaximumSize(new Dimension(55, 30));
		
		if(selectionTeam == 'e')
		{
			String[] pieceList = eagle.getPieceNames();
			list = new JList<String>(pieceList);
			
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane jsp = new JScrollPane(list);
			jsp.setMaximumSize(new Dimension(230, 100));
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
			
			next.setEnabled(false);
			select.setEnabled(true);
			
			this.piecePanelButtonListeners();
		}
		else if(selectionTeam == 's')
		{
			String[] pieceList = shark.getPieceNames();
			list = new JList<String>(pieceList);
			
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane jsp = new JScrollPane(list);
			jsp.setMaximumSize(new Dimension(240, 100));
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
        		/*else
        		{
        			JOptionPane.showMessageDialog(null, "You cannot select more than 3 pieces. Please click next", "Error", JOptionPane.ERROR_MESSAGE);
        			select.setEnabled(false);
        		}*/
        		
        	}
        });
		
		next.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(selectionTeam == 'e')
        		{
            		eagle.selectPieces(selectionIndex);
            		
            		board.arrangePieceInitial(selectionTeam, eagle.getIcons(), eagle.getAllNames());
            		
            		buttonPanel.removeAll();
            		selectionTeam = 's';
            		selectPiecesPanel();
            		selectionCount = 0;
        		}
        		else if(selectionTeam == 's')
        		{
        			shark.selectPieces(selectionIndex);
        			
        			board.arrangePieceInitial(selectionTeam, shark.getIcons(), shark.getAllNames());
        			
        			selectionCount = 0;
        			buttonPanel.removeAll();
        			addDiceButtonToPanel(); 
        		}
        	}
        });
	}
	
	/*public static void main(String[] args)
	{
		new Game();
	}*/

	@Override
	public void update(boolean diceRolled) {
		// TODO Auto-generated method stub
		this.diceRolled = diceRolled;
	}
	
	public static Game getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Game(); 
  
        return single_instance; 
    } 
}
