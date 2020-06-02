package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.GameSetupController;

class SaveButtonPanelHelper 
{
	private JPanel saveButtonPanel;
	private JButton save;
	private JButton saveAndExit;
	private final Color lightBrown = new Color(153,102,0);
	private GameSetupController gsc = new GameSetupController();
	
	public SaveButtonPanelHelper(JPanel sidePanel, JPanel saveButtonPanel)
	{
		this.saveButtonPanel = saveButtonPanel;
		this.save = new JButton("Save");
		this.saveAndExit = new JButton("Save and Exit");
		this.saveButtonPanel.setLayout(new FlowLayout());
		this.saveButtonPanel.setBackground(lightBrown);
		this.saveButtonPanel.setMinimumSize(new Dimension(sidePanel.getWidth() - 10, sidePanel.getHeight()/10));
		this.saveButtonPanel.setMaximumSize(new Dimension(sidePanel.getWidth() - 10, sidePanel.getHeight()/10));
	}
	
	protected void placeButtons()
	{
		this.save.setActionCommand("Save");
		this.saveAndExit.setActionCommand("Save and Exit");
		this.save.addActionListener(gsc);
		this.saveAndExit.addActionListener(gsc);
		
		this.saveButtonPanel.add(save);
		this.saveButtonPanel.add(saveAndExit);
	}
}
