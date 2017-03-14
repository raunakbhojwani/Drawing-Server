import java.awt.Color;


/**
 * Representation of a message for updating a sketch
 * Dartmouth CS 10, Winter 2015
 * @authors Raunak Bhojwani and Dami Apoeso
 */
public class Message {
	// Instance variables
	// TODO: YOUR CODE HERE
	private String[] commands;
	
	/**
	 * Initializes it from a string representation used for communication
	 * @param msg
	 */
	public Message(String msg) {
		// TODO: YOUR CODE HERE
		commands = msg.split(" ");
	}
	
	/**
	 * Updates the sketch according to the message
	 * This may result in a modification of the message to be passed on
	 */
	public void update(Sketch sketch) {
		// TODO: YOUR CODE HERE
			for (String command:commands) {
				if (commands[0].equals("add")){
					if (commands[1].equals("rectangle")) {
						System.out.println("HIT RECTANGLE");
						sketch.doAddEnd(new Rectangle(Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]), Integer.parseInt(commands[5]), new Color(Integer.parseInt(commands[6]))));	
					}
					else if (commands[1].equals("segment")) {
						System.out.println("HIT SEGMENT");
						sketch.doAddEnd(new Segment(Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]), Integer.parseInt(commands[5]), new Color(Integer.parseInt(commands[6]))));	
					}
					else if (commands[1].equals("ellipse")) {
						System.out.println("HIT ELLIPSE");
						sketch.doAddEnd(new Ellipse(Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]), Integer.parseInt(commands[5]), new Color(Integer.parseInt(commands[6]))));	
					}	
				}
				
				else if (commands[0].equals("delete")) {
					System.out.println("HIT DELETE");
					sketch.doDelete(Integer.parseInt(commands[1]));
				}
				else if (commands[0].equals("recolor")) {
					System.out.println("HIT RECOLOR");
					sketch.doRecolor(Integer.parseInt(commands[1]), new Color(Integer.parseInt(commands[2])));
				}
				else if (commands[0].equals("move")) {
					System.out.println("HIT MOVE");
					sketch.doMoveTo(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]));
				}
		}
	}

	/**
	 * Converts to a string representation for communication
	 */
	public String toString() {
		// TODO: YOUR CODE HERE
		String str = "";
		for (String command:commands) {
			str += command + " ";
		}
		return str;
	}
}

