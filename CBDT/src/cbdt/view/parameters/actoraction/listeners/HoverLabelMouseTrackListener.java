package cbdt.view.parameters.actoraction.listeners;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;

public class HoverLabelMouseTrackListener implements MouseTrackListener {

	private Label closeLabel;
	private Image hoverImage;
	private Image noHoverImage;

	public HoverLabelMouseTrackListener(Label closeLabel, Image hoverImage, Image noHoverImage) {
		this.closeLabel = closeLabel;
		this.hoverImage = hoverImage;
		this.noHoverImage = noHoverImage;
	}
	
	@Override
	public void mouseEnter(MouseEvent e) {
		closeLabel.setImage(hoverImage);
	}

	@Override
	public void mouseExit(MouseEvent e) {
		closeLabel.setImage(noHoverImage);
	}

	@Override
	public void mouseHover(MouseEvent e) {
	}
	
}
