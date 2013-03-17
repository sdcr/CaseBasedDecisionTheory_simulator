package cbdt.view.analysis.tree;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import processing.core.PApplet;

public class TreePApplet extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setup() {
		size(500, 500);
		background(255);

		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent mwe) {
				mouseWheel(mwe.getWheelRotation());
			}
		});

	}

	protected void mouseWheel(int wheelRotation) {
		// TODO Auto-generated method stub
		System.out.println(wheelRotation);
	}

	public void draw() {
		ellipseMode(CENTER);
		for (int i = 0; i < 10; i++)
			ellipse(300, i * 200, 100, 100);

		// stroke(255);
		// if (mousePressed) {
		// line(mouseX,mouseY,pmouseX,pmouseY);
		// }
	}
}
