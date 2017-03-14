import java.net.*;
import java.util.*;
import java.io.*;

/**
 * A subclass of Sketch for the server, getting requests from the clients,
 * updating the overall state, and passing them on to the clients
 * Dartmouth CS 10, Winter 2015
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012; revised Winter 2014 to separate SketchServerCommunicator
 * @author Travis Peters, Dartmouth CS 10, Winter 2015 (updated to use MyIPAddressHelper)
 */
public class SketchServer {
	private ServerSocket listen;						// for accepting connections
	private ArrayList<SketchServerCommunicator> comms;	// all the connections with clients
	private Sketch sketch;								// the state of the world
	
	public SketchServer(ServerSocket listen) {
		this.listen = listen;
		sketch = new Sketch();
		comms = new ArrayList<SketchServerCommunicator>();
	}

	public Sketch getSketch() {
		return sketch;
	}
	
	/**
	 * The usual loop of accepting connections and firing off new threads to handle them
	 */
	public void getConnections() throws IOException {
		while (true) {
			SketchServerCommunicator comm = new SketchServerCommunicator(listen.accept(), this);
			comm.setDaemon(true);
			comm.start();
			addCommunicator(comm);
		}
	}

	/**
	 * Adds the communicator to the list of current communicators
	 */
	public synchronized void addCommunicator(SketchServerCommunicator comm) {
		comms.add(comm);
	}

	/**
	 * Removes the communicator from the list of current communicators
	 */
	public synchronized void removeCommunicator(SketchServerCommunicator comm) {
		comms.remove(comm);
	}

	/**
	 * Sends the message from the one communicator to all (including the originator)
	 */
	public synchronized void broadcast(String msg) {
		for (SketchServerCommunicator comm : comms) {
			comm.send(msg);
		}
	}
	
	public static void main(String[] args) throws Exception {
        // Display your IP address so that you can tell others and they can connect to your server.
        System.out.println("SketchServer's IP Address INSIDE of the current network : " + MyIPAddressHelper.getMyLocalIP());
        System.out.println("SketchServer's IP Address OUTSIDE of the current network: " + MyIPAddressHelper.getMyGlobalIP());

        System.out.println("SketchServer waiting for connections...");
		new SketchServer(new ServerSocket(4242)).getConnections();
	}
}
