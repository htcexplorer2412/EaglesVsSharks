package View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.StringJoiner;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class MessageBoxPanelHelper 
{
	private JPanel sidePanel;
	private JPanel messageBoxPanel;
	private final Color lightBrown = new Color(153,102,0);
	private JTextArea area;
	
	/**
	 * Setup the message box panel
	 * 
	 * @param sidePanel The side panel
	 * @param messageBoxPanel Panel that will display message box
	 * @param sidePanelWidth Width of side panel
	 * @param sidePanelHeight Height of side panel
	 */
	public MessageBoxPanelHelper(JPanel sidePanel, JPanel messageBoxPanel)
	{
		this.sidePanel = sidePanel;
		this.messageBoxPanel = messageBoxPanel;
		BoxLayout bl = new BoxLayout(messageBoxPanel, BoxLayout.Y_AXIS);
		this.messageBoxPanel.setLayout(bl);
		this.messageBoxPanel.setBackground(lightBrown);
		this.messageBoxPanel.setMinimumSize(new Dimension(sidePanel.getWidth() - 10, sidePanel.getHeight()/4 + 10));
		this.messageBoxPanel.setMaximumSize(new Dimension(sidePanel.getWidth() - 10, sidePanel.getHeight()/4 + 10));
	}
	
	/**
	 * Creates a text area and adds it to the side panel.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	protected void createMessageBox()
	{
		area = new JTextArea("Welcome!");
		area.setMaximumSize(new Dimension(sidePanel.getWidth() - 10, sidePanel.getHeight()/4 + 10));
		area.setLineWrap(true);
		area.setEditable(false);
		
		messageBoxPanel.add(area);
		messageBoxPanel.repaint();
		messageBoxPanel.revalidate();
	}
	
	/**
	 * Shows a message in the text area.
	 * 
	 * @param s Message to be displayed
	 * @since 1.0
	 * @version 1.0
	 */
	protected void showMessage(String s)
	{
		if(area.getLineCount() == 12)
		{
			String[] string = area.getText().split("\n");
			for(int i = 0; i < string.length - 1; i++)
			{
				string[i] = string[i + 1];
			}
			string[string.length - 1] = s;
			StringJoiner str = new StringJoiner("\n");
			for(int i = 0; i < string.length; i++)
			{
				str.add(string[i]);
			}
			area.setText(str.toString());
		}
		else
		{
			area.append("\n" + s);
		}
		
	}
}
