package cbdt.view.parameterspage.parameters.actoraction;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.view.parameterspage.parameters.actoraction.listeners.HoverLabelMouseTrackListener;

public class HoverLabelWrapper {
	
	private Label hoverLabel;
	private Composite parent;

	public HoverLabelWrapper(Composite parent, int style, String hoverImageLocation, String noHoverImageLocation) {
		this.parent = parent;
		hoverLabel = new Label(parent, style);
		
		Image hoverImage = this.getImageFromLocation(hoverImageLocation);
		Image noHoverImage = this.getImageFromLocation(noHoverImageLocation);
		hoverLabel.setImage(noHoverImage);
		hoverLabel.addMouseTrackListener(new HoverLabelMouseTrackListener(hoverLabel,
				hoverImage,
				noHoverImage));
	}
	
	public Label getLabel() {
		return hoverLabel;
	}
	
	private Image getImageFromLocation(String location) {
		ImageData imageData = new ImageData(getClass().getResourceAsStream(location));
		return new Image(parent.getDisplay(),imageData);
	}
}
