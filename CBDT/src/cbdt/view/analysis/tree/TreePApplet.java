package cbdt.view.analysis.tree;

import processing.core.PApplet;
import processing.core.PShape;

public class TreePApplet extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NodeCircle[] nodeCircles;

	private int numberOfNodeCircles;
	
	public void setup() {
		size(500, 500, P2D);
		background(255);

//		addMouseWheelListener(new MouseWheelListener() {
//			public void mouseWheelMoved(MouseWheelEvent mwe) {
//				mouseWheel(mwe.getWheelRotation());
//			}
//		});

		PShape circleShape = createShape(ELLIPSE, 0, 0, 100, 100);
		
		numberOfNodeCircles = 10;
		nodeCircles = new NodeCircle[numberOfNodeCircles];
		
		for (int i = 0; i < 10; i++){
			nodeCircles[i] = new NodeCircle(this, circleShape);
		}
	}

	protected void mouseWheel(int wheelRotation) {
		System.out.println(wheelRotation);
	}

	public void draw() {
		background(255);
		
		for (int i = 0; i < numberOfNodeCircles; i++){
//			NodeCircle cicle = new NodeCircle(this, 200, i*200, 100);
//			nodeCircles[i].update(mouseX,mouseY);
			nodeCircles[i].draw();
			translate(mouseX, mouseY);
		}
	}
}
