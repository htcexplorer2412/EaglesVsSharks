//package Client;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Container c;
	private Button connectServerButton;
	private Button exitButton;
	private Client client = null;
	private String serverHostname;
	private int PORT_NUMBER_OUT;
	private int PORT_NUMBER_IN;
	private static ClientView single_instance = null;
	private JPanel mainPanel = null, subPanel = new JPanel();
	private JLabel label1 = null;
	protected boolean toggle = false;
	private Game game = null;
	private JTextField field1, field2, field3;
	
	private ClientView()
	{
		this.serverHostname = new String("127.0.0.1");
		this.PORT_NUMBER_OUT = 8081;
		this.PORT_NUMBER_IN = 8082;
		this.welcomePage();
		setVisible(true);
	}
	
	public static ClientView getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new ClientView(); 
  
        return single_instance; 
    } 
	
	private void welcomePage()
	{
		c = getContentPane();
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Connect to Server");
        
        JLabel label1 = new JLabel("Server Address");
        JLabel label2 = new JLabel("Server In Port");
        JLabel label3 = new JLabel("Server Out Port");
        field1 = new JTextField("localhost");
        field2 = new JTextField("8081");
        field3 = new JTextField("8082");
        label1.setMaximumSize(new Dimension(80, 30));
        field1.setMaximumSize(new Dimension(100, 30));
        
        label2.setMaximumSize(new Dimension(80, 30));
        field2.setMaximumSize(new Dimension(100, 30));
        
        label3.setMaximumSize(new Dimension(80, 30));
        field3.setMaximumSize(new Dimension(100, 30));
        
        field1.setEditable(true);
        field2.setEditable(false);
        field3.setEditable(false);
        
        connectServerButton = new Button("Connect");
        exitButton = new Button("Exit");
        connectServerButton.setMaximumSize(new Dimension(80, 30));
        exitButton.setMaximumSize(new Dimension(80, 30));
        
        JPanel hPanel1 = new JPanel();
        JPanel hPanel2 = new JPanel();
        JPanel hPanel3 = new JPanel();
        BoxLayout hb1 = new BoxLayout(hPanel1, BoxLayout.X_AXIS);
        BoxLayout hb2 = new BoxLayout(hPanel2, BoxLayout.X_AXIS);
        BoxLayout hb3 = new BoxLayout(hPanel3, BoxLayout.X_AXIS);
        
        hPanel1.setLayout(hb1);
        hPanel1.setBounds(0, 0, 200, 45);
        
        hPanel2.setLayout(hb2);
        hPanel2.setBounds(0, 45, 200, 45);
        
        hPanel2.setLayout(hb2);
        hPanel2.setBounds(0, 90, 200, 45);
        
        hPanel1.add(label1);
        hPanel1.add(Box.createRigidArea(new Dimension(3,0)));
        hPanel1.add(field1);
        
        hPanel2.add(label2);
        hPanel2.add(Box.createRigidArea(new Dimension(3,0)));
        hPanel2.add(field2);
        
        hPanel3.add(label3);
        hPanel2.add(Box.createRigidArea(new Dimension(3,0)));
        hPanel3.add(field3);
        
        //Panel1 for label and textfield
        JPanel vPanel1 = new JPanel();
        BoxLayout vb1 = new BoxLayout(vPanel1, BoxLayout.Y_AXIS);
        vPanel1.setLayout(vb1);
        vPanel1.setBounds(10, 10, 200, 90);
        vPanel1.add(hPanel1);
        vPanel1.add(hPanel2);
        //vPanel1.add(hPanel3);
        
        //Panel2 for buttons
        JPanel vPanel2 = new JPanel();
        BoxLayout vb2 = new BoxLayout(vPanel2, BoxLayout.Y_AXIS);
        
        vPanel2.setLayout(vb2);
        
        vPanel2.setBounds(10, 300, 200, 90);
        
        vPanel2.add(connectServerButton);
        vPanel2.add(Box.createRigidArea(new Dimension(0,3)));
        //vPanel.add(loadGameButton);
        vPanel2.add(exitButton);
        
        mainPanel = new JPanel();
        BoxLayout hb = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(hb);
        mainPanel.setBounds(0, 0, 500, 300);
        mainPanel.add(vPanel1);
        mainPanel.add(vPanel2);
        
        c.add(mainPanel);
        c.setVisible(true);
        setResizable(false);
        setLayout(null);
        //Setting the size of the frame and background of the frame
        setBounds(100, 100, 300, 300);
		c.setBackground(Color.WHITE);
		
		//Listeners for all the buttons
        this.addButtonListeners();
	}
	
	private void addButtonListeners()
	{
		connectServerButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) 
        	{
        		//c.removeAll();
        		//drawGameContainer();
        		try
        		{
        			if(field1.getText().equals("localhost"))
        				serverHostname = "127.0.0.1";
        			else
        				serverHostname = field1.getText();
        			
        			
        			client = Client.init(serverHostname, PORT_NUMBER_IN, PORT_NUMBER_OUT);
        			label1 = new JLabel("Waiting to connect to the server");
        			
        			subPanel.removeAll();
        			subPanel.add(label1);
        			mainPanel.add(subPanel);
        			mainPanel.repaint();
        			mainPanel.revalidate();
        			connectServerButton.setEnabled(false);
        		}
        		catch(InitializationException ex)
        		{
        			ex.printStackTrace();
        		}
        	}
        });
		
		//When exit is clicked, window will be disposed and program will terminate gracefully
		exitButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		dispose();
        		
        		try {
					Client.getInstance().close();
					System.exit(0);
				} catch (InitializationException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
					System.exit(1);
				}
        	}
        });
	}
	
	public void waitingScreen()
	{
		label1 = new JLabel("Waiting for an opponent");
		
		subPanel.removeAll();
		subPanel.add(label1);
		mainPanel.add(subPanel);
		mainPanel.repaint();
		mainPanel.revalidate();
		c.repaint();
		c.revalidate();
		
		client.sendPacket(new Packet());
	}
	
	public void connectedScreen()
	{
		label1 = new JLabel("Opponent has connected");
		
		subPanel.removeAll();
		subPanel.add(label1);
		mainPanel.add(subPanel);
		mainPanel.repaint();
		mainPanel.revalidate();
		
		client.sendPacket(new Packet(null, false, false, false, false, false, true));
	}
	
	public void showGame(GameDecorator decoratedGame)
	{
		dispose();
		this.game = (Game) decoratedGame;
		System.out.println(this.game.check);
		//this.game = (Game) game;
		this.game.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		ClientView.getInstance();
	}
}
