import java.awt.BorderLayout;
import java.awt.Color;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import TileFactory.*;

public class TileView extends JPanel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cellPanel, mainPanel;
	
	public TileView(JPanel cellPanel, JPanel mainPanel)
	{
		this.cellPanel = cellPanel;
		this.mainPanel = mainPanel;
	}
	
	public void setTileVisible(MouseAdapterParent map, int yPos, Tile t)
	{
		this.cellPanel = new JPanel(new BorderLayout());
		if(t instanceof unborderedTile)
		{
			this.cellPanel.setBorder(new LineBorder(Color.BLACK));
			
			if(yPos < 6)
				this.cellPanel.setBackground(new Color(51,153,255));
			else
				this.cellPanel.setBackground(Color.GRAY);
		}
		
		if(t instanceof borderedTile)
		{
			this.cellPanel.setBackground(Color.WHITE);
			
			if(t instanceof NBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(3, 0, 0, 0, Color.BLACK));					//top, left, bottom, right
			}
			else if(t instanceof SBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK));					//top, left, bottom, right
			}
			else if(t instanceof EBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(0, 0, 0, 3, Color.BLACK));					//top, left, bottom, right
			}
			else if(t instanceof WBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(0, 3, 0, 0, Color.BLACK));					//top, left, bottom, right
			}
			else if(t instanceof NEBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(3, 0, 0, 3, Color.BLACK));					//top, left, bottom, right
			}
			else if(t instanceof NWBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(3, 3, 0, 0, Color.BLACK));					//top, left, bottom, right
			}
			else if(t instanceof SEBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));					//top, left, bottom, right
			}
			else if(t instanceof SWBorderedTile)
			{
				this.cellPanel.setBorder(new MatteBorder(0, 3, 3, 0, Color.BLACK));					//top, left, bottom, right
			}
			
		}

		cellPanel.addMouseListener(map);
		mainPanel.add(cellPanel);
	}

	public void putPieceOnTile(JLabel icon)
	{
		icon.setVisible(true);
		cellPanel.add(icon, BorderLayout.CENTER);
		cellPanel.repaint();
		cellPanel.revalidate();
	}
	
	public void removePieceFromTile()
	{
		for(int i = 0; i < this.cellPanel.getComponentCount(); i++)
		{
			if(this.cellPanel.getComponent(i).getClass().toString().indexOf("JLabel") > -1)
				this.cellPanel.remove(i);
		}
		
		this.cellPanel.repaint();
		this.cellPanel.revalidate();
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
	
}
