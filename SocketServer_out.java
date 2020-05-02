import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketServer_out extends Thread{
	protected Socket socket;
	private ObjectOutputStream outObj = null;
	
	public SocketServer_out(Socket socket) 
	{
		this.socket = socket;
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress() + " at Output Stream " + socket.getLocalPort() + " from " + socket.getPort());
		start();
		
		try 
		{
			outObj = new ObjectOutputStream(socket.getOutputStream());
			outObj.flush();
		} catch (IOException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	public void run() 
	{
		while(true)
		{
			
		}
	}
	
	public synchronized void sendPacket(Packet packet)
	{
		try
		{
			if(outObj != null)
			{
				//System.out.println("Sending packet " + packet.getConnectedPlayers());
				outObj.writeObject(packet);
				outObj.flush();
				//System.out.println("Packet sent");
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
