package cbdt.view.parameters.actoraction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cbdt.controller.Controller;
import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.AbstractControllerAccessComposite;
import cbdt.view.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

public class ActorActionComposite extends AbstractControllerAccessComposite {
	
	private static final String CLOSE_ICON_LARGE_18_LOCATION = "/resources/close-icon-large-18.png";
	private static final String CLOSE_ICON_MEDIUM_18_LOCATION = "/resources/close-icon-medium-18.png";
	private Text actionNameText;
	private ActorActionOutcomesTableViewer actorActionOutcomesTableViewer;
	private ActorAction representedActorAction;

	public ActorActionComposite(final Composite parent, int style, ActorAction representedActorAction, Controller controller) {
		super(parent, style | SWT.BORDER , controller);
		this.representedActorAction = representedActorAction;

		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginTop = 0;
		setLayout(gridLayout);

		Label actionNameLabel = new Label(this, SWT.NONE);
		actionNameLabel.setText("Action name:");
		actionNameText = new Text(this, SWT.SINGLE);

		HoverLabelWrapper closeLabel = new HoverLabelWrapper(this, SWT.NONE,
				CLOSE_ICON_LARGE_18_LOCATION, CLOSE_ICON_MEDIUM_18_LOCATION);
		closeLabel.getLabel().addMouseListener(new RemoveActorActionMouseListener(this));
		
		Label actionOutcomesLabel = new Label(this, SWT.NONE);
		actionOutcomesLabel.setText("Action outcomes:");
		
		actorActionOutcomesTableViewer = new ActorActionOutcomesTableViewer(this, SWT.NONE);

		// just for fun
		List<ActorActionOutcome> list = new ArrayList<ActorActionOutcome>();
		list.add(new ActorActionOutcome(0, 1));
		list.add(new ActorActionOutcome(0, 1));		
		actorActionOutcomesTableViewer.setActorActionOutcomesInput(list);
		
		this.getParent().getParent().pack();
	}

	public void updateFromActorAction(ActorAction actorAction){
		actionNameText.setText(actorAction.getActionName());
		actorActionOutcomesTableViewer.setActorActionOutcomesInput(actorAction
				.getActionOutcomes());
	}

	public ActorAction getRepresentedActorAction() {
		return representedActorAction;
	}
	
}
