package cbdt.view.parameters.actoraction;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CloseActorActionCompositeLabelWrapper {

	private static final String CLOSE_ICON_LARGE_18_LOCATION = "/resources/close-icon-large-18.png";
	private static final String CLOSE_ICON_MEDIUM_18_LOCATION = "/resources/close-icon-medium-18.png";
	
	public CloseActorActionCompositeLabelWrapper(final ActorActionComposite parent, int style) {
		Label closeLabel = new Label(parent, style);
		
		final Image closeActorActionCompositeIconLarge = parent.getImageFromLocation(CLOSE_ICON_LARGE_18_LOCATION);
		final Image closeActorActionCompositeIconMedium = parent.getImageFromLocation(CLOSE_ICON_MEDIUM_18_LOCATION);
		
		closeLabel.setImage(closeActorActionCompositeIconMedium);
		closeLabel.addMouseTrackListener(new HoverLabelMouseTrackListener(closeLabel,
				closeActorActionCompositeIconLarge,
				closeActorActionCompositeIconMedium));
		
		closeLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				Composite actorActionCompositesFrame = parent.getParent();
				parent.dispose();
				actorActionCompositesFrame.getParent().getParent().pack();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
	}
	
}
