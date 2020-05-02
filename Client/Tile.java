import java.awt.BorderLayout;
import java.awt.Color;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;


/*
 * Add valid entry sides for each tile
 */

public class Tile extends JPanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xPos, yPos;
	private JPanel cellPanel, mainPanel;
	private boolean isPartOfIsland = false; //diceRolled;
	//private char isClicked;														//n - not clicked, f - first click, s - second click
	
	public Tile(int x, int y, JPanel cellPanel, JPanel mainPanel)
	{
		this.xPos = x;
		this.yPos = y;
		this.cellPanel = cellPanel;
		this.mainPanel = mainPanel;
		//this.diceRolled = false;
		//this.isClicked = 'n';			
	}
	
	public JLabel getIcon()
	{
		JLabel jlab = new JLabel();
		
		for(int i = 0; i < this.cellPanel.getComponentCount(); i++)
		{
			if(this.cellPanel.getComponent(i).getClass().toString().indexOf("JLabel") > -1)
			{
				jlab = (JLabel) this.cellPanel.getComponent(i);
				break;
			}
		}
		return jlab;
	}
	
	public JTextField getJTextField()
	{
		JTextField jtf = new JTextField();
		
		for(int i = 0; i < this.cellPanel.getComponentCount(); i++)
		{
			if(this.cellPanel.getComponent(i).getClass().toString().indexOf("JTextField") > -1)
			{
				jtf = (JTextField) this.cellPanel.getComponent(i);
				break;
			}
		}
		return jtf;
	}
	
	
	public void setTileVisible(MouseAdapterParent map)
	{
		this.cellPanel = new JPanel(new BorderLayout());
		this.cellPanel.setBorder(new LineBorder(Color.BLACK));
		
		if(yPos < 6)
			cellPanel.setBackground(new Color(51,153,255));
		else
			cellPanel.setBackground(Color.GRAY);
		
		cellPanel.addMouseListener(map);
		mainPanel.add(cellPanel);
	}
	
	
	/*
	 * Island location are fixed on the board. We won't be giving the selection of placing islands to user. Hence, hard-coded.
	 */
	public void setAsPartOfIsland(String islandType, String tileType)
	{
		isPartOfIsland = true;
		
		if(islandType.equals("2x2"))
		{
			if(tileType.equals("TLS"))
				cellPanel.setBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK));
			else if(tileType.equals("TRS"))
				cellPanel.setBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK));
			else if(tileType.equals("BLS"))
				cellPanel.setBorder(new MatteBorder(0, 3, 0, 0, Color.BLACK));
			else if(tileType.equals("BRS"))
				cellPanel.setBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK));
			else if(tileType.equals("TLE"))
				cellPanel.setBorder(new MatteBorder(0, 3, 0, 0, Color.BLACK));
			else if(tileType.equals("TRE"))
				cellPanel.setBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK));
			else if(tileType.equals("BLE"))
				cellPanel.setBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK));
			else if(tileType.equals("BRE"))
				cellPanel.setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));
		}
		else if(islandType.equals("2x1"))
		{
			if(tileType.equals("LHS"))
				cellPanel.setBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK));
			else if (tileType.equals("RHS"))
				cellPanel.setBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK));
			else if (tileType.equals("TVS"))
				cellPanel.setBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK));
			else if (tileType.equals("BVS"))
				cellPanel.setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));
			else if(tileType.equals("LHE"))
				cellPanel.setBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK));
			else if (tileType.equals("RHE"))
				cellPanel.setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));
			else if (tileType.equals("TVE"))
				cellPanel.setBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK));
			else if (tileType.equals("BVE"))
				cellPanel.setBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK));
		}
		
		cellPanel.setBackground(Color.WHITE);
	}
	
	public boolean getIsPartOfIsland()
	{
		return isPartOfIsland;
	}
	
	//Put a piece on tile
	public void putPieceOnTile(JLabel icon, JTextField name)
	{
		icon.setVisible(true);
		cellPanel.add(icon, BorderLayout.CENTER);
		name.setVisible(false);
		cellPanel.add(name, BorderLayout.SOUTH);
		//System.out.println(name.getText() + " Tile " + xPos + "," + yPos);
		cellPanel.repaint();
		cellPanel.revalidate();
	}
	
	public void removePieceFromTile()
	{
		for(int i = 0; i < this.cellPanel.getComponentCount(); i++)
		{
			if((this.cellPanel.getComponent(i).getClass().toString().indexOf("JTextField") > -1) || this.cellPanel.getComponent(i).getClass().toString().indexOf("JLabel") > -1)
				cellPanel.remove(i);
		}
		
		cellPanel.repaint();
		cellPanel.revalidate();
	}
	
	//Check if part of island - if no, check if there is a piece, if yes pass a short string for piece, else pass empty string. If part of island, check for defending piece, if yes pass a short string for piece, else pass empty string
	public String whatIsOnTile()		
	{
		String temp = "";
		
		if(isPartOfIsland)
		{
			for(int i = 0; i < this.cellPanel.getComponentCount(); i++)
			{
				if(this.cellPanel.getComponent(i).getClass().toString().indexOf("JTextField") > -1)
				{
					JTextField jtf = (JTextField) this.cellPanel.getComponent(i);
					temp = jtf.getText();
					//System.out.println(cellPanel.getComponent(i).getClass().toString() + " 1");
					break;
				}
			}
		}
		else
		{
			for(int i = 0; i < this.cellPanel.getComponentCount(); i++)
			{
				if(this.cellPanel.getComponent(i).getClass().toString().indexOf("JTextField") > -1)
				{
					JTextField jtf = (JTextField) this.cellPanel.getComponent(i);
					temp = jtf.getText();
					//System.out.println(cellPanel.getComponent(i).getClass().toString() + " 2");
					break;
				}
			}
		}
		
		return temp;
	}
	
	public void highlightPiece(boolean selected)
    {
		Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
		
		//Bordering JLabel instead of JPanel
		for(int z = 0; z < this.cellPanel.getComponentCount(); z++)
		{
			if(this.cellPanel.getComponent(z).getClass().toString().indexOf("JLabel") > -1)
			{
				JLabel lblTemp = (JLabel)this.cellPanel.getComponent(z);
				if(selected)
					lblTemp.setBorder(redBorder);
	            else
	            	lblTemp.setBorder(null);
	         }
	     }
    }
}
