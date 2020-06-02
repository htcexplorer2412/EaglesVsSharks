package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controller.SingletonMouseAdapterParent;
import Model.PrototypeTileFactory.*;

/**
 * <H1>Tile (View)</H1>
 * <p>
 * This class represents the (visual) View for each Tile in Model-View-Controller paradigm.
 * <p>
 * Each tile on the board in the game is represented by its own JPanel 
 * and the view is designed according to the internal information of the Tile.
 * For example, if a Tile contains an occupier, this class will show the icon of that occupying piece to the user through GUI.
 * If a user selects a Tile as a source then that Tile is highlighted through this class. 
 * Basically it provides a picture of a certain Tile's state to the user through GUI.
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-05-03
 */
public class TileView extends JPanel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cellPanel, mainPanel;
	private Color originalColor;
	private int boardColumnSize;
	
	public TileView(JPanel cellPanel, JPanel mainPanel, int columnSize)
	{
		this.cellPanel = cellPanel;
		this.mainPanel = mainPanel;
		this.boardColumnSize = columnSize;
	}
	
	/*
	 * Designing the view of each tile according to the information related to the corresponding Model class
	 */
	/**
	 * This method is responsible for designing the view for the user. 
	 * It adds a mouse listener to this panel, draws visual borders if there are any borders in its corresponding model class
	 * and sets the background color to display if this tile is on land side or water side.
	 * 
	 * @param map	Object of the class that is listening for the mouse click.
	 * @param yPos	Position of this tile along y-axis w.r.t the Board
	 * @param t		The model class this view belongs to.
	 * @version 1.1
	 * @since 1.0
	 */
	public void setTileVisible(SingletonMouseAdapterParent map, int yPos, Tile t)
	{
		this.cellPanel = new JPanel(new BorderLayout());
		if(t instanceof unborderedTile)
		{
			this.cellPanel.setBorder(new LineBorder(Color.BLACK));
			
			if(yPos < this.boardColumnSize/2)
				this.originalColor = new Color(51,153,255);
			else
				this.originalColor = Color.GRAY;
			
			this.cellPanel.setBackground(this.originalColor);
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

	/**
	 * Displays an icon to the user.
	 * 
	 * @param icon - Image in the form of a JLabel.
	 * @version 1.0
	 * @since 1.0
	 */
	public void putPieceOnTile(JLabel icon)
	{
		icon.setVisible(true);
		cellPanel.add(icon, BorderLayout.CENTER);
		cellPanel.repaint();
		cellPanel.revalidate();
	}
	
	/**
	 * Remove the icon from the display.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
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
	
	/**
	 * Highlight the icon with a red border. Highlights only if there is any icon in the JPanel.
	 * 
	 * @param selected - TRUE if it should be highlighted. FALSE if the highlight should be removed.
	 * @version 1.0
	 * @since 1.0
	 */
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
	
	/*public void highlightTile(boolean selected)
	{
		if (selected)
			cellPanel.setBackground(Color.GREEN);
		else
			cellPanel.setBackground(this.originalColor);
	}*/
	
	/**
	 * Returns the icon if there is an icon in the JPanel.
	 * 
	 * @return an image in the form of a JLabel
	 * @version 1.0
	 * @since 1.0
	 */
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
