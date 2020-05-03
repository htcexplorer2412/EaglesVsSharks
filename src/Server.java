import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

/*
 * Update code for client disconnection - use packet.isExit on client side for safe disconnection
 * 
 * After connection send player number
 */

public class Server {

	private static HashMap<Integer, SocketServer_out> outSocketMap = new HashMap<Integer, SocketServer_out>();
	private static HashMap<Integer, SocketServer_in> inSocketMap = new HashMap<Integer, SocketServer_in>();
	
	private static final int PORT_NUMBER_IN = 8081;
	private static final int PORT_NUMBER_OUT = 8082;
	private static Server single_instance = null;
	
	private Server() {}
	
	public static Server getInstance()
	{
		if(single_instance == null)
			single_instance = new Server();
		
		return single_instance;
	}
	
	protected void sendPacket(Packet packet)
	{
		for(int i = 1; i <= outSocketMap.size(); i++)
		{
			System.out.println("Sending packet to Player " + i);
			System.out.println("Sending packet to socket " + outSocketMap.get(i).socket);
			outSocketMap.get(i).sendPacket(packet);
		}
	}
	
	public static void main(String[] args) {
		int counter = 0;
		//String key;
		System.out.println("Server starting");
		
		Server.getInstance();
		ServerSocket server_in = null, server_out = null;
		try {
			server_in = new ServerSocket(PORT_NUMBER_IN);
			server_out = new ServerSocket(PORT_NUMBER_OUT);
			
			while (true) {
				/**
				 * create a new {@link SocketServer} object for each connection
				 * this will allow multiple client connections
				 */
				
		        counter++;
				inSocketMap.put(counter, new SocketServer_in(server_in.accept()));			//Add new connection to map
				outSocketMap.put(counter, new SocketServer_out(server_out.accept()));		//Add new connection to map
				//System.out.println("Counter: " + counter);
				
				if(counter < 2)
				{
					//System.out.println("Sending empty packet because only one player");
					Server.getInstance().sendPacket(new Packet());
				}
					
				if(counter == 2)
				{
					//System.out.println("Sending non-empty packet because two player");
					Server.getInstance().sendPacket(new Packet(null, false, false, false, false, false, true));
				}
					
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Unable to start server.");
		} finally {
			try {
				if (server_in != null)
					server_in.close();
				if (server_out != null)
					server_out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
