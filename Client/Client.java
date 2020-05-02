//package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

		
public class Client extends Thread{

	private Socket echoSocket_in = null, echoSocket_out = null;
	private ObjectOutputStream outObj = null;
	private ObjectInputStream inObj = null;
	private Packet packet;
	private static Client single_instance = null;

	private Client(String host, int port_in, int port_out) {
		try 
		{
			//serverHostname = host;

			System.out.println("Connecting to host " + host + " on port " + port_out + " and port " + port_in);

			echoSocket_out = new Socket(host, port_out);
			echoSocket_in = new Socket(host, port_in);
			outObj = new ObjectOutputStream(echoSocket_out.getOutputStream());
			inObj = new ObjectInputStream(echoSocket_in.getInputStream());
			
			start();
			System.out.println("Connected with server");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Client init(String host, int port_in, int port_out) throws InitializationException
	{
		if(single_instance != null)
			throw new InitializationException("Already initialized");
		
		single_instance = new Client(host, port_in, port_out);
		return single_instance;
	}
	
	public static Client getInstance() throws InitializationException
    { 
        if (single_instance == null) 
            throw new InitializationException("Initialize the class first!"); 
  
        return single_instance; 
    } 
	
	private void handlePacket(Packet pack)
	{
		if(pack.getConnectedPlayers())
		{
			if(pack.getCommand() == null)
			{
				//System.out.println("Players connected");
				ClientView.getInstance().connectedScreen();
			}
			else if(pack.getCommand() != null)
			{
				if(pack.getCommand().getGameObj() != null)
				{
					ClientView.getInstance().showGame(pack.getCommand().getGameObj());
				}
			}
		}
		if(!pack.getConnectedPlayers())
		{
			//System.out.println("Waiting for opponent");
			ClientView.getInstance().waitingScreen();
		}
	}
	
	public void sendPacket(Packet packet)
	{
		try
		{
			if(outObj != null)
			{
				outObj.writeObject(packet);
				outObj.flush();
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		do
		{
			try
			{
				if(inObj != null)
				{
					//System.out.println("Listening for incoming packet");
					packet = (Packet) inObj.readObject();
	            	System.out.println("Received: " + packet + " at port " + echoSocket_in);
	            	handlePacket(packet);
				}
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(!this.packet.isExit());
	}
	
	public boolean close()
	{
		try
		{
			outObj.close();
			inObj.close();
			echoSocket_in.close();
			echoSocket_out.close();
			return true;
    		//System.exit(0);
		}
		catch(Exception ex)
		{
			return false;
			//System.exit(1);
		}
	}
}
