package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.TableButtonController;
import Model.PlayerRegistry;

class DataBoxPanelHelper 
{
	private JPanel sidePanel;
	private JPanel dataBoxPanel;
	private final Color lightBrown = new Color(153,102,0);
	/** Stores data tables with information (health points and orientation) about all the selected pieces */
	private ArrayList<JTable> data_tables;
	private ArrayList<ArrayList<JButton>> undo_buttons;
	
	/**
	 * Setup the data box panel
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	public DataBoxPanelHelper(JPanel sidePanel, JPanel dataBoxPanel)
	{
		this.sidePanel = sidePanel;
		this.dataBoxPanel = dataBoxPanel;
		BoxLayout bl = new BoxLayout(dataBoxPanel, BoxLayout.X_AXIS);
		dataBoxPanel.setLayout(bl);
		dataBoxPanel.setBackground(lightBrown);
		dataBoxPanel.setMinimumSize(new Dimension(sidePanel.getWidth(), sidePanel.getHeight()/4));
		dataBoxPanel.setMaximumSize(new Dimension(sidePanel.getWidth(), sidePanel.getHeight()/4));
		this.data_tables = new ArrayList<JTable>();
		this.undo_buttons = new ArrayList<ArrayList<JButton>>();
	}
	
	/**
	 * Creates panels which shows a table for all the selected piece's health points.
	 * 
	 * @param team TRUE for Player 1, FALSE for Player 2
	 * @version 1.3
	 * @since 1.0
	 */
	protected void createDataBox(boolean team, TableButtonController tbc)
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		panel.setMaximumSize(new Dimension(sidePanel.getWidth() - 10, sidePanel.getHeight()/4));
		if(team)
			label.setText("Player 1");
		else
			label.setText("Player 2");

		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(bl);
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() 
		{
			private static final long serialVersionUID = 1L;
			Font font = new Font(Font.SERIF, Font.PLAIN, 20);
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setFont(font);
				return this;
			}
		};
		model.addColumn("Name");
		model.addColumn("Health");
		model.addColumn("Orientation");
		model.addColumn("On board");
		
		Object[][] data = new Object[PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().size()][4];
		
		for(int i = 0; i < PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().size(); i++)
		{
			data[i][0] = PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().get(i).getFullName();
			data[i][1] = PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().get(i).getHealthPoints() + "/" + PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().get(i).getMaxHealthPoints();
			data[i][2] = String.valueOf(PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().get(i).getMovementOrientation());
			data[i][3] = new JButton("Place");
			model.addRow(data[i]);
		}
		
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		data_tables.add(table);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setMinWidth(panel.getWidth()/2);
		table.getColumnModel().getColumn(1).setPreferredWidth(panel.getWidth()/4);
		table.getColumnModel().getColumn(2).setPreferredWidth(panel.getWidth()/4);
		table.getColumnModel().getColumn(2).setCellRenderer(renderer);
		
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(scrollPane);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		this.createUndoButtons(team, panel, tbc);
		
		dataBoxPanel.add(panel);
		dataBoxPanel.add(Box.createRigidArea(new Dimension(10,0)));
	}
	
	private void createUndoButtons(boolean team, JPanel panel, TableButtonController tbc)
	{
		JPanel bpanel = new JPanel();
		JButton undoButton1 = new JButton("Undo 1");
		JButton undoButton2 = new JButton("Undo 2");
		JButton undoButton3 = new JButton("Undo 3");
		panel.setMaximumSize(new Dimension(sidePanel.getWidth() - 10, sidePanel.getHeight()/4));
		if(team)
		{
			undoButton1.setActionCommand("Undo1 P1");
			undoButton2.setActionCommand("Undo2 P1");
			undoButton3.setActionCommand("Undo3 P1");
		}
		else
		{
			undoButton1.setActionCommand("Undo1 P2");
			undoButton2.setActionCommand("Undo2 P2");
			undoButton3.setActionCommand("Undo3 P2");
		}
		undoButton1.addActionListener(tbc);
		undoButton2.addActionListener(tbc);
		undoButton3.addActionListener(tbc);
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(undoButton1);
		buttons.add(undoButton2);
		buttons.add(undoButton3);
		this.undo_buttons.add(buttons);
		bpanel.add(undoButton1);
		bpanel.add(undoButton2);
		bpanel.add(undoButton3);
		panel.add(bpanel);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
	}
	
	/**
	 * Update the panels that contain data about health points of all the pieces.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void updateDataBoxes()
	{
		for(int i = 0; i < data_tables.size(); i++)
		{
			boolean team;
			if(i == 0)
				team = true;
			else
				team = false;
			
			for(int j = 0; j < PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().size(); j++)
			{
				String str = PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().get(j).getHealthPoints() + "/" + PlayerRegistry.getPlayerObj(team).getAllSelectedPieces().get(j).getMaxHealthPoints();
				data_tables.get(i).setValueAt(str, j, 1);
			}
		}
	}
	
	/**
	 * Highlight a row in a JTable
	 * 
	 * @param team TRUE if Player 1, FALSE if Player 2
	 * @param pieceName Full name of the Piece
	 * @param highlight TRUE if row should be highlighted, FALSE if row should be unhighlighted.
	 * @version 1.0
	 * @since 1.1
	 */
	protected void highlightRow(boolean team, String pieceName, boolean highlight)
	{
		JTable table;
		int row = 0;
		if(team)
			table = data_tables.get(0);
		else
			table = data_tables.get(1);
		
		for(int i = 0; i < table.getRowCount(); i++)
		{
			if(pieceName.equals(table.getValueAt(i, 0)))
			{
				row = i;
				break;
			}
		}
		if(highlight)
			table.addRowSelectionInterval(row, row);
		else
			table.removeRowSelectionInterval(row, row);	
	}
	
	protected void enableUndoButtonForPlayer(boolean player1UndoEnable, boolean player2UndoEnable)
	{
		for(int i = 0; i < this.undo_buttons.size(); i++)
		{
			for(int j = 0; j < this.undo_buttons.get(i).size(); j++)
			{
				if(i == 0)
					this.undo_buttons.get(i).get(j).setEnabled(player1UndoEnable);
				if(i == 1)
					this.undo_buttons.get(i).get(j).setEnabled(player2UndoEnable);
			}			
		}
	}
}