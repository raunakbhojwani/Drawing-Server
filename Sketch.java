import java.util.*;
import java.awt.*;

/**
 * Maintains a set of shared graphical shapes.
 * Dartmouth CS 10, Winter 2015
 * @author Raunak Bhojwani 
 */

public class Sketch {
	private ArrayList<Shape> shapes;

	public Sketch() {
		shapes = new ArrayList<Shape>();
	}
	
	public synchronized int size() {
		return shapes.size();
	}
	
	public synchronized Shape get(int idx) {
		return shapes.get(idx);
	}
	
	/**
	 * Returns the index of the topmost shape containing the point, 
	 * or -1 if it's not in any shape
	 */
	public synchronized int container(int x, int y) {
		// TODO: YOUR CODE HERE
		int index = -1;
		for(Shape shape:shapes) {
			if (shape != null) {
				if (shape.contains((int) x, (int) y)) {
					index =  shapes.indexOf(shape);
				}
			}
		}
		return index;
		
	}
	
	/**
	 * Draws all the shapes on the graphics, properly ordered from bottom to top
	 * If one is selected, gives it a border
	 */
	public synchronized void draw(Graphics g, int selected) {
		// TODO: YOUR CODE HERE
		for (Shape shape: shapes) {
			if (shape != null && !shapes.isEmpty()) {
					shape.draw(g);	
			}
			if (shape != null) {
				if (selected != -1 && shape.equals(shapes.get(selected))) {
						shape.border(g);
				}
			}
		}
	}
	
	/**
	 * Adds the shape to the sketch at the requested index
	 */
	public synchronized void doAddAt(int idx, Shape shape) {
		// Add holes up to the point where the shape is supposed to go
		while (shapes.size() <= idx) 
		    shapes.add(null);
		shapes.set(idx, shape);
	}
	
	/**
	 * Adds the shape to the sketch at the end of the list and returns its index
	 */
	public synchronized int doAddEnd(Shape shape) {
		shapes.add(shape);
		return shapes.size()-1;
	}
	
	/**
	 * Deletes a shape by making its position in the sketch list null
	 * @param idx
	 */
	public synchronized void doDelete(int idx) {
		if (idx != -1) 
		    shapes.set(idx, null);
	}
	
	/**
	 * Sets the shape of the given index to the given color
	 */
	public synchronized void doRecolor(int idx, Color c) {
		shapes.get(idx).color = c;
	}
	
	/**
	 * Moves the shape of the given index by the given step
	 */
	public synchronized void doMoveTo(int idx, int x1, int y1) {	
		shapes.get(idx).moveTo(x1, y1);
	}	
}
