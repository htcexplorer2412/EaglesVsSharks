package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Model.Memento.Memento;
import Model.Memento.SaveGameOriginator;
import View.SingletonGame;

public class GameSetupController implements ActionListener
{
	private JFrame frame;
	
	public GameSetupController(JFrame frame)
	{
		this.frame = frame;
	}
	
	public GameSetupController()
	{
		this.frame = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		if(e.getActionCommand().equals("New Game"))
		{
			frame.dispose();
			SingletonGame.getInstance().boardTypesAndPieceSelections();
		}
		if(e.getActionCommand().equals("Load Game"))
		{
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = fc.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION) 
			{
			    File selectedFile = fc.getSelectedFile();
			    if(getExtension(selectedFile.getName()).equals("sav"))
			    {
			    	try 
			    	{
			    		FileInputStream fin = new FileInputStream(selectedFile);
						ObjectInputStream objIn = new ObjectInputStream(fin);
						
						frame.dispose();
				    	SaveGameOriginator originator = new SaveGameOriginator();
						originator.restore((Memento) objIn.readObject());
						
						objIn.close();
						fin.close();
					} 
			    	catch (ClassNotFoundException e1) 
			    	{
						e1.printStackTrace();
					}
			    	catch(IOException e1)
			    	{
			    		e1.printStackTrace();
			    	}
			    }
			    else
			    	System.out.println("Not the correct file");
			}
		}
		if(e.getActionCommand().equals("Exit"))
		{
			frame.dispose();
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("Save"))
		{
			SaveGameOriginator originator = new SaveGameOriginator();
			Memento memento = originator.createMemento();
			
			try
			{
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
				FileOutputStream fout = new FileOutputStream(new File("saved files/saved_" + java.time.LocalDate.now() + "_" + formatter.format(calendar.getTime()) + ".sav"));
				ObjectOutputStream objOut = new ObjectOutputStream(fout);
				
				objOut.writeObject(memento);
				
				objOut.close();
				fout.close();
				SingletonGame.getInstance().showMessage("Game saved successfully!");
			}
			catch (FileNotFoundException ex) 
			{
				ex.printStackTrace();
				System.out.println("File not found");
			} 
			catch (IOException ex) 
			{
				System.out.println("Error initializing stream");
			}
		}
		
		if(e.getActionCommand().equals("Save and Exit"))
		{
			SaveGameOriginator originator = new SaveGameOriginator();
			Memento memento = originator.createMemento();
			
			try
			{
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
				FileOutputStream fout = new FileOutputStream(new File("saved files/saved_" + java.time.LocalDate.now() + "_" + formatter.format(calendar.getTime()) + ".sav"));
				ObjectOutputStream objOut = new ObjectOutputStream(fout);
				
				objOut.writeObject(memento);
				
				objOut.close();
				fout.close();
				
				SingletonGame.getInstance().closeGracefully();
			}
			catch (FileNotFoundException ex) 
			{
				System.out.println("File not found");
			} 
			catch (IOException ex) 
			{
				System.out.println("Error initializing stream");
			}
		}
	}

	public static String getExtension(String fileName) 
	{
	    char ch;
	    int len;
	    
	    if(fileName == null || (len = fileName.length()) == 0 || (ch = fileName.charAt(len-1)) == '/' || ch == '\\' || ch == '.' ) 
	        return "";
	    int dotInd = fileName.lastIndexOf('.');
	    int sepInd = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
	    if( dotInd <= sepInd )
	        return "";
	    else
	        return fileName.substring(dotInd+1).toLowerCase();
	}
}
