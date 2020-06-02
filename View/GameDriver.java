package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.GameSetupController;

public class GameDriver extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Container c;
	private GameSetupController gsc;

	/**
	 * Shows the welcome page with options to start a new game, load game or exit
	 * 
	 * @version 1.2
	 * @since 2.0
	 */
	public void welcomePage()
	{
		c = getContentPane();
		
		gsc = new GameSetupController(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Eagles vs Sharks");
        
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");
        
        newGameButton.setActionCommand("New Game");
        loadGameButton.setActionCommand("Load Game");
        exitButton.setActionCommand("Exit");
        
        //Adding all buttons in vertical layout
        JPanel vPanel = new JPanel();
        //BoxLayout vb = new BoxLayout(vPanel, BoxLayout.Y_AXIS);
        vPanel.setLayout(new GridLayout(3,0));
        
        vPanel.setBounds(1, 1, 256, 160);
        
        vPanel.add(newGameButton);
        //vPanel.add(Box.createRigidArea(new Dimension(5,0)));
        vPanel.add(loadGameButton);
        //vPanel.add(Box.createRigidArea(new Dimension(5,0)));
        vPanel.add(exitButton);
        
        c.add(vPanel);
        c.setVisible(true);
        setResizable(false);
        setLayout(null);
        //Setting the size of the frame and background of the frame
        setBounds(200, 200, 275, 200);
		c.setBackground(Color.WHITE);
		
		//Listeners for all the buttons
		newGameButton.addActionListener(gsc);
		loadGameButton.addActionListener(gsc);
		exitButton.addActionListener(gsc);
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		GameDriver driver = new GameDriver();
		driver.welcomePage();
		
		//SingletonGame.getInstance();		
	}
}
