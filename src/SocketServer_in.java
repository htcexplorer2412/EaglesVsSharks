import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketServer_in extends Thread {
	protected Socket socket;
	private Packet packet;
	private ObjectInputStream inObj = null;
	
	public SocketServer_in(Socket socket) 
	{
		this.socket = socket;
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress() + " at Input Stream " + socket.getLocalPort() + " from " + socket.getPort());
		start();
	}
	
	public void run() {
		try {
			inObj = new ObjectInputStream(socket.getInputStream());
	        do
	        {
	        	try
	            {
	        		//System.out.println("Trying to receive a packet");
	        		this.packet = (Packet) inObj.readObject();
	        		System.out.println("Incoming packet: " + this.packet);
		            handlePacket(this.packet);
	            }
	            catch(ClassNotFoundException classnot)
	        	{
	                System.err.println("Data received in unknown format");
	            }
	        }
	        while(!this.packet.isExit());  //need to send and receive some kind of closing signal

		} catch (IOException ex) {
			//System.out.println(System.nanoTime());
			ex.printStackTrace();
			System.out.println("Unable to get streams from client - in stream");
		} finally {
			try {
				inObj.close();
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void handlePacket(Packet packet)
	{
		if(packet.getConnectedPlayers())
		{
			//new teamSelectionPanelDecorator(Game.getInstance());
			//System.out.println("Received a packet - Both players are connected");
			//Server.getInstance().sendPacket(new Packet(new Command(new teamSelectionPanelDecorator(Game.getInstance())), false, false, false, false, false, true));
			Game.getInstance().check = true;
			System.out.println(Game.getInstance().check);
			Server.getInstance().sendPacket(new Packet(new Command(Game.getInstance()), false, false, false, false, false, true));
		}
		else
		{
			System.out.println("Received a packet - Only one player connected");
		}
	}
	
}
