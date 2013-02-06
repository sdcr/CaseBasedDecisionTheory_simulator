package cbdt.view.parameters.actoraction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.internal.Platform;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

public class ActorActionComposite extends Composite {


	private static final String CLOSE_ICON_MEDIUM_12_LOCATION = "/resources/close-icon-medium-12.png";
	private static final String CLOSE_ICON_SMALL_12_LOCATION = "/resources/close-icon-small-12.png";

	public ActorActionComposite(final Composite parent, int style) {
		super(parent, style | SWT.BORDER);

		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginTop = 0;
		setLayout(gridLayout);

		Label actionNameLabel = new Label(this, SWT.NONE);
		actionNameLabel.setText("Action name:");
		Text actionNameText = new Text(this, SWT.SINGLE);

		CloseActorActionCompositeLabelWrapper closeLabel = new CloseActorActionCompositeLabelWrapper(this, SWT.NONE);
		
		Label actionOutcomesLabel = new Label(this, SWT.NONE);
		actionOutcomesLabel.setText("Action outcomes:");
		
		ActorActionOutcomesTableViewer tableViewer = new ActorActionOutcomesTableViewer(this, SWT.NONE);

		// just for fun
		List<ActorActionOutcome> list = new ArrayList<ActorActionOutcome>();
		list.add(new ActorActionOutcome(0, 1));
		list.add(new ActorActionOutcome(0, 1));		
		tableViewer.setActorActionOutcomesInput(list);
		
		this.getParent().getParent().pack();
	}

	public Image getImageFromLocation(String location) {
		ImageData imageData = new ImageData(getClass().getResourceAsStream(location));
		return new Image(getDisplay(),imageData);
	}

}
