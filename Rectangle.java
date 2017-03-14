import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * A rectangle-shaped Shape
 * Dartmouth CS 10, Winter 2015
 * @authors Raunak Bhojwani and Dami Apoeso
 */
public class Rectangle extends Shape {
	// TODO: YOUR CODE HERE
	// constructor for Rectangle class
	public Rectangle(int x1, int y1, int x2, int y2, Color c) {
		super(x1,y1,x2,y2,c);
	}
	
	public boolean contains(int x, int y) {
		// Return whether point (x,y) is in the rectangle
		return Math.min(x1, x2) <= x && x <= Math.max(x1, x2) && Math.min(y1, y2) <= y && y <= Math.max(y1, y2);
	}

	public void setCorners(int x1, int y1, int x2, int y2) {
		// Infer upper left and lower right
		this.x1 = Math.min(x1, x2); this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2); this.y2 = Math.max(y1,  y2);
	}
	
	public void draw(Graphics g) {
		// Draw a rectangle
		g.setColor(color);
		g.fillRect(x1, y1, x2-x1, y2-y1);
	}

	public void border(Graphics g) {
		// Draw a green dotted border
		((Graphics2D)g).setStroke(dottedStroke);
		g.setColor(Color.green);
		g.drawRect(x1, y1, x2-x1, y2-y1);
	}
	public String toString() {
		// Consistent toString method
		return "rectangle "+super.toString();
	}
}
