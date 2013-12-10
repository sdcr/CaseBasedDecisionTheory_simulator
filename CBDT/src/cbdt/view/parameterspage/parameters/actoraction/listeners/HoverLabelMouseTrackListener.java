package cbdt.view.parameterspage.parameters.actoraction.listeners;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;

/**
 * This class implements the {@link MouseTrackListener} and is used to set the
 * {@link Image} of a {@link Label} depending on whether the user is hovering
 * over the {@link Label} or not.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class HoverLabelMouseTrackListener implements MouseTrackListener {

	private Label label;
	private Image hoverImage;
	private Image noHoverImage;

	/**
	 * The constructor.
	 * 
	 * @param label
	 * @param hoverImage
	 * @param noHoverImage
	 */
	public HoverLabelMouseTrackListener(Label label, Image hoverImage,
			Image noHoverImage) {
		this.label = label;
		this.hoverImage = hoverImage;
		this.noHoverImage = noHoverImage;
	}

	@Override
	public void mouseEnter(MouseEvent e) {
		label.setImage(hoverImage);
	}

	@Override
	public void mouseExit(MouseEvent e) {
		label.setImage(noHoverImage);
	}

	@Override
	public void mouseHover(MouseEvent e) {
	}

}
