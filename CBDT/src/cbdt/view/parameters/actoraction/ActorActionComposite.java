package cbdt.view.parameters.actoraction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

public class ActorActionComposite extends Composite {
	
	private static final String CLOSE_ICON_LARGE_18_LOCATION = "/resources/close-icon-large-18.png";
	private static final String CLOSE_ICON_MEDIUM_18_LOCATION = "/resources/close-icon-medium-18.png";

	public ActorActionComposite(final Composite parent, int style) {
		super(parent, style | SWT.BORDER);

		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginTop = 0;
		setLayout(gridLayout);

		Label actionNameLabel = new Label(this, SWT.NONE);
		actionNameLabel.setText("Action name:");
		Text actionNameText = new Text(this, SWT.SINGLE);

		HoverLabelWrapper closeLabel = new HoverLabelWrapper(this, SWT.NONE,
				CLOSE_ICON_LARGE_18_LOCATION, CLOSE_ICON_MEDIUM_18_LOCATION);
		closeLabel.getHoverLabel().addMouseListener(new CloseActorActionMouseListener(this));
		
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

	private class CloseActorActionMouseListener implements MouseListener {

		private Composite toDispose;

		public CloseActorActionMouseListener(Composite toDispose) {
			this.toDispose = toDispose;
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}

		@Override
		public void mouseDown(MouseEvent e) {
		}

		@Override
		public void mouseUp(MouseEvent e) {
			Composite actorActionCompositesFrame = toDispose.getParent();
			toDispose.dispose();
			actorActionCompositesFrame.getParent().getParent().pack();
		}
	}
	
}
